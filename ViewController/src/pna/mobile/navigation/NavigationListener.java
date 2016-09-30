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
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.updatedItemQty}", valueChangeEvent.getNewValue());
        try{
        UpdateItemQuantityFromCart updQty =  new UpdateItemQuantityFromCart();
        updQty.updateQtyFrmCart();
            
            ValueExpression ve = null;
            Number oldRowQtyValue = 0;
            Number changedRowAmount =0;
            Number currentPriceList = 0;
            
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowOldQty}", Number.class);
            oldRowQtyValue = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext())); 
            Number newValue = (Number) valueChangeEvent.getNewValue();
            
            System.out.println("1 currentRowOldQty-->"+oldRowQtyValue);
            System.out.println("2 currentRow New Qty-->"+newValue);
            
        /*    ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowAmount}", Number.class);
            changedRowAmount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext())); */
            
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowPriceList}", Number.class);
            currentPriceList = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext())); 
            
           // System.out.println("changedRowAmount-->"+changedRowAmount);
            System.out.println("3 currentPriceList-->"+currentPriceList);
            
            
            Number oldRowAmount = oldRowQtyValue.intValue() * currentPriceList.intValue();
            Number newRowAmount = newValue.intValue() * currentPriceList.intValue();
            
            
            System.out.println("4 Old Row Amount is-->"+oldRowAmount);
            System.out.println("5 New Row Amount is-->"+newRowAmount);
                
            Number differenceInValue = 0;
            Number oldTotalAmount = 0;
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.totalAmountInCart}", Number.class);
            oldTotalAmount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext())); 
            
            Number newTotalAmount = 0;
            
            if ( oldRowAmount.intValue() < newRowAmount.intValue() ){
                differenceInValue = newRowAmount.intValue() - oldRowAmount.intValue();
                newTotalAmount = oldTotalAmount.intValue() + differenceInValue.intValue();
            }
            else if (oldRowAmount.intValue() == newRowAmount.intValue()){
                differenceInValue = 0;
                newTotalAmount = oldTotalAmount;
            }
            else{
                differenceInValue = oldRowAmount.intValue()- newRowAmount.intValue();
                newTotalAmount = oldTotalAmount.intValue() - differenceInValue.intValue();
            }
            


            
            
            
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.totalAmountInCart}", newTotalAmount);
            
            System.out.println("6 difference in Amount is-->"+differenceInValue);
            System.out.println("7 old Total Amount is-->"+oldTotalAmount);
            System.out.println("8 New Total Amount is-->"+newTotalAmount);
            
        }
        catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        System.out.println("After updatingItem");
    }

    public void navigateToCartPage(ActionEvent actionEvent) {
        NavigateToCartPage();
    }
}
