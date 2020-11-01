import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String id;
    private String name;
    private List<Product> products;

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;

        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
