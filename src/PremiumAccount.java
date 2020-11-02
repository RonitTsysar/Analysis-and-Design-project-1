import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PremiumAccount extends Account {

    private List<Product> products;

    public PremiumAccount(String id, String billingAddress, Customer customer) {
        super(id, billingAddress, customer);
        //String accountId,String billingAddress,Customer customer
        this.products = new ArrayList<>();
    }

    public Collection<Product> getProductsList() {
        //added by Lior
        return this.products;
    }

    /*public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
}
