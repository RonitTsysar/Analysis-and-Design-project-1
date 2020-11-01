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

    public Account(String accountId, String billingAddress, Customer customer){
        this.id=accountId;
        this.billing_address=billingAddress;
        this.is_closed=false;
//        this.open = LocalDateTime.now();
        this.closed=null;
        this.balance=0;
        this.customer=customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart=shoppingCart;
    }

    public List<Order> getOrders() {
        return this.orders;
    }
}