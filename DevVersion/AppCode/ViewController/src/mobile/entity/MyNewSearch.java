package mobile.entity;

import java.math.BigDecimal;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

import oracle.sql.DATE;

public class MyNewSearch {

    private Number itemNumber, item_quantity, user_id, priceList;
    private String item_description, customerNumber;
    private Date requestedDate;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public MyNewSearch() {
        super();
    }
    
    /*  public MyNewSearch(JSONObject temp) throws JSONException {
          this.setItemNumber(new BigDecimal(temp.getString("ITEM_NUMBER")));
          this.setItem_quantity(new BigDecimal(temp.getString("ITEM_QUANTITY")));
          this.setUser_id(new BigDecimal(temp.getString("USER_ID")));
          this.setPriceList(new BigDecimal(temp.getString("ITEM_NAME")));
          this.setItem_description(temp.getString("ITEM_DESCRIPTION"));
          this.setCustomerNumber(temp.getString("CUSTOMER_NUMBER"));
          this.setRequestedDate(new Date(temp.getString("REQUESTED_DATE")));
      } */
      
      public Object clone() {

          MyNewSearch mynewsearch = new MyNewSearch();
          mynewsearch.setItemNumber(itemNumber);
          mynewsearch.setItem_quantity(item_quantity);
          mynewsearch.setUser_id(user_id);
          mynewsearch.setPriceList(priceList);
          mynewsearch.setItem_description(item_description);
          mynewsearch.setCustomerNumber(customerNumber);
          mynewsearch.setRequestedDate(requestedDate);

          return mynewsearch;
      }

    public void setItemNumber(Number itemNumber) {
        Number oldItemNumber = this.itemNumber;
        this.itemNumber = itemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, itemNumber);
    }

    public Number getItemNumber() {
        return itemNumber;
    }

    public void setItem_quantity(Number item_quantity) {
        Number oldItem_quantity = this.item_quantity;
        this.item_quantity = item_quantity;
        _propertyChangeSupport.firePropertyChange("item_quantity", oldItem_quantity, item_quantity);
    }

    public Number getItem_quantity() {
        return item_quantity;
    }

    public void setUser_id(Number user_id) {
        Number oldUser_id = this.user_id;
        this.user_id = user_id;
        _propertyChangeSupport.firePropertyChange("user_id", oldUser_id, user_id);
    }

    public Number getUser_id() {
        return user_id;
    }

    public void setPriceList(Number priceList) {
        Number oldPriceList = this.priceList;
        this.priceList = priceList;
        _propertyChangeSupport.firePropertyChange("priceList", oldPriceList, priceList);
    }

    public Number getPriceList() {
        return priceList;
    }

    public void setItem_description(String item_description) {
        String oldItem_description = this.item_description;
        this.item_description = item_description;
        _propertyChangeSupport.firePropertyChange("item_description", oldItem_description, item_description);
    }

    public String getItem_description() {
        return item_description;
    }

    public void setCustomerNumber(String customerNumber) {
        String oldCustomerNumber = this.customerNumber;
        this.customerNumber = customerNumber;
        _propertyChangeSupport.firePropertyChange("customerNumber", oldCustomerNumber, customerNumber);
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setRequestedDate(Date requestedDate) {
        Date oldRequestedDate = this.requestedDate;
        this.requestedDate = requestedDate;
        _propertyChangeSupport.firePropertyChange("requestedDate", oldRequestedDate, requestedDate);
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

     public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
