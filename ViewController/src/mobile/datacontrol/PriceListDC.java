package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.ItemEntity;
import mobile.entity.PriceListEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: PriceListDC Class
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : PriceListDC Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to call PriceList REST Service
*                     to fetch price list list of values
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           20-Jul-2016            Tushar Pant             Final Version
*********************************************************************************************** */

public class PriceListDC {
    public PriceListDC() {
        super();
    }
    private static List<PriceListEntity> s_PriceList;
    public PriceListEntity[] getPriceList() {
        ValueExpression ve = null;
        s_PriceList = new ArrayList<PriceListEntity>();
        s_PriceList.clear();
        PriceListEntity[] priceListArray = null;
        String itemId = null;
        String orgId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        if(itemId != null && !"".equals(itemId)){
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgId}", String.class);
        orgId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        String restURI = RestURIs.getPriceListLov();
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n"  + "\"PORGID\" : \""+orgId+"\",\n" + "\"PITEMNUM\" : \"" + itemId + "\"\n" + "}";
        System.out.println("paylod is "+payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("XPRICELIST");
            JSONArray nodeArray = parentNode.getJSONArray("XPRICELIST_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                PriceListEntity priceList = new PriceListEntity(temp);
                s_PriceList.add(priceList);
                System.out.println(" Here 1 -->"+priceList.getLISTDESC());
                if(priceList.getLISTDESC().equalsIgnoreCase("Y")){
                    System.out.println(" Here");
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.priceList}", priceList.getListName());
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        }
        priceListArray = (PriceListEntity[]) s_PriceList.toArray(new PriceListEntity[s_PriceList.size()]);
        if (s_PriceList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "No Search Results");
        }
        
        return priceListArray;
    }
    public void fetchPriceList(){
        this.getPriceList();
    }
}
