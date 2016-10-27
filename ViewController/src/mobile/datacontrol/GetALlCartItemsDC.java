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
        System.out.println("Here 10-->");
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

        System.out.println("paylod is " + payload);

        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("jsonArrayAsString for Get All Cart Items-->" + jsonArrayAsString);

        try {
            Number totalAmount = 0;
            System.out.println("Inside Try Block");
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_CART_DTLS");
            JSONArray nodeArray = parentNode.getJSONArray("P_CART_DTLS_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                System.out.println("Before GetItemFromCart is called-->" + i + "time");
                GetItemFromCart getCartItms = new GetItemFromCart(temp);
                s_getItemFromCart.add(getCartItms);

                totalAmount = totalAmount.intValue() + getCartItms.getAmount().intValue();
            }

            System.out.println("Total Amount is " + totalAmount);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.totalAmountInCart}", totalAmount);
        } catch (Exception e) {
            System.out.println("Exception is -->" + e.getMessage());
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
        ValueExpression ve = null;

        String userName = null;
        Number orgId = 0;
        Number orderTypeId = 0;
        Number billToAccNum = 0;
        Number shipTpAccNum = 0;
        String coupon = null;
        Number inventoryItemId = 0;
        Number priceListId = 0;
        Number orderedQty = 0;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String restURI = RestURIs.createOrder();
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{\n" + "\"USER_ID\" : \"" + userName + "\",\"ORG_ID\": \"" + null + "\",\"ORDER_TYPE_ID\": \"" + null +
            "\",\"PRICE_LIST_ID\": \"" + null + "\",\"BILL_TO\": \"" + null + "\",\"SHIP_TO\": \"" + null +
            "\",\"COUPON\": \"" + null + "\",\"ATTRIBUTE1\": \"" + null + "\",\"ATTRIBUTE2\": \"" + null +
            "\",\"ATTRIBUTE3\": \"" + null + "\",\"ATTRIBUTE4\": \"" + null + "\",\"ATTRIBUTE5\": \"" + null +
            "\",\"ATTRIBUTE6\": \"" + null + "\",\"ATTRIBUTE7\": \"" + null + "\",\"ATTRIBUTE8\": \"" + null +
            "\",\"ATTRIBUTE9\": \"" + null + "\",\"ATTRIBUTE10\": \"" + null + "\",\"ATTRIBUTE11\": \"" + null +
            "\",\"ATTRIBUTE12\": \"" + null + "\",\"ATTRIBUTE13\": \"" + null + "\",\"ATTRIBUTE14\": \"" + null +
            "\",\"ATTRIBUTE15\": \"" + null + "\n" + "}";


        System.out.println("paylod is " + payload);

        Iterator i = s_getItemFromCart.iterator();

        while (i.hasNext()) {
            //      serial = (SerialBO) i.next();
            payload =
                payload + "{\"INVENTORY_ITEM_ID\":\"" + null + "\",\"PRICE_LIST_ID\": \"" + null +
                "\",\"ORDERED_QUANTITY\": \"" + null + "\",\"ATTRIBUTE1\": \"" + null + "\",\"ATTRIBUTE2\": \"" + null +
                "\",\"ATTRIBUTE3\": \"" + null + "\",\"ATTRIBUTE4\": \"" + null + "\",\"ATTRIBUTE5\": \"" + null +
                "\",\"ATTRIBUTE6\": \"" + null + "\",\"ATTRIBUTE7\": \"" + null + "\",\"ATTRIBUTE8\": \"" + null +
                "\",\"ATTRIBUTE9\": \"" + null + "\",\"ATTRIBUTE10\": \"" + null + "\",\"ATTRIBUTE11\": \"" + null +
                "\",\"ATTRIBUTE12\": \"" + null + "\",\"ATTRIBUTE13\": \"" + null + "\",\"ATTRIBUTE14\": \"" + null +
                "\",\"ATTRIBUTE15\": \"" + null + "\"},";
        }
        
        System.out.println("paylod is " + payload);

        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("jsonArrayAsString for Get All Cart Items-->" + jsonArrayAsString);


    }

}
