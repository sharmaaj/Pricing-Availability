package mobile.datacontrol;

import javax.el.ValueExpression;

import mobile.entity.GetItemFromCart;

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

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: DeleteItemFromCart Class
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : DeleteItemFromCart Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to call DeleteItemFromCart REST Service
*                     to Delete Items From Cart
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           20-Sep-2016            Tushar Pant              Final Version
*********************************************************************************************** */

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
        
       /*The current row of the iterator and delete based on row selection */
        if(AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deleteItemNum}", String.class) !=null){ 
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deleteItemNum}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        }
        else{
            itemNum = null;
        }
        
        
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
    
    public void deleteItemFromCart(String itemNum) {

        String userName = null;
      //  String itemNum = null;

        ValueExpression ve = null;
        
        System.out.println("deleteItemFromCart DC: 1 itemNum from Param-->"+itemNum);

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("deleteItemFromCart DC:  2-->"+userName);
        
       /*The current row of the iterator and delete based on row selection */
//        if(AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deleteItemNum}", String.class) !=null){ 
//        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.deleteItemNum}", String.class);
//        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
//        }
//        else{
//            itemNum = null;
//        }
        

        String restURI = RestURIs.deleteItemFromCart();
        System.out.println("restURI is " + restURI);
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + 
                "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        
        (rcu.invokeUPDATE(restURI, payload)).toString();
        
        GetALlCartItemsDC refresh =  new GetALlCartItemsDC();
        refresh.getAllCartItems();

    }
    
    
    public void emptyCart() {

        String userName = null;
        String itemNum = null;

        ValueExpression ve = null;
        
        System.out.println("Here 1");

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("Here 2-->"+userName);
        
        String restURI = RestURIs.deleteItemFromCart();
        System.out.println("restURI is " + restURI);
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + 
                "\n" + "\"P_ITEM_NUMBER\" : \"" + 'E' + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        (rcu.invokeUPDATE(restURI, payload)).toString();
        
        GetALlCartItemsDC refresh =  new GetALlCartItemsDC();
        refresh.getAllCartItems();

    }
}
