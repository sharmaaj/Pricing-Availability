package mobile.json.arraystructure;

import mobile.entity.GetItemNumberLOV;
import mobile.entity.GetPriceListLov;
import mobile.entity.GetPricingInformation;

public class EntityResultArray {

    private GetItemNumberLOV[] getItemNumLov = null;
    private GetPriceListLov[] getPriceListLov = null;
    private GetPricingInformation[] getPricingInfo = null;


    public EntityResultArray() {
        super();
    }

    public GetPriceListLov[] getGetPriceListLov() {
        return getPriceListLov;
    }

    public void setGetItemNumLov(GetItemNumberLOV[] getItemNumLov) {
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


    public GetItemNumberLOV[] getGetItemNumLov() {
        return getItemNumLov;
    }
}
