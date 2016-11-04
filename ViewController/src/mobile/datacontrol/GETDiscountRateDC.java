package mobile.datacontrol;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.util.List;

import javax.el.ValueExpression;

import mobile.bean.AEntity;

import mobile.entity.ApplyDiscount;
import mobile.entity.GetPricingInformation;
import mobile.entity.PriceListEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

/* ********************************************************************************************
+==================================================================+
(c) Copyright Deloitte Consulting India Private Limited (DCIPL)
All Rights Reserved
$Header: GETDiscountRateDC Class
Ver    : 1.0
Author : Tushar Pant
+==================================================================+
* TYPE              : GETDiscountRateDC Data Control Class
* INPUT Parameters  : None
* OUTPUT Parametrs  : None
* PURPOSE           : This Data Control Class is used to call Apply Coupon REST Service
*                     to fetch discount rate corresponsing to the coupon code applied
* History
* Version        Date                  Author                  Description
* --------------------------------------------------------------------------------------------
* 1.0           23-Oct-2016            Tushar Pant              Final Version
*********************************************************************************************** */

public class GETDiscountRateDC {
    public GETDiscountRateDC() {
        super();
    }
    private static List<ApplyDiscount> s_ApplyDisc;

    public void applyCouponCode() {
        ValueExpression ve = null;
        s_ApplyDisc = new ArrayList<ApplyDiscount>();
        s_ApplyDisc.clear();
        ApplyDiscount[] applyDiscArray = null;
        String userName = null;
        String itemNumber = null;
        String couponCode = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        //        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        //        itemNumber = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.couponCode}", String.class);
        couponCode = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();


        String restURI = RestURIs.applyDiscount();
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNumber + "\"," +
            "\n" + "\"P_DISCOUNT\" : \"" + couponCode + "\"\n" + "}";


        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        Number discountRate = 0;
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            if(jsonObject.getString("P_DISCOUNT_RATE")!=null){
                AEntity aentity = new AEntity();
                String value = aentity.getValue(jsonObject.getString("P_DISCOUNT_RATE"));
                if(value=="" || value == null){
                    discountRate = 0;
                    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert",
                                                                                  new Object[] { "Invalid",
                                                                                                 "Invalid Coupon! Please enter valid coupon",
                                                                                                 "Ok" });
                }
                else{
                    discountRate = Integer.parseInt(jsonObject.getString("P_DISCOUNT_RATE"));
                }
         }
            else{
            discountRate = 0;
            }
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.couponDiscount}", discountRate);

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void fetchDiscountUsingCoupon() {
        System.out.println("Inside fetchDiscountUsingCoupon before applyCouponCode called");
        this.applyCouponCode();
        System.out.println("Inside fetchDiscountUsingCoupon after applyCouponCode called");
    }
}
