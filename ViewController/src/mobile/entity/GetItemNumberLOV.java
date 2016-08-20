package mobile.entity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class GetItemNumberLOV {
    
    private String itemNum;
    private String itemDesc;
    private Number orgId;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public GetItemNumberLOV() {
        super();
    }
    
    public Object clone() {

        GetItemNumberLOV getItemLov = new GetItemNumberLOV();
        getItemLov.setItemNum(itemNum);
        getItemLov.setItemDesc(itemDesc);
        getItemLov.setOrgId(orgId);
        
        return getItemLov;
    }


    public void setItemNum(String itemNum) {
        String oldItemNum = this.itemNum;
        this.itemNum = itemNum;
        _propertyChangeSupport.firePropertyChange("itemNum", oldItemNum, itemNum);
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemDesc(String itemDesc) {
        String oldItemDesc = this.itemDesc;
        this.itemDesc = itemDesc;
        _propertyChangeSupport.firePropertyChange("itemDesc", oldItemDesc, itemDesc);
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setOrgId(Number orgId) {
        Number oldOrgId = this.orgId;
        this.orgId = orgId;
        _propertyChangeSupport.firePropertyChange("orgId", oldOrgId, orgId);
    }

    public Number getOrgId() {
        return orgId;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
