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

    public static Account accountFactory(String id, String billingAddress, Customer customer){
        Account account = new Account(id, billingAddress, customer);
        ShoppingCart shoppingCart = ShoppingCart.shoppingCartFactory(customer.getWebUser());
        shoppingCart.addAccount(account);
        account.shoppingCart = shoppingCart;
        //no need to set account's customer!!
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
}