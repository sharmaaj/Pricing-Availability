package mobile.datacontrol;

import java.util.Date;

import javax.el.ValueExpression;

import mobile.bean.AEntity;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class AddToCart {
    public AddToCart() {
        super();
    }

    public void addtoCart() {

        String userName = null;
        Number itemNum = null;
        String itemName = null;
        String itemDesc = null;
        Number itemQuantity = null;


        ValueExpression ve = null;

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", Number.class);
        itemNum = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDesc}", String.class);
        itemDesc = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.pnaDashboardPGBean.itemQuantity}", String.class);
        itemName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.customerNumber}", Number.class);
        itemQuantity = (Number) (ve.getValue(AdfmfJavaUtilities.getELContext()));

        String restURI = RestURIs.getPricingInformation();
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"," +
            "\n" + "\"P_ITEM_NAME\" : \"" + itemName + "\"," + "\n" + "\"P_ITEM_DESCRIPTION\" : \"" + itemDesc + "\"," +
            "\n" + "\"P_ITEM_QUANTITY\" : \"" + itemQuantity + "\"\n" + "}";

        System.out.println("paylod is " + payload);
        String jsonArrayAsString = (rcu.invokeUPDATE(restURI, payload)).toString();

    }
}
