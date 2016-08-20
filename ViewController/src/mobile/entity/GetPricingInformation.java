package mobile.entity;

import java.util.Date;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class GetPricingInformation {
    
     private String p_item_description  ;
     private String p_item_quantity  ;
     private Date p_request_date  ;
     private String p_customer_number  ;
     private Number created_by  ;
     private Number last_updated_by  ;
     private Date last_updated_date  ;
     private Date creation_date  ;
     private Number last_update_login  ;
     private Number p_price_list  ;
     private String p_item_number  ;
     private String p_currency  ;
     private Number p_selling_price  ;
     private Number p_availabe  ;
     private Date p_available_date  ;
     private String p_uom  ;
     private String p_atp_flag  ;
     private String p_discount  ;
     private String p_dis_desc  ;
     private Date p_dis_valid_date;
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
    
    public Object clone() {

        GetPricingInformation getPricingInfo = new GetPricingInformation();
        getPricingInfo.setP_item_description(p_item_description);
        getPricingInfo.setP_item_quantity(p_item_quantity);
        getPricingInfo.setP_request_date(p_request_date);
        
        getPricingInfo.setP_customer_number(p_customer_number);
        getPricingInfo.setCreated_by(created_by);
        getPricingInfo.setLast_updated_by(last_updated_by);
        getPricingInfo.setLast_updated_date(last_updated_date);
        getPricingInfo.setCreation_date(creation_date);
        getPricingInfo.setLast_update_login(last_update_login);
        getPricingInfo.setP_price_list(p_price_list);
        getPricingInfo.setP_item_number(p_item_number);
        getPricingInfo.setP_currency(p_currency);
        getPricingInfo.setP_selling_price(p_selling_price);
        getPricingInfo.setP_availabe(p_availabe);
        getPricingInfo.setP_available_date(p_available_date);
        getPricingInfo.setP_uom(p_uom);
        getPricingInfo.setP_atp_flag(p_atp_flag);
        getPricingInfo.setP_discount(p_discount);
        getPricingInfo.setP_dis_desc(p_dis_desc);
        getPricingInfo.setP_dis_valid_date(p_dis_valid_date);
        getPricingInfo.setP_warehouse(p_warehouse);
        getPricingInfo.setAttribute1(attribute1);
        getPricingInfo.setAttribute2(attribute2);
        getPricingInfo.setAttribute3(attribute3);
        getPricingInfo.setAttribute4(attribute4);
        getPricingInfo.setAttribute5(attribute5);
        getPricingInfo.setAttribute6(attribute6);
        getPricingInfo.setAttribute7(attribute7);
        getPricingInfo.setAttribute8(attribute8);
        getPricingInfo.setAttribute9(attribute9);
        getPricingInfo.setAttribute10(attribute10);
        getPricingInfo.setAttribute11(attribute11);
        getPricingInfo.setAttribute12(attribute12);
        getPricingInfo.setAttribute13(attribute13);
        getPricingInfo.setAttribute14(attribute14);
        getPricingInfo.setAttribute15(attribute15);
        return getPricingInfo;
    }

    public void setP_item_description(String p_item_description) {
        String oldP_item_description = this.p_item_description;
        this.p_item_description = p_item_description;
        _propertyChangeSupport.firePropertyChange("p_item_description", oldP_item_description, p_item_description);
    }

    public String getP_item_description() {
        return p_item_description;
    }

    public void setP_item_quantity(String p_item_quantity) {
        String oldP_item_quantity = this.p_item_quantity;
        this.p_item_quantity = p_item_quantity;
        _propertyChangeSupport.firePropertyChange("p_item_quantity", oldP_item_quantity, p_item_quantity);
    }

    public String getP_item_quantity() {
        return p_item_quantity;
    }

    public void setP_request_date(Date p_request_date) {
        Date oldP_request_date = this.p_request_date;
        this.p_request_date = p_request_date;
        _propertyChangeSupport.firePropertyChange("p_request_date", oldP_request_date, p_request_date);
    }

    public Date getP_request_date() {
        return p_request_date;
    }

    public void setP_customer_number(String p_customer_number) {
        String oldP_customer_number = this.p_customer_number;
        this.p_customer_number = p_customer_number;
        _propertyChangeSupport.firePropertyChange("p_customer_number", oldP_customer_number, p_customer_number);
    }

    public String getP_customer_number() {
        return p_customer_number;
    }

    public void setCreated_by(Number created_by) {
        Number oldCreated_by = this.created_by;
        this.created_by = created_by;
        _propertyChangeSupport.firePropertyChange("created_by", oldCreated_by, created_by);
    }

    public Number getCreated_by() {
        return created_by;
    }

    public void setLast_updated_by(Number last_updated_by) {
        Number oldLast_updated_by = this.last_updated_by;
        this.last_updated_by = last_updated_by;
        _propertyChangeSupport.firePropertyChange("last_updated_by", oldLast_updated_by, last_updated_by);
    }

    public Number getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_date(Date last_updated_date) {
        Date oldLast_updated_date = this.last_updated_date;
        this.last_updated_date = last_updated_date;
        _propertyChangeSupport.firePropertyChange("last_updated_date", oldLast_updated_date, last_updated_date);
    }

    public Date getLast_updated_date() {
        return last_updated_date;
    }

    public void setCreation_date(Date creation_date) {
        Date oldCreation_date = this.creation_date;
        this.creation_date = creation_date;
        _propertyChangeSupport.firePropertyChange("creation_date", oldCreation_date, creation_date);
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setLast_update_login(Number last_update_login) {
        Number oldLast_update_login = this.last_update_login;
        this.last_update_login = last_update_login;
        _propertyChangeSupport.firePropertyChange("last_update_login", oldLast_update_login, last_update_login);
    }

    public Number getLast_update_login() {
        return last_update_login;
    }

    public void setP_price_list(Number p_price_list) {
        Number oldP_price_list = this.p_price_list;
        this.p_price_list = p_price_list;
        _propertyChangeSupport.firePropertyChange("p_price_list", oldP_price_list, p_price_list);
    }

    public Number getP_price_list() {
        return p_price_list;
    }

    public void setP_item_number(String p_item_number) {
        String oldP_item_number = this.p_item_number;
        this.p_item_number = p_item_number;
        _propertyChangeSupport.firePropertyChange("p_item_number", oldP_item_number, p_item_number);
    }

    public String getP_item_number() {
        return p_item_number;
    }

    public void setP_currency(String p_currency) {
        String oldP_currency = this.p_currency;
        this.p_currency = p_currency;
        _propertyChangeSupport.firePropertyChange("p_currency", oldP_currency, p_currency);
    }

    public String getP_currency() {
        return p_currency;
    }

    public void setP_selling_price(Number p_selling_price) {
        Number oldP_selling_price = this.p_selling_price;
        this.p_selling_price = p_selling_price;
        _propertyChangeSupport.firePropertyChange("p_selling_price", oldP_selling_price, p_selling_price);
    }

    public Number getP_selling_price() {
        return p_selling_price;
    }

    public void setP_availabe(Number p_availabe) {
        Number oldP_availabe = this.p_availabe;
        this.p_availabe = p_availabe;
        _propertyChangeSupport.firePropertyChange("p_availabe", oldP_availabe, p_availabe);
    }

    public Number getP_availabe() {
        return p_availabe;
    }

    public void setP_available_date(Date p_available_date) {
        Date oldP_available_date = this.p_available_date;
        this.p_available_date = p_available_date;
        _propertyChangeSupport.firePropertyChange("p_available_date", oldP_available_date, p_available_date);
    }

    public Date getP_available_date() {
        return p_available_date;
    }

    public void setP_uom(String p_uom) {
        String oldP_uom = this.p_uom;
        this.p_uom = p_uom;
        _propertyChangeSupport.firePropertyChange("p_uom", oldP_uom, p_uom);
    }

    public String getP_uom() {
        return p_uom;
    }

    public void setP_atp_flag(String p_atp_flag) {
        String oldP_atp_flag = this.p_atp_flag;
        this.p_atp_flag = p_atp_flag;
        _propertyChangeSupport.firePropertyChange("p_atp_flag", oldP_atp_flag, p_atp_flag);
    }

    public String getP_atp_flag() {
        return p_atp_flag;
    }

    public void setP_discount(String p_discount) {
        String oldP_discount = this.p_discount;
        this.p_discount = p_discount;
        _propertyChangeSupport.firePropertyChange("p_discount", oldP_discount, p_discount);
    }

    public String getP_discount() {
        return p_discount;
    }

    public void setP_dis_desc(String p_dis_desc) {
        String oldP_dis_desc = this.p_dis_desc;
        this.p_dis_desc = p_dis_desc;
        _propertyChangeSupport.firePropertyChange("p_dis_desc", oldP_dis_desc, p_dis_desc);
    }

    public String getP_dis_desc() {
        return p_dis_desc;
    }

    public void setP_dis_valid_date(Date p_dis_valid_date) {
        Date oldP_dis_valid_date = this.p_dis_valid_date;
        this.p_dis_valid_date = p_dis_valid_date;
        _propertyChangeSupport.firePropertyChange("p_dis_valid_date", oldP_dis_valid_date, p_dis_valid_date);
    }

    public Date getP_dis_valid_date() {
        return p_dis_valid_date;
    }

    public void setP_warehouse(String p_warehouse) {
        String oldP_warehouse = this.p_warehouse;
        this.p_warehouse = p_warehouse;
        _propertyChangeSupport.firePropertyChange("p_warehouse", oldP_warehouse, p_warehouse);
    }

    public String getP_warehouse() {
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
