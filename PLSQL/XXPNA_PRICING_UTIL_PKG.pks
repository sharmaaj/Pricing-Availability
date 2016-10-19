create or replace PACKAGE xxpna_pricing_util_pkg
AS
  PROCEDURE xxpna_add_item_cart(
      P_USER_NAME        IN VARCHAR2 ,
      p_item_number      IN VARCHAR2 ,
      P_ITEM_NAME        IN VARCHAR2 ,
      P_ITEM_DESCRIPTION IN VARCHAR2 ,
      P_ITEM_QUANTITY    IN NUMBER ,
      P_LIST_PRICE       IN NUMBER ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_apply_discount(
      P_USER_NAME   IN VARCHAR2 ,
      p_item_number IN VARCHAR2 ,
      p_discount    IN VARCHAR2 ,
      p_discount_rate OUT NUMBER ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_delete_item_cart(
      P_USER_NAME   IN VARCHAR2 ,
      p_item_number IN VARCHAR2 ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_add_quantity_item_cart(
      P_USER_NAME   IN VARCHAR2 ,
      p_item_number IN VARCHAR2 ,
      p_quantity    IN NUMBER ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE XXPNA_ITEM_PRICE_AVAI_INFO(
      p_user_id          IN VARCHAR2 ,
      p_item_number      IN VARCHAR2 ,
      p_item_description IN VARCHAR2 ,
      p_org_id           IN NUMBER ,
      p_quantity         IN NUMBER ,
      p_customer_number  IN VARCHAR2 ,
      p_price_list       IN VARCHAR2 ,
      p_requested_date   IN DATE ,
      p_item_detail OUT xxpna_item_det_type ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_order_creation(
      p_order_header IN xxpna_order_crtn_header_in ,
      p_item_lines   IN xxpna_order_crtn_lines_in_tab ,
      p_status OUT VARCHAR2 ,
      p_message OUT VARCHAR2 );
  PROCEDURE xxpna_search_his(
      p_org_id  IN NUMBER,
      p_user_id IN VARCHAR2 ,
      p_search_his OUT XXPNA_SEARCH_HIS_IN_TAB ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_check_user(
      piv_user_name IN VARCHAR2 ,
      pout_status OUT VARCHAR2 );
  PROCEDURE xxpna_get_my_order_details(
      p_user_name IN NUMBER ,
      p_order_details OUT xxpna_my_orders_tab ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
  PROCEDURE xxpna_get_all_items_from_cart(
      p_user_name IN VARCHAR2 ,
      p_cart_dtls OUT XXPNA_GET_ALL_CART_ITEMS_TAB ,
      status OUT VARCHAR2 ,
      MESSAGE OUT VARCHAR2 );
END XXPNA_PRICING_UTIL_PKG;