import java.util.Scanner;
public class Customer {

    private String id;
    private Address address;
    private String phone;
    private String email;
    private WebUser webUser;
    private Account account;

    public WebUser getWebUser() {return this.webUser;}
    public Account getAccount() {return this.account;}

    public static Customer customerFactory(String id, Address address, String phone, String email, WebUser webUser, boolean fromStartUp){
        Customer customer = new Customer(id, address, phone, email, webUser);

        if(!fromStartUp)
            customer.account = createAccount(customer);

        return customer;
    }

    private Customer(String id, Address address, String phone, String email, WebUser webUser) {
        
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webUser = webUser;
        this.account = null;
    }

    private static Account createAccount(Customer customer){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter billing address:");
        String billingAddress = scanner.nextLine();

        System.out.println("Are you a Premium Account? Please enter yes/no:");
        if(scanner.nextLine().equals("yes")) {
            return (PremiumAccount.PremiumAccountFactory(customer.id, billingAddress, customer));
        }
        else {
            return (Account.accountFactory(customer.id, billingAddress, customer));
        }
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public boolean addAccount(Account account){
        if(this.account == null && account != null){
            setAccount(account);
            return true;
        }
        return false;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public boolean addWebUser(WebUser newWebUser){
        if(this.webUser == null && newWebUser != null){
            setWebUser(newWebUser);
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void removeWebUser(){
        this.webUser = null;
    }

    public void delete() {
        if(this.account != null)
            this.account.delete();
        this.webUser.delete();
    }

    public void showDetailsAndConnections() {
        System.out.println("----------Customer---------\n *** Attributes: ***");
        System.out.println("id: "+id+"\n"
                +"address: "+address+"\n"
                +"phone: "+phone+"\n"
                +"email: "+email);
        System.out.println("*** Connections: ***");
        System.out.println("Account ID: "+account.getId()+"\n"
                +"Web User Login ID: "+webUser.getLogin_id()+"\n\n");
    }
}