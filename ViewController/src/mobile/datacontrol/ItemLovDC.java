package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import javax.el.ValueExpression;

import mobile.entity.ItemEntity;

import mobile.rest.RestURIs;
import mobile.rest.RestServiceManager;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: ItemLovDC Class
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : ItemLovDC Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to call ItemLov REST Service
*                     to fetch list of items based on user search
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           15-Jul-2016            Tushar Pant              Final Version
*********************************************************************************************** */

public class ItemLovDC {
    public ItemLovDC() {
        super();
    }
    private static List<ItemEntity> s_ItemList;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public ItemEntity[] getItemDetails() {
        ValueExpression ve = null;
        s_ItemList = new ArrayList<ItemEntity>();
        s_ItemList.clear();
        ItemEntity[] itemDetailsArray = null;
        String itemId = null;
        String orgName = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgName}", String.class);
        orgName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        orgName = "Vision Operations";
        String restURI = RestURIs.getItemNumberLov();
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n"  + "\"POU\" : \""+orgName +"\",\n" + "\"PITEMNUM\" : \"" + itemId + "\"\n" + "}";
        System.out.println("paylod is "+payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("XITEM");
            JSONArray nodeArray = parentNode.getJSONArray("XITEM_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                ItemEntity item = new ItemEntity(temp);
                s_ItemList.add(item);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        itemDetailsArray = (ItemEntity[]) s_ItemList.toArray(new ItemEntity[s_ItemList.size()]);
        if (s_ItemList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItemServiceResults}", "No Search Results");
        }
        return itemDetailsArray;
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void refreshItems() {
        providerChangeSupport.fireProviderRefresh("itemDetails");
    }

}
