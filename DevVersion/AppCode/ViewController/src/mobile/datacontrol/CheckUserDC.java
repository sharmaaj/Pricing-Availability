package mobile.datacontrol;

import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;

import mobile.entity.CheckUser;
import mobile.entity.ItemEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class CheckUserDC {
    
    private static List<CheckUser> s_CheckUserList;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public CheckUserDC() {
        super();
    }
    
    public void getUserTypeInfo() {
        ValueExpression ve = null;
        s_CheckUserList = new ArrayList<CheckUser>();
        s_CheckUserList.clear();
        CheckUser[] userTypeArray = null;
        String userName = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        String restURI = RestURIs.checkUserType();
        RestServiceManager rcu = new RestServiceManager();
        String payload = "{\n"  + "\"PIV_USER_NAME\" : \"" +userName + "\"\n" +  "}";
        System.out.println("paylod is "+payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();
        String status = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
//            JSONObject parentNode = (JSONObject) jsonObject.get("POUT_STATUS");
            status = jsonObject.getString("POUT_STATUS");
//            JSONArray nodeArray = parentNode.getJSONArray("POUT_STATUS_USER");
//            int size = nodeArray.length();
//            for (int i = 0; i < size; i++) {
//                JSONObject temp = nodeArray.getJSONObject(i);
//                CheckUser checkUsr = new CheckUser(temp);
//                s_CheckUserList.add(checkUsr);
//            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        if(status.equalsIgnoreCase("INTERNAL_USER")){
            AdfmfJavaUtilities.setELValue("#{applicationScope.UserType}", "INTERNAL");
        }else if (status.equalsIgnoreCase("EXTERNAL_USER")){
            AdfmfJavaUtilities.setELValue("#{applicationScope.UserType}", "EXTERNAL");
        }else {
            AdfmfJavaUtilities.setELValue("#{applicationScope.UserType}", "INVALID_USER");
            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert",
                                                                      new Object[] { "Error",
                                                                                     "Invalid User for PNA App.",
                                                                                     "Ok" });
            AdfmfJavaUtilities.logout();
            AdfmfContainerUtilities.gotoFeature("dashboard");
        }
            
//        userTypeArray = (CheckUser[]) s_CheckUserList.toArray(new CheckUser[s_CheckUserList.size()]);
//        
//        if (s_CheckUserList.size() != 0) {   
//          if(s_CheckUserList.get(0).getUserTypeStatus().equalsIgnoreCase("SUCCESS"))
//            AdfmfJavaUtilities.setELValue("#{sessionScope.UserType}", "INTERNAL");
//          else if (s_CheckUserList.get(0).getUserTypeStatus().equalsIgnoreCase("FAILURE"))
//            AdfmfJavaUtilities.setELValue("#{sessionScope.UserType}", "EXTERNAL");
//        } else {
//            AdfmfJavaUtilities.setELValue("#{pageFlowScope.UserType}", "USER NOT FOUND");
//        }
    //    return userTypeArray[0].getUserTypeStatus().toString();
    }

    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        this.providerChangeSupport = providerChangeSupport;
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
    
     public void signOut(){
         AdfmfJavaUtilities.logout();
         AdfmfContainerUtilities.gotoFeature("dashboard");
     }

 }
