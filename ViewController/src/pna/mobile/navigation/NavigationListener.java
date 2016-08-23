package pna.mobile.navigation;

import mobile.datacontrol.CheckUserDC;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class NavigationListener {
    public NavigationListener() {
    }

    public String validateAndNavigateItem() {
        // Add event code here...
        String item =
            AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchKeyword}") == null ? "" :
            AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchKeyword}").toString();
        if (item.length() < 3) {

            AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(), "showAlert",
                                                                      new Object[] { "Error",
                                                                                     "Please enter atleast 3 characters for Item.",
                                                                                     "Ok" });
            return "";
        } else
            return "itemLov";
    }
    
    public String validateAndNavigateUserType(){
     CheckUserDC  cud = new CheckUserDC();
     cud.getUserTypeInfo();
     
        String userTypeInfo =
            AdfmfJavaUtilities.getELValue("#{sessionScope.UserType}") == null ? "" :
            AdfmfJavaUtilities.getELValue("#{sessionScope.UserType}").toString();
        
            if(userTypeInfo.equalsIgnoreCase("INTERNAL"))
                return "internalUser";
            else if (userTypeInfo.equalsIgnoreCase("EXTERNAL"))
               return "externalUser";
            else
                return "noUserFound";
     
    }
}
