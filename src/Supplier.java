package src;

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
}
