package mobile.json.arraystructure;

import mobile.entity.ItemEntity;
import mobile.entity.GetPriceListLov;
import mobile.entity.GetPricingInformation;

public class EntityResultArray {

    private ItemEntity[] getItemNumLov = null;
    private GetPriceListLov[] getPriceListLov = null;
    private GetPricingInformation[] getPricingInfo = null;


    public EntityResultArray() {
        super();
    }

    public GetPriceListLov[] getGetPriceListLov() {
        return getPriceListLov;
    }

    public void setGetItemNumLov(ItemEntity[] getItemNumLov) {
        this.getItemNumLov = getItemNumLov;
    }

    public void setGetPriceListLov(GetPriceListLov[] getPriceListLov) {
        this.getPriceListLov = getPriceListLov;
    }

    public void setGetPricingInfo(GetPricingInformation[] getPricingInfo) {
        this.getPricingInfo = getPricingInfo;
    }

    public GetPricingInformation[] getGetPricingInfo() {
        return getPricingInfo;
    }


    public ItemEntity[] getGetItemNumLov() {
        return getItemNumLov;
    }
}
