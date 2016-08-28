package mobile.entity;

import java.math.BigDecimal;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

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
    private Date requestDate;
    private String customerNumber;
    private String priceList;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SearchHistory() {
        super();
    }

    public SearchHistory(JSONObject temp) throws JSONException {
        this.setItemNumber(temp.getString("ITEM_NUMBER"));
        this.setItemDescription(temp.getString("ITEM_DESCRIPTION"));
        this.setItemQuantity(temp.getString("ITEM_QUANTITY"));
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        try{
        Date startDate = df.parse(temp.getString("REQUEST_DATE").substring(0,10));
        this.setRequestDate(startDate);
        }catch(Exception e){
            System.out.println("Error during request date conversion");
        }
        this.setCustomerNumber(temp.getString("CUSTOMER_NUMBER"));
        this.setPriceList(temp.getString("PRICE_LIST"));
    }

    public void setItemNumber(String itemNumber) {
        String oldItemNumber = this.itemNumber;
        this.itemNumber = itemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, itemNumber);
    }

    public String getItemNumber() {
        return super.getAttributeValue(itemNumber);
    }

    public void setItemDescription(String itemDescription) {
        String oldItemDescription = this.itemDescription;
        this.itemDescription = itemDescription;
        _propertyChangeSupport.firePropertyChange("itemDescription", oldItemDescription, itemDescription);
    }

    public String getItemDescription() {
        return super.getAttributeValue(itemDescription);
    }

    public void setItemQuantity(String itemQuantity) {
        String oldItemQuantity = this.itemQuantity;
        this.itemQuantity = itemQuantity;
        _propertyChangeSupport.firePropertyChange("itemQuantity", oldItemQuantity, itemQuantity);
    }

    public String getItemQuantity() {
        return super.getAttributeValue(itemQuantity);
    }

    public void setRequestDate(Date requestDate) {
        Date oldRequestDate = this.requestDate;
        this.requestDate = requestDate;
        _propertyChangeSupport.firePropertyChange("requestDate", oldRequestDate, requestDate);
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setCustomerNumber(String customerNumber) {
        String oldCustomerNumber = this.customerNumber;
        this.customerNumber = customerNumber;
        _propertyChangeSupport.firePropertyChange("customerNumber", oldCustomerNumber, customerNumber);
    }

    public String getCustomerNumber() {
        return super.getAttributeValue(customerNumber);
    }

    public void setPriceList(String priceList) {
        String oldPriceList = this.priceList;
        this.priceList = priceList;
        _propertyChangeSupport.firePropertyChange("priceList", oldPriceList, priceList);
    }

    public String getPriceList() {
        return super.getAttributeValue(priceList);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
