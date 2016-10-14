create or replace
PACKAGE BODY      XXPNA_LOV_PKG 
AS
   /* $Header: XXPNA_LOV_PKG.pkh 120.2.4 2015/02/18 ankubansal $ */

   /*#

   * This package contains LOV procedures for Pricing and availability  Mobile App.

   * @rep:scope public

   * @rep:product INV

   * @rep:displayname PNA LOV Module

   * @rep:lifecycle active

   * @rep:compatibility S

   * @rep:category BUSINESS_ENTITY INV_LOV

   */

   /*#

    * Get Item LOV

    * This procedure can be used to get lpn

    * @param pou this is to pass OU

    * @param itemnum this is to pass item number

    * @param xitem this is db object of type XXPNA_ITEM_TAB contains response with list of items

    * @param xStatus this is S for success, F for failure

    * @param xMsg if F then contains error message else blank

    * @rep:scope public

    * @rep:lifecycle active

    * @rep:displayname Get Warehouse LOV

   */
   PROCEDURE getitem (
      pou        IN       VARCHAR2 DEFAULT NULL
     ,pitemnum   IN       VARCHAR2 DEFAULT NULL
     ,xitem      OUT      xxpna_item_tab
     ,xstatus    OUT      VARCHAR2
     ,xmsg       OUT      VARCHAR2
   )
   AS
      lv_item_desc   VARCHAR2 (100);
      ln_org_id      NUMBER;
      lv_error_msg   VARCHAR2 (100);
      ln_idx         NUMBER := 0;
      l_item_tab xxpna_item_tab := xxpna_item_tab();
      
      CURSOR cur_items (porgid IN NUMBER)
      IS
      SELECT segment1, description, organization_id
      FROM mtl_system_items_b
      WHERE upper(segment1) like upper(pitemnum)||'%'
      AND organization_id = porgid
  --    AND nvl(atp_flag,'N')='Y'
      AND nvl(enabled_flag,'N')='Y';
      
   BEGIN
      lv_error_msg := 'Not able to get Organization ID';

      SELECT organization_id
        INTO ln_org_id
        FROM hr_operating_units
       WHERE NAME = pou;

      FOR rec IN cur_items (ln_org_id)
      LOOP
        ln_idx := ln_idx + 1;
        l_item_tab.EXTEND;
        l_item_tab(ln_idx) := xxpna_item (rec.segment1,
                                          rec.description,
                                          rec.organization_id);
      END LOOP;

      IF ln_idx > 0
      THEN
        xitem := l_item_tab;
         xstatus := 'S';
         xmsg := 'SUCCESS';
      ELSE
         xstatus := 'E';
         xmsg := 'ERROR';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
        xstatus := 'E';
        xmsg := lv_error_msg;
         DBMS_OUTPUT.put_line ('No DATA Found' || lv_error_msg);
      WHEN OTHERS
      THEN
        xstatus := 'E';
        xmsg := 'ERROR- ' || SUBSTR(SQLERRM, 1, 200);
        DBMS_OUTPUT.put_line (xmsg);
   END getitem;
   
PROCEDURE getPriceList (
       porgid    IN       NUMBER DEFAULT 0
     ,pitemnum   IN       VARCHAR2 DEFAULT NULL
     ,xpricelist OUT      xxpna_pricelist_tab
     ,xstatus    OUT      VARCHAR2
     ,xmsg       OUT      VARCHAR2
   )
   AS
    lv_list_name   VARCHAR2 (100);
    lv_list_desc   VARCHAR2 (100);
    ln_item_id     NUMBER;
    lv_error_msg   VARCHAR2 (100);
    ln_idx         NUMBER := 0;
   -- l_list_tab xxpna_pricelist_tab := xxpna_pricelist_tab();
   l_list_tab xxpna_pricelist_tab ;
    
    CURSOR cur_list (pitemid IN NUMBER)
    IS
--    select qlh.name, qlh.description
--    from qp_list_headers_all qlh,
--         qp_list_lines qll
--    where qlh.list_header_id = qll.list_header_id
--    and  qll.inventory_item_id = pitemid
--    and  qll.organization_id = nvl(porgid, qll.organization_id) 
SELECT distinct qph.name, qph.attribute1 as description
  FROM qp_List_headers_all qph,
       qp_list_lines qll,
      -- qp_pricing_attributes qpa,
	   (Select qp.list_line_id, to_number(qp.product_attr_value) product_attr_value , qp.product_attribute_context from qp_pricing_attributes qp where REGEXP_LIKE(qp.product_attr_value, '^[[:digit:]]+$')) qpa,
       apps.mtl_system_items_b msi
WHERE     qph.list_header_id = qll.list_header_id
       AND qll.list_line_id = qpa.list_line_id
       AND msi.inventory_item_id = pitemid
       AND qpa.product_attribute_context = 'ITEM'
       AND msi.inventory_item_id = to_number(qpa.product_attr_value)
       AND msi.organization_id = porgid
       --and qph.description is not null
       ;
	      
    
  BEGIN
  l_list_tab  := xxpna_pricelist_tab() ;
    lv_error_msg := 'Not able to get Item id';
    SELECT inventory_item_id
    INTO ln_item_id
    FROM mtl_system_items_b
    WHERE organization_id = porgid
    AND   segment1 = pitemnum;
    
    FOR rec IN cur_list (ln_item_id)
    LOOP
      
      ln_idx := ln_idx + 1;
      l_list_tab.EXTEND;
     l_list_tab (ln_idx) := xxpna_pricelist ( rec.name, rec.description);
     
    END LOOP;
    xpricelist := l_list_tab;
    
    IF ln_idx > 0
    THEN
       xstatus := 'S';
       xmsg := 'SUCCESS';
    ELSE
       xstatus := 'E';
       xmsg := 'ERROR';
    END IF;
  
  EXCEPTION
  WHEN OTHERS THEN
    xstatus := 'E';
    xmsg := 'ERROR- ' || SUBSTR(SQLERRM, 1, 200);
  END getPriceList; 

  
END xxpna_lov_pkg;