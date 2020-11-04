import java.util.ArrayList;
import java.util.List;

public class PremiumAccount extends Account {

    private List<Product> products;

    public static PremiumAccount PremiumAccountFactory(String id, String billingAddress, Customer customer){
        PremiumAccount premiumAccount = new PremiumAccount(id, billingAddress, customer);

        return premiumAccount;
    }

    private PremiumAccount(String id, String billingAddress, Customer customer){
        super(id, billingAddress, customer);
        this.setShoppingCart(ShoppingCart.shoppingCartFactory(this));
        this.products = new ArrayList<>();
    }

    public List<Product> getProductsList() {
        return this.products;
    }

    @Override
    public boolean isPremium(){
        return true;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void displayProductsToSell(){
        for (Product product : products) {
            product.showLineItems();
        }
    }

    public boolean addProduct(Product productToAdd){
        for (Product product : products) {
            if(product.getId().equals(productToAdd.getId())) {
                System.out.println("This product is already exist");
                return false;
            }
        }
        this.products.add(productToAdd);
        if(productToAdd.getPremiumAccount() != null)
            productToAdd.setPremiumAccount(this);
        return true;
    }
    public void showDetailsAndConnections() {
        System.out.println("----------Premium Account---------\n *** Attributes: ***");
        System.out.println("id: "+getId()+"\n"
                +"billing_address: "+getBilling_address()+"\n"
                +"is_closed: "+getIsClosed()+"\n"
                +"open: "+getOpen()+"\n"
                +"closed: "+getClosed()+"\n"
                +"balance: "+getBalance());
        System.out.println("*** Connections: ***");
        System.out.println("ShoppingCart Creation date: "+getShoppingCart().getCreated()+"\n"
                +"Payments: "+getPaymentsIdList()+"\n"
                +"Orders: "+getOrderNumberList()+"\n"
                +"Products: "+getProductsNameList()+"\n\n");
    }

    private String getProductsNameList() {
        String productsIds="";
        if(products == null) return "product list empty";
        for (Product product:products) {
            productsIds+=product.getName()+"\n";
        }
        return productsIds;
    }

    public void delete(){
            super.delete();
            for (Product prod : getProductsList()) {
                prod.setPremiumAccount(null);
            }
//            setProducts(null);
        }
}

