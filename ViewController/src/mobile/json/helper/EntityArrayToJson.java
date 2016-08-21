package mobile.json.helper;

import mobile.entity.ItemEntity;
import mobile.entity.PriceListEntity;
import mobile.entity.GetPricingInformation;
import mobile.entity.MyNewSearch;

public class EntityArrayToJson {
    public EntityArrayToJson() {
        super();
    }
    
    public static String getJsonForItemNumLovEntity(ItemEntity getItemNumberLov) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");

        if (getItemNumberLov.getItemNum() != null) {
            sb.append("\"itemNum\":\"");
            sb.append(getItemNumberLov.getItemNum() + "\",");
        }
        if (getItemNumberLov.getItemDesc()!= null) {
            sb.append("\"itemDesc\":\"");
            sb.append(getItemNumberLov.getItemDesc() + "\",");
        }
        if (getItemNumberLov.getOrgId() != null) {
            sb.append("\"orgId\":\"");
            sb.append(getItemNumberLov.getOrgId() + "\",");
        }
        
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        String jsonObject = sb.toString();

        return jsonObject;

    }
    
    public static String getJsonForPriceListLovEntity(PriceListEntity getPriceListLov) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");

        if (getPriceListLov.getListName() != null) {
            sb.append("\"listName\":\"");
            sb.append(getPriceListLov.getListName() + "\",");
        }
        if (getPriceListLov.getLISTDESC()!= null) {
            sb.append("\"LISTDESC\":\"");
            sb.append(getPriceListLov.getLISTDESC() + "\",");
        }
  
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        String jsonObject = sb.toString();

        return jsonObject;

    }
    
    public static String getJsonForGetPricingInformationEntity(GetPricingInformation getPricingInfo) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        
        if (getPricingInfo.getP_item_description() != null) {
            sb.append("\"p_item_description\":\"");
            sb.append(getPricingInfo.getP_item_description() + "\",");
        }
        if (getPricingInfo.getP_item_quantity() != null) {
            sb.append("\"p_item_quantity\":\"");
            sb.append(getPricingInfo.getP_item_quantity() + "\",");
        }
        if (getPricingInfo.getP_request_date()!= null) {
            sb.append("\"p_request_date\":\"");
            sb.append(getPricingInfo.getP_request_date() + "\",");
        }
        if (getPricingInfo.getP_customer_number() != null) {
            sb.append("\"p_customer_number\":\"");
            sb.append(getPricingInfo.getP_customer_number() + "\",");
        }
        if (getPricingInfo.getCreated_by() != null) {
            sb.append("\"created_by\":\"");
            sb.append(getPricingInfo.getCreated_by() + "\",");
        }
        if (getPricingInfo.getLast_updated_by() != null) {
            sb.append("\"last_updated_by\":\"");
            sb.append(getPricingInfo.getLast_updated_by() + "\",");
        }
        
        if (getPricingInfo.getCreation_date() != null) {
            sb.append("\"creation_date\":\"");
            sb.append(getPricingInfo.getCreation_date() + "\",");
        }
        
        if (getPricingInfo.getLast_update_login() != null) {
            sb.append("\"last_update_login\":\"");
            sb.append(getPricingInfo.getLast_update_login() + "\",");
        }
        
        if (getPricingInfo.getP_price_list() != null) {
            sb.append("\"p_price_list\":\"");
            sb.append(getPricingInfo.getP_price_list() + "\",");
        }
        
        if (getPricingInfo.getP_item_number() != null) {
            sb.append("\"p_item_number\":\"");
            sb.append(getPricingInfo.getP_item_number() + "\",");
        }
        
        if (getPricingInfo.getP_currency() != null) {
            sb.append("\"p_currency\":\"");
            sb.append(getPricingInfo.getP_currency() + "\",");
        }
        if (getPricingInfo.getP_selling_price() != null) {
            sb.append("\"p_selling_price\":\"");
            sb.append(getPricingInfo.getP_selling_price() + "\",");
        }
        
        if (getPricingInfo.getP_availabe() != null) {
            sb.append("\"p_availabe\":\"");
            sb.append(getPricingInfo.getP_availabe() + "\",");
        }
        
        if (getPricingInfo.getP_available_date() != null) {
            sb.append("\"p_available_date\":\"");
            sb.append(getPricingInfo.getP_available_date() + "\",");
        }
        
        if (getPricingInfo.getP_uom() != null) {
            sb.append("\"p_uom\":\"");
            sb.append(getPricingInfo.getP_uom() + "\",");
        }
        
        if (getPricingInfo.getP_atp_flag() != null) {
            sb.append("\"p_atp_flag\":\"");
            sb.append(getPricingInfo.getP_atp_flag() + "\",");
        }
        
        if (getPricingInfo.getP_discount() != null) {
            sb.append("\"p_discount\":\"");
            sb.append(getPricingInfo.getP_discount() + "\",");
        }
        
        if (getPricingInfo.getP_dis_desc() != null) {
            sb.append("\"p_dis_desc\":\"");
            sb.append(getPricingInfo.getP_dis_desc() + "\",");
        }
        
        if (getPricingInfo.getP_dis_valid_date() != null) {
            sb.append("\"p_dis_valid_date\":\"");
            sb.append(getPricingInfo.getP_dis_valid_date() + "\",");
        }
        
        if (getPricingInfo.getP_warehouse() != null) {
            sb.append("\"p_warehouse\":\"");
            sb.append(getPricingInfo.getP_warehouse() + "\",");
        }

        if (getPricingInfo.getAttribute1() != null) {
            sb.append("\"attribute1\":\"");
            sb.append(getPricingInfo.getAttribute1() + "\",");
        }
        if (getPricingInfo.getAttribute2() != null) {
            sb.append("\"attribute2\":\"");
            sb.append(getPricingInfo.getAttribute2() + "\",");
        }
        
        if (getPricingInfo.getAttribute3() != null) {
            sb.append("\"attribute3\":\"");
            sb.append(getPricingInfo.getAttribute3() + "\",");
        }
        if (getPricingInfo.getAttribute4() != null) {
            sb.append("\"attribute4\":\"");
            sb.append(getPricingInfo.getAttribute4() + "\",");
        }
        if (getPricingInfo.getAttribute5() != null) {
            sb.append("\"attribute5\":\"");
            sb.append(getPricingInfo.getAttribute5() + "\",");
        }
        if (getPricingInfo.getAttribute6() != null) {
            sb.append("\"attribute6\":\"");
            sb.append(getPricingInfo.getAttribute6() + "\",");
        }
        if (getPricingInfo.getAttribute7() != null) {
            sb.append("\"attribute7\":\"");
            sb.append(getPricingInfo.getAttribute7() + "\",");
        }
        if (getPricingInfo.getAttribute8() != null) {
            sb.append("\"attribute8\":\"");
            sb.append(getPricingInfo.getAttribute8() + "\",");
        }
        if (getPricingInfo.getAttribute9() != null) {
            sb.append("\"attribute9\":\"");
            sb.append(getPricingInfo.getAttribute9() + "\",");
        }
        if (getPricingInfo.getAttribute10() != null) {
            sb.append("\"attribute10\":\"");
            sb.append(getPricingInfo.getAttribute10() + "\",");
        }
        if (getPricingInfo.getAttribute11() != null) {
            sb.append("\"attribute11\":\"");
            sb.append(getPricingInfo.getAttribute11() + "\",");
        }
        if (getPricingInfo.getAttribute12() != null) {
            sb.append("\"attribute12\":\"");
            sb.append(getPricingInfo.getAttribute12() + "\",");
        }
        if (getPricingInfo.getAttribute13() != null) {
            sb.append("\"attribute13\":\"");
            sb.append(getPricingInfo.getAttribute13() + "\",");
        }
        if (getPricingInfo.getAttribute14() != null) {
            sb.append("\"attribute14\":\"");
            sb.append(getPricingInfo.getAttribute14() + "\",");
        }
        if (getPricingInfo.getAttribute15() != null) {
            sb.append("\"attribute15\":\"");
            sb.append(getPricingInfo.getAttribute15() + "\",");
        }
        
 
     
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        String jsonObject = sb.toString();

        return jsonObject;

    }
}
