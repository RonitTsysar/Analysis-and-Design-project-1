import java.util.Date;

public class ShoppingCart {
    private Date created;

    private WebUser webUser;

    public ShoppingCart(Date created, WebUser webUser) {
        this.created = created;

        boolean webUserAdded = addWebUser(webUser);
        if(!webUserAdded){
            throw new RuntimeException("WebUser already exist");
        }
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
