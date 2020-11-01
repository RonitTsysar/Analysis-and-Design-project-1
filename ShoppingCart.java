import java.util.Date;

public class ShoppingCart {
    private Date created;
    private WebUser webUser;

    public ShoppingCart(WebUser webUser) throws Exception {
        
        //TODO - Date created
        this.webUser=webUser;

        this.webUser = webUser;
        webUser.setShoppingCart(this);
    }
}