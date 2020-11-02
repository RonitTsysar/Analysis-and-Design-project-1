import java.util.*;


public class Main {

    static Scanner scanner = new Scanner(System.in);
//    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static ArrayList<WebUser> webUsersList = new ArrayList<>();
    static Map<String, Supplier> suppliersList = new HashMap<>();
    static Map<String, Product> productsList = new HashMap<>(); // key: productName , value: Product
    static Account activeAccount;



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
        boolean stopProgram = false;

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
                        loginWebUser(arg);
                        break;

                    case "Logout":
                        System.out.println(" Logout WebUser ");
                        logoutWebUser(arg);
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
                        linkProduct(arg);
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

    private static void logoutWebUser(String login_id){
        // written by Lior.
        WebUser tmpUser=null;
        for (WebUser wu : webUsersList) {
            if(wu.getLogin_id().equals(login_id))
            {
                tmpUser=wu;
            }
        }
        Account account = tmpUser.getCustomer().getAccount();
        if(activeAccount.equals(account)) {
            activeAccount = null;
            tmpUser.setState(UserState.Blocked);//i assume Blocked=LoggedOut
        }
        else
            System.out.println("User with id: "+login_id+" is not currently logged in");
    }

    private static void loginWebUser(String login_id) {
        // written by Lior
        String webUserPassword=null;
        WebUser tmpUser=null;
        for (WebUser wu : webUsersList) {
            if(wu.getLogin_id().equals(login_id))
            {
                webUserPassword=wu.getPassword();
                tmpUser=wu;
            }
        }
        //TODO: decide if we want to check first if user exist (in my opinion its useless) - lior
        System.out.println("Please enter password:");
        String typedPassword=scanner.nextLine();
        if(!typedPassword.equals(webUserPassword)){
            System.out.println("Password incorrect");
            return;
        }
        activeAccount=tmpUser.getCustomer().getAccount();
        tmpUser.setState(UserState.Active);
        System.out.println("user "+login_id+" logged in successfully");
    }


    private static void linkProduct(String productName){
        // written by lior
        if(activeAccount instanceof PremiumAccount){
            ((PremiumAccount) activeAccount).getProductsList().add(productsList.get(productName));
        }

    }

    private static void deleteProduct(String productName){
        //changed by Lior
        Product prod = productsList.get(productName);
        prod.getSupplier().getProducts().remove(prod);//delete product from supplier product list
        productsList.remove(productName);
        //when removing product - delete all lineItems connected to him:
        List lineItemsOfProduct=prod.getLineItemsList();
        for (Object lineItem : lineItemsOfProduct) {
            ((LineItem)lineItem).getShoppingCart().getLineItemList().remove(lineItem);
            ((LineItem)lineItem).getOrder().getLineItems().remove(lineItem);
        }
        if(prod.getPremiumAccount() != null)
            prod.getPremiumAccount().getProductsList().remove(prod);
    }


    private static void addProduct() {
        System.out.println("Please enter product name:");
        String productName = scanner.nextLine();
        while(productExists(productName)){
            System.out.println("Product name already exists. enter another name:");
            productName = scanner.nextLine();
        }
        System.out.println("Please enter supplier name:");
        Supplier supplier = checkSupplier(scanner.nextLine());
        System.out.println("please enter product id:");
        String productId = scanner.nextLine();
        Product product = new Product(productId, productName, supplier);
        supplier.getProducts().add(product);
        productsList.put(productName, product);
    }

    private static boolean productExists(String productName) {
        return productsList.containsKey(productName);
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

    public static void makeOrder(){
        System.out.println("Please Enter UserName (login_id): ");
        String login_id = scanner.nextLine();
        WebUser curWebUser = null;
        for (WebUser webUser : webUsersList) {
            if(webUser.getLogin_id().equals(login_id)){
                curWebUser = webUser;
            }
            else{
                throw new RuntimeException("User not found");
            }
        }
        if(curWebUser != null)
            curWebUser.displayOwnedItems();
        System.out.println("Please enter the Order Number");
        String orderNum = scanner.nextLine();
        System.out.println("Do You want to continue to the Products List?  Y/N");
        String cmd = scanner.nextLine();
        if(!cmd.equals("Y")) {
            return;
        }
        else{
            for (String productID : productsList.keySet()) {
                System.out.println(productsList.get(productID));
            }
            System.out.println("Please type the product ID you want");
            String productID = scanner.nextLine();
            Product chosenProduct = productsList.get(productID);
            if (chosenProduct == null)
                throw new RuntimeException("Product not found");
            System.out.println("Showing you the LineItems od this product");
            for (LineItem lineItem : productsList.get(productID).getLineItemsList()) {
                System.out.println(lineItem);
            }
            //todo: check about line item
            System.out.println("Please enter the QUANTITY you want");
            String quantity = scanner.nextLine();
            //TODO: CREATE NEW LINE ITEMS FOR EACH PRODUCT AND QUANTITY
            // TODO: CREATE NEW ORDER THAT CONTAINS ALL THE ITEMS
            Date orderDate = new Date();
            Date shippedDate = null;
            System.out.println("Please enter shipping Address");
            String shippingAddress = scanner.nextLine();
            Address ship_toAddress= new Address(shippingAddress);
            //todo: multiple the QUANTITY*PRICE - from the lineITEM attributes
            float total = Float.parseFloat(quantity);
            Order newOrder = new Order(orderNum, orderDate, shippedDate, ship_toAddress, OrderStatus.New, total, activeAccount);
            //Order was created for the account

            System.out.println("How do you want to pay - Immediate Payment/ Delayed Payment ?  Please enter 1-Immediate or 2-Delayed");
            String paymentType = scanner.nextLine();
            System.out.println("Please enter PaymentID");
            String paymentID = scanner.nextLine();
            System.out.println("Do you want a phone confirmation ? y/n");
            String phoneCofirmAns = scanner.nextLine();
            boolean phoneConfirmation = false;
            if(phoneCofirmAns.equals("y"))
                phoneConfirmation = true;
            if (paymentType.equals("1")){
                newOrder.addImmediatePayment(paymentID, new Date(), total, null, phoneConfirmation);
            }
            else{
                newOrder.addDelayedPayment(paymentID, new Date(), total, null, new Date());
            }
            newOrder.setAccount(activeAccount);
      }
//
  }
}
