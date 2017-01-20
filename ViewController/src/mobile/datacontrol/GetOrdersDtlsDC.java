package mobile.datacontrol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.el.ValueExpression;
import mobile.entity.OrderCreation;
import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: GetALlCartItemsDC Class
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : GetOrdersDtlsDC Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to :-
*                     1: Call getAllOrdersfromCart REST Service and fetch all the items from the cart
*                     2: Call get order details REST Service to fetch order from ERP system
*                     
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           04-Nov-2016            Tushar Pant              Final Version

*********************************************************************************************** */

public class GetOrdersDtlsDC {

    private static List<OrderCreation> s_getAllOrders;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public GetOrdersDtlsDC() {
        super();
    }

    public OrderCreation[] getOrderDtls() {
        System.out.println("getOrderDtls DC 1-->");
        ValueExpression ve = null;
        s_getAllOrders = new ArrayList<OrderCreation>();
        s_getAllOrders.clear();
        OrderCreation[] getOrderDtlsArray = null;

        String userName = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
       // userName= "HSPRAGUE";   //To be removed once the order creation logic is working

        String restURI = RestURIs.getOrdrDtls();
        RestServiceManager rcu = new RestServiceManager();

        String payload = "{\n" + "\"P_USER_NAME\" : \"" + userName + "\"\n" + "}";

        System.out.println("getOrderDtls: paylod is " + payload);

        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("getOrderDtls: jsonArrayAsString-->" + jsonArrayAsString);

        try {
            Number totalAmount = 0;
            System.out.println("getOrderDtls : Inside Try Block");
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_ORDER_DETAILS");
            JSONArray nodeArray = parentNode.getJSONArray("P_ORDER_DETAILS_ITEM");
          // JSONObject parentNode = (JSONObject) jsonObject.get("P_ORDER_DETAILS");
//            JSONArray nodeArray = jsonObject.getJSONArray("P_ORDER_DETAILS");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                System.out.println("getOrderDtls : Before OrderCreation is called-->" + i + "time");
                OrderCreation getCartItms = new OrderCreation(temp);
                s_getAllOrders.add(getCartItms);
            }

        } catch (Exception e) {
            System.out.println("getOrderDtls: Exception is -->" + e.getMessage());
            e.getMessage();
            e.printStackTrace();
        }

        getOrderDtlsArray = (OrderCreation[]) s_getAllOrders.toArray(new OrderCreation[s_getAllOrders.size()]);
        if (s_getAllOrders.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.getOrderDtls}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.getOrderDtls}", "No Search Results");
        }
        return getOrderDtlsArray;

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
