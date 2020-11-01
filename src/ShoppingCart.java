import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private Date created;
    private WebUser webUser;

    private List<LineItem> lineItemList;

    public ShoppingCart() {
         this.created = new Date();
         this.lineItemList = new ArrayList<>();
         this.webUser = null;
    }

    private void addWebUser(WebUser webUser){
        if(webUser == null){
            throw new RuntimeException("webUser can't be null");
        }

        if(this.webUser != null){
            throw new RuntimeException("This shopping cart already has a webUser");
        }
        this.webUser = webUser;
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