package mobile.entity;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class OrderCreation {

    private String order_date;
    private String amount;
    private String order_status;
    private String order_number;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    public OrderCreation() {
        super();
    }

    public OrderCreation(JSONObject temp) throws JSONException {

        this.setOrder_status(temp.getString("ORDER_STATUS"));
        this.setAmount(temp.getString("AMOUNT"));
        this.setOrder_date(temp.getString("ORDER_DATE").substring(0, 10));
        this.setOrder_number(temp.getString("ORDER_NUMBER"));

    }

    public void setOrder_date(String order_date) {
        String oldOrder_date = this.order_date;
        this.order_date = order_date;
        _propertyChangeSupport.firePropertyChange("order_date", oldOrder_date, order_date);
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setAmount(String amount) {
        String oldAmount = this.amount;
        this.amount = amount;
        _propertyChangeSupport.firePropertyChange("amount", oldAmount, amount);
    }

    public String getAmount() {
        return amount;
    }

    public void setOrder_status(String order_status) {
        String oldOrder_status = this.order_status;
        this.order_status = order_status;
        _propertyChangeSupport.firePropertyChange("order_status", oldOrder_status, order_status);
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_number(String order_number) {
        String oldOrder_number = this.order_number;
        this.order_number = order_number;
        _propertyChangeSupport.firePropertyChange("order_number", oldOrder_number, order_number);
    }

    public String getOrder_number() {
        return order_number;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
