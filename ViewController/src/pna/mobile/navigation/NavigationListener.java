package pna.mobile.navigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import mobile.entity.GetItemFromCart;

import javax.el.ValueExpression;

import mobile.datacontrol.CheckUserDC;

import mobile.datacontrol.GETDiscountRateDC;
import mobile.datacontrol.GetOrderTypeLOVDC;
import mobile.datacontrol.SearchHistoryDC;

import mobile.datacontrol.UpdateItemQuantityFromCart;

import mobile.entity.GetItemFromCart;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.SelectionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.dbf.AmxAttributeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

import oracle.adfmf.framework.internal.AdfmfJavaUtilitiesInternal;

import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

import oracle.jbo.Row;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: NavigationListener Bean
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : NavigationListener Bean Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Bean Class is used to handle navigation logic driven by UI
*                     actions and process validations through action listeners
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           10-Aug-2016            Tushar Pant              Incremental Version
*********************************************************************************************** */

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

        if (userTypeInfo.equalsIgnoreCase("INTERNAL")) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemQuantity}", "1");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestedDate}", dateFormat.format(date));
            return "internalUser";
        } else if (userTypeInfo.equalsIgnoreCase("EXTERNAL"))
            return "externalUser";
        else {
            return "noUserFound";

        }
    }

    public String NavigateToCartPage() {
        return "myCart";
    }


    public void updateItemQuantity(ValueChangeEvent valueChangeEvent) {
        System.out.println("In Here updatingItem");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.updatedItemQty}", valueChangeEvent.getNewValue());
        try {
            UpdateItemQuantityFromCart updQty = new UpdateItemQuantityFromCart();
            updQty.updateQtyFrmCart();

            ValueExpression ve = null;
            String oldRowQtyValuetemp = null;
            Number changedRowAmount = 0;
            Number currentPriceList = 0;

            //            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowOldQty}", Number.class);
            //            oldRowQtyValuetemp = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext()));

            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowOldQty}", String.class);
            oldRowQtyValuetemp = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));
            Number oldRowQtyValue = Integer.parseInt(oldRowQtyValuetemp);

            Number newValue = (Number) valueChangeEvent.getNewValue();

            System.out.println("1 currentRowOldQty-->" + oldRowQtyValue);
            System.out.println("2 currentRow New Qty-->" + newValue);


            /*    ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowAmount}", Number.class);
            changedRowAmount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext())); */
            String currentPriceListtemp = null;
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.currentRowPriceList}", String.class);
            currentPriceListtemp = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));
            System.out.println("currentPriceListtemp-->" + currentPriceListtemp);
            currentPriceList = Integer.parseInt(currentPriceListtemp);

            // System.out.println("changedRowAmount-->"+changedRowAmount);
            System.out.println("3 currentPriceList-->" + currentPriceList);


            Number oldRowAmount = oldRowQtyValue.intValue() * currentPriceList.intValue();
            Number newRowAmount = newValue.intValue() * currentPriceList.intValue();


            System.out.println("4 Old Row Amount is-->" + oldRowAmount);
            System.out.println("5 New Row Amount is-->" + newRowAmount);

            Number differenceInValue = 0;
            Number oldTotalAmount = 0;
            ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.totalAmountInCart}", Number.class);
            oldTotalAmount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext()));

            Number newTotalAmount = 0;

            if (oldRowAmount.intValue() < newRowAmount.intValue()) {
                differenceInValue = newRowAmount.intValue() - oldRowAmount.intValue();
                newTotalAmount = oldTotalAmount.intValue() + differenceInValue.intValue();
            } else if (oldRowAmount.intValue() == newRowAmount.intValue()) {
                differenceInValue = 0;
                newTotalAmount = oldTotalAmount;
            } else {
                differenceInValue = oldRowAmount.intValue() - newRowAmount.intValue();
                newTotalAmount = oldTotalAmount.intValue() - differenceInValue.intValue();
            }


            AdfmfJavaUtilities.setELValue("#{pageFlowScope.totalAmountInCart}", newTotalAmount);

            System.out.println("6 difference in Amount is-->" + differenceInValue);
            System.out.println("7 old Total Amount is-->" + oldTotalAmount);
            System.out.println("8 New Total Amount is-->" + newTotalAmount);

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("Exception while updating quantity for item-->" + e.getMessage());
        }
        System.out.println("After updatingItem");
    }

    public void navigateToCartPage(ActionEvent actionEvent) {
        NavigateToCartPage();
    }

    public void applyDiscountAL(ActionEvent actionEvent) {
        System.out.println("Inside applyDiscountAL");
        GETDiscountRateDC dis = new GETDiscountRateDC();
        dis.fetchDiscountUsingCoupon();

        System.out.println("After GETDiscountRateDC Class's ; fetchDiscountUsingCoupon method call ");

        Number couponDiscountRate = 0;
        ValueExpression ve = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.couponDiscount}", Number.class);
        couponDiscountRate = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext()));

        System.out.println("couponDiscountRate from Service is :--" + couponDiscountRate);

        Number TotalAmount = 0;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.totalAmountInCart}", Number.class);
        TotalAmount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext()));

        System.out.println("Current TotalAmount is :--" + TotalAmount);

        Number TotalAmtAfterDiscount = 0;
        Number codeDiscount = 0;
        codeDiscount = (couponDiscountRate.doubleValue() / 100.00) * TotalAmount.doubleValue();
        System.out.println("New Discount After code is applied :--" + codeDiscount);

        TotalAmtAfterDiscount = TotalAmount.doubleValue() - codeDiscount.doubleValue();

        System.out.println("Current TotalAmount After Discount is :--" + TotalAmtAfterDiscount);

        AdfmfJavaUtilities.setELValue("#{pageFlowScope.totalAmountInCart}", TotalAmtAfterDiscount);

        Number oldDiscount = 0;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.TotalDiscount}", Number.class);
        oldDiscount = ((Number) ve.getValue(AdfmfJavaUtilities.getELContext()));

        if (oldDiscount == null)
            oldDiscount = 0.0;

        Number newDiscount = 0;
        if (TotalAmtAfterDiscount.doubleValue() < TotalAmount.doubleValue()) {
            System.out.println("First Condition ");
            if (codeDiscount.doubleValue() > oldDiscount.doubleValue()) {
                System.out.println("Inside IF Condition ");
                newDiscount = codeDiscount.doubleValue() - oldDiscount.doubleValue();
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.TotalDiscount}", newDiscount);
            } else {
                System.out.println("Inside Else Condition");
                newDiscount = oldDiscount.doubleValue() - codeDiscount.doubleValue();
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.TotalDiscount}", newDiscount);
            }

        } else {
            System.out.println("Inside Outer Else Condition ");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.TotalDiscount}", 0);
        }


    }

    public void checkoutButtonAL(ActionEvent actionEvent) {
    System.out.println("Inside Checkout Button Action Listener");
    
    GetOrderTypeLOVDC obj = new GetOrderTypeLOVDC();
    obj.callGetOrderTypeMtd();

    }
}
