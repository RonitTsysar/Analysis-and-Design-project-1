import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<WebUser> webUsersList = new ArrayList<WebUser>();
    static ArrayList<Supplier> suppliersList = new ArrayList<Supplier>();
    static ArrayList<Product> productsList = new ArrayList<Product>();


    public static WebUser addWebUser(String login_id){

        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        return(new WebUser(login_id, password));
    }

    public static void SystemStartUp()
    {
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        suppliersList.add(mosheSupplier);

        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier);
        productsList.add(bambaProduct);

        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier);
        productsList.add(ramenProduct);

        WebUser daniWU = new WebUser("Dani");
        daniWU.setPassword("Dani123");
        Customer daniCustomer = new Customer(null , null, null, null, daniWU);
        daniWU.setCustomer(daniCustomer);
        Account daniAccount = new Account("Dani",null,daniCustomer);
        daniCustomer.setAccount(daniAccount);
        ShoppingCart daniShoppingCart = new ShoppingCart(daniAccount,daniWU);
        daniAccount.setShoppingCart(daniShoppingCart);
        daniWU.setShoppingCart(daniShoppingCart);
        //TODO: add webUser State;
        customersList.add(daniCustomer);

        //WebUser Dana
        WebUser danaWU = new WebUser("Dana");
        danaWU.setPassword("Dana123");
        Customer danaCustomer = new Customer(null , null, null, null, danaWU);
        danaWU.setCustomer(danaCustomer);
        PremiumAccount danaAccount = new PremiumAccount("Dana",null,danaCustomer);
        danaCustomer.setAccount(danaAccount);
        ShoppingCart danaShoppingCart = new ShoppingCart(danaAccount,danaWU);
        danaAccount.setShoppingCart(danaShoppingCart);
        danaWU.setShoppingCart(danaShoppingCart);
        //TODO: add webUser State;
        customersList.add(danaCustomer);

        danaAccount.getProducts().add(bambaProduct);
        bambaProduct.setPremiumAccount(danaAccount);
    }

    public static void main(String[] args) 
    {
        SystemStartUp();

        try{
            while (true)
            { 
                String command = scanner.nextLine();
                if (command.equals(""))
                    continue;
    
                ArrayList<String> command_list = new ArrayList<String>(Arrays.asList(command.split(" ")));
                command = command_list.get(0);
                String type = command_list.get(1);
                String arg = "";
                
                if(command_list.size() > 2)
                    arg = command_list.get(2);
    
                switch (command) {
                    case "Add":
                        if (type.equals("WebUser")) {
                            webUsersList.add(addWebUser(arg));
                        }
                        else if (type.equals("Product"))
                            System.out.println("Add Product");
                        break;
    
                    case "Remove":
                        System.out.println("Remove WebUser");
                        break;
                    
                    case "Login":
                        System.out.println("Login WebUser");
                        break;
                    
                    case "Logout":
                        System.out.println(" Logout WebUser ");
                        break;
                               
                    case "Make":
                        System.out.println(" Make order ");
                        break;
    
                    case "Display":
                        System.out.println(" Display order ");
                        break;
                    
                    case "Link":
                        System.out.println(" Link Product ");
                        break;
    
                    case "Delete":
                        System.out.println(" Delete Product ");
                        break;
    
                    case "ShowAllObjects":
                        System.out.println(" ShowAllObjects ");
                        break;
    
                    case "ShowObjectId ":
                        System.out.println(" ShowObjectId ");
                        break;
                }
            }
        }
        catch(Exception e) {
            scanner.close();
        }
    }
}
