import java.util.ArrayList;
import java.util.List;

public class PremiumAccount extends Account {

    private List<Product> products;

    public PremiumAccount(String accountId,String billingAddress,Customer customer) {
        super(accountId,billingAddress,customer);
        //String accountId,String billingAddress,Customer customer
        this.products = new ArrayList<>();
    }
}
