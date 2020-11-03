import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PremiumAccount extends Account {

    private List<Product> products;

    public static PremiumAccount PremiumAccountFactory(String id, String billingAddress, Customer customer){
        PremiumAccount premiumAccount = new PremiumAccount(id, billingAddress, customer);

        return premiumAccount;
    }

    private PremiumAccount(String id, String billingAddress, Customer customer){
        super(id, billingAddress, customer);

        this.products = new ArrayList<>();
    }

    public Collection<Product> getProductsList() {
        return this.products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
