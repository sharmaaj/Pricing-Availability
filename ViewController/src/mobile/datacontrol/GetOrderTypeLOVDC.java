package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.GetOrderTypeLOV;
import mobile.entity.ItemEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class GetOrderTypeLOVDC {

    private static List<GetOrderTypeLOV> s_ItemList;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public GetOrderTypeLOVDC() {
        super();
    }

    public GetOrderTypeLOV[] getorderTypeLov() {
        System.out.println("getOrderType: Inside getorderTypeLov");
        ValueExpression ve = null;
        s_ItemList = new ArrayList<GetOrderTypeLOV>();
        s_ItemList.clear();
        GetOrderTypeLOV[] itemDetailsArray = null;
        String orgName = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgName}", String.class);
        orgName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        orgName = "204";
        System.out.println("getOrderType: orgName is -" + orgName);

        String restURI = RestURIs.getOrderTypeLov();
        System.out.println("getOrderType: restURI is -" + restURI);
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n" + "\"PORGID\" : \"" + orgName + "\"\n" + "}";
        System.out.println("getOrderType: paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("getOrderType: jsonArrayAsString is -" + jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("XORDERTYPELIST");
            JSONArray nodeArray = parentNode.getJSONArray("XORDERTYPELIST_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                GetOrderTypeLOV item = new GetOrderTypeLOV(temp);
                s_ItemList.add(item);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        itemDetailsArray = (GetOrderTypeLOV[]) s_ItemList.toArray(new GetOrderTypeLOV[s_ItemList.size()]);
        if (s_ItemList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OrderTypeLOVServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OrderTypeLOVServiceResults}", "No Search Results");
        }
        return itemDetailsArray;
    }


    public void setItemList(List<GetOrderTypeLOV> s_ItemList) {
        List<GetOrderTypeLOV> oldItemList = GetOrderTypeLOVDC.s_ItemList;
        GetOrderTypeLOVDC.s_ItemList = s_ItemList;
        propertyChangeSupport.firePropertyChange("itemList", oldItemList, s_ItemList);
    }

    public static List<GetOrderTypeLOV> getItemList() {
        return s_ItemList;
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        ProviderChangeSupport oldProviderChangeSupport = this.providerChangeSupport;
        this.providerChangeSupport = providerChangeSupport;
        propertyChangeSupport.firePropertyChange("providerChangeSupport", oldProviderChangeSupport,
                                                 providerChangeSupport);
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
