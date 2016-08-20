package mobile.entity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class CheckUser {
    
    private Number userId;
    private String userName;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public CheckUser() {
        super();
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
