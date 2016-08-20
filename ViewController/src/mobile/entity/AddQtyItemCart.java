package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class AddQtyItemCart {
    
    private Number quantity,user_id,itemNumber;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    
    public AddQtyItemCart() {
        super();
    }
    
    public AddQtyItemCart(JSONObject temp) throws JSONException {
        this.setItemNumber(new BigDecimal(temp.getString("item_number")));
        this.setQuantity(new BigDecimal(temp.getString("item_quantity")));
        this.setUser_id(new BigDecimal(temp.getString("user_id")));
    }


    public void setQuantity(Number quantity) {
        Number oldQuantity = this.quantity;
        this.quantity = quantity;
        _propertyChangeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }

    public Number getQuantity() {
        return quantity;
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
}
