package mobile.datacontrol;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.ApplyDiscount;
import mobile.entity.GetPricingInformation;
import mobile.entity.PriceListEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

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
            //JSONObject parentNode = (JSONObject) jsonObject.get("P_DISCOUNT_RATE");
            discountRate = Integer.parseInt(jsonObject.getString("P_DISCOUNT_RATE"));
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.couponDiscount}", discountRate);
            //            ApplyDiscount apDis = new ApplyDiscount(parentNode);
            //            s_ApplyDisc.add(apDis);
            //            if (apDis.getDiscount_rate() != null && apDis.getDiscount_rate() != new BigDecimal(0)) {
            //                System.out.println(" Printing Discount Rate Fetched from Web Service");
            //                AdfmfJavaUtilities.setELValue("#{pageFlowScope.couponDiscount}", apDis.getDiscount_rate());
            //            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        //
        //        applyDiscArray = (ApplyDiscount[]) s_ApplyDisc.toArray(new ApplyDiscount[s_ApplyDisc.size()]);
        //        if (s_ApplyDisc.size() != 0) {
        //            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ApplyDiscountServiceResults}", "");
        //        } else {
        //            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ApplyDiscountServiceResults}", "No Search Results");
        //        }

    }

    public void fetchDiscountUsingCoupon() {
        System.out.println("Inside fetchDiscountUsingCoupon before applyCouponCode called");
        this.applyCouponCode();
        System.out.println("Inside fetchDiscountUsingCoupon after applyCouponCode called");
    }
}
