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

    private List<Payment> payments;
    private List<Order> orders;

    public Account(String id, String billingAddress, Customer customer) throws Exception {
        
        this.id = id;
        this.billing_address = billingAddress;
        this.is_closed = false;
        //TODO - Date open
        //       Date closed
        //       balance
        this.customer = customer;
        this.shoppingCart = new ShoppingCart();
        
        payments = new ArrayList<Payment>();
        orders = new ArrayList<Order>();
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart=shoppingCart;
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}