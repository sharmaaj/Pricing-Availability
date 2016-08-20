package mobile.json.helper;

import java.util.logging.Level;

import mobile.entity.GetItemNumberLOV;
import mobile.entity.GetPriceListLov;
import mobile.entity.GetPricingInformation;
import mobile.entity.MyNewSearch;

import mobile.json.arraystructure.EntityResultArray;

import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.util.logging.Trace;

public class JsonToEntityArray {
    public JsonToEntityArray() {
        super();
    }
    
    public static GetItemNumberLOV[] getItemNumberLovArray(String jsonArrayAsString) {
        EntityResultArray getItemNumberLov = null;

        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();
        try {
            getItemNumberLov = (EntityResultArray) jbsh.fromJSON(EntityResultArray.class, jsonArrayAsString);


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, EntityResultArray.class, "getItemNumberLov",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }

        return getItemNumberLov.getGetItemNumLov();
    }
   
    public static GetPriceListLov[] getPriceListLovArray(String jsonArrayAsString) {
        EntityResultArray getPriceListLov = null;

        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();
        try {
            getPriceListLov = (EntityResultArray) jbsh.fromJSON(EntityResultArray.class, jsonArrayAsString);


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, EntityResultArray.class, "getPriceListLov",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }

        return getPriceListLov.getGetPriceListLov();
    } 
    
    public static GetPricingInformation[] getPricingInforamation(String jsonArrayAsString) {
        EntityResultArray getPriceInfo = null;

        //object that serializes the JSON payload into the Java object
        JSONBeanSerializationHelper jbsh = new JSONBeanSerializationHelper();
        try {
            getPriceInfo = (EntityResultArray) jbsh.fromJSON(EntityResultArray.class, jsonArrayAsString);


        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, EntityResultArray.class, "getPriceInfo",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }

        return getPriceInfo.getGetPricingInfo();
    } 
    
}
