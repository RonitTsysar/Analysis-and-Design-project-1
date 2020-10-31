import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static ArrayList<Supplier> suppliersList = new ArrayList<Supplier>();
    static ArrayList<Product> productsList = new ArrayList<Product>();


    public static Customer addWebUser(String login_id){
        Scanner scanner = new Scanner(System.in);
        WebUser webUser = new WebUser(login_id);
        System.out.println("Please enter password:");
        String password = scanner.nextLine();
        System.out.println("Please enter customer id:");
        String customerId = scanner.nextLine();
        System.out.println("Please enter customer address:");
        Address customerAddress = new Address(scanner.nextLine());
        System.out.println("Please enter customer phone:");
        String customerPhone= scanner.nextLine();
        System.out.println("Please enter customer email:");
        String customerEmail = scanner.nextLine();
        System.out.println("Please enter billing address:");
        String billingAddress = scanner.nextLine();
        Customer customer = new Customer(customerId, customerAddress, customerPhone, customerEmail, webUser); // make connection between (customer -> webuser)
        webUser.addCustomer(customer);// make connection between (webuser -> customer)
        Account account = new Account(login_id, billingAddress, customer);// sending customer arg to make back connection inside the ctor ( Account -> customer)
        customer.setAccount(account); // make connection between (customer -> account)
        ShoppingCart shoppingCart = new ShoppingCart(account,webUser); // create shopingCart &  make connection between (shopingCart -> account&webuser)
        account.setShoppingCart(shoppingCart); // make connection between (account -> shopingCart)
        webUser.addShoppingCart(shoppingCart);//make connection between (webuser -> shoppingCart)
        webUser.setPassword(password);
        //TODO: add webUser State;
        return customer;
    }

    public static void SystemStartUp()
    {
        Scanner scanner = new Scanner(System.in);

        // start-up place to init users in the system (DANA AND DANI)
        //****************** */
//        ArrayList<Customer> customersList = new ArrayList<Customer>();
//        ArrayList<Supplier> suppliersList = new ArrayList<Supplier>();
//        ArrayList<Product> productsList = new ArrayList<Product>();

        //Supplier Moshe
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        suppliersList.add(mosheSupplier);

        //Product Bamba
        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier);
        productsList.add(bambaProduct);

        //Product Ramen
        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier);
        productsList.add(ramenProduct);

        //WebUser Dani
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
        System.out.println( "System startup completed");
        System.out.println(" num of customers in the system : "+customersList.size() );



        Scanner scanner = new Scanner(System.in);
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
                            System.out.println("Add WebUser");
                            customersList.add(addWebUser(arg));
                            System.out.println("System added  : OK!");
                            System.out.println(" num of customers in the system : "+customersList.size() );


                        }
                        else if (type.equals("Product"))
                            System.out.println("Add Product");
                            //TODO: addProduct()
                        break;
    
                    case "Remove":
                        System.out.println("Remove WebUser");
                        //TODO: removeWebUser()
                        break;
                    
                    case "Login":
                        System.out.println("Login WebUser");
                        //TODO: loginWebUser()
                        break;
                    
                    case "Logout":
                        System.out.println(" Logout WebUser ");
                        //TODO: logoutWebUser()
                        break;
                               
                    case "Make":
                        System.out.println(" Make order ");
                        //TODO: makeOrder()
                        break;
    
                    case "Display":
                        System.out.println(" Display order ");
                        //TODO: displayOrder()
                        break;
                    
                    case "Link":
                        System.out.println(" Link Product ");
                        //TODO: linkProduct(String productName)
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
        catch(Exception e) 
        {
            scanner.close();
        }
    }
}
