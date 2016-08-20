package mobile.entity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class GetPriceListLov {
    
    private String listName;
    private String LISTDESC;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public GetPriceListLov() {
        super();
    }
    
    public Object clone() {

        GetPriceListLov getPriceListLov = new GetPriceListLov();
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
