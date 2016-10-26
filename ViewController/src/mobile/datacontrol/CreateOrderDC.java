package mobile.datacontrol;

import javax.el.ValueExpression;

import mobile.rest.RestServiceManager;
import mobile.rest.RestURIs;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class CreateOrderDC {
    public CreateOrderDC() {
        super();
    }
    
    public void createNewOrder() {

        String userName = null;
        String itemNum = null;
        String itemName = null;
        String itemDesc = null;
        String itemQuantity = null;
        String listPrice = null;


        ValueExpression ve = null;
        
        System.out.println("Here 1");

        ve = AdfmfJavaUtilities.getValueExpression("#{securityContext.userName}", String.class);
        userName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        
        System.out.println("Here 2-->"+userName);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.searchKeyword}", String.class);
        itemNum = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        System.out.println("Here 3-->"+itemNum);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDesc}", String.class);
        itemDesc = ((String) ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        System.out.println("Here 4-->"+itemDesc);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemDesc}", String.class);
        itemName = ((String) ve.getValue(AdfmfJavaUtilities.getELContext()));
        System.out.println("Here 5-->"+itemName);

        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.itemQuantity}", String.class);
        itemQuantity = ((String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        System.out.println("Here 6-->"+itemQuantity);
        
        String listPriceInString = null;
        ve = AdfmfJavaUtilities.getValueExpression("#{pageFlowScope.listPrice}", String.class);
        listPriceInString = ((String)ve.getValue(AdfmfJavaUtilities.getELContext())).trim();
        listPrice = listPriceInString;
        System.out.println("Here 7 listPriceInString-->"+listPriceInString);
        
        //listPrice = Integer.parseInt(listPriceInString);
        
        //System.out.println("Here 8 listPrice in Integer-->"+Integer.parseInt(listPriceInString));

        String restURI = RestURIs.addToCart();
        System.out.println("restURI is " + restURI);
        RestServiceManager rcu = new RestServiceManager();

        String payload =
            "{  \n" + "\"P_USER_NAME\" : \"" + userName + "\"," + "\n" + "\"P_ITEM_NUMBER\" : \"" + itemNum + "\"," +
            "\n" + "\"P_ITEM_NAME\" : \"" + itemName + "\"," + "\n" + "\"P_ITEM_DESCRIPTION\" : \"" + itemDesc + "\"," +
            "\n" + "\"P_ITEM_QUANTITY\" : \"" + itemQuantity + "\"," + "\n" + "\"P_LIST_PRICE\" : \"" + listPrice +"\"\n" + "}";
        
         
        System.out.println("paylod is " + payload);
        (rcu.invokeUPDATE(restURI, payload)).toString();
        
        System.out.println("Here 9-->");

    }
}
