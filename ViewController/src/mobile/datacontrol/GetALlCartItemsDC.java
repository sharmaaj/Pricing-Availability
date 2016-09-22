package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;


import javax.el.ValueExpression;

import mobile.entity.GetItemFromCart;
import mobile.entity.QtyLOV;
import mobile.entity.SearchHistory;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class GetALlCartItemsDC {
    
    private static List<GetItemFromCart> s_getItemFromCart;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public GetALlCartItemsDC() {
        super();
    }
    
    public GetItemFromCart[] getItemsFromCart() {
        
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
        System.out.println("jsonArrayAsString for Get All Cart Items-->"+jsonArrayAsString);
        
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_CART_DTLS");
            JSONArray nodeArray = parentNode.getJSONArray("P_CART_DTLS_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                GetItemFromCart getCartItms = new GetItemFromCart(temp);
                s_getItemFromCart.add(getCartItms);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        
        getItemFromCartArray = (GetItemFromCart[]) s_getItemFromCart.toArray(new GetItemFromCart[s_getItemFromCart.size()]);
        if (s_getItemFromCart.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetItemFromCartResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetItemFromCartResults}", "No Search Results");
        }
        return getItemFromCartArray;
        
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
