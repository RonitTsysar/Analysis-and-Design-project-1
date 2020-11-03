import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private String id;
    private String billing_address;
    private boolean is_closed;
    private Date open;
    private Date closed;
    private int balance;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private Order lastOrder;

    private List<Payment> payments;

    private List<Order> orders;
    public static Account accountFactory(String id, String billingAddress, Customer customer){
        Account account = new Account(id, billingAddress, customer);
        account.shoppingCart = createShoppingCart(account);

        return account;
    }

    protected Account(String id, String billingAddress, Customer customer){

        this.id = id;
        this.billing_address = billingAddress;
        this.is_closed = false;
        //TODO - Date open
        //       Date closed
        //       balance
        this.shoppingCart = null;
        this.customer = customer;
        payments = new ArrayList<Payment>();
        orders = new ArrayList<Order>();
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }

    public String getId() {
        return id;
    }

    private static ShoppingCart createShoppingCart(Account account) {
        ShoppingCart shoppingCart = ShoppingCart.shoppingCartFactory(account);
        account.shoppingCart = shoppingCart;

        return shoppingCart;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public boolean addShoppingCart(ShoppingCart shoppingCart) {
        if(this.shoppingCart == null && shoppingCart != null){
            setShoppingCart(shoppingCart);
            return true;
        }
        return false;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public boolean isPremium(){
        return false;
    }

    public void displayProductsToSell(){
        System.out.println("I can't sell anything because I'm not a Premium account");
    }

    public List<Product> getProducts(){
        System.out.println("This Account can't sell Products");
        return null;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void delete() {
        if(this.customer != null)
            this.customer.delete();
        for (Payment payment : payments) {
            payment.delete();
        }
        this.payments = null;
        for (Order order : orders) {
            order.delete();
        }
        this.orders = null;
    }

    public void showDetailsAndConnections() {
        //TODO: print all attributes and connections
    }

    public void showLastOrder() {
        System.out.println(this.lastOrder);
    }
}