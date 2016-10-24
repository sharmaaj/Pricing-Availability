package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class ApplyDiscount {
    
    private Number discountRate;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    
    public ApplyDiscount() {
        super();
    }
    
    public ApplyDiscount(JSONObject temp) throws JSONException {
        this.setDiscount_rate(new BigDecimal(temp.getString("DISCOUNTRATE")));
    }

 
    public void setDiscount_rate(Number discount_rate) {
        Number oldDiscount_rate = this.discountRate;
        this.discountRate = discount_rate;
        _propertyChangeSupport.firePropertyChange("discountRate", oldDiscount_rate, discount_rate);
    }

    public Number getDiscount_rate() {
        return discountRate;
    }

     public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
