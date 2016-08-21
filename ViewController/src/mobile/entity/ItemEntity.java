package mobile.entity;

import java.math.BigDecimal;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class ItemEntity {
    
    private String itemNum;
    private String itemDesc;
    private Number orgId;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public ItemEntity() {
        super();
    }
    
    public Object clone() {

        ItemEntity getItemLov = new ItemEntity();
        getItemLov.setItemNum(itemNum);
        getItemLov.setItemDesc(itemDesc);
        getItemLov.setOrgId(orgId);
        
        return getItemLov;
    }
    
    public ItemEntity(JSONObject temp) throws JSONException {
        this.setItemNum(temp.getString("ITEMNUM"));
        this.setOrgId(new BigDecimal(temp.getString("ORGID")));
        this.setItemDesc(temp.getString("ITEMDESC"));
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
