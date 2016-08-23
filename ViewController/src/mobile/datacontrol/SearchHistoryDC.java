package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.SearchHistory;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class SearchHistoryDC {

    private static List<SearchHistory> s_searchHistory;

    public SearchHistoryDC() {
        super();
    }

    public SearchHistory[] getSearchHistory() {
        ValueExpression ve = null;
        s_searchHistory = new ArrayList<SearchHistory>();
        s_searchHistory.clear();
        SearchHistory[] searchHisArray = null;
        String itemNumber = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemNumber = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String userName = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();


        if (itemNumber != null && !"".equals(itemNumber)) {

            String restURI = RestURIs.getSearchHistory();
            RestServiceManager rcu = new RestServiceManager();
            String payload =
                "{\n" + "\"P_USER_NAME\" : \"" + userName + "\",\n" + "\"P_ITEM_NUMBER\" : \"" + itemNumber + "\"\n" +
                "}";
            System.out.println("paylod is " + payload);
            String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
            try {
                JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                JSONObject parentNode = (JSONObject) jsonObject.get("P_ITEM_DETAIL");
                JSONArray nodeArray = parentNode.getJSONArray("P_ITEM_DETAIL_ITEM_SEARCH");
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
        }
        searchHisArray = (SearchHistory[]) s_searchHistory.toArray(new SearchHistory[s_searchHistory.size()]);
        if (s_searchHistory.size() != 0) {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SearchHistoryServiceResults}", "");
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.SearchHistoryServiceResults}", "No Search Results");
        }
        return searchHisArray;
    }
}
