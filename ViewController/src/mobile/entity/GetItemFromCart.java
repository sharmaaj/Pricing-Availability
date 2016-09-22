package mobile.entity;

import java.math.BigDecimal;

import java.util.Date;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class GetItemFromCart {
    
    private String  USER_NAME ;
    private String  ITEM_NUMBER;
    private String  ITEM_NAME;
    private String  ITEM_DESCRIPTION;
    private Number  ITEM_QUANTITY;   
    private String  REQUEST_DATE;  
    private Number  PRICE_LIST;
    private String CUSTOMER_NUMBER; 
    private String  ATTRIBUTE1; //Amount
    private String ATTRIBUTE2;
    private String ATTRIBUTE3;
    private String ATTRIBUTE4;
    private String ATTRIBUTE5;
    private String ATTRIBUTE6;
    private String ATTRIBUTE7;
    private String ATTRIBUTE8;
    private String ATTRIBUTE9;
    private String ATTRIBUTE10;
    private String ATTRIBUTE11;
    private String ATTRIBUTE12;
    private String ATTRIBUTE13;
    private String ATTRIBUTE14;
    private String ATTRIBUTE15;  
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    
    public GetItemFromCart() {
        super();
    }
    
    public GetItemFromCart(JSONObject temp) throws JSONException {
        this.setUSER_NAME(temp.getString("USER_NAME"));
        this.setITEM_NUMBER(temp.getString("ITEM_NUMBER"));
        this.setITEM_NAME(temp.getString("ITEM_NAME"));
        String tempQty = temp.getString("ITEM_QUANTITY");
        this.setITEM_QUANTITY(Integer.parseInt(tempQty));
        String tempPList = temp.getString("ITEM_QUANTITY");
        this.setPRICE_LIST(Integer.parseInt(tempPList));
        this.setITEM_DESCRIPTION(temp.getString("ITEM_DESCRIPTION"));
        this.setREQUEST_DATE(temp.getString("REQUEST_DATE"));
        this.setCUSTOMER_NUMBER(temp.getString("CUSTOMER_NUMBER"));
        this.setATTRIBUTE1(temp.getString("ATTRIBUTE1"));
        this.setATTRIBUTE2(temp.getString("ATTRIBUTE2"));
        this.setATTRIBUTE3(temp.getString("ATTRIBUTE3"));
        this.setATTRIBUTE4(temp.getString("ATTRIBUTE4"));
        this.setATTRIBUTE5(temp.getString("ATTRIBUTE5"));
        this.setATTRIBUTE6(temp.getString("ATTRIBUTE6"));
        this.setATTRIBUTE7(temp.getString("ATTRIBUTE7"));
        this.setATTRIBUTE8(temp.getString("ATTRIBUTE8"));
        this.setATTRIBUTE9(temp.getString("ATTRIBUTE9"));
        this.setATTRIBUTE10(temp.getString("ATTRIBUTE10"));
        this.setATTRIBUTE11(temp.getString("ATTRIBUTE11"));
        this.setATTRIBUTE12(temp.getString("ATTRIBUTE12"));
        this.setATTRIBUTE13(temp.getString("ATTRIBUTE13"));
        this.setATTRIBUTE14(temp.getString("ATTRIBUTE14"));
        this.setATTRIBUTE15(temp.getString("ATTRIBUTE15"));
    }


    public void setUSER_NAME(String USER_NAME) {
        String oldUSER_NAME = this.USER_NAME;
        this.USER_NAME = USER_NAME;
        _propertyChangeSupport.firePropertyChange("USER_NAME", oldUSER_NAME, USER_NAME);
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setITEM_NUMBER(String ITEM_NUMBER) {
        String oldITEM_NUMBER = this.ITEM_NUMBER;
        this.ITEM_NUMBER = ITEM_NUMBER;
        _propertyChangeSupport.firePropertyChange("ITEM_NUMBER", oldITEM_NUMBER, ITEM_NUMBER);
    }

    public String getITEM_NUMBER() {
        return ITEM_NUMBER;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        String oldITEM_NAME = this.ITEM_NAME;
        this.ITEM_NAME = ITEM_NAME;
        _propertyChangeSupport.firePropertyChange("ITEM_NAME", oldITEM_NAME, ITEM_NAME);
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_DESCRIPTION(String ITEM_DESCRIPTION) {
        String oldITEM_DESCRIPTION = this.ITEM_DESCRIPTION;
        this.ITEM_DESCRIPTION = ITEM_DESCRIPTION;
        _propertyChangeSupport.firePropertyChange("ITEM_DESCRIPTION", oldITEM_DESCRIPTION, ITEM_DESCRIPTION);
    }

    public String getITEM_DESCRIPTION() {
        return ITEM_DESCRIPTION;
    }

    public void setITEM_QUANTITY(Number ITEM_QUANTITY) {
        
        System.out.println("Entry 1.0 ");
        Number oldITEM_QUANTITY = this.ITEM_QUANTITY;
        this.ITEM_QUANTITY = ITEM_QUANTITY;
        _propertyChangeSupport.firePropertyChange("ITEM_QUANTITY", oldITEM_QUANTITY, ITEM_QUANTITY);
        System.out.println("Entry 2.0 ");
        
    }

    public Number getITEM_QUANTITY() {
        return ITEM_QUANTITY;
    }

    public void setREQUEST_DATE(String REQUEST_DATE) {
        String oldREQUEST_DATE = this.REQUEST_DATE;
        this.REQUEST_DATE = REQUEST_DATE;
        _propertyChangeSupport.firePropertyChange("REQUEST_DATE", oldREQUEST_DATE, REQUEST_DATE);
    }

    public String getREQUEST_DATE() {
        return REQUEST_DATE;
    }

    public void setPRICE_LIST(Number PRICE_LIST) {
        Number oldPRICE_LIST = this.PRICE_LIST;
        this.PRICE_LIST = PRICE_LIST;
        _propertyChangeSupport.firePropertyChange("PRICE_LIST", oldPRICE_LIST, PRICE_LIST);
    }

    public Number getPRICE_LIST() {
        return PRICE_LIST;
    }

    public void setCUSTOMER_NUMBER(String CUSTOMER_NUMBER) {
        String oldCUSTOMER_NUMBER = this.CUSTOMER_NUMBER;
        this.CUSTOMER_NUMBER = CUSTOMER_NUMBER;
        _propertyChangeSupport.firePropertyChange("CUSTOMER_NUMBER", oldCUSTOMER_NUMBER, CUSTOMER_NUMBER);
    }

    public String getCUSTOMER_NUMBER() {
        return CUSTOMER_NUMBER;
    }

    public void setATTRIBUTE1(String ATTRIBUTE1) {
        String oldATTRIBUTE1 = this.ATTRIBUTE1;
        this.ATTRIBUTE1 = ATTRIBUTE1;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE1", oldATTRIBUTE1, ATTRIBUTE1);
    }

    public String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }

    public void setATTRIBUTE2(String ATTRIBUTE2) {
        String oldATTRIBUTE2 = this.ATTRIBUTE2;
        this.ATTRIBUTE2 = ATTRIBUTE2;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE2", oldATTRIBUTE2, ATTRIBUTE2);
    }

    public String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }

    public void setATTRIBUTE3(String ATTRIBUTE3) {
        String oldATTRIBUTE3 = this.ATTRIBUTE3;
        this.ATTRIBUTE3 = ATTRIBUTE3;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE3", oldATTRIBUTE3, ATTRIBUTE3);
    }

    public String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }

    public void setATTRIBUTE4(String ATTRIBUTE4) {
        String oldATTRIBUTE4 = this.ATTRIBUTE4;
        this.ATTRIBUTE4 = ATTRIBUTE4;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE4", oldATTRIBUTE4, ATTRIBUTE4);
    }

    public String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }

    public void setATTRIBUTE5(String ATTRIBUTE5) {
        String oldATTRIBUTE5 = this.ATTRIBUTE5;
        this.ATTRIBUTE5 = ATTRIBUTE5;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE5", oldATTRIBUTE5, ATTRIBUTE5);
    }

    public String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }

    public void setATTRIBUTE6(String ATTRIBUTE6) {
        String oldATTRIBUTE6 = this.ATTRIBUTE6;
        this.ATTRIBUTE6 = ATTRIBUTE6;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE6", oldATTRIBUTE6, ATTRIBUTE6);
    }

    public String getATTRIBUTE6() {
        return ATTRIBUTE6;
    }

    public void setATTRIBUTE7(String ATTRIBUTE7) {
        String oldATTRIBUTE7 = this.ATTRIBUTE7;
        this.ATTRIBUTE7 = ATTRIBUTE7;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE7", oldATTRIBUTE7, ATTRIBUTE7);
    }

    public String getATTRIBUTE7() {
        return ATTRIBUTE7;
    }

    public void setATTRIBUTE8(String ATTRIBUTE8) {
        String oldATTRIBUTE8 = this.ATTRIBUTE8;
        this.ATTRIBUTE8 = ATTRIBUTE8;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE8", oldATTRIBUTE8, ATTRIBUTE8);
    }

    public String getATTRIBUTE8() {
        return ATTRIBUTE8;
    }

    public void setATTRIBUTE9(String ATTRIBUTE9) {
        String oldATTRIBUTE9 = this.ATTRIBUTE9;
        this.ATTRIBUTE9 = ATTRIBUTE9;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE9", oldATTRIBUTE9, ATTRIBUTE9);
    }

    public String getATTRIBUTE9() {
        return ATTRIBUTE9;
    }

    public void setATTRIBUTE10(String ATTRIBUTE10) {
        String oldATTRIBUTE10 = this.ATTRIBUTE10;
        this.ATTRIBUTE10 = ATTRIBUTE10;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE10", oldATTRIBUTE10, ATTRIBUTE10);
    }

    public String getATTRIBUTE10() {
        return ATTRIBUTE10;
    }

    public void setATTRIBUTE11(String ATTRIBUTE11) {
        String oldATTRIBUTE11 = this.ATTRIBUTE11;
        this.ATTRIBUTE11 = ATTRIBUTE11;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE11", oldATTRIBUTE11, ATTRIBUTE11);
    }

    public String getATTRIBUTE11() {
        return ATTRIBUTE11;
    }

    public void setATTRIBUTE12(String ATTRIBUTE12) {
        String oldATTRIBUTE12 = this.ATTRIBUTE12;
        this.ATTRIBUTE12 = ATTRIBUTE12;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE12", oldATTRIBUTE12, ATTRIBUTE12);
    }

    public String getATTRIBUTE12() {
        return ATTRIBUTE12;
    }

    public void setATTRIBUTE13(String ATTRIBUTE13) {
        String oldATTRIBUTE13 = this.ATTRIBUTE13;
        this.ATTRIBUTE13 = ATTRIBUTE13;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE13", oldATTRIBUTE13, ATTRIBUTE13);
    }

    public String getATTRIBUTE13() {
        return ATTRIBUTE13;
    }

    public void setATTRIBUTE14(String ATTRIBUTE14) {
        String oldATTRIBUTE14 = this.ATTRIBUTE14;
        this.ATTRIBUTE14 = ATTRIBUTE14;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE14", oldATTRIBUTE14, ATTRIBUTE14);
    }

    public String getATTRIBUTE14() {
        return ATTRIBUTE14;
    }

    public void setATTRIBUTE15(String ATTRIBUTE15) {
        String oldATTRIBUTE15 = this.ATTRIBUTE15;
        this.ATTRIBUTE15 = ATTRIBUTE15;
        _propertyChangeSupport.firePropertyChange("ATTRIBUTE15", oldATTRIBUTE15, ATTRIBUTE15);
    }

    public String getATTRIBUTE15() {
        return ATTRIBUTE15;
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
