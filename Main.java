import java.util.*;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static Map<String, Supplier> suppliersList = new HashMap<>();
    static Map<String, Product> productsList = new HashMap<>();



    public static WebUser addWebUser(String login_id){

        System.out.println("Please enter password: ");
        String password = scanner.nextLine();

        return(new WebUser(login_id, password));
    }

    public static void SystemStartUp()
    {
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        suppliersList.put(mosheSupplier.getId(), mosheSupplier);

        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier);
        productsList.put(bambaProduct.getId(), bambaProduct);

        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier);
        productsList.put(ramenProduct.getId(), ramenProduct);

        WebUser daniWU = new WebUser("Dani");
        daniWU.setPassword("Dani123");
        Customer daniCustomer = new Customer(null , null, null, null, daniWU);
        daniWU.setCustomer(daniCustomer);
        Account daniAccount = new Account("Dani",null,daniCustomer);
        daniCustomer.setAccount(daniAccount);
        ShoppingCart daniShoppingCart = new ShoppingCart();
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
        ShoppingCart danaShoppingCart = new ShoppingCart();
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
                        else if (type.equals("Product")){
                            System.out.println("Add Product");
                            addProduct();
                        }
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
                        System.out.println("Make order");
                        //todo: makeOrder()
                        break;
    
                    case "Display":
                        System.out.println("Display order");
                        break;
                    
                    case "Link":
                        System.out.println(" Link Product ");
                        break;
    
                    case "Delete":
                        System.out.println(" Delete Product ");
                        deleteProduct(arg);
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

    private static void deleteProduct(String productName){
        ArrayList<Product> prods = (ArrayList<Product>) productsList.values();
        for (Product prod : prods) {
            if(prod.getName().equals(productName))
            {
                String prodId = prod.getId();
                prod.getSupplier().getProducts().remove(prod);//delete product from supplier product list
                productsList.remove(prodId);
            }
        }

    }

    private static void addProduct() {
        System.out.println("Please enter product id:");
        String productId = scanner.nextLine();
        while(productExists(productId)){
            System.out.println("Product id already exists. enter another id:");
            productId = scanner.nextLine();
        }

        System.out.println("Please enter supplier name:");
        Supplier supplier = checkSupplier(scanner.nextLine());

        System.out.println("please enter product name:");
        String productName = scanner.nextLine();
        Product product = new Product(productId, productName, supplier);
        supplier.getProducts().add(product);

        productsList.put(productId, product);
    }

    private static boolean productExists(String productId) {
        return productsList.containsKey(productId);
    }

    private static Supplier checkSupplier(String supplierId) {
        if(suppliersList.containsKey(supplierId))
            return suppliersList.get(supplierId);

        System.out.println("Please enter supplier's name:");
            String supplierName = scanner.nextLine();

        Supplier supplier = new Supplier(supplierId, supplierName);
        suppliersList.put(supplierId, supplier);

        return supplier;

    }
}
