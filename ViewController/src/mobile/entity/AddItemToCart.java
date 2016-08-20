package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class AddItemToCart {
    
    private Number itemNumber, item_quantity,user_id;
    private String item_name, item_description;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    
    public AddItemToCart() {
        super();
    }
    
    public AddItemToCart(JSONObject temp) throws JSONException {
        this.setItemNumber(new BigDecimal(temp.getString("ITEM_NUMBER")));
        this.setItem_quantity(new BigDecimal(temp.getString("ITEM_QUANTITY")));
        this.setUser_id(new BigDecimal(temp.getString("USER_ID")));
        this.setItem_name(temp.getString("ITEM_NAME"));
        this.setItem_description(temp.getString("ITEM_DESCRIPTION"));
    }

  
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
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

    public void setItem_name(String item_name) {
        String oldItem_name = this.item_name;
        this.item_name = item_name;
        _propertyChangeSupport.firePropertyChange("item_name", oldItem_name, item_name);
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_description(String item_description) {
        String oldItem_description = this.item_description;
        this.item_description = item_description;
        _propertyChangeSupport.firePropertyChange("item_description", oldItem_description, item_description);
    }

    public String getItem_description() {
        return item_description;
    }
}
