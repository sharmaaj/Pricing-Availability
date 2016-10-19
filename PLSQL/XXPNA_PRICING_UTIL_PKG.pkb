create or replace PACKAGE BODY      xxpna_pricing_util_pkg
AS
   PROCEDURE xxpna_add_item_cart (
      p_user_name          IN       VARCHAR2
     ,p_item_number        IN       VARCHAR2
     ,p_item_name          IN       VARCHAR2
     ,p_item_description   IN       VARCHAR2
     ,p_item_quantity      IN       NUMBER
     ,p_list_price         IN       NUMBER
     ,status               OUT      VARCHAR2
     ,MESSAGE              OUT      VARCHAR2
   )
   AS
   BEGIN
      BEGIN
         INSERT INTO xxpna_item_cart_details
                     (item_number
                     ,item_name
                     ,item_description
                     ,item_quantity
                     ,price_list
                     ,user_name
                     )
              VALUES (p_item_number
                     ,p_item_name
                     ,p_item_description
                     ,p_item_quantity
                     ,p_list_price
                     ,p_user_name
                     );
      EXCEPTION
         WHEN OTHERS
         THEN
            status := 'FAILURE';
            MESSAGE := 'EXCEPTION IN ADDING ITEM TO CART';
      END;

      -- COMMIT;
      status := 'SUCCESS';
      MESSAGE := 'Item added to Cart';
   END xxpna_add_item_cart;
   
   PROCEDURE xxpna_get_all_items_from_cart(
    p_user_name IN VARCHAR2 ,
    p_cart_dtls OUT XXPNA_GET_ALL_CART_ITEMS_TAB ,
    status OUT VARCHAR2 ,
    MESSAGE OUT VARCHAR2 )
AS
  CURSOR cur_get_item_frm_cart(c_userName IN VARCHAR2)
  IS
    SELECT USER_NAME ,
      ITEM_NUMBER,
      ITEM_NAME,
      ITEM_DESCRIPTION,
      ITEM_QUANTITY,
      REQUEST_DATE,
      PRICE_LIST,
      CUSTOMER_NUMBER,
      ATTRIBUTE1 ,
      ATTRIBUTE2 ,
      ATTRIBUTE3 ,
      ATTRIBUTE4 ,
      ATTRIBUTE5 ,
      ATTRIBUTE6 ,
      ATTRIBUTE7 ,
      ATTRIBUTE8 ,
      ATTRIBUTE9 ,
      ATTRIBUTE10,
      ATTRIBUTE11,
      ATTRIBUTE12,
      ATTRIBUTE13,
      ATTRIBUTE14,
      ATTRIBUTE15
    FROM XXPNA_ITEM_CART_DETAILS
    WHERE USER_NAME = c_userName;
  ln_idx NUMBER    := 0;
  l_list_tab XXPNA_GET_ALL_CART_ITEMS_TAB ;
BEGIN
  l_list_tab := XXPNA_GET_ALL_CART_ITEMS_TAB() ;
  FOR rec IN cur_get_item_frm_cart (p_user_name)
  LOOP
    dbms_output.put_line( 'rec.USER_ID '||rec.USER_NAME);
    ln_idx := ln_idx + 1;
    l_list_tab.EXTEND;
    l_list_tab (ln_idx) := XXPNA_GET_ALL_CART_ITEMS ( USER_NAME => rec.USER_NAME, ITEM_NUMBER => rec.ITEM_NUMBER, ITEM_NAME => rec.ITEM_NAME, ITEM_DESCRIPTION => rec.ITEM_DESCRIPTION, CUSTOMER_NUMBER => rec.CUSTOMER_NUMBER, ITEM_QUANTITY => rec.ITEM_QUANTITY, PRICE_LIST => rec.PRICE_LIST, REQUEST_DATE => rec.REQUEST_DATE, ATTRIBUTE1 => rec.ATTRIBUTE1, ATTRIBUTE2 => rec.ATTRIBUTE2, ATTRIBUTE3 => rec.ATTRIBUTE3, ATTRIBUTE4 => rec.ATTRIBUTE4, ATTRIBUTE5 => rec.ATTRIBUTE5, ATTRIBUTE6 => rec.ATTRIBUTE6, ATTRIBUTE7 => rec.ATTRIBUTE7, ATTRIBUTE8 => rec.ATTRIBUTE8, ATTRIBUTE9 => rec.ATTRIBUTE9, ATTRIBUTE10 => rec.ATTRIBUTE10, ATTRIBUTE11 => rec.ATTRIBUTE11, ATTRIBUTE12 => rec.ATTRIBUTE12, ATTRIBUTE13 => rec.ATTRIBUTE13, ATTRIBUTE14 => rec.ATTRIBUTE14, ATTRIBUTE15 => rec.ATTRIBUTE15 );
  END LOOP;
  IF ln_idx      > 0 THEN
    p_cart_dtls := l_list_tab;
    status      := 'S';
    MESSAGE     := 'SUCCESS';
  ELSE
    status  := 'E';
    MESSAGE := 'No items in cart';
  END IF;
EXCEPTION
WHEN OTHERS THEN
  dbms_output.put_line( 'Error while extracting cart items' || SQLCODE || ' ' || SUBSTR(SQLERRM, 1,1000) || ' ' || DBMS_UTILITY.format_error_backtrace);
  status  := 'E';
  MESSAGE := 'Error while extracting cart items'|| SQLCODE || ' ' || SUBSTR(SQLERRM, 1,1000);
END xxpna_get_all_items_from_cart;

   PROCEDURE xxpna_apply_discount (
      p_user_name       IN       VARCHAR2
     ,p_item_number     IN       VARCHAR2
     ,p_discount        IN       VARCHAR2
     ,p_discount_rate   OUT      NUMBER
     ,status            OUT      VARCHAR2
     ,MESSAGE           OUT      VARCHAR2
   )
   AS
   BEGIN
    
    SELECT ffvv.description
    INTO   p_discount_rate
    FROM   fnd_flex_values_vl ffvv,
           fnd_flex_value_sets ffvs
    WHERE  ffvv.flex_value_set_id = FFVS.flex_value_set_id
    AND    ffvs.flex_value_set_name = 'Coupon Test'
    AND    ffvv.flex_value = p_discount;
    
    status := 'SUCCESS';
    MESSAGE := 'Discount Applied';
    
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
    status := 'FAIL';
      MESSAGE := 'Invalid coupon name; No data found';
  WHEN OTHERS THEN
    status := 'FAIL';
    MESSAGE := 'Error fetching coupon value - ' || SQLERRM;
      
   END xxpna_apply_discount;

-- How  will discount calculate and what to display on the screen
   PROCEDURE xxpna_delete_item_cart (
      p_user_name     IN       VARCHAR2
     ,p_item_number   IN       VARCHAR2
     ,status          OUT      VARCHAR2
     ,MESSAGE         OUT      VARCHAR2
   )
   AS
   BEGIN
      BEGIN
         DELETE FROM xxpna_item_cart_details
         WHERE       user_name = p_user_name
                 AND item_number = p_item_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            status := 'FAILURE';
            MESSAGE := 'Exception In deleting the ITEM' || SQLERRM;
      END;

      -- COMMIT;
      status := 'SUCCESS';
      MESSAGE := 'Item Deleted From Cart';
   END xxpna_delete_item_cart;

   PROCEDURE xxpna_add_quantity_item_cart (
      p_user_name     IN       VARCHAR2
     ,p_item_number   IN       VARCHAR2
     ,p_quantity      IN       NUMBER
     ,status          OUT      VARCHAR2
     ,MESSAGE         OUT      VARCHAR2
   )
   AS
   BEGIN
      BEGIN
         UPDATE xxpna_item_cart_details
         SET item_quantity = p_quantity
         WHERE  user_name = p_user_name
            AND item_number = p_item_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            status := 'FAILURE';
            MESSAGE := 'EXCEPTION in updating' || '-' || SQLERRM;
      END;

      -- COMMIT;
      status := 'SUCCESS';
      MESSAGE := p_quantity || 'Quantity Added to Cart';
   END xxpna_add_quantity_item_cart;

   PROCEDURE XXPNA_ITEM_PRICE_AVAI_INFO (
      p_user_id           IN       VARCHAR2
     ,p_item_number       IN       VARCHAR2
     ,p_item_description  IN       VARCHAR2 
     ,p_org_id            IN       NUMBER
     ,P_QUANTITY          in       number
     ,p_customer_number   IN       VARCHAR2
     ,p_price_list        IN       VARCHAR2
     ,p_requested_date    IN       DATE
     ,p_item_detail       OUT      xxpna_item_det_type
     ,status              OUT      VARCHAR2
     ,MESSAGE             OUT      VARCHAR2
   )
   AS
      --l_item_detail         xxpna_item_det_type;
      l_list_price          NUMBER;
      --l_description        VARCHAR2 (100);
      l_currency_code       CONSTANT    VARCHAR2 (30) := 'USD';
      l_item_number         VARCHAR2 (40);
      l_item_description    VARCHAR2 (240);
      l_uom_code            VARCHAR2 (25);
      l_discount            VARCHAR2 (240);
      l_dis_desc            VARCHAR2 (2000);
      l_atp_flag            VARCHAR2 (1);
      l_quantity            NUMBER;
      l_selling_price       NUMBER;
      l_discount_val_date   DATE;
      l_available_date      DATE;
      l_warehouse           VARCHAR2 (100);
      
      l_dis_description    VARCHAR2 (500);
      l_dis_start_date     DATE;
      l_dis_end_date       DATE;
      l_discount_value    NUMBER;
      l_dis_line_id       NUMBER;
      l_dis_header_id     NUMBER;
      l_customer_id       NUMBER;
      l_price_list_id     NUMBER;
      l_item_id           NUMBER;
      lv_message           VARCHAR2(1000);
      ln_idx              PLS_INTEGER;

      p_line_tbl 				QP_PREQ_GRP.LINE_TBL_TYPE;
      p_qual_tbl 				QP_PREQ_GRP.QUAL_TBL_TYPE;
      p_line_attr_tbl 			QP_PREQ_GRP.LINE_ATTR_TBL_TYPE;
      p_LINE_DETAIL_tbl 		QP_PREQ_GRP.LINE_DETAIL_TBL_TYPE;
      p_LINE_DETAIL_qual_tbl 		QP_PREQ_GRP.LINE_DETAIL_QUAL_TBL_TYPE;
      p_LINE_DETAIL_attr_tbl 		QP_PREQ_GRP.LINE_DETAIL_ATTR_TBL_TYPE;
      p_related_lines_tbl 		QP_PREQ_GRP.RELATED_LINES_TBL_TYPE;
      p_control_rec 			QP_PREQ_GRP.CONTROL_RECORD_TYPE;
      x_line_tbl 				QP_PREQ_GRP.LINE_TBL_TYPE;
      x_line_qual 			QP_PREQ_GRP.QUAL_TBL_TYPE;
      x_line_attr_tbl 			QP_PREQ_GRP.LINE_ATTR_TBL_TYPE;
      x_line_detail_tbl 		QP_PREQ_GRP.LINE_DETAIL_TBL_TYPE;
      x_line_detail_qual_tbl 		QP_PREQ_GRP.LINE_DETAIL_QUAL_TBL_TYPE;
      x_line_detail_attr_tbl 		QP_PREQ_GRP.LINE_DETAIL_ATTR_TBL_TYPE;
      x_related_lines_tbl 		QP_PREQ_GRP.RELATED_LINES_TBL_TYPE;
      x_return_status 		VARCHAR2(2000);
      x_return_status_text 	VARCHAR2(240);
      qual_rec 				QP_PREQ_GRP.QUAL_REC_TYPE;
      line_attr_rec 			QP_PREQ_GRP.LINE_ATTR_REC_TYPE;
      line_rec 				QP_PREQ_GRP.LINE_REC_TYPE;
      detail_rec 				QP_PREQ_GRP.LINE_DETAIL_REC_TYPE;
      ldet_rec 				QP_PREQ_GRP.LINE_DETAIL_REC_TYPE;
      rltd_rec 				QP_PREQ_GRP.RELATED_LINES_REC_TYPE;
      l_pricing_contexts_Tbl 		QP_Attr_Mapping_PUB.Contexts_Result_Tbl_Type;
      l_qualifier_contexts_Tbl 	QP_Attr_Mapping_PUB.Contexts_Result_Tbl_Type;
      v_line_tbl_cnt integer;
      
      -- Availability API
      l_available     NUMBER;
      l_atp_rec                       MRP_ATP_PUB.ATP_Rec_Typ;
      p_atp_rec                       MRP_ATP_PUB.ATP_Rec_Typ;
      x_atp_rec                       MRP_ATP_PUB.ATP_Rec_Typ;
      x_atp_supply_demand             MRP_ATP_PUB.ATP_Supply_Demand_Typ;
      x_atp_period                    MRP_ATP_PUB.ATP_Period_Typ;
      x_atp_details                   MRP_ATP_PUB.ATP_Details_Typ;
      x_msg_data                      VARCHAR2(500);
      x_msg_count                     NUMBER;
      l_session_id                    NUMBER;
      l_error_message                 VARCHAR2(250);
      x_error_message                 VARCHAR2(80);
      l_ascp_user_id NUMBER;
        l_ascp_application_id NUMBER;
        l_ascp_resp_id NUMBER;
      l_atp_org_id     NUMBER;
      l_sourcing_org_id NUMBER;
      l_non_atp_temp    NUMBER;
        
   BEGIN
      p_item_detail :=
         NEW xxpna_item_det_type (NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 ,NULL
                                 );
      
      status := 'SUCCESS';
      
      BEGIN
        lv_message := 'Fetching inventory_item_id for item_number - ' || p_item_number;
        
        SELECT inventory_item_id, primary_uom_code, atp_flag
        INTO l_item_id, l_uom_code, l_atp_flag
        FROM mtl_system_items_b
        WHERE organization_id = p_org_id
        AND   segment1 = p_item_number;
        
        lv_message := 'Fetching price_list_id for price_list - ' || p_price_list;
        
        SELECT list_header_id
        INTO l_price_list_id
        FROM qp_list_headers_all qlh
        WHERE 1=1
        AND qlh.name = p_price_list;
      
      EXCEPTION
      WHEN OTHERS THEN
        message := 'ERROR- ' || lv_message || ' - ' || SQLERRM;
        RAISE ;
      END;
      
      BEGIN
         -- Start API--
          ---- Control Record
          status := 'SUCCESS';
          p_control_rec.pricing_event 	:='BATCH';
          p_control_rec.calculate_flag 	:= 'Y'; --QP_PREQ_GRP.G_SEARCH_N_CALCULATE;
          p_control_rec.simulation_flag := 'N';

          p_control_rec.rounding_flag 			:= 'Q';
          p_control_Rec.manual_discount_flag 		:= 'Y';
          p_control_rec.request_type_code 		:= 'ONT';
          p_control_rec.TEMP_TABLE_INSERT_FLAG 	:= 'Y';
          -------------------------
          ---- Line Records ---------
          line_rec.request_type_code :='ONT';
          line_rec.line_id 				:= 12345; -- Order Line Id. This can be any thing for this script
          line_rec.line_Index 			:='1'; -- Request Line Index
          line_rec.line_type_code 		:= 'LINE'; -- LINE or ORDER(Summary Line)
          line_rec.pricing_effective_date 	:= sysdate; -- Pricing as of what date ?
          line_rec.active_date_first 		:= sysdate; -- Can be Ordered Date or Ship Date
          line_rec.active_date_second 		:= sysdate; -- Can be Ordered Date or Ship Date
          line_rec.active_date_first_type 	:= 'NO TYPE'; -- ORD/SHIP
          line_rec.active_date_second_type 	:='NO TYPE'; -- ORD/SHIP
          line_rec.line_quantity 			:= p_quantity; -- Ordered Quantity
          line_rec.line_uom_code 			:= l_uom_code; -- Ordered UOM Code
          line_rec.currency_code 			:= l_currency_code; -- Currency Code
          line_rec.price_flag 			:= 'Y'; -- Price Flag can have 'Y' , 'N'(No pricing) , 'P'(Phase)
          p_line_tbl(1) := line_rec;

          ---- Line Attribute Record

          line_attr_rec.LINE_INDEX 			:= 1;
          line_attr_rec.PRICING_CONTEXT 		:='ITEM'; --
          line_attr_rec.PRICING_ATTRIBUTE 		:='PRICING_ATTRIBUTE3';
          line_attr_rec.PRICING_ATTR_VALUE_FROM 	:='ALL';
          line_attr_rec.VALIDATED_FLAG 			:='N';
          p_line_attr_tbl(1)				:= line_attr_rec;


          line_attr_rec.LINE_INDEX 			:= 1;
          line_attr_rec.PRICING_CONTEXT 		:='ITEM'; --
          line_attr_rec.PRICING_ATTRIBUTE 		:='PRICING_ATTRIBUTE1';
          line_attr_rec.PRICING_ATTR_VALUE_FROM 	:= l_item_id;                 -- INVENTORY ITEM ID
          line_attr_rec.VALIDATED_FLAG 			:='N';
          p_line_attr_tbl(2)				:= line_attr_rec;

          ---- Qualifier Attribute Record

          qual_rec.LINE_INDEX 				:= 1; -- Attributes for the above line. Attributes are attached with the line index
          qual_rec.QUALIFIER_CONTEXT 			:='MODLIST';
          qual_rec.QUALIFIER_ATTRIBUTE 			:='QUALIFIER_ATTRIBUTE4';
          qual_rec.QUALIFIER_ATTR_VALUE_FROM 		:= l_price_list_id;                    -- PRICE LIST ID
          qual_rec.COMPARISON_OPERATOR_CODE 		:= '=';
          qual_rec.VALIDATED_FLAG 			:='Y';
          p_qual_tbl(1)					:= qual_rec;
          
          IF p_customer_number IS NOT NULL
          THEN
            BEGIN
            SELECT customer_id
            INTO l_customer_id
            FROM ra_customers
            WHERE customer_number = p_customer_number;
            
            qual_rec.LINE_INDEX 				:= 1;
            qual_rec.QUALIFIER_CONTEXT 			:='CUSTOMER';
            qual_rec.QUALIFIER_ATTRIBUTE 			:='QUALIFIER_ATTRIBUTE2';
            qual_rec.QUALIFIER_ATTR_VALUE_FROM 		:= l_customer_id ;				            -- CUSTOMER ID;
            qual_rec.COMPARISON_OPERATOR_CODE 		:= '=';
            qual_rec.VALIDATED_FLAG 			:='Y';
            p_qual_tbl(2)					:= qual_rec;
            
            EXCEPTION
            WHEN OTHERS THEN
            message := 'Error in fetching customer_id for customer_number ' || p_customer_number || ' - ' || SQLERRM;
            END;
          END IF;
          
          qp_preq_pub.price_request
                          (p_line_tbl,
                          p_qual_tbl,
                          p_line_attr_tbl,
                          p_line_detail_tbl,
                          p_line_detail_qual_tbl,
                          p_line_detail_attr_tbl,
                          p_related_lines_tbl,
                          p_control_rec,
                          x_line_tbl,
                          x_line_qual,
                          x_line_attr_tbl,
                          x_line_detail_tbl,
                          x_line_detail_qual_tbl,
                          x_line_detail_attr_tbl,
                          x_related_lines_tbl,
                          x_return_status,
                          x_return_status_text);
          
          DBMS_OUTPUT.PUT_LINE('Return Status text '||  x_return_status_text);
          DBMS_OUTPUT.PUT_LINE('Return Status  '||  x_return_status);
        EXCEPTION
        WHEN OTHERS THEN
          lv_message:= 'Error in calling pricing API- '|| SQLERRM;
          message := message || CHR(10) || lv_message;
          status := 'FAIL';
        END;
        
        IF status <> 'FAIL'
        THEN
          ln_idx := x_line_tbl.FIRST;
          IF ln_idx IS NOT NULL
          THEN
            LOOP
            l_selling_price := x_line_tbl(ln_idx).adjusted_unit_price;
            l_list_price := x_line_tbl(ln_idx).unit_price;
            EXIT WHEN ln_idx = x_line_tbl.LAST;
            LN_IDX := X_LINE_TBL.NEXT(LN_IDX);
            END LOOP;
          END IF;
            
          ln_idx := x_line_detail_tbl.FIRST;
          IF ln_idx IS NOT NULL
          THEN
            LOOP
            IF x_line_detail_tbl(ln_idx).list_line_type_code = 'DIS'
              AND x_line_detail_tbl(ln_idx).STATUS_TEXT IN ('INSERTED IN L_LIST_CUR','PRODUCT_QUALIFIER_ONLY')
              AND x_line_detail_tbl(ln_idx).automatic_flag = 'Y'
            THEN
              DBMS_OUTPUT.put_line ('Discount line selected');
              l_discount_value := x_line_detail_tbl(ln_idx).operand_value;
              l_dis_line_id := x_line_detail_tbl(ln_idx).list_line_id;
              l_dis_header_id := x_line_detail_tbl(ln_idx).list_header_id;
              DBMS_OUTPUT.put_line ('Discount header id - ' || l_dis_header_id);
            END IF;
            EXIT WHEN ln_idx = x_line_detail_tbl.LAST;
            ln_idx := x_line_detail_tbl.NEXT(ln_idx);
            END LOOP;
          END IF;
            
          IF l_dis_header_id IS NOT NULL
          THEN        
            BEGIN
            SELECT description, start_date_active, end_date_active
            INTO l_dis_description, l_dis_start_date, l_dis_end_date
            FROM QP_LIST_HEADERS_ALL
            WHERE list_header_id = l_dis_header_id;
            
            EXCEPTION
            WHEN OTHERS THEN
              lv_message := ' Error in fetching discount description -' || SQLERRM;
              message := message || lv_message;
            END;
          END IF;
        END IF;
         -- END API --
         
         DBMS_OUTPUT.put_line (p_item_detail.p_price_list);
         p_item_detail.p_price_list := l_list_price;
        p_item_detail.p_currency := l_currency_code;
        p_item_detail.p_customer_number := p_customer_number;
        p_item_detail.p_request_date := p_requested_date;        
        p_item_detail.p_item_number := p_item_number;
        p_item_detail.p_item_description := p_item_description;
        p_item_detail.p_uom := l_uom_code;
        p_item_detail.p_discount := l_discount_value;
        p_item_detail.p_dis_desc := l_dis_description;
        p_item_detail.p_atp_flag := l_atp_flag;
        p_item_detail.p_dis_valid_date := l_dis_end_date;        
        p_item_detail.p_selling_price := l_selling_price;
        p_item_detail.p_item_quantity := p_quantity;
        
        IF message IS NULL
        THEN
          status := 'SUCCESS';
          MESSAGE := 'Pricing API Completed successfully';
        END IF;

        -- AVailability API
        IF l_atp_flag = 'Y' THEN
        
        BEGIN        
        
          SELECT application_id
          INTO l_ascp_application_id
          FROM FND_APPLICATION_VL
          WHERE application_name = 'Advanced Supply Chain Planning'; 
          
          SELECT responsibility_id
          INTO l_ascp_resp_id
          FROM FND_RESPONSIBILITY_VL
          WHERE RESPONSIBILITY_NAME = 'Advanced Supply Chain Planner';
          
          SELECT user_id
          INTO l_ascp_user_id
          FROM FND_USER 
          WHERE user_name = 'MFG';

          
          -- fnd_global.apps_initialize(user_id        => l_ascp_user_id  ,   -- MFG User ID
                                     -- resp_id        => l_ascp_resp_id ,   -- Advanced Supply Chain Planner
                                     -- resp_appl_id   => l_ascp_application_id       -- Advanced Supply Chain Planning
                              -- ); 

          MSC_ATP_GLOBAL.Extend_Atp(l_atp_rec,x_return_status,1);
          
          l_atp_rec.Inventory_Item_Id(1)         := l_item_id;  -- from msc_system_items.sr_inventory_item_id  
        -- l_atp_rec.Inventory_Item_Name(1)       := 'SK-ATPFG'; 
          l_atp_rec.Quantity_Ordered(1)          := p_quantity;
          l_atp_rec.Quantity_UOM(1)              := 'Ea';
          l_atp_rec.Requested_Ship_Date(1)       := p_requested_date;
          l_atp_rec.Action(1)                    := 100;  
          l_atp_rec.Instance_Id(1)               := 61;   -- needed when using calling_module = 724, use msc_system_items.sr_instance_id  
          -- l_atp_rec.Source_Organization_Id(1)    := p_org_id;  
          l_atp_rec.OE_Flag(1)                   := 'N';
          l_atp_rec.Insert_Flag(1)               := 1;    -- Hardcoded value for profile MRP:Calculate Supply Demand 0= NO  
          l_atp_rec.Attribute_04(1)              := 1;    -- With this Attribute set to 1 this will enable the Period (Horizontal Plan),
                                                          -- Supply Demand, and Pegging data in the Pl/SQL records of ATP API.
                                                          --
                                                          -- Period (Horizontal Plan) and Supply/DEmand data is available in 
                                                          -- mrp_atp_details_temp based on session_id as follows:
                                                          -- HP data: Record_Type=1
                                                          -- SD data: Record_Type=2 
                                                          -- Peggng : Record_type=3
                                                          -- 
                                                          -- If this attribute_04 is set to 1, Please set
                                                          -- the Insert_Flag as well to 1.
                                                          --
                                                          -- If there is a performance hit Please set the
                                                          -- following to 0
                                                          -- l_atp_rec.Insert_Flag(1)               := 0
                                                          -- l_atp_rec.Attribute_04(1)              := 0
          l_atp_rec.Customer_Id(1)               := null;
          l_atp_rec.Customer_Site_Id(1)          := null;
          l_atp_rec.Calling_Module(1)            := null; -- use 724 when calling from MSC_ATP_CALL - otherwise NULL  
          l_atp_rec.Row_Id(1)                    := null;
          l_atp_rec.Source_Organization_Code(1)  := null;
          l_atp_rec.Organization_Id(1)           := null;
          l_atp_rec.order_number(1)              := null;
          l_atp_rec.line_number(1)               := null;
          l_atp_rec.override_flag(1)             := 'N';
          l_error_message                        := null;
          
          SELECT OE_ORDER_SCH_UTIL.Get_Session_Id
          INTO   l_session_id
          FROM   dual;
          
          APPS.MRP_ATP_PUB.Call_ATP (l_session_id,
                             l_atp_rec,
                             x_atp_rec ,
                             x_atp_supply_demand ,
                             x_atp_period,
                             x_atp_details,
                             x_return_status,
                             x_msg_data,
                             x_msg_count
                            );

          IF (x_return_status = 'S') 
          THEN
            l_available_date := x_atp_rec.Ship_Date(1);
            -- l_available := x_atp_rec.Requested_Date_Quantity(1);
            
            BEGIN
              SELECT supply_demand_quantity, organization_id
              INTO   l_available, l_atp_org_id
              FROM   mrp_atp_details_temp
              WHERE  supply_demand_source_type = 100
              AND    session_id = l_session_id;
            
            EXCEPTION
            WHEN TOO_MANY_ROWS THEN
              SELECT supply_demand_quantity, organization_id
              INTO   l_available, l_atp_org_id
              FROM   mrp_atp_details_temp
              WHERE  supply_demand_source_type = 100
              AND    session_id = l_session_id
              AND    rownum = 1;
            
            WHEN NO_DATA_FOUND THEN
              l_available := 0;
              l_atp_org_id := NULL;
            
            WHEN OTHERS THEN
              message := message || CHR(10) || ' Error in fetching availability values after ATP API -'|| SQLERRM;
              status := 'FAIL';
            END;
            
            IF l_atp_org_id IS NOT NULL
            THEN
              BEGIN
                SELECT organization_name
                INTO l_warehouse
                FROM ORG_ORGANIZATION_DEFINITIONS
                WHERE organization_id = l_atp_org_id;
              
              EXCEPTION
              WHEN OTHERS THEN
                message := message || CHR(10) || ' Error in getting warehouse name -'|| SQLERRM;
                status := 'FAIL';
              END;
            ELSE
              l_warehouse := NULL;
            END IF;
          END IF;
          
          IF (x_atp_rec.Error_Code(1) <> 0) 
          THEN
            SELECT meaning
            INTO   x_error_message
            FROM   mfg_lookups
            WHERE  lookup_type = 'MTL_DEMAND_INTERFACE_ERRORS'
            AND    lookup_code = x_atp_rec.Error_Code(1);
            
            message := message || CHR(10) || 'Availibility API return error - ' || x_error_message;
          END IF;
        
        
        EXCEPTION
        WHEN OTHERS THEN
        message:= message || ' Error in executing Availability API - ' || SQLERRM;
        status := 'FAIL';
        END;
        
        ELSE
        
          BEGIN
            l_available_date := SYSDATE;
            
            lv_message := 'Deriving warehouse';
            
            SELECT ood.organization_name, ood.organization_id
            INTO l_warehouse, l_sourcing_org_id
            FROM apps.mrp_sr_assignments_v msra,
                 mrp_assignment_sets mas,
                 mrp_sourcing_rules msr,
                 mrp_sr_source_org msso,
                 mrp_sr_receipt_org sro,
                 org_organization_definitions ood
            WHERE     msr.sourcing_rule_id = msra.sourcing_rule_id
            AND msra.assignment_set_id = mas.assignment_set_id
            AND mas.assignment_set_id = FND_PROFILE.value('MRP_DEFAULT_ASSIGNMENT_SET')
            AND msr.sourcing_rule_id = sro.sourcing_rule_id
            AND sro.sr_receipt_id = msso.sr_receipt_id
            AND msra.entity_name = p_item_number
            AND ood.organization_id = msso.source_organization_id;
            
            lv_message := 'Calculating onhand quantity';
            
            SELECT SUM(primary_transaction_quantity)
            INTO l_available
            FROM mtl_onhand_quantities_detail
            WHERE inventory_item_id = l_item_id
            AND organization_id = l_sourcing_org_id;
            
            
          
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            dbms_output.put_line('No data found for sourcing');
            BEGIN
              FOR rec IN ( SELECT organization_id, organization_name
                           FROM   org_organization_definitions
                           WHERE  operating_unit = p_org_id
                           AND    inventory_enabled_flag = 'Y')
              LOOP
                dbms_output.put_line('Checking onhand for org ' || rec.organization_name);
                SELECT SUM(primary_transaction_quantity)
                INTO l_non_atp_temp
                FROM mtl_onhand_quantities_detail
                WHERE inventory_item_id = l_item_id
                AND organization_id = rec.organization_id;
                dbms_output.put_line('Onhand - ' || l_non_atp_temp);
                
                IF l_non_atp_temp IS NOT NULL AND l_non_atp_temp > 0
                THEN
                  l_available := l_non_atp_temp;
                  l_warehouse := rec.organization_name;
                  EXIT;
                END IF;
                
              END LOOP;
            
            END;
          WHEN OTHERS THEN
          message := message || CHR(10) 
                      || lv_message 
                      || ' Error - ' 
                      || SQLERRM;
          
          status := 'FAIL';
          END;
        END IF;
        
        p_item_detail.p_available_date := l_available_date;
        p_item_detail.p_available  := l_available;
        p_item_detail.p_warehouse := l_warehouse;

        DBMS_OUTPUT.put_line ('Available quantity- ' || p_item_detail.p_available);
        
        MESSAGE := message || CHR(10) || 'Availability calculation completed';
        
        BEGIN
        
        INSERT INTO XXPNA_ITEM_SEARCH_HIS
        (
          ORG_ID,
          USER_ID,
          ITEM_NUMBER,        
          ITEM_DESCRIPTION,
          CUSTOMER_NUMBER,     
          ITEM_QUANTITY,      
          PRICE_LIST,
          REQUEST_DATE,
          CREATION_DATE,
          LAST_UPDATED_DATE
        )
        VALUES
        (
        p_org_id,
        p_user_id,
        p_ITEM_NUMBER,
        p_ITEM_DESCRIPTION,
        p_CUSTOMER_NUMBER,
        p_QUANTITY,
        p_PRICE_LIST,
        p_requested_date,
        SYSDATE,
        SYSDATE
        );
        
        COMMIT;
        
        EXCEPTION
        WHEN OTHERS THEN
        message := message || ' Error in inserting search hist data - ' || SQLERRM;
        END;
        
        
     
   EXCEPTION
   WHEN OTHERS   
   THEN
   STATUS := 'FAIL';
   END xxpna_item_price_avai_info;
    

   PROCEDURE xxpna_order_creation (
      p_order_header   IN       xxpna_order_crtn_header_in
     ,p_item_lines     IN       xxpna_order_crtn_lines_in_tab
     ,p_status         OUT      VARCHAR2
     ,p_message        OUT      VARCHAR2
   )
   AS
      v_api_version_number           NUMBER                                   := 1;
      v_return_status                VARCHAR2 (2000);
      v_msg_count                    NUMBER;
      v_msg_data                     VARCHAR2 (2000);
-- IN Variables --
      v_header_rec                   oe_order_pub.header_rec_type;
      v_line_tbl                     oe_order_pub.line_tbl_type;
      v_action_request_tbl           oe_order_pub.request_tbl_type;
      v_line_adj_tbl                 oe_order_pub.line_adj_tbl_type;
-- OUT Variables --
      v_header_rec_out               oe_order_pub.header_rec_type;
      v_header_val_rec_out           oe_order_pub.header_val_rec_type;
      v_header_adj_tbl_out           oe_order_pub.header_adj_tbl_type;
      v_header_adj_val_tbl_out       oe_order_pub.header_adj_val_tbl_type;
      v_header_price_att_tbl_out     oe_order_pub.header_price_att_tbl_type;
      v_header_adj_att_tbl_out       oe_order_pub.header_adj_att_tbl_type;
      v_header_adj_assoc_tbl_out     oe_order_pub.header_adj_assoc_tbl_type;
      v_header_scredit_tbl_out       oe_order_pub.header_scredit_tbl_type;
      v_header_scredit_val_tbl_out   oe_order_pub.header_scredit_val_tbl_type;
      v_line_tbl_out                 oe_order_pub.line_tbl_type;
      v_line_val_tbl_out             oe_order_pub.line_val_tbl_type;
      v_line_adj_tbl_out             oe_order_pub.line_adj_tbl_type;
      v_line_adj_val_tbl_out         oe_order_pub.line_adj_val_tbl_type;
      v_line_price_att_tbl_out       oe_order_pub.line_price_att_tbl_type;
      v_line_adj_att_tbl_out         oe_order_pub.line_adj_att_tbl_type;
      v_line_adj_assoc_tbl_out       oe_order_pub.line_adj_assoc_tbl_type;
      v_line_scredit_tbl_out         oe_order_pub.line_scredit_tbl_type;
      v_line_scredit_val_tbl_out     oe_order_pub.line_scredit_val_tbl_type;
      v_lot_serial_tbl_out           oe_order_pub.lot_serial_tbl_type;
      v_lot_serial_val_tbl_out       oe_order_pub.lot_serial_val_tbl_type;
      v_action_request_tbl_out       oe_order_pub.request_tbl_type;
/*Pricing Context */
      v_line_price_att_tbl           oe_order_pub.line_price_att_tbl_type;
/* Initialize the proper Context */
      l_org_id                       NUMBER                                   := 204;
      l_application_id               NUMBER                                   := 660;
      l_responsibility_id            NUMBER                                   := 21623;
/* Parameter Assigned variable*/
      l_user_id                      NUMBER;
      l_order_type_id                NUMBER;
      l_bill_to                      NUMBER;
      l_ship_to                      NUMBER;
      l_price_list_id                NUMBER;
      l_coupon                       VARCHAR2 (100);
      l_customer_id                  NUMBER;
      l_currency_code                VARCHAR2 (100);
      l_error_msg                    VARCHAR2 (1000)                          := NULL;
   BEGIN
      DBMS_OUTPUT.put_line ('Starting of script');
      DBMS_OUTPUT.put_line ('Parameter l_user_id ' || p_order_header.user_id);
      DBMS_OUTPUT.put_line ('Parameter org_id ' || p_order_header.org_id);
      DBMS_OUTPUT.put_line ('Parameter order_type_id ' || p_order_header.order_type_id);
      DBMS_OUTPUT.put_line ('Parameter bill_to ' || p_order_header.bill_to);
      DBMS_OUTPUT.put_line ('Parameter ship_to ' || p_order_header.ship_to);
--DBMS_OUTPUT.PUT_LINE('Parameter price_list_id '|| p_order_header.price_list_id);

      -- Setting the input paramters --
      l_user_id := p_order_header.user_id;
      l_org_id := p_order_header.org_id;
      l_order_type_id := p_order_header.order_type_id;
      l_bill_to := p_order_header.bill_to;
      l_ship_to := p_order_header.ship_to;
--l_price_list_id :=  p_order_header.price_list_id;
      l_coupon := p_order_header.coupon;
      mo_global.init ('ONT');
      fnd_global.apps_initialize (user_id =>           l_user_id   --0
                                 ,resp_id =>           l_responsibility_id
                                 ,resp_appl_id =>      l_application_id
                                 );
      mo_global.set_policy_context ('S', l_org_id);   --204

      /*Get currency Code*/
      BEGIN
         SELECT list_header_id
               ,currency_code
           INTO l_price_list_id
               ,l_currency_code
           FROM apps.qp_list_headers_all
          WHERE attribute1 = 'Y';
      EXCEPTION
         WHEN OTHERS
         THEN
            DBMS_OUTPUT.put_line (   'Error while fetching currency code and price_list_id '
                                  || SQLCODE
                                  || ' '
                                  || SQLERRM   --SUBSTR(SQLERRM, 1,500)
                                  || ' '
                                  || DBMS_UTILITY.format_error_backtrace);
            l_error_msg :=
                  l_error_msg
               || 'Error while fetching currency code and price_list_id '
               || SQLCODE
               || ' '
               || SUBSTR (SQLERRM
                         ,1
                         ,500
                         )
               || ' '
               || DBMS_UTILITY.format_error_backtrace;
            p_status := 'E';
            p_message := 'Error While fetching data for Order creation';
      END;

      BEGIN
         SELECT hca.cust_account_id
           INTO l_customer_id
           FROM hz_cust_site_uses_all hcsua
               ,hz_cust_acct_sites_all hcasa
               ,hz_cust_accounts hca
          WHERE hcsua.site_use_id = l_bill_to   --1017
            AND hcsua.site_use_code = 'BILL_TO'
            AND hcsua.cust_acct_site_id = hcasa.cust_acct_site_id
            AND hca.cust_account_id = hcasa.cust_account_id;
      EXCEPTION
         WHEN OTHERS
         THEN
            DBMS_OUTPUT.put_line (   'Error while fetching customer_id '
                                  || SQLCODE
                                  || ' '
                                  || SQLERRM   --SUBSTR(SQLERRM, 1,500)
                                  || ' '
                                  || DBMS_UTILITY.format_error_backtrace);
            l_error_msg :=
                  l_error_msg
               || 'Error while fetching customer_id '
               || SQLCODE
               || ' '
               || SUBSTR (SQLERRM
                         ,1
                         ,500
                         )
               || ' '
               || DBMS_UTILITY.format_error_backtrace;
            p_status := 'E';
            p_message := 'Error While fetching data for Order creation';
      END;

      DBMS_OUTPUT.put_line ('l_customer_id ' || l_customer_id);

      IF l_error_msg IS NULL
      THEN
-- Header Record --
         v_header_rec := oe_order_pub.g_miss_header_rec;
         v_header_rec.operation := oe_globals.g_opr_create;
--v_header_rec.order_type_id          :=  1437;--1005;
         v_header_rec.order_type_id := l_order_type_id;
         v_header_rec.sold_to_org_id := l_customer_id;   --1004;  --Customer_id
--v_header_rec.ship_to_org_id         := 1018;--5480;
         v_header_rec.ship_to_org_id := l_ship_to;   --5480;
--v_header_rec.invoice_to_org_id      := 1017;--5181;
         v_header_rec.invoice_to_org_id := l_bill_to;   --5181;Bill_to of the customer
--v_header_rec.order_source_id        := 26;  ---
         v_header_rec.booked_flag := 'N';
--v_header_rec.price_list_id          := 1000;--7018;
         v_header_rec.price_list_id := l_price_list_id;
         v_header_rec.pricing_date := SYSDATE;
         v_header_rec.flow_status_code := 'ENTERED';
--v_header_rec.cust_po_number         := '99478222532';

         --v_header_rec.sold_from_org_id       := 204;--83;

         --v_header_rec.salesrep_id            := 1007;---3;
         v_header_rec.transactional_curr_code := l_currency_code;
         v_action_request_tbl (1) := oe_order_pub.g_miss_request_rec;
--v_action_request_tbl(1).request_type := OE_GLOBALS.G_BOOK_ORDER;
         v_action_request_tbl (1).entity_code := oe_globals.g_entity_header;

         -- Line Record --
         FOR j IN 1 .. p_item_lines.COUNT
         LOOP
            DBMS_OUTPUT.put_line (   'p_item_lines inventory_item_id '
                                  || j
                                  || ' '
                                  || p_item_lines (j).inventory_item_id);
            DBMS_OUTPUT.put_line (   'p_item_lines ordered_quantity '
                                  || j
                                  || ' '
                                  || p_item_lines (j).ordered_quantity);
            v_line_tbl (j) := oe_order_pub.g_miss_line_rec;
            v_line_tbl (j).operation := oe_globals.g_opr_create;
            v_line_tbl (j).inventory_item_id := p_item_lines (j).inventory_item_id;   --62454;--27893;
            v_line_tbl (j).ordered_quantity := p_item_lines (j).ordered_quantity;   --2;
            -- v_line_tbl (j).price_list_id     := p_item_lines(j).price_list_id;

            /* Populate Line level Pricing Attributes like Coupons, Promotions, etc. */

            -- l_line_attr_cnt := l_line_attr_cnt + 1;
            v_line_price_att_tbl (j) := oe_order_pub.g_miss_line_price_att_rec;
            v_line_price_att_tbl (j).line_index := j;
            v_line_price_att_tbl (j).operation := oe_globals.g_opr_create;
            v_line_price_att_tbl (j).flex_title := 'QP_ATTR_DEFNS_PRICING';
            v_line_price_att_tbl (j).pricing_context := 'COUPON NEW';
            v_line_price_att_tbl (j).pricing_attribute51 := 'Coupon_T1';
         END LOOP;

         DBMS_OUTPUT.put_line ('Starting of API');
-- Calling the API to create an Order --
         oe_order_pub.process_order (p_api_version_number =>          v_api_version_number
                                    ,p_header_rec =>                  v_header_rec
                                    ,p_line_tbl =>                    v_line_tbl
                                    ,p_action_request_tbl =>          v_action_request_tbl
                                    ,p_line_adj_tbl =>                v_line_adj_tbl
                                    ,p_line_price_att_tbl =>          v_line_price_att_tbl
-- OUT variables
         ,                           x_header_rec =>                  v_header_rec_out
                                    ,x_header_val_rec =>              v_header_val_rec_out
                                    ,x_header_adj_tbl =>              v_header_adj_tbl_out
                                    ,x_header_adj_val_tbl =>          v_header_adj_val_tbl_out
                                    ,x_header_price_att_tbl =>        v_header_price_att_tbl_out
                                    ,x_header_adj_att_tbl =>          v_header_adj_att_tbl_out
                                    ,x_header_adj_assoc_tbl =>        v_header_adj_assoc_tbl_out
                                    ,x_header_scredit_tbl =>          v_header_scredit_tbl_out
                                    ,x_header_scredit_val_tbl =>      v_header_scredit_val_tbl_out
                                    ,x_line_tbl =>                    v_line_tbl_out
                                    ,x_line_val_tbl =>                v_line_val_tbl_out
                                    ,x_line_adj_tbl =>                v_line_adj_tbl_out
                                    ,x_line_adj_val_tbl =>            v_line_adj_val_tbl_out
                                    ,x_line_price_att_tbl =>          v_line_price_att_tbl_out
                                    ,x_line_adj_att_tbl =>            v_line_adj_att_tbl_out
                                    ,x_line_adj_assoc_tbl =>          v_line_adj_assoc_tbl_out
                                    ,x_line_scredit_tbl =>            v_line_scredit_tbl_out
                                    ,x_line_scredit_val_tbl =>        v_line_scredit_val_tbl_out
                                    ,x_lot_serial_tbl =>              v_lot_serial_tbl_out
                                    ,x_lot_serial_val_tbl =>          v_lot_serial_val_tbl_out
                                    ,x_action_request_tbl =>          v_action_request_tbl_out
                                    ,x_return_status =>               v_return_status
                                    ,x_msg_count =>                   v_msg_count
                                    ,x_msg_data =>                    v_msg_data
                                    );
         DBMS_OUTPUT.put_line ('Completion of API');

         IF v_return_status = fnd_api.g_ret_sts_success
         THEN
            COMMIT;
            DBMS_OUTPUT.put_line ('Order Creation Success : ' || v_header_rec_out.order_number);
            p_status := 'S';
            p_message := 'Order Creation Success : ' || v_header_rec_out.order_number;
         ELSE
            DBMS_OUTPUT.put_line ('Order Creation failed:' || v_msg_data);
            p_status := 'E';
            p_message := 'Order Creation failed';
            ROLLBACK;

            FOR i IN 1 .. v_msg_count
            LOOP
               v_msg_data := oe_msg_pub.get (p_msg_index =>      i, p_encoded => 'F');
               DBMS_OUTPUT.put_line (i || ') ' || v_msg_data);
            END LOOP;
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
      dbms_output.put_line( 'Error in Order creation process'
                   || SQLCODE
                   || ' '
                   || SUBSTR(SQLERRM, 1,1000)
                   || ' '
                   || DBMS_UTILITY.format_error_backtrace);
 p_status := 'E';
 p_message := 'Error in Order creation process ' ;
      --l_item_type 
         p_status := 'E';
         p_message := 'Error in Order creation process ';
   END xxpna_order_creation;

   PROCEDURE xxpna_search_his (
      p_org_id IN NUMBER
     ,p_user_id     IN       VARCHAR2     
     ,p_search_his   OUT      XXPNA_SEARCH_HIS_IN_TAB
     ,status          OUT      VARCHAR2
     ,MESSAGE         OUT      VARCHAR2
     )
     AS
     
   CURSOR cur_item_search_his(c_org_id IN NUMBER, c_user_id VARCHAR2)
IS
  SELECT ORG_ID ,
    USER_ID ,
    ITEM_NUMBER ,
    ITEM_DESCRIPTION ,
    CUSTOMER_NUMBER ,
    ITEM_QUANTITY ,
    PRICE_LIST ,
    REQUEST_DATE ,
    ATTRIBUTE1 ,
    ATTRIBUTE2 ,
    ATTRIBUTE3 ,
    ATTRIBUTE4 ,
    ATTRIBUTE5 ,
    ATTRIBUTE6 ,
    ATTRIBUTE7 ,
    ATTRIBUTE8 ,
    ATTRIBUTE9 ,
    ATTRIBUTE10 ,
    ATTRIBUTE11 ,
    ATTRIBUTE12 ,
    ATTRIBUTE13 ,
    ATTRIBUTE14 ,
    ATTRIBUTE15
  FROM XXPNA_ITEM_SEARCH_HIS
  WHERE USER_ID = c_user_id
  AND ORG_ID    = c_org_id
  ORDER BY CREATION_DATE DESC;
    
      ln_idx         NUMBER := 0;
      l_list_tab XXPNA_SEARCH_HIS_IN_TAB ;
   BEGIN
    l_list_tab  := XXPNA_SEARCH_HIS_IN_TAB() ;
    
     FOR rec IN cur_item_search_his ( p_org_id ,p_user_id    )
    LOOP
     dbms_output.put_line( 'rec.USER_ID '||rec.USER_ID);
     dbms_output.put_line( 'rec.ITEM_NUMBER '||rec.ITEM_NUMBER);
      ln_idx := ln_idx + 1;
        l_list_tab.EXTEND;     
      l_list_tab (ln_idx) :=
          XXPNA_SEARCH_HIS_IN (
    ORG_ID             => rec.ORG_ID,
    USER_ID            => rec.USER_ID,
    ITEM_NUMBER        => rec.ITEM_NUMBER,
    ITEM_DESCRIPTION   => rec.ITEM_DESCRIPTION,
    CUSTOMER_NUMBER    => rec.CUSTOMER_NUMBER,
    ITEM_QUANTITY      => rec.ITEM_QUANTITY,
    PRICE_LIST         => rec.PRICE_LIST,
    REQUEST_DATE       => rec.REQUEST_DATE,                                 
    ATTRIBUTE1    =>     rec.ATTRIBUTE1,
    ATTRIBUTE2   =>     rec.ATTRIBUTE2,
    ATTRIBUTE3   =>     rec.ATTRIBUTE3,
    ATTRIBUTE4   =>     rec.ATTRIBUTE4,
    ATTRIBUTE5   =>     rec.ATTRIBUTE5,
    ATTRIBUTE6   =>     rec.ATTRIBUTE6,
    ATTRIBUTE7   =>     rec.ATTRIBUTE7,
    ATTRIBUTE8   =>     rec.ATTRIBUTE8,
    ATTRIBUTE9   =>     rec.ATTRIBUTE9,
    ATTRIBUTE10  =>     rec.ATTRIBUTE10,
    ATTRIBUTE11  =>     rec.ATTRIBUTE11,
    ATTRIBUTE12  =>     rec.ATTRIBUTE12,
    ATTRIBUTE13  =>     rec.ATTRIBUTE13,
    ATTRIBUTE14  =>     rec.ATTRIBUTE14,
    ATTRIBUTE15  =>     rec.ATTRIBUTE15
    );
 END LOOP;

IF ln_idx > 0
      THEN
        p_search_his := l_list_tab;
         status := 'S';
         MESSAGE := 'SUCCESS';
      ELSE
        status := 'E';
        MESSAGE := 'No Data in history';
      END IF;

      EXCEPTION
         WHEN OTHERS
         THEN
         dbms_output.put_line( 'Error while extracting history data'
                   || SQLCODE
                   || ' '
                   || SUBSTR(SQLERRM, 1,1000)
                   || ' '
                   || DBMS_UTILITY.format_error_backtrace);

            status := 'E';
            MESSAGE := 'Error while extracting history data'|| SQLCODE
                   || ' '
                   || SUBSTR(SQLERRM, 1,1000);   

      
   END xxpna_search_his;

   PROCEDURE xxpna_check_user (
      piv_user_name   IN       VARCHAR2
     ,pout_status     OUT      VARCHAR2
   )
   AS
      ln_employee_id   NUMBER;
      ln_customer_id   NUMBER;
   BEGIN
      SELECT employee_id
            ,customer_id
        INTO ln_employee_id
            ,ln_customer_id
        FROM fnd_user
       WHERE UPPER (user_name) = UPPER (piv_user_name);

      IF ln_employee_id <> 0
      THEN
         pout_status := 'INTERNAL_USER';
         DBMS_OUTPUT.put_line ('The User is a internal');
      ELSIF ln_customer_id <> 0
      THEN
         pout_status := 'EXTERNAL_USER';
         DBMS_OUTPUT.put_line ('The User is a external');
       ELSIF ln_employee_id IS NULL AND ln_customer_id IS NULL
       THEN 
            pout_status := 'INVALID_USER';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         pout_status := 'F';
         DBMS_OUTPUT.put_line ('User Not Found');
      WHEN OTHERS
      THEN
         pout_status := 'ERROR';
         DBMS_OUTPUT.put_line ('Exception is fetching User Details');
   END XXPNA_CHECK_USER;
   
   PROCEDURE xxpna_get_my_order_details (
     p_user_name     IN  NUMBER
    ,p_order_details OUT xxpna_my_orders_tab
    ,status          OUT VARCHAR2
    ,message         OUT VARCHAR2
    )
   AS
   
   l_order_details_tab xxpna_my_orders_tab;
   BEGIN
   
    l_order_details_tab := xxpna_my_orders_tab ();
    l_order_details_tab.EXTEND;
    l_order_details_tab(1) := xxpna_my_orders (
                                                    order_number => 'Order1',
                                                    order_status => 'Closed',
                                                    amount => 100,
                                                    order_date => SYSDATE
                                                  );
    
    status := 'SUCCESS';
    message := 'Order details fetched';
   
   EXCEPTION
   WHEN OTHERS THEN
    status := 'FAIL';
    message := 'Error in fetching order details-'||SQLERRM;
   END xxpna_get_my_order_details;  
   
   
END xxpna_pricing_util_pkg;