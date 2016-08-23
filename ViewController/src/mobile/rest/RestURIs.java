package mobile.rest;

import java.util.Date;

public class RestURIs {
    
    private static final String USER_TYPE_URI = "/v1/User/checkUserType";
    private static final String ITEM_DETAILS_URI = "/v1/Item/getItemLov";
    private static final String PRICE_LIST_URI = "/v1/PriceList/getPriceListLov";
    private static final String PRICING_INFO_URI = "/v1/Pricing/getPricingInformation";
    private static final String CREATE_ORDER_URI = "/v1/Order/createOrder";
    private static final String ADD_TO_CART_URI = "/v1/Cart/addToCart";
    private static final String ADD_ITEM_QTY_URI = "/v1/addQtyItemCart";
    private static final String APPLY_DISCOUNT_URI = "/v1/Discount/applyDiscount";
    private static final String DELETE_ITEM_URI = "/v1/CartItem/deleteItemFromCart";
    private static final String SEARCH_HISTORY_URI = "/v1/Search/getSearchHistory";
    
    public RestURIs() {
        super();
    }
    
    public static String addToCart() {
        return ADD_TO_CART_URI;
    }
    
    public static String addQtyItemToCart() {
        return ADD_ITEM_QTY_URI;
    }
    
    public static String applyDiscount() {
        return APPLY_DISCOUNT_URI;
    }

    public static String deleteItemFromCart() {
        return DELETE_ITEM_URI;
    }

    public static String createOrder() {
        return CREATE_ORDER_URI;
    }
    
    public static String getPricingInformation() {
        return PRICING_INFO_URI;
    }
    
    public static String getSearchHistory() {
        return SEARCH_HISTORY_URI;
    }
    
    public static String checkUserType() {
        return USER_TYPE_URI;
    }
    
    public static String getItemNumberLov() {
        return ITEM_DETAILS_URI;
    }
    
    public static String getPriceListLov() {
        return PRICE_LIST_URI;
    }
}
