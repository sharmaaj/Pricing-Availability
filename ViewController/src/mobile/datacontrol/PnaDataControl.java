package mobile.datacontrol;

import java.util.Date;

import java.util.List;

import mobile.entity.GetItemNumberLOV;
import mobile.entity.GetPriceListLov;
import mobile.entity.GetPricingInformation;
import mobile.entity.MyNewSearch;

import mobile.json.helper.JsonToEntityArray;

import mobile.rest.RestServiceHelperClass;
import mobile.rest.RestURIs;

public class PnaDataControl {


    public PnaDataControl() {
        super();
    }

    private GetItemNumberLOV[] getAllItemNumbers = null;
    private GetPriceListLov[] getAllPriceList = null;

    public void setGetAllItemNumbers(GetItemNumberLOV[] getAllItemNumbers) {
        this.getAllItemNumbers = getAllItemNumbers;
    }

    public GetItemNumberLOV[] getGetAllItemNumbers() {
        if (getAllItemNumbers == null) {

            String payload = "{ \"POU\" : \"" + null + "\",\"PITEMNUM\" : \"" + null + "\"}";


            String restURI = RestURIs.getItemNumberLov();
            RestServiceHelperClass rshc = new RestServiceHelperClass();
            String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
            GetItemNumberLOV[] getAllItems = JsonToEntityArray.getItemNumberLovArray(jsonArrayAsString);
            getAllItemNumbers = getAllItems;

        }
        return getAllItemNumbers;
    }


    public GetItemNumberLOV getItemDetailsByItemNumber(String orgId, String itemNumber) {

        String payload = "{ \"POU\" : \"" + orgId + "\",\"PITEMNUM\" : \"" + itemNumber + "\"}";

        String restURI = RestURIs.getItemNumberLov();
        RestServiceHelperClass rshc = new RestServiceHelperClass();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        GetItemNumberLOV[] getItemsdtls = JsonToEntityArray.getItemNumberLovArray(jsonArrayAsString);
        //    getAllItemNumbers = getAllItems;

        if (getItemsdtls.length > 0) {
            return getItemsdtls[0];
        } else
            return null;
    }


    public void setGetAllPriceList(GetPriceListLov[] getAllPriceList) {
        this.getAllPriceList = getAllPriceList;
    }

    public GetPriceListLov[] getGetAllPriceList1() {
        if (getAllPriceList == null) {

            String payload = "{ \"PORGID\" : \"" + null + "\",\"PITEMNUM\" : \"" + null + "\"}";


            String restURI = RestURIs.getItemNumberLov();
            RestServiceHelperClass rshc = new RestServiceHelperClass();
            String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
            GetPriceListLov[] getAllPriceListLt = JsonToEntityArray.getPriceListLovArray(jsonArrayAsString);
            getAllPriceList = getAllPriceListLt;

        }
        return getAllPriceList;
    }

    public GetPriceListLov getGetAllPriceList(Number orgId, String ItemNum) {

        String payload = "{ \"PORGID\" : \"" + orgId + "\",\"PITEMNUM\" : \"" + ItemNum + "\"}";


        String restURI = RestURIs.getItemNumberLov();
        RestServiceHelperClass rshc = new RestServiceHelperClass();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        GetPriceListLov[] getAllPriceListLt = JsonToEntityArray.getPriceListLovArray(jsonArrayAsString);
        //     getAllPriceList = getAllPriceListLt;

        if (getAllPriceListLt.length > 0) {
            return getAllPriceListLt[0];
        } else
            return null;
    }
    
    public GetPricingInformation getGetPricingInfo(String ItemNum, Number custNum , String priceList) {

        String payload = "{ \"P_ITEM_NUMBER\" : \"" + ItemNum + "\",\"P_CUSTOMER_NUMBER\" : \"" + custNum + "\",\"P_PRICE_LIST\":\""+ priceList + "\"}";


        String restURI = RestURIs.getItemNumberLov();
        RestServiceHelperClass rshc = new RestServiceHelperClass();
        String jsonArrayAsString = rshc.invokeUPDATE(restURI, payload);
        GetPricingInformation[] getAllPriceListLt = JsonToEntityArray.getPricingInforamation(jsonArrayAsString);
        //     getAllPriceList = getAllPriceListLt;

        if (getAllPriceListLt.length > 0) {
            return getAllPriceListLt[0];
        } else
            return null;
    }
}
