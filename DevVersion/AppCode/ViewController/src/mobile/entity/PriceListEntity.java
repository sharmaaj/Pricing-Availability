package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class PriceListEntity {
    
    private String listName;
    private String LISTDESC;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public PriceListEntity() {
        super();
    }
    
    public PriceListEntity(JSONObject temp) throws JSONException {
        this.setLISTDESC(temp.getString("LISTDESC"));
        this.setListName(temp.getString("LISTNAME"));
    }
    
    public Object clone() {

        PriceListEntity getPriceListLov = new PriceListEntity();
        getPriceListLov.setListName(listName);
        getPriceListLov.setLISTDESC(LISTDESC);
        
        return getPriceListLov;
    }


    public void setListName(String listName) {
        String oldListName = this.listName;
        this.listName = listName;
        _propertyChangeSupport.firePropertyChange("listName", oldListName, listName);
    }

    public String getListName() {
        return listName;
    }

    public void setLISTDESC(String LISTDESC) {
        String oldLISTDESC = this.LISTDESC;
        this.LISTDESC = LISTDESC;
        _propertyChangeSupport.firePropertyChange("lISTDESC", oldLISTDESC, LISTDESC);
    }

    public String getLISTDESC() {
        return LISTDESC;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
