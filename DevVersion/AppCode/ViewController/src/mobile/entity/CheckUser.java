package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class CheckUser {
    
    private Number userId;
    private String userName;
    private String userTypeStatus;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public CheckUser() {
        super();
    }
    
    public CheckUser(JSONObject temp) throws JSONException {
    //    this.setUserId(new BigDecimal(temp.getString("USERID")));
    //    this.setUserName(temp.getString("USERNAME"));
        this.setUserTypeStatus(temp.getString("USERTYPESTATUS"));
    }

    public void setUserTypeStatus(String userTypeStatus) {
        this.userTypeStatus = userTypeStatus;
    }

    public String getUserTypeStatus() {
        return userTypeStatus;
    }

    public void setUserId(Number userId) {
        Number oldUserId = this.userId;
        this.userId = userId;
        _propertyChangeSupport.firePropertyChange("userId", oldUserId, userId);
    }

    public Number getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        String oldUserName = this.userName;
        this.userName = userName;
        _propertyChangeSupport.firePropertyChange("userName", oldUserName, userName);
    }

    public String getUserName() {
        return userName;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
