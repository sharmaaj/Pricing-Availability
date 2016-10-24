package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class ApplyDiscount {
    
    private Number discount, discount_rate,user_id,itemNumber;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    
    public ApplyDiscount() {
        super();
    }
    
    public ApplyDiscount(JSONObject temp) throws JSONException {
        this.setItemNumber(new BigDecimal(temp.getString("item_number")));
        this.setDiscount(new BigDecimal(temp.getString("item_quantity")));
        this.setUser_id(new BigDecimal(temp.getString("user_id")));
        this.setDiscount_rate(new BigDecimal(temp.getString("item_name")));
    }

    public void setDiscount(Number discount) {
        Number oldDiscount = this.discount;
        this.discount = discount;
        _propertyChangeSupport.firePropertyChange("discount", oldDiscount, discount);
    }

    public Number getDiscount() {
        return discount;
    }

    public void setDiscount_rate(Number discount_rate) {
        Number oldDiscount_rate = this.discount_rate;
        this.discount_rate = discount_rate;
        _propertyChangeSupport.firePropertyChange("discount_rate", oldDiscount_rate, discount_rate);
    }

    public Number getDiscount_rate() {
        return discount_rate;
    }

    public void setUser_id(Number user_id) {
        Number oldUser_id = this.user_id;
        this.user_id = user_id;
        _propertyChangeSupport.firePropertyChange("user_id", oldUser_id, user_id);
    }

    public Number getUser_id() {
        return user_id;
    }

    public void setItemNumber(Number itemNumber) {
        Number oldItemNumber = this.itemNumber;
        this.itemNumber = itemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, itemNumber);
    }

    public Number getItemNumber() {
        return itemNumber;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
