package mobile.entity;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;

import mobile.bean.AEntity;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class GetPricingInformation extends AEntity {
    
     private String p_item_description  ;
     private String p_item_quantity  ;
     private String p_request_date  ;
     private String p_customer_number  ;
     private String created_by  ;
     private String last_updated_by  ;
     private String last_updated_date  ;
     private String creation_date  ;
     private String last_update_login  ;
     private String p_price_list  ;
     private String p_item_number  ;
     private String p_currency  ;
     private String p_selling_price  ;
     private String p_availabe  ;
     private String p_available_date  ;
     private String p_uom  ;
     private String p_atp_flag  ;
     private String p_discount  ;
     private String p_dis_desc  ;
     private String p_dis_valid_date;
     private String p_warehouse  ;
     private String attribute1 ;
     private String attribute2 ;
     private String attribute3  ;
     private String attribute4 ;
     private String attribute5  ;
     private String attribute6  ;
     private String attribute7 ;
     private String attribute8  ;
     private String attribute9;
     private String attribute10  ;
     private String attribute11  ;
     private String attribute12 ;
     private String attribute13  ;
     private String attribute14  ;
     private String attribute15  ;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    
    public GetPricingInformation() {
        super();
    }
    
    public GetPricingInformation(JSONObject temp) throws JSONException {
        
       
        this.setP_item_description(temp.getString("P_ITEM_DESCRIPTION"));
        this.setP_item_quantity(temp.getString("P_ITEM_QUANTITY"));
        this.setP_request_date(temp.getString("P_REQUEST_DATE").substring(0,10));
     //   this.setP_request_date(temp.getString("P_REQUEST_DATE"));
        this.setP_customer_number(temp.getString("P_CUSTOMER_NUMBER"));
        this.setCreated_by(temp.getString("CREATED_BY"));
        this.setLast_updated_by(temp.getString("LAST_UPDATED_BY"));
        this.setLast_updated_date(temp.getString("LAST_UPDATED_DATE").substring(0,10));
        this.setCreation_date(temp.getString("CREATION_DATE").substring(0,10));
        this.setLast_update_login(temp.getString("LAST_UPDATE_LOGIN"));
        this.setP_price_list(temp.getString("P_PRICE_LIST"));
        this.setP_item_number(temp.getString("P_ITEM_NUMBER"));
        this.setP_currency(temp.getString("P_CURRENCY"));
        this.setP_selling_price(temp.getString("P_SELLING_PRICE"));
        this.setP_availabe(temp.getString("P_AVAILABLE"));
        this.setP_available_date(temp.getString("P_AVAILABLE_DATE").substring(0,10));
        this.setP_uom(temp.getString("P_UOM"));
        this.setP_atp_flag(temp.getString("P_ATP_FLAG"));
        this.setP_discount(temp.getString("P_DISCOUNT"));
        System.out.println("DIscount from Pricing Info-->"+temp.getString("P_DISCOUNT"));
        this.setP_dis_desc(temp.getString("P_DIS_DESC"));
        System.out.println("DIscount Description from Pricing Info-->"+temp.getString("P_DIS_DESC"));
        this.setP_dis_valid_date(temp.getString("P_DIS_VALID_DATE").substring(0,10));
        this.setP_warehouse(temp.getString("P_WAREHOUSE"));
        this.setAttribute1(temp.getString("ATTRIBUTE1"));
        this.setAttribute2(temp.getString("ATTRIBUTE2"));
        this.setAttribute3(temp.getString("ATTRIBUTE3"));
        this.setAttribute4(temp.getString("ATTRIBUTE4"));
        this.setAttribute5(temp.getString("ATTRIBUTE5"));
        this.setAttribute6(temp.getString("ATTRIBUTE6"));
        this.setAttribute7(temp.getString("ATTRIBUTE7"));
        this.setAttribute8(temp.getString("ATTRIBUTE8"));
        this.setAttribute9(temp.getString("ATTRIBUTE9"));
        this.setAttribute10(temp.getString("ATTRIBUTE10"));
        this.setAttribute11(temp.getString("ATTRIBUTE11"));
        this.setAttribute12(temp.getString("ATTRIBUTE12"));
        this.setAttribute13(temp.getString("ATTRIBUTE13"));
        this.setAttribute14(temp.getString("ATTRIBUTE14"));
        this.setAttribute15(temp.getString("ATTRIBUTE15"));
        
    }

    public void setP_item_description(String p_item_description) {
        String oldP_item_description = this.p_item_description;
        this.p_item_description = super.getValue(p_item_description);
        _propertyChangeSupport.firePropertyChange("p_item_description", oldP_item_description, p_item_description);
    }

    public String getP_item_description() {
        AEntity ent = new AEntity();
        p_item_description = ent.getValue(p_item_description);
        if (p_item_description.equals(null) || p_item_description.equals("") )
            p_item_description = "N/A";
        return p_item_description;
    }

    public void setP_item_quantity(String p_item_quantity) {
        String oldP_item_quantity = this.p_item_quantity;
        this.p_item_quantity = super.getValue(p_item_quantity);
        _propertyChangeSupport.firePropertyChange("p_item_quantity", oldP_item_quantity, p_item_quantity);
    }

    public String getP_item_quantity() {
        AEntity ent = new AEntity();
        p_item_quantity = ent.getValue(p_item_quantity);
        if (p_item_quantity.equals(null) || p_item_quantity.equals("") )
            p_item_quantity = "0";
        return p_item_quantity;
    }

    public void setP_request_date(String p_request_date) {
        String oldP_request_date = this.p_request_date;
        this.p_request_date = super.getValue(p_request_date);
        _propertyChangeSupport.firePropertyChange("p_request_date", oldP_request_date, p_request_date);
    }

    public String getP_request_date() {
        AEntity ent = new AEntity();
        p_request_date = ent.getValue(p_request_date);
        if (p_request_date.equals(null) || p_request_date.equals("") )
            p_request_date = "N/A";
        return p_request_date;
    }

    public void setP_customer_number(String p_customer_number) {
        String oldP_customer_number = this.p_customer_number;
        this.p_customer_number = super.getValue(p_customer_number);
        _propertyChangeSupport.firePropertyChange("p_customer_number", oldP_customer_number, p_customer_number);
    }

    public String getP_customer_number() {
        AEntity ent = new AEntity();
        p_customer_number = ent.getValue(p_customer_number);
        if (p_customer_number.equals(null) || p_customer_number.equals("") )
            p_customer_number = "N/A";
        return p_customer_number;
    }

    public void setCreated_by(String created_by) {
        String oldCreated_by = this.created_by;
        this.created_by = super.getValue(created_by);
        _propertyChangeSupport.firePropertyChange("created_by", oldCreated_by, created_by);
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        String oldLast_updated_by = this.last_updated_by;
        this.last_updated_by = super.getValue(last_updated_by);
        _propertyChangeSupport.firePropertyChange("last_updated_by", oldLast_updated_by, last_updated_by);
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_date(String last_updated_date) {
        String oldLast_updated_date = this.last_updated_date;
        this.last_updated_date = super.getValue(last_updated_date);
        _propertyChangeSupport.firePropertyChange("last_updated_date", oldLast_updated_date, last_updated_date);
    }

    public String getLast_updated_date() {
        return last_updated_date;
    }

    public void setCreation_date(String creation_date) {
        String oldCreation_date = this.creation_date;
        this.creation_date = super.getValue(creation_date);
        _propertyChangeSupport.firePropertyChange("creation_date", oldCreation_date, creation_date);
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setLast_update_login(String last_update_login) {
        String oldLast_update_login = this.last_update_login;
        this.last_update_login = super.getValue(last_update_login);
        _propertyChangeSupport.firePropertyChange("last_update_login", oldLast_update_login, last_update_login);
    }

    public String getLast_update_login() {
        return last_update_login;
    }

    public void setP_price_list(String p_price_list) {
        String oldP_price_list = this.p_price_list;
        this.p_price_list = super.getValue(p_price_list);
        _propertyChangeSupport.firePropertyChange("p_price_list", oldP_price_list, p_price_list);
    }

    public String getP_price_list() {
        AEntity ent = new AEntity();
        p_price_list = ent.getValue(p_price_list);
        if (p_price_list.equals(null) || p_price_list.equals("") )
            p_price_list = "0";
        return p_price_list;
    }


    public void setP_item_number(String p_item_number) {
        String oldP_item_number = this.p_item_number;
        this.p_item_number = super.getValue(p_item_number);
        _propertyChangeSupport.firePropertyChange("p_item_number", oldP_item_number, p_item_number);
    }

    public String getP_item_number() {
        return p_item_number;
    }

    public void setP_currency(String p_currency) {
        String oldP_currency = this.p_currency;
        this.p_currency = super.getValue(p_currency);
        _propertyChangeSupport.firePropertyChange("p_currency", oldP_currency, p_currency);
    }

    public String getP_currency() {
        AEntity ent = new AEntity();
        p_currency = ent.getValue(p_currency);
        if (p_currency.equals(null) || p_currency.equals("") )
            p_currency = "N/A";
        return p_currency;
    }

    public void setP_selling_price(String p_selling_price) {
        String oldP_selling_price = this.p_selling_price;
        this.p_selling_price = super.getValue(p_selling_price);
        _propertyChangeSupport.firePropertyChange("p_selling_price", oldP_selling_price, p_selling_price);
    }

    public String getP_selling_price() {
        return p_selling_price;
    }

    public void setP_availabe(String p_availabe) {
        String oldP_availabe = this.p_availabe;
        this.p_availabe = super.getValue(p_availabe);
        _propertyChangeSupport.firePropertyChange("p_availabe", oldP_availabe, p_availabe);
    }

    public String getP_availabe() {
        AEntity ent = new AEntity();
        p_availabe = ent.getValue(p_availabe);
        if (p_availabe.equals(null) || p_availabe.equals("") ) {
            p_availabe = "N/A";   
            Date today = Calendar.getInstance().getTime();
            String df = "dd-MM-YYYY";
      //      p_availabe = df.format(today);
        }
        return p_availabe;
    }

    public void setP_available_date(String p_available_date) {
        String oldP_available_date = this.p_available_date;
        this.p_available_date = super.getValue(p_available_date);
        _propertyChangeSupport.firePropertyChange("p_available_date", oldP_available_date, p_available_date);
    }

    public String getP_available_date() {
        AEntity ent = new AEntity();
        p_available_date = ent.getValue(p_available_date);
        if (p_available_date.equals(null) || p_available_date.equals("") )
            p_available_date = "N/A";
        return p_available_date;
    }

    public void setP_uom(String p_uom) {
        String oldP_uom = this.p_uom;
        this.p_uom = super.getValue(p_uom);
        _propertyChangeSupport.firePropertyChange("p_uom", oldP_uom, p_uom);
    }

    public String getP_uom() {
        AEntity ent = new AEntity();
        p_uom = ent.getValue(p_uom);
        if (p_uom.equals(null) || p_uom.equals("") )
            p_uom = "N/A";
        return p_uom;
    }

    public void setP_atp_flag(String p_atp_flag) {
        String oldP_atp_flag = this.p_atp_flag;
        this.p_atp_flag = super.getValue(p_atp_flag);
        _propertyChangeSupport.firePropertyChange("p_atp_flag", oldP_atp_flag, p_atp_flag);
    }

    public String getP_atp_flag() {
        return p_atp_flag;
    }

    public void setP_discount(String p_discount) {
        String oldP_discount = this.p_discount;
        this.p_discount = super.getValue(p_discount);
        _propertyChangeSupport.firePropertyChange("p_discount", oldP_discount, p_discount);
    }

    public String getP_discount() {
        AEntity ent = new AEntity();
        p_discount = ent.getValue(p_discount);
        if (p_discount.equals(null) || p_discount.equals("") )
            p_discount = "N/A";
        return p_discount;
    }

    public void setP_dis_desc(String p_dis_desc) {
        String oldP_dis_desc = this.p_dis_desc;
        this.p_dis_desc = super.getValue(p_dis_desc);
        _propertyChangeSupport.firePropertyChange("p_dis_desc", oldP_dis_desc, p_dis_desc);
    }

    public String getP_dis_desc() {
        AEntity ent = new AEntity();
        p_dis_desc = ent.getValue(p_dis_desc);
        if (p_dis_desc.equals(null) || p_dis_desc.equals("") )
            p_dis_desc = "N/A";
        return p_dis_desc;
    }

    public void setP_dis_valid_date(String p_dis_valid_date) {
        String oldP_dis_valid_date = this.p_dis_valid_date;
        this.p_dis_valid_date = super.getValue(p_dis_valid_date);
        _propertyChangeSupport.firePropertyChange("p_dis_valid_date", oldP_dis_valid_date, p_dis_valid_date);
    }

    public String getP_dis_valid_date() {
        AEntity ent = new AEntity();
        p_dis_valid_date = ent.getValue(p_dis_valid_date);
        if (p_dis_valid_date.equals(null) || p_dis_valid_date.equals("") )
            p_dis_valid_date = "N/A";
        return p_dis_valid_date;
    }

    public void setP_warehouse(String p_warehouse) {
        String oldP_warehouse = this.p_warehouse;
        this.p_warehouse = super.getValue(p_warehouse);
        _propertyChangeSupport.firePropertyChange("p_warehouse", oldP_warehouse, p_warehouse);
    }

    public String getP_warehouse() {
        AEntity ent = new AEntity();
        p_warehouse = ent.getValue(p_warehouse);
        if (p_warehouse.equals(null) || p_warehouse.equals("") )
            p_warehouse = "N/A";
        return p_warehouse;
    }

    public void setAttribute1(String attribute1) {
        String oldAttribute1 = this.attribute1;
        this.attribute1 = attribute1;
        _propertyChangeSupport.firePropertyChange("attribute1", oldAttribute1, attribute1);
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        String oldAttribute2 = this.attribute2;
        this.attribute2 = attribute2;
        _propertyChangeSupport.firePropertyChange("attribute2", oldAttribute2, attribute2);
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        String oldAttribute3 = this.attribute3;
        this.attribute3 = attribute3;
        _propertyChangeSupport.firePropertyChange("attribute3", oldAttribute3, attribute3);
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        String oldAttribute4 = this.attribute4;
        this.attribute4 = attribute4;
        _propertyChangeSupport.firePropertyChange("attribute4", oldAttribute4, attribute4);
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        String oldAttribute5 = this.attribute5;
        this.attribute5 = attribute5;
        _propertyChangeSupport.firePropertyChange("attribute5", oldAttribute5, attribute5);
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute6(String attribute6) {
        String oldAttribute6 = this.attribute6;
        this.attribute6 = attribute6;
        _propertyChangeSupport.firePropertyChange("attribute6", oldAttribute6, attribute6);
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        String oldAttribute7 = this.attribute7;
        this.attribute7 = attribute7;
        _propertyChangeSupport.firePropertyChange("attribute7", oldAttribute7, attribute7);
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        String oldAttribute8 = this.attribute8;
        this.attribute8 = attribute8;
        _propertyChangeSupport.firePropertyChange("attribute8", oldAttribute8, attribute8);
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        String oldAttribute9 = this.attribute9;
        this.attribute9 = attribute9;
        _propertyChangeSupport.firePropertyChange("attribute9", oldAttribute9, attribute9);
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        String oldAttribute10 = this.attribute10;
        this.attribute10 = attribute10;
        _propertyChangeSupport.firePropertyChange("attribute10", oldAttribute10, attribute10);
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute11(String attribute11) {
        String oldAttribute11 = this.attribute11;
        this.attribute11 = attribute11;
        _propertyChangeSupport.firePropertyChange("attribute11", oldAttribute11, attribute11);
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute12(String attribute12) {
        String oldAttribute12 = this.attribute12;
        this.attribute12 = attribute12;
        _propertyChangeSupport.firePropertyChange("attribute12", oldAttribute12, attribute12);
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute13(String attribute13) {
        String oldAttribute13 = this.attribute13;
        this.attribute13 = attribute13;
        _propertyChangeSupport.firePropertyChange("attribute13", oldAttribute13, attribute13);
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute14(String attribute14) {
        String oldAttribute14 = this.attribute14;
        this.attribute14 = attribute14;
        _propertyChangeSupport.firePropertyChange("attribute14", oldAttribute14, attribute14);
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute15(String attribute15) {
        String oldAttribute15 = this.attribute15;
        this.attribute15 = attribute15;
        _propertyChangeSupport.firePropertyChange("attribute15", oldAttribute15, attribute15);
    }

    public String getAttribute15() {
        return attribute15;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
