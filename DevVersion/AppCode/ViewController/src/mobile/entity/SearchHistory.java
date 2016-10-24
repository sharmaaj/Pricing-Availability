package mobile.entity;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import mobile.bean.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class SearchHistory extends AEntity {

    private String itemNumber;
    private String itemDescription;
    private String itemQuantity;
   // private Date requestDate;
    private String requestDate;
    private String customerNumber;
    private String priceList;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    AEntity ent = new AEntity();
    
    public SearchHistory() {
        super();
    }

    public SearchHistory(JSONObject temp) throws JSONException {
        this.setItemNumber(temp.getString("ITEM_NUMBER"));
        this.setItemDescription(temp.getString("ITEM_DESCRIPTION"));
        this.setItemQuantity(temp.getString("ITEM_QUANTITY"));
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        /*try{
        Date startDate = df.parse(temp.getString("REQUEST_DATE").substring(0,10));
            System.out.println("Start Date is Request Date-->"+startDate);
        this.setRequestDate(startDate);
        }catch(Exception e){
            System.out.println("Error during request date conversion");
        } */
        
        this.setRequestDate(temp.getString("REQUEST_DATE").substring(0,10));
        System.out.println("Start Date is Request Date-->"+temp.getString("REQUEST_DATE").substring(0,10));
        this.setCustomerNumber(temp.getString("CUSTOMER_NUMBER"));
        this.setPriceList(temp.getString("PRICE_LIST"));
    }


    public void setRequestDate(String requestDate) {
        String oldRequestDate = this.requestDate;
        this.requestDate = requestDate;
        _propertyChangeSupport.firePropertyChange("requestDate", oldRequestDate, requestDate);
    }

    public String getRequestDate() {
        requestDate = ent.getValue(requestDate);
        if (requestDate.equals(null) || requestDate.equals("") )
            requestDate = "N/A";
        return requestDate;
    }

    public void setItemNumber(String itemNumber) {
        String oldItemNumber = this.itemNumber;
        this.itemNumber = itemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, itemNumber);
    }

    public String getItemNumber() {
        itemNumber = ent.getValue(itemNumber);
        if (itemNumber.equals(null) || itemNumber.equals("") )
            itemNumber = "N/A";
        return super.getAttributeValue(itemNumber);
    }

    public void setItemDescription(String itemDescription) {
        String oldItemDescription = this.itemDescription;
        this.itemDescription = itemDescription;
        _propertyChangeSupport.firePropertyChange("itemDescription", oldItemDescription, itemDescription);
    }

    public String getItemDescription() {
        itemDescription = ent.getValue(itemDescription);
        if (itemDescription.equals(null) || itemDescription.equals("") )
            itemDescription = "N/A";
        return super.getAttributeValue(itemDescription);
    }

    public void setItemQuantity(String itemQuantity) {
        String oldItemQuantity = this.itemQuantity;
        this.itemQuantity = itemQuantity;
        _propertyChangeSupport.firePropertyChange("itemQuantity", oldItemQuantity, itemQuantity);
    }

    public String getItemQuantity() {
        itemQuantity = ent.getValue(itemQuantity);
        if (itemQuantity.equals(null) || itemQuantity.equals("") )
            itemQuantity = "0";
        return super.getAttributeValue(itemQuantity);
    }

 /*   public void setRequestDate(Date requestDate) {
        Date oldRequestDate = this.requestDate;
        this.requestDate = requestDate;
        _propertyChangeSupport.firePropertyChange("requestDate", oldRequestDate, requestDate);
    } 

    public Date getRequestDate() {
        return requestDate;
    } */

    public void setCustomerNumber(String customerNumber) {
        String oldCustomerNumber = this.customerNumber;
        this.customerNumber = customerNumber;
        _propertyChangeSupport.firePropertyChange("customerNumber", oldCustomerNumber, customerNumber);
    }

    public String getCustomerNumber() {
        customerNumber = ent.getValue(customerNumber);
        if (customerNumber.equals(null) || customerNumber.equals("") )
            customerNumber = "N/A";
        return super.getAttributeValue(customerNumber);
    }

    public void setPriceList(String priceList) {
        String oldPriceList = this.priceList;
        this.priceList = priceList;
        _propertyChangeSupport.firePropertyChange("priceList", oldPriceList, priceList);
    }

    public String getPriceList() {
        priceList = ent.getValue(priceList);
        if (priceList.equals(null) || priceList.equals("") )
            priceList = "N/A";
        return super.getAttributeValue(priceList);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
