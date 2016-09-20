package pna.mobile.navigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        UpdateItemQuantityFromCart updQty =  new UpdateItemQuantityFromCart();
        updQty.updateQtyFrmCart();
        System.out.println("After updatingItem");
    }

    public void navigateToCartPage(ActionEvent actionEvent) {
        NavigateToCartPage();
    }
}
