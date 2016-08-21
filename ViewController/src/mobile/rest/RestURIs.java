package mobile.rest;

import java.util.Date;

public class RestURIs {
    public RestURIs() {
        super();
    }
    
    public static String addToCart() {
        return "/v1/addToCart";
    }
    
    public static String addQtyItemToCart() {
        return "/v1/addQtyItemCart";
    }
    
    public static String applyDiscount() {
        return "/v1/applyDiscount";
    }

    public static String deleteItemFromCart() {
        return "/v1/deleteItemFromCart";
    }

    public static String createOrder() {
        return "/v1/createOrder";
    }
    
    public static String getPricingInformation() {
        return "/v1/getPricingInformation";
    }
    
    public static String getSearchHistory() {
        return "/v1/getSearchHistory";
    }
    
    public static String checkUserType() {
        return "/v1/checkUserType";
    }
    
    private static final String ITEM_DETAILS_URI = "//v1/getItemNumber";
    
    public static String getItemNumberLov() {
        return ITEM_DETAILS_URI;
    }
    
    public static String getPriceListLov() {
        return "/v1/getPriceList";
    }
}
