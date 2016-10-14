create or replace
PACKAGE      xxpna_lov_pkg AUTHID CURRENT_USER
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
   );
   
   PROCEDURE getPriceList (
       porgid    IN       NUMBER DEFAULT 0
     ,pitemnum   IN       VARCHAR2 DEFAULT NULL
     ,xpricelist OUT      xxpna_pricelist_tab
     ,xstatus    OUT      VARCHAR2
     ,xmsg       OUT      VARCHAR2
   );
END xxpna_lov_pkg;