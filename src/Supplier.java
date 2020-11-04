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

    public boolean removeProduct(Product productToRemove){
        for (Product product : products) {
            if(product.getId().equals(productToRemove.getId())){
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public void showDetailsAndConnections() {
        System.out.println("----------Supplier---------\n *** Attributes: ***");
        System.out.println("id: "+getId()+"\n"
                +"name: "+getName()+"\n");
        System.out.println("*** Connections: ***");
        System.out.println("Product Names: "+getProductsNamesList()+"\n\n");
    }

    public String getProductsNamesList() {
        String productsNames="";
        if(products==null) return "product list empty";
        for (Product product:products) {
            productsNames+=product.getName()+"\n";
        }
        return productsNames;
    }
}
