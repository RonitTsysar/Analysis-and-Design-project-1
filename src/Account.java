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

    public boolean getIsClosed() {
        return is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public Date getClosed() {
        return closed;
    }

    public int getBalance() {
        return balance;
    }

    public void delete() {
        if(this.customer != null) {
            this.customer.delete();
            this.customer = null;
        }
        for (Payment payment : payments) {
            payment.delete();
        }
        this.payments = null;
        for (Order order : orders) {
            order.delete();
        }
        this.orders = null;
        this.shoppingCart=null;


    }

    public void showDetailsAndConnections() {
        System.out.println("----------Account---------\n *** Attributes: ***");
        System.out.println("id: "+id+"\n"
                +"billing_address: "+billing_address+"\n"
                +"is_closed: "+is_closed+"\n"
                +"open: "+open+"\n"
                +"closed: "+closed+"\n"
                +"balance: "+balance);
        System.out.println("*** Connections: ***");
        System.out.println("ShoppingCart Creation date: "+shoppingCart.getCreated()+"\n"
                +"Payments: "+getPaymentsIdList()+"\n"
                +"Orders: "+getOrderNumberList()+"\n\n");
    }

    public String getOrderNumberList() {
        String ordersNumbers="";
        if(orders==null) return "order list empty";
        for (Order order:orders) {
            ordersNumbers+=order.getNumber()+"\n";
        }
        return ordersNumbers;
    }

    public void showLastOrder() {
        System.out.println(this.lastOrder);
    }

    public String getPaymentsIdList()
    {
        String paymentsIds="";
        if(payments==null) return "payment list empty";
        for (Payment payment:payments) {
            paymentsIds+=payment.getPaymentId()+"\n";
        }
        return paymentsIds;
    }
}