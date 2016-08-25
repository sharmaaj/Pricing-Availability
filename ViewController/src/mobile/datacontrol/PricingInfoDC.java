package mobile.datacontrol;

import java.util.ArrayList;

import java.util.List;

import javax.el.ValueExpression;

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

public class PricingInfoDC {

    private static List<GetPricingInformation> s_pricngInfo;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PricingInfoDC() {
        super();
    }

    public GetPricingInformation[] getPricingInformation() {
        ValueExpression ve = null;
        s_pricngInfo = new ArrayList<GetPricingInformation>();
        s_pricngInfo.clear();
        GetPricingInformation[] pricngInfoArray = null;
        String userId = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.userName}", String.class);
        userId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String orgId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        orgId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String restURI = RestURIs.getPricingInformation();
        RestServiceManager rcu = new RestServiceManager();

        String payload = "{\n" + "\"P_ORG_ID\" : \"" + 201 + "\",\n" + "\"P_USER_ID\" : \"" + userId + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_SEARCH_HIS");
            JSONArray nodeArray = parentNode.getJSONArray("P_SEARCH_HIS_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                GetPricingInformation pricngInf = new GetPricingInformation(temp);
                s_pricngInfo.add(pricngInf);
            }
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
