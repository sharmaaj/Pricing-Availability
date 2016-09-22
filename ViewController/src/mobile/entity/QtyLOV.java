package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class QtyLOV {
    private String ItemNumber;
    private Number quantity;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public QtyLOV() {
        super();
    }
    
    public QtyLOV(Number temp){
        this.setQuantity(temp);
    }

    public void setItemNumber(String ItemNumber) {
        String oldItemNumber = this.ItemNumber;
        this.ItemNumber = ItemNumber;
        _propertyChangeSupport.firePropertyChange("itemNumber", oldItemNumber, ItemNumber);
    }

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setQuantity(Number quantity) {
        Number oldQuantity = this.quantity;
        this.quantity = quantity;
        _propertyChangeSupport.firePropertyChange("quantity", oldQuantity, quantity);
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport _propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this._propertyChangeSupport;
        this._propertyChangeSupport = _propertyChangeSupport;
        _propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                  _propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return _propertyChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
