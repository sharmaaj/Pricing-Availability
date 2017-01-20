package mobile.entity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class GetOrderTypeLOV {

    private String orderTypeName;
    private String ordertypedesc;
    private String orderTypeId;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    public GetOrderTypeLOV() {
        super();
    }

    public GetOrderTypeLOV(JSONObject temp) throws JSONException {
        this.setOrderTypeName(temp.getString("ORDERTYPENAME"));
        this.setOrdertypedesc(temp.getString("ORDERTYPEDESC"));
        System.out.println("Here inside order type 999999999999");
        this.setOrdertypedesc(temp.getString("ORDERTYPEID"));
    }

    public void setOrderTypeName(String orderTypeName) {
        String oldOrderTypeName = this.orderTypeName;
        this.orderTypeName = orderTypeName;
        _propertyChangeSupport.firePropertyChange("orderTypeName", oldOrderTypeName, orderTypeName);
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrdertypedesc(String ordertypedesc) {
        String oldOrdertypedesc = this.ordertypedesc;
        this.ordertypedesc = ordertypedesc;
        _propertyChangeSupport.firePropertyChange("ordertypedesc", oldOrdertypedesc, ordertypedesc);
    }

    public String getOrdertypedesc() {
        return ordertypedesc;
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

    public void setOrderTypeId(String orderTypeId) {
        String oldOrderTypeId = this.orderTypeId;
        this.orderTypeId = orderTypeId;
        _propertyChangeSupport.firePropertyChange("orderTypeId", oldOrderTypeId, orderTypeId);
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }
}
