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

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.org}", String.class);
        orgId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        String restURI = RestURIs.getItemNumberLov();
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n"  + "\"POU\" : \"Vision Operations\",\n" + "\"PITEMNUM\" : \"" + itemId + "\"\n" + "}";
        System.out.println("paylod is "+payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("XITEM");
            JSONArray nodeArray = parentNode.getJSONArray("XITEM_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                PriceListEntity priceList = new PriceListEntity(temp);
                s_PriceList.add(priceList);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        priceListArray = (PriceListEntity[]) s_PriceList.toArray(new PriceListEntity[s_PriceList.size()]);
        if (s_PriceList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "No Search Results");
        }
        return priceListArray;
    }
}
