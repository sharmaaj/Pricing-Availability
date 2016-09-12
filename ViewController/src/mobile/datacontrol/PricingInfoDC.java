package mobile.datacontrol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.el.ValueExpression;

import mobile.bean.AEntity;

import mobile.entity.GetPricingInformation;

import mobile.entity.SearchHistory;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

import pna.mobile.navigation.NavigationListener;

public class PricingInfoDC {

    private static List<GetPricingInformation> s_pricngInfo;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PricingInfoDC() {
        super();
    }

    public GetPricingInformation[] getPricingInformation() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        ValueExpression ve = null;
        s_pricngInfo = new ArrayList<GetPricingInformation>();
        s_pricngInfo.clear();
        GetPricingInformation[] pricngInfoArray = null;
        
        String userId = null;
        String itemNum = null;
        String itemDesc = null;
        Number orgId = null;
        Number quantity = null;
        String custNumber = null;
        String priceList = null;
        Date reqDate = null;
        
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgId}", Number.class);
        orgId = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));
        orgId = 204;
        
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userId = ( (String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemNum = ( (String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDesc}", String.class);
        itemDesc = ( (String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.pnaDashboardPGBean.itemQuantity}", Number.class);
        quantity = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.customerNumber}", String.class);
        custNumber = ( (String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        AEntity ent = new AEntity();
        custNumber = ent.getAttributeValue(custNumber);
        
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.priceList}", String.class);
        priceList = ( (String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
    
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.pnaDashboardPGBean.requestedDate}", Date.class);
        reqDate = (Date) (ve.getValue(AdfmfJavaUtilities.getELContext()));
        
        
        String restURI = RestURIs.getPricingInformation();
        RestServiceManager rcu = new RestServiceManager();
        
        System.out.println("UserId-->"+userId);
        System.out.println("itemNum-->"+itemNum);
        System.out.println("itemDesc-->"+itemDesc);
        System.out.println("orgId-->"+orgId);
        System.out.println("quantity-->"+quantity);
        System.out.println("custNumber-->"+custNumber);
        System.out.println("priceList-->"+priceList);
        System.out.println("reqDate-->"+reqDate);
        
        

        String payload = "{  \n" + "\"P_USER_ID\" : \"" + userId + "\"," +
                            "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"," +
                            "\n" + "\"P_ITEM_DESCRIPTION\" : \"" + itemDesc + "\"," +
                            "\n" + "\"P_ORG_ID\" : \"" + orgId + "\"," +
                            "\n" + "\"P_QUANTITY\" : \"" + quantity + "\"," +
                            "\n" + "\"P_CUSTOMER_NUMBER\" : \"" + custNumber + "\"," +
                            "\n" + "\"P_PRICE_LIST\" : \"" + priceList + "\"," +
                            "\n" + "\"P_REQUESTED_DATE\" : \"" +dateFormat.format(reqDate) + "\"\n" + "}";
        
        
        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        
        System.out.println("jsonArrayAsString-->"+jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_ITEM_DETAIL");
          //  JSONArray nodeArray = parentNode.getJSONArray("P_ITEM_DETAIL");
            int size = parentNode.length();
            /* int size = parentNode.length();
               for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                GetPricingInformation pricngInf = new GetPricingInformation(temp);
                s_pricngInfo.add(pricngInf);
            } */
            GetPricingInformation pricngInf = new GetPricingInformation(parentNode);
            s_pricngInfo.add(pricngInf);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        //      }
        pricngInfoArray =
            (GetPricingInformation[]) s_pricngInfo.toArray(new GetPricingInformation[s_pricngInfo.size()]);
        if (s_pricngInfo.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetPricingInformationServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.GetPricingInformationServiceResults}", "No Search Results");
        }
        return pricngInfoArray;
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        ProviderChangeSupport oldProviderChangeSupport = this.providerChangeSupport;
        this.providerChangeSupport = providerChangeSupport;
        propertyChangeSupport.firePropertyChange("providerChangeSupport", oldProviderChangeSupport,
                                                 providerChangeSupport);
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
    
    public void fetchPricingInformation(){
        this.getPricingInformation();

    }
}
