import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


//    public void addWebUser(String loginId){
//        Scanner scanner = new Scanner();
//        WebUser webUser= new WebUser(loginId);
//        System.out.println("Please enter password:");
//        String password= scanner.nextLine();
//        System.out.println("Please enter customer id:");
//        String customerId= scanner.nextLine();
//        System.out.println("Please enter customer address:");
//        String customerAddress= scanner.nextLine();
//        System.out.println("Please enter customer phone:");
//        String customerPhone= scanner.nextLine();
//        System.out.println("Please enter customer email:");
//        String customerEmail= scanner.nextLine();
//        System.out.println("Please enter billing address:");
//        String billingAddress= scanner.nextLine();
//        Customer customer = new Customer(customerId,customerAddress,customerPhone,customerEmail,webuser);
//        Account account = new Account(webuser.login_id,billingAddress,customer);// sending customer arg to make back connection inside the ctor ( Account -> customer)
//        customer.setAccount(account); // make connection between (customer -> account)
//        ShoppingCart shoppingCart = new ShoppingCart(account,webUser); // create shopingCart &  make connection between (shopingCart -> account&webuser)
//        account.setShoppingCart(shoppingCart); // make connection between (account -> shopingCart)
//        webUser.addShoppingCart(shoppingCart);//make connection between (webuser -> shoppingCart)
//    }

//    public Customer[] SystemStartUp()
//    {
//        Scanner scanner = new Scanner();
//
//        // start-up place to init users in the system (DANA AND DANI)
//        //****************** */
//        ArrayList<Customer> customersList= new ArrayList<Customer>();
//        WebUser daniWU= WebUser("Dani");
//        daniWU.setPassword("Dani123");
//        Customer daniCustomer=new Customer(null, null, null, null,daniWU,null);
//        daniWU.setCustomer(daniCustomer);
//        Account daniAccount = new Account("Dani",null,daniCustomer);
//        daniCustomer.setAccount(daniAccount);
//        ShoppingCart daniShoppingCart = new ShoppingCart(daniAccount,daniWU);
//        daniAccount.setShoppingCart(daniShoppingCart);
//        daniWU.setShoppingCart(daniShoppingCart);
//
//        customersList.add(daniCustomer)
//
//        //place to add dana !
//
//
//
//        return customersList;
//    }




    public static void main(String[] args) 
    {
//        ArrayList<Customer> customersList = SystemStartUp();



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
                        if (type.equals("WebUser"))
                            System.out.println("Add WebUser");
                            //TODO: addWebUser()
                        else if (type.equals("Product"))
                            System.out.println("Add Product");
                            //TODO: addProduct()
                        break;
    
                    case "Remove":
                        System.out.println(" Remove WebUser ");
                        break;
                    
                    case "Login":
                        System.out.println(" Login WebUser ");
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
        catch(Exception e) 
        {
            scanner.close();
        }
    }
}
