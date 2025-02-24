package mobile.datacontrol;

import javax.el.ValueExpression;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;

public class UpdateItemQuantityFromCart {
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public UpdateItemQuantityFromCart() {
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

    public void updateQtyFrmCart() {

        String userName = null;
        String itemNum = null;
        Number itemQuantity = null;


        ValueExpression ve = null;
        
        System.out.println("Here 1");

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("Here 2-->"+userName);
        
        

        /*The current row of the iterator and update based on row selection */
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowItemNum}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        System.out.println("Here 3 currentRowItemNum-->"+itemNum);

        /*The current row of the iterator and delete based on row selection */
        String qtyInString;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.updatedItemQty}", Number.class);
        itemQuantity = ((Number)ve.getValue(AdfmfJavaUtilities.getELContext()));
        System.out.println("Here 6 itemQuantity -->"+itemQuantity);
        
        /*Number updatedItemQtyInInt = Integer.parseInt(itemQuantity);
        System.out.println("Here 7 updatedItemQtyInInt -->"+updatedItemQtyInInt);*/

        String restURI = RestURIs.addQtyItemToCart();
        System.out.println("restURI is " + restURI);
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"," +
            "\n" + "\"P_QUANTITY\" : \"" + itemQuantity + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        (rcu.invokeUPDATE(restURI, payload)).toString();

    }
}
