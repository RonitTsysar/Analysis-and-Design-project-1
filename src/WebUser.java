import java.util.Scanner;
import java.util.List;

public class WebUser {

    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;
    private ShoppingCart shoppingCart;

	public void setShoppingCart(ShoppingCart shoppingCart) throws Exception {
        if(shoppingCart == null)
            throw new RuntimeException();
        this.shoppingCart = shoppingCart; 
    }

    public WebUser(String login_id ,String password) {
        this.login_id = login_id;
        this.password = password;
        this.state = UserState.New;
        this.customer = createCustomer();
    }

    private Customer createCustomer() {
        
        shoppingCart = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter id: ");
        String id = scanner.nextLine();

        System.out.println("Please enter address: ");
        Address address = new Address(scanner.nextLine());

        System.out.println("Please enter phone: ");
        String phone= scanner.nextLine();

        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        scanner.close();

        //TODO: check if you can send 'this' before WebUser constructor finished
        return (new Customer(id, address, phone, email, this));
    }

    public void displayOwnedItems() {
        List<Order> orders = this.customer.getAccount().getOrders();
        for (Order order : orders) {
            List<LineItem> orderLineItems = order.getLineItems();
            for (LineItem orderLineItem : orderLineItems) {
                System.out.println(orderLineItem);
            }
        }
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer newCustomer) {
        this.customer = newCustomer;
    }

    public boolean addCustomer(Customer newCustomer){
        if(this.customer == null){
            setCustomer(newCustomer);
            return true;
        }
        return false;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

//    public void setShoppingCart(ShoppingCart shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }

    public void removeShoppingCart(){
        this.shoppingCart = null;
    }

    public boolean addShoppingCart(ShoppingCart newCart){
        if(this.shoppingCart == null){
            setShoppingCart(newCart);
            return true;
        }
        return false;
    }
}