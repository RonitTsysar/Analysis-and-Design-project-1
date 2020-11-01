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

    public Customer(String id, Address address, String phone, String email, WebUser webUser) {
        
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.webUser = webUser;

        account = createAccuount();
    }

    private Account createAccuount() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter billing address:");
        String billingAddress = scanner.nextLine();
        scanner.close();
        
        System.out.println("Are you a Premium Account? Please enter yes/no:");
        if(scanner.nextLine().equals("yes"))
            return(new PremiumAccount(this.id, billingAddress, this));
        else
            return(new Account(this.id, billingAddress, this));
    }
    /*public void setAccount(Account account)
    {
        // TODO: create check if account already connected !!!!!***********
        this.account=account;

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

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public boolean addWebUser(WebUser newWebUser){
        if(this.webUser == null){
            setWebUser(newWebUser);
            return true;
        }
        return false;
    }

    public void removeWebUser(){
        this.webUser = null;
    }*/
}