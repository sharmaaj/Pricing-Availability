package mobile.bean;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import mobile.PnABeanClass;

import mobile.datacontrol.PricingInfoDC;
import mobile.datacontrol.SearchHistoryDC;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class pnaDashboardPGBean {
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public pnaDashboardPGBean() {
        super();
    }
    
    private  Boolean myNewSearchFlag = true;
    private Boolean searchHistoryFlag = false;
    private String newSearchTabClass = "sub-header";
    private String searchHistoryTabClass = "sub-header-disable";
    private Number itemNumber;
    private Number itemQuantity = 1;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
    Date date = new Date();
  //  private String defaultDate = dateFormat.format(date);
    
    DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
    Date today = Calendar.getInstance().getTime();
    private String defaultDate = df.format(today);


    public void setDefaultDate(String defaultDate) {
        String oldDefaultDate = this.defaultDate;
        this.defaultDate = defaultDate;
        _propertyChangeSupport.firePropertyChange("defaultDate", oldDefaultDate, defaultDate);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.defaultDate}", dateFormat.format(date));
        
        System.out.println("Default date is-->"+defaultDate);
    }

    public String getDefaultDate() {
        return defaultDate;
    }

    public void setNewSearchTabClass(String newSearchTabClass) {
        String oldNewSearchTabClass = this.newSearchTabClass;
        this.newSearchTabClass = newSearchTabClass;
        _propertyChangeSupport.firePropertyChange("newSearchTabClass", oldNewSearchTabClass, newSearchTabClass);
        
    }

    public String getNewSearchTabClass() {
        return newSearchTabClass;
    }

    public void setSearchHistoryTabClass(String searchHistoryTabClass) {
        String oldSearchHistoryTabClass = this.searchHistoryTabClass;
        this.searchHistoryTabClass = searchHistoryTabClass;
        _propertyChangeSupport.firePropertyChange("searchHistoryTabClass", oldSearchHistoryTabClass,
                                                  searchHistoryTabClass);
    }

    public String getSearchHistoryTabClass() {
        return searchHistoryTabClass;
    }
    private Number priceList;
    private String itemDesc;
    private String cutomerNumber;
    private Date requestedDate = date;

    public void setRequestedDate(Date requestedDate) {
      //  if (requestedDate == null)
      //      AdfmfJavaUtilities.setELValue("#{pageFlowScope.requestedDate}", dateFormat.format(date));
     //       requestedDate = date;
        this.requestedDate = requestedDate;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }
    

    public void setItemNumber(Number itemNumber) {
        this.itemNumber = itemNumber;
        
    }

    public Number getItemNumber() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemNumber}",itemNumber);
        return itemNumber;
    }

    public void setItemQuantity(Number itemQuantity) {
        if (itemQuantity == null)
            itemQuantity = 1;
        this.itemQuantity = itemQuantity;
    }

    public Number getItemQuantity() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemQuantity}",itemQuantity);
        return itemQuantity;
    }

    public void setPriceList(Number priceList) {
        this.priceList = priceList;
    }

    public Number getPriceList() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.priceList}",priceList);
        return priceList;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemDesc}",itemDesc);
        return itemDesc;
    }

    public void setCutomerNumber(String cutomerNumber) {
        this.cutomerNumber = cutomerNumber;
    }

    public String getCutomerNumber() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.cutomerNumber}",cutomerNumber);
        return cutomerNumber;
    }

     public void setMyNewSearchFlag(Boolean myNewSearchFlag) {
         this.myNewSearchFlag = myNewSearchFlag;
     }

     public Boolean getMyNewSearchFlag() {
         return myNewSearchFlag;
     }

     public void setSearchHistoryFlag(Boolean searchHistoryFlag) {
         this.searchHistoryFlag = searchHistoryFlag;
     }

     public Boolean getSearchHistoryFlag() {
         return searchHistoryFlag;
     }

     public void whenMyNewSearchTabClicked(ActionEvent actionEvent) {
         myNewSearchFlag = true;
         searchHistoryFlag = false;
     }

     public void whenSearchHistoryTabClicked(ActionEvent actionEvent) {
         myNewSearchFlag = false;
         searchHistoryFlag = true;
         
     }

  /*  public void onSearchButtonClick() {
        getPricingInformation();
    }  */

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void getSearchHistory(ActionEvent actionEvent) {
        SearchHistoryDC scd = new SearchHistoryDC();
        scd.fetchSearchHistory();
    }
    
    public void signOut(){
        AdfmfJavaUtilities.logout();
        AdfmfContainerUtilities.gotoFeature("dashboard");
    }
   
}
