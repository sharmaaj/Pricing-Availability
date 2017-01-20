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
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class GetOrderTypeLOVDC {

    private static List<GetOrderTypeLOV> s_order;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public GetOrderTypeLOVDC() {
        super();
    }

    public GetOrderTypeLOV[] getOrderType() {
        System.out.println("getOrderType: Inside getorderTypeLov");
        ValueExpression ve = null;
        s_order = new ArrayList<GetOrderTypeLOV>();
        s_order.clear();
        GetOrderTypeLOV[] orderTypesArray = null;
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
            System.out.println("getOrderType: Inside try");
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("XORDERTYPELIST");
            JSONArray nodeArray = parentNode.getJSONArray("XORDERTYPELIST_ITEM");
            int size = nodeArray.length();
            System.out.println("getOrderType: size is :" + size);
            for (int i = 0; i < size; i++) {
                System.out.println("getOrderType: Inside Loop  :" + i + " th time");
                JSONObject temp = nodeArray.getJSONObject(i);
                GetOrderTypeLOV item = new GetOrderTypeLOV(temp);
                s_order.add(item);
                System.out.println("print values for ordertyupe DB @ index "+i+"is-->"+s_order.get(i).getOrderTypeName());
            }
      //     AdfmfJavaUtilities.setELValue("#{pageFlowScope.orderTypeLov}", "Standard"); 
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        orderTypesArray = (GetOrderTypeLOV[]) s_order.toArray(new GetOrderTypeLOV[s_order.size()]);
        if (s_order.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OrderTypeLOVServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OrderTypeLOVServiceResults}", "No Search Results");
        }
        return orderTypesArray;
    }
    
    public void getCurrentOrderId(){
       ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orderTypeLov}", String.class);
        String currSelectedOrderType = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));
        System.out.println("Inside getCurrentOrderIdDC to fecth value for getCurrentOrderId:--"+currSelectedOrderType);
        String getIdforOrderType = null;
        for (int i = 0; i < s_order.size(); i++) {
            if(s_order.get(i).getOrderTypeName().equalsIgnoreCase(currSelectedOrderType)){
              getIdforOrderType =  s_order.get(i).getOrderTypeId();
              AdfmfJavaUtilities.setELValue("#{pageFlowScope.getIdforOrderType}", getIdforOrderType);
              break;
            }
        }
    }


    public void setOrder(List<GetOrderTypeLOV> s_order) {
        List<GetOrderTypeLOV> oldOrder = GetOrderTypeLOVDC.s_order;
        GetOrderTypeLOVDC.s_order = s_order;
        propertyChangeSupport.firePropertyChange("order", oldOrder, s_order);
    }

    public static List<GetOrderTypeLOV> getOrder() {
        return s_order;
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


    public void callGetOrderTypeMtd() {
        this.getOrderType();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
