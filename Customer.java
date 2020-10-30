public class Customer {

    private String id;
    private Address address;
    private String phone;
    private String email;

    private WebUser webUser;

    public Customer(String id, Address address, String phone, String email) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;

        this.webUser = null;
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
    }
}
