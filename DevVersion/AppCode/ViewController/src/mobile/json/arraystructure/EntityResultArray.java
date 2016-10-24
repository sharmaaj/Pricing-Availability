package mobile.json.arraystructure;

import mobile.entity.ItemEntity;
import mobile.entity.PriceListEntity;
import mobile.entity.GetPricingInformation;

public class EntityResultArray {

    private ItemEntity[] getItemNumLov = null;
    private PriceListEntity[] getPriceListLov = null;
    private GetPricingInformation[] getPricingInfo = null;


    public EntityResultArray() {
        super();
    }

    public PriceListEntity[] getGetPriceListLov() {
        return getPriceListLov;
    }

    public void setGetItemNumLov(ItemEntity[] getItemNumLov) {
        this.getItemNumLov = getItemNumLov;
    }

    public void setGetPriceListLov(PriceListEntity[] getPriceListLov) {
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
