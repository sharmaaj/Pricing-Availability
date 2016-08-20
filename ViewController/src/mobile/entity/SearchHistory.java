package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class SearchHistory {
    
    private Number userID, itemNumber;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SearchHistory() {
       super();
    }
    
    public SearchHistory(JSONObject temp) throws JSONException {
         this.setUserID(new BigDecimal(temp.getString("USER_ID")));
         this.setItemNumber(new BigDecimal(temp.getString("ITEM_NUMBER")));
    }

    public void setUserID(Number userID) {
        Number oldUserID = this.userID;
        this.userID = userID;
        _propertyChangeSupport.firePropertyChange("userID", oldUserID, userID);
    }

    public Number getUserID() {
        return userID;
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
