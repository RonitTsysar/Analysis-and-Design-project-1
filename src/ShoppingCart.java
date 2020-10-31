import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private WebUser webUser;

    public ShoppingCart(Account account,WebUser webUser) {
//        this.created = Date.now();
        this.account=account;
        this.webUser=webUser;

        boolean webUserAdded = addWebUser(webUser);
//        if(!webUserAdded){
//            throw new RuntimeException("WebUser already exist");
//        }
    }

    private boolean addWebUser(WebUser webUser) {
        if(this.webUser == null){
            setWebUser(webUser);
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
}