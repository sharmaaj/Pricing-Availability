package mobile.datacontrol;

import java.util.Date;

import javax.el.ValueExpression;

import mobile.bean.AEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class AddToCart {
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public AddToCart() {
        super();
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

    public void addtoCart() {

        String userName = null;
        Number itemNum = null;
        String itemName = null;
        String itemDesc = null;
        Number itemQuantity = null;


        ValueExpression ve = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemNum}", Number.class);
        itemNum = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemNum}", String.class);
        itemDesc = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemName}", String.class);
        itemName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemQuantity}", Number.class);
        itemQuantity = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));

        String restURI = RestURIs.getPricingInformation();
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"," +
            "\n" + "\"P_ITEM_NAME\" : \"" + itemName + "\"," + "\n" + "\"P_ITEM_DESCRIPTION\" : \"" + itemDesc + "\"," +
            "\n" + "\"P_ITEM_QUANTITY\" : \"" + itemQuantity + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();

    }
}
