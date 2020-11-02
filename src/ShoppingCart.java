import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private WebUser webUser;
    private Account account;

    private List<LineItem> lineItemList;

    public static ShoppingCart shoppingCartFactory(WebUser webUser){
        ShoppingCart shoppingCart = new ShoppingCart(webUser);
        shoppingCart.webUser.addShoppingCart(shoppingCart);

        return shoppingCart;
    }
    private ShoppingCart(WebUser webUser) {
         this.created = new Date();
         this.lineItemList = new ArrayList<>();
         this.webUser = webUser;
         this.account = null;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean addAccount(Account account){
        if(this.account == null && account != null)
        {
            setAccount(account);
            return true;
        }
        return false;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }
}