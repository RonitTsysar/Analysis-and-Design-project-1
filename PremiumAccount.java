import java.util.ArrayList;
import java.util.List;

public class PremiumAccount extends Account {

    private List<Product> products;

    public PremiumAccount(String id) {
        super(id);
        this.products = new ArrayList<>();
    }
}
