package mobile.datacontrol;

import java.util.Date;

import java.util.List;

import mobile.entity.ItemEntity;
import mobile.entity.PriceListEntity;
import mobile.entity.GetPricingInformation;
import mobile.entity.MyNewSearch;

import mobile.json.helper.JsonToEntityArray;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

public class PnaDataControl {


    public PnaDataControl() {
        super();
    }

    private ItemEntity[] getAllItemNumbers = null;
    private PriceListEntity[] getAllPriceList = null;

    public void setGetAllItemNumbers(ItemEntity[] getAllItemNumbers) {
        this.getAllItemNumbers = getAllItemNumbers;
    }

    public ItemEntity[] getGetAllItemNumbers() {
        if (getAllItemNumbers == null) {

            String payload = "{ \"POU\" : \"" + null + "\",\"PITEMNUM\" : \"" + null + "\"}";


            String restURI = RestURIs.getItemNumberLov();
            RestServiceManager rshc = new RestServiceManager();
            String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
            ItemEntity[] getAllItems = JsonToEntityArray.getItemNumberLovArray(jsonArrayAsString);
            getAllItemNumbers = getAllItems;

        }
        return getAllItemNumbers;
    }


    public ItemEntity getItemDetailsByItemNumber(String orgId, String itemNumber) {

        String payload = "{ \"POU\" : \"" + orgId + "\",\"PITEMNUM\" : \"" + itemNumber + "\"}";

        String restURI = RestURIs.getItemNumberLov();
        RestServiceManager rshc = new RestServiceManager();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        ItemEntity[] getItemsdtls = JsonToEntityArray.getItemNumberLovArray(jsonArrayAsString);
        //    getAllItemNumbers = getAllItems;

        if (getItemsdtls.length > 0) {
            return getItemsdtls[0];
        } else
            return null;
    }


    public void setGetAllPriceList(PriceListEntity[] getAllPriceList) {
        this.getAllPriceList = getAllPriceList;
    }

    public PriceListEntity[] getGetAllPriceList1() {
        if (getAllPriceList == null) {

            String payload = "{ \"PORGID\" : \"" + null + "\",\"PITEMNUM\" : \"" + null + "\"}";


            String restURI = RestURIs.getItemNumberLov();
            RestServiceManager rshc = new RestServiceManager();
            String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
            PriceListEntity[] getAllPriceListLt = JsonToEntityArray.getPriceListLovArray(jsonArrayAsString);
            getAllPriceList = getAllPriceListLt;

        }
        return getAllPriceList;
    }

    public PriceListEntity getGetAllPriceList(Number orgId, String ItemNum) {

        String payload = "{ \"PORGID\" : \"" + orgId + "\",\"PITEMNUM\" : \"" + ItemNum + "\"}";


        String restURI = RestURIs.getItemNumberLov();
        RestServiceManager rshc = new RestServiceManager();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        PriceListEntity[] getAllPriceListLt = JsonToEntityArray.getPriceListLovArray(jsonArrayAsString);
        //     getAllPriceList = getAllPriceListLt;

        if (getAllPriceListLt.length > 0) {
            return getAllPriceListLt[0];
        } else
            return null;
    }
    
    public GetPricingInformation getGetPricingInfo(String ItemNum, Number custNum , String priceList) {

        String payload = "{ \"P_ITEM_NUMBER\" : \"" + ItemNum + "\",\"P_CUSTOMER_NUMBER\" : \"" + custNum + "\",\"P_PRICE_LIST\":\""+ priceList + "\"}";


        String restURI = RestURIs.getItemNumberLov();
        RestServiceManager rshc = new RestServiceManager();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        GetPricingInformation[] getAllPriceListLt = JsonToEntityArray.getPricingInforamation(jsonArrayAsString);
        //     getAllPriceList = getAllPriceListLt;

        if (getAllPriceListLt.length > 0) {
            return getAllPriceListLt[0];
        } else
            return null;
    }
}
