package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.SearchHistory;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class SearchHistoryDC {

    private static List<SearchHistory> s_searchHistory;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SearchHistoryDC() {
        super();
    }

    public SearchHistory[] getSearchHistory() {
        ValueExpression ve = null;
        s_searchHistory = new ArrayList<SearchHistory>();
        s_searchHistory.clear();
        SearchHistory[] searchHisArray = null;
        String userName = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String orgId = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.orgId}", String.class);
        orgId = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        orgId = "204";
        /*      String userName = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();*/


        //      if (itemNumber != null && !"".equals(itemNumber)) {

        String restURI = RestURIs.getSearchHistory();
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n" + "\"P_ORG_ID\" : \"" + orgId + "\",\n" + "\"P_USER_ID\" : \"" + userName + "\"\n" + "}";
        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        System.out.println("jsonArrayAsString for Search History-->"+jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parentNode = (JSONObject) jsonObject.get("P_SEARCH_HIS");
            JSONArray nodeArray = parentNode.getJSONArray("P_SEARCH_HIS_ITEM");
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                SearchHistory searchHis = new SearchHistory(temp);
                s_searchHistory.add(searchHis);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        //      }
        searchHisArray = (SearchHistory[]) s_searchHistory.toArray(new SearchHistory[s_searchHistory.size()]);
        if (s_searchHistory.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SearchHistoryServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SearchHistoryServiceResults}", "No Search Results");
        }
        return searchHisArray;
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
    
    public void fetchSearchHistory(){
        this.getSearchHistory();
    }
}
