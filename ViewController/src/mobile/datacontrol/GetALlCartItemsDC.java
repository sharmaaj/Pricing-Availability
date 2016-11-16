package mobile.datacontrol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.el.ValueExpression;

import mobile.entity.GetItemFromCart;
import mobile.entity.QtyLOV;

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
* TYPE              : GetALlCartItemsDC Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to :-
*                     1: Call getAllitemsfromCart REST Service and fetch all the items from the cart
*                     2: Call order creation REST Service to create order in ERP system
*                     
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           26-Oct-2016            Tushar Pant              Final Version

*********************************************************************************************** */

public class GetALlCartItemsDC {
    private static List<GetItemFromCart> xxt;
    private static List<GetItemFromCart> s_getItemFromCart;
    private static List<QtyLOV> s_qtyLovList;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public GetALlCartItemsDC() {
        super();
    }

    public QtyLOV[] getStaticQty() {
        s_qtyLovList = new ArrayList<QtyLOV>();
        s_qtyLovList.clear();
        QtyLOV[] QtyLOVArray = null;
        for (int i = 0; i < 500; i++) {
            QtyLOV ql = new QtyLOV(i);
            s_qtyLovList.add(ql);
        }
        QtyLOVArray = (QtyLOV[]) s_qtyLovList.toArray(new QtyLOV[s_qtyLovList.size()]);

        if (s_qtyLovList.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.StatisQtyResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.StatisQtyResults}", "No Search Results");
        }
        return QtyLOVArray;
    }

    public GetItemFromCart[] getItemsFromCart() {
        System.out.println("getItemsFromCart DC 1-->");
        ValueExpression ve = null;
        s_getItemFromCart = new ArrayList<GetItemFromCart>();
        s_getItemFromCart.clear();
        GetItemFromCart[] getItemFromCartArray = null;

        String userName = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String restURI = RestURIs.getAllItemsFromCart();
        RestServiceManager rcu = new RestServiceManager();

        String payload = "{\n" + "\"P_USER_NAME\" : \"" + userName + "\"\n" + "}";

        System.out.println("getItemsFromCartDC: paylod is " + payload);

        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("getItemsFromCartDC: jsonArrayAsString-->" + jsonArrayAsString);

        try {
            Number totalAmount = 0;
            System.out.println("getItemsFromCartDC : Inside Try Block");
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_CART_DTLS");
            JSONArray nodeArray = parentNode.getJSONArray("P_CART_DTLS_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                System.out.println("getItemsFromCartDC : Before GetItemFromCart is called-->" + i + "time");
                GetItemFromCart getCartItms = new GetItemFromCart(temp);
                s_getItemFromCart.add(getCartItms);

                totalAmount = totalAmount.intValue() + getCartItms.getAmount().intValue();
            }

            System.out.println("getItemsFromCartDC :Total Amount is " + totalAmount);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.totalAmountInCart}", totalAmount);
        } catch (Exception e) {
            System.out.println("getItemsFromCartDC: Exception is -->" + e.getMessage());
            e.getMessage();
            e.printStackTrace();
        }

        getItemFromCartArray =
            (GetItemFromCart[]) s_getItemFromCart.toArray(new GetItemFromCart[s_getItemFromCart.size()]);
        if (s_getItemFromCart.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetItemFromCartResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetItemFromCartResults}", "No Search Results");
        }
        return getItemFromCartArray;

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

    public void getAllCartItems() {
        this.getItemsFromCart();
        this.getStaticQty();
    }

    public void createOrder() {
        
        System.out.println("createOrder: - Inside createOrder method call");
        ValueExpression ve = null;

        String userName = null;
        String orgId = null;
        String orderTypeId = null;
        String billToAccNum = null;
        String shipTpAccNum = null;
        String coupon = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("createOrder: - userName is :"+userName);

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.orgId}", String.class);
        orgId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        orgId ="204";
        
        System.out.println("createOrder: - orgId is :"+orgId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orderType}", String.class);
         orderTypeId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("createOrder: - orderTypeId is :"+orderTypeId);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.billToAccNum}", String.class);
        billToAccNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("createOrder: - billToAccNum is :"+billToAccNum);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.shipToAccNum}", String.class);
        shipTpAccNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("createOrder: - shipTpAccNum is :"+shipTpAccNum);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.couponCode}", String.class);
        coupon = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("createOrder: - coupon is :"+coupon);


        String restURI = RestURIs.createOrder();
        RestServiceManager rcu = new RestServiceManager();
        
        System.out.println("createOrder: - restURI is :"+restURI);

        String payload =
            "{\n" + "\"USER_ID\" : \"" + userName + "\",\"ORG_ID\": \"" + orgId + "\",\"ORDER_TYPE_ID\": \"" +
            orderTypeId + "\",\"BILL_TO\": \"" + billToAccNum +
            "\",\"SHIP_TO\": \"" + shipTpAccNum + "\",\"COUPON\": \"" + coupon + "\",\"ATTRIBUTE1\": \"" + "\"" +
            ",\"ATTRIBUTE2\": \"" + "\"" + ",\"ATTRIBUTE3\": \"" + "\"" + ",\"ATTRIBUTE4\": \"" + "\"" +
            ",\"ATTRIBUTE5\": \"" + "\"" + ",\"ATTRIBUTE6\": \"" + "\"" + ",\"ATTRIBUTE7\": \"" + "\"" +
            ",\"ATTRIBUTE8\": \"" + "\"" + ",\"ATTRIBUTE9\": \"" + "\"" + ",\"ATTRIBUTE10\": \"" + "\"" +
            ",\"ATTRIBUTE11\": \"" + "\"" + ",\"ATTRIBUTE12\": \"" + "\"" + ",\"ATTRIBUTE13\": \"" + "\"" +
            ",\"ATTRIBUTE14\": \"" + "\"" + ",\"ATTRIBUTE15\": \"" + "\"" + "\n";

        payload = payload + ", \"P_ITEM_LINES\": { \"P_ITEM_LINES_ITEM\": [  ";

        System.out.println("createOrder: Order Header paylod is " + payload);

       for (int i = 0; i < s_getItemFromCart.size(); i++) {
            s_getItemFromCart.get(i).getATTRIBUTE1();  //Inventory_Item_Id
            s_getItemFromCart.get(i).getITEM_QUANTITY();
            s_getItemFromCart.get(i).getPRICE_LIST();
           System.out.println("createOrder: Inventory Item Id-->"+s_getItemFromCart.get(i).getATTRIBUTE1());
            System.out.println("createOrder: Item Quantity-->"+s_getItemFromCart.get(i).getITEM_QUANTITY());
            System.out.println("createOrder: Price List-->"+s_getItemFromCart.get(i).getPRICE_LIST());

            payload =
                payload + "{\"INVENTORY_ITEM_ID\":\"" + s_getItemFromCart.get(i).getATTRIBUTE1() + "\",\"PRICE_LIST_ID\": \"" + s_getItemFromCart.get(i).getPRICE_LIST() +
                "\",\"ORDERED_QUANTITY\": \"" + s_getItemFromCart.get(i).getITEM_QUANTITY() + "\",\"ATTRIBUTE1\": \"" + "\"" + ",\"ATTRIBUTE2\": \"" + "\"" +
                ",\"ATTRIBUTE3\": \"" + "\"" + ",\"ATTRIBUTE4\": \"\"" +",\"ATTRIBUTE5\": \"" + "\"" +
                ",\"ATTRIBUTE6\": \"" + "\"" + ",\"ATTRIBUTE7\": \"" + "\"" + ",\"ATTRIBUTE8\": \"" + "\"" +
                ",\"ATTRIBUTE9\": \"" + "\"" + ",\"ATTRIBUTE10\": \"" + "\"" + ",\"ATTRIBUTE11\": \"" + "\"" +
                ",\"ATTRIBUTE12\": \"" + "\"" + ",\"ATTRIBUTE13\": \"" + "\"" + ",\"ATTRIBUTE14\": \"" + "\"" +
                ",\"ATTRIBUTE15\": \"" + "\"" + "},";
        }
        System.out.println("createOrder: Order Line paylod is " + payload);

        payload = payload.substring(0, payload.length() - 1);
        payload = payload + "]}\n" + "}";
        System.out.println("Request to order "+payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("createOrder: jsonArrayAsString for Get All Cart Items-->" + jsonArrayAsString);
        System.out.println("createOrder: Received  response");

    }


}
