package mobile.datacontrol;

import javax.el.ValueExpression;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;

import oracle.jbo.Row;

public class DeleteItemFromCart {
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public DeleteItemFromCart() {
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
    
    public void deleteItemFromCart() {

        String userName = null;
        String itemNum = null;

        ValueExpression ve = null;
        
        System.out.println("Here 1");

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("Here 2-->"+userName);
        
     /*   try {
             ve = AdfmfJavaUtilities.getValueExpression("#{bindings.itemsFromCart.IteratorBinding}", String.class);
            AmxIteratorBinding iter = (AmxIteratorBinding)ve.getValue(AdfmfJavaUtilities.getAdfELContext());
            
            BasicIterator bIter = iter.getIterator();
            Row row = (Row) iter.getCurrentRow();
            
            System.out.println("itemNum in Delete Method is -->"+itemNum);
            itemNum = row.getAttribute("ITEM_NUMBER").toString();
            }
        catch(Exception e){
            System.out.println("Exception in Delete Method is -->"+e);
        }  */

       /*The current row of the iterator and delete based on row selection */
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deleteItemNum}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        System.out.println("Here 3-->"+itemNum);


        String restURI = RestURIs.deleteItemFromCart();
        System.out.println("restURI is " + restURI);
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + 
                "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        (rcu.invokeUPDATE(restURI, payload)).toString();

    }
}
