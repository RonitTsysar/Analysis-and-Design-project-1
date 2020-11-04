import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;

    private Supplier supplier;
    private PremiumAccount premiumAccount;
    private List<LineItem> lineItemsList;



    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;

        this.premiumAccount = null;
        this.supplier = supplier;
        this.lineItemsList = new ArrayList<>();

    }

    public List<LineItem> getLineItemsList() { return lineItemsList; }

    public String getId() {
        return id;
    }

    public void showLineItems(){
        if(this.lineItemsList.size() < 1)
            System.out.println("No Line Items of this product");
        else{
            for (LineItem lineItem : lineItemsList) {
                System.out.println(lineItemsList.indexOf(lineItem)+1 + ". "  + "Quantity: " +lineItem.getQuantity() + "Price per Item: " + lineItem.getPrice());
            }
        }
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;

    }

    @Override
    public String toString() {
        return "Product: " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void delete(){
        for (LineItem lineItem : lineItemsList) {
            lineItem.delete();
        }
        this.lineItemsList = null;
        this.supplier.removeProduct(this);
    }

    public void showDetailsAndConnections() {
        //TODO: print all attributes and connections
    }
}
