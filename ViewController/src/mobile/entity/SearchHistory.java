package mobile.entity;

import java.math.BigDecimal;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class SearchHistory {

    private String itemNumber;
    private String itemDescription;
    private String itemQuantity;
    private Date requestDate;
    private String customerNumber;
    private Number priceList;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SearchHistory() {
        super();
    }

    public SearchHistory(JSONObject temp) throws JSONException {
        this.setItemNumber(temp.getString("ITEMNUMBER"));
        this.setItemDescription(temp.getString("ITEMDESCRIPTION"));
        this.setItemQuantity(temp.getString("ITEMQUANTITY"));
        this.setRequestDate(new Date(temp.getString("REQUESTDATE")));
        this.setCustomerNumber(temp.getString("CUSTOMERNUMBER"));
        this.setPriceList(new BigDecimal(temp.getString("PRICELIST")));
    }

    public void setItemNumber(String itemNumber) {
        String oldItemNumber = this.itemNumber;
        this.itemNumber = itemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, itemNumber);
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemDescription(String itemDescription) {
        String oldItemDescription = this.itemDescription;
        this.itemDescription = itemDescription;
        _propertyChangeSupport.firePropertyChange("itemDescription", oldItemDescription, itemDescription);
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemQuantity(String itemQuantity) {
        String oldItemQuantity = this.itemQuantity;
        this.itemQuantity = itemQuantity;
        _propertyChangeSupport.firePropertyChange("itemQuantity", oldItemQuantity, itemQuantity);
    }

    public String getItemQuantity() {
        return itemQuantity;
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
        return customerNumber;
    }

    public void setPriceList(Number priceList) {
        Number oldPriceList = this.priceList;
        this.priceList = priceList;
        _propertyChangeSupport.firePropertyChange("priceList", oldPriceList, priceList);
    }

    public Number getPriceList() {
        return priceList;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
