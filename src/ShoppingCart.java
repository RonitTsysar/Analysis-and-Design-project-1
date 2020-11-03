import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private WebUser webUser;
    private Account account;

    private List<LineItem> lineItemList;

    public static ShoppingCart shoppingCartFactory(Account account){
        ShoppingCart shoppingCart = new ShoppingCart(account);
        shoppingCart.webUser.addShoppingCart(shoppingCart);
        shoppingCart.account = account;

        return shoppingCart;
    }
    private ShoppingCart(Account account) {
         this.created = new Date();
         this.lineItemList = new ArrayList<>();
         this.webUser = account.getCustomer().getWebUser();
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

    public boolean addLineItem(LineItem lineItem){
        if(lineItem == null){
            return false;
        }
        this.lineItemList.add(lineItem);
        lineItem.setShoppingCart(this);
        return true;
    }
}