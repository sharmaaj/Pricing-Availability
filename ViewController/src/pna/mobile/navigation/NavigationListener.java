package pna.mobile.navigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.el.ValueExpression;

import mobile.datacontrol.CheckUserDC;

import mobile.datacontrol.SearchHistoryDC;

import mobile.datacontrol.UpdateItemQuantityFromCart;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.SelectionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import oracle.adfmf.framework.internal.AdfmfJavaUtilitiesInternal;

import oracle.jbo.Row;

public class NavigationListener {
    public NavigationListener() {
    }

    public String validateAndNavigateItem() {
        // Add event code here...
        String item =
            AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchKeyword}") == null ? "" :
            AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchKeyword}").toString();
        if (item.length() < 3) {

            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert",
                                                                      new Object[] { "Error",
                                                                                     "Please enter atleast 3 characters for Item.",
                                                                                     "Ok" });
            return "";
        } else
            return "itemLov";
    }

    public String validateAndNavigateUserType() {
        CheckUserDC cud = new CheckUserDC();
        cud.getUserTypeInfo();

        String userTypeInfo =
            AdfmfJavaUtilities.getELValue("#{applicationScope.UserType}") == null ? "" :
            AdfmfJavaUtilities.getELValue("#{applicationScope.UserType}").toString();

        if (userTypeInfo.equalsIgnoreCase("INTERNAL")){
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemQuantity}", "1");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestedDate}", dateFormat.format(date));
            return "internalUser";
            }
        else if (userTypeInfo.equalsIgnoreCase("EXTERNAL"))
            return "externalUser";
        else {
            return "noUserFound";

        }
    }
    
    public String NavigateToCartPage(){
        return "myCart";
    }

    
    public void updateItemQuantity(ValueChangeEvent valueChangeEvent) {
        System.out.println("In Here updatingItem");
        
      //  Number newQty = (Number) valueChangeEvent.getNewValue();
    /*    Object[] newVal = (Object[]) valueChangeEvent.getNewValue();
        Object[]  oldVal = (Object[]) valueChangeEvent.getOldValue();
        
        ArrayList<Integer> selectedCustomers = new ArrayList<Integer>();      
         for (int i = 0; i < newVal.length; i++) {
             selectedCustomers.add(new Integer((String)newVal[i]));
         }
        AmxAttributeBinding customerList = (AmxAttributeBinding) AdfmfJavaUtilities.getELValue(elExpression);

        AmxIteratorBinding amxListIterator =  customerList.getIteratorBinding();
        BasicIterator      basicIterator = amxListIterator.getIterator();
        for (Integer customerIndx : selectedCustomers) {
             //set new current row in list iterator
             basicIterator.setCurrentIndex(customerIndx.intValue());

             Customer customer = (Customer) basicIterator.getDataProvider();
             //for this sample, print selected customers into a text field on the page
             selectedCustomerNames.append("Customer on index  " + (customerIndx.intValue() + 1) + ": " + customer.getName()+"\n");
          }
          
          //update the input text field with the selection. The set-method of the 
          //selectedValue property uses the PropertyChane listener in MAF to trigger
          //the field to refresh
          this.setSelectedValues(selectedCustomerNames.toString()); */

          
        UpdateItemQuantityFromCart updQty =  new UpdateItemQuantityFromCart();
        updQty.updateQtyFrmCart();
        System.out.println("After updatingItem");
    }

    public void navigateToCartPage(ActionEvent actionEvent) {
        NavigateToCartPage();
    }
}
