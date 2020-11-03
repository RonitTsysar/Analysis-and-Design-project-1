import java.util.*;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static Map<String, WebUser> webUsersList = new HashMap();
    static Map<String, Supplier> suppliersList = new HashMap<>();
    static Map<String, Product> productsList = new HashMap<>(); // key: productName , value: Product
    static WebUser activeWebUser;

    public static void SystemStartUp()
    {
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        suppliersList.put(mosheSupplier.getId(), mosheSupplier);

        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier);
        mosheSupplier.getProducts().add(bambaProduct);
        productsList.put(bambaProduct.getId(), bambaProduct);

        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier);
        mosheSupplier.getProducts().add(ramenProduct);
        productsList.put(ramenProduct.getId(), ramenProduct);

        //WebUser dani
        WebUser daniWU = WebUser.webUserFactory("Dani","Dani123", true);
        Customer daniCustomer = Customer.customerFactory(null , null, null, null, daniWU, true);
        daniWU.addCustomer(daniCustomer);
        Account daniAccount = Account.accountFactory("Dani",null, daniCustomer);
        daniCustomer.addAccount(daniAccount);

        //WebUser Dana
        WebUser danaWU = WebUser.webUserFactory("Dana","Dana123", true);
        Customer danaCustomer = Customer.customerFactory(null , null, null, null, danaWU, true);
        danaWU.setCustomer(danaCustomer);
        PremiumAccount danaAccount = PremiumAccount.PremiumAccountFactory("Dana",null, danaCustomer);
        danaCustomer.addAccount(danaAccount);

        webUsersList.put("Dana", danaWU);
        webUsersList.put("Dani", daniWU);
        danaAccount.getProductsList().add(bambaProduct);
        bambaProduct.setPremiumAccount(danaAccount);
    }

    public static void main(String[] args) 
    {
        SystemStartUp();
        boolean stopProgram = false;

        try{
            while (true)
            {
                System.out.println("Please enter command: ");
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
                            while(webUsersList.containsKey(arg)) {
                                System.out.println("Please choose another id:");
                                arg = scanner.nextLine();
                            }
                            webUsersList.put(arg, addWebUser(arg));
                        }
                        else if (type.equals("Product")){
                            System.out.println("Add Product");
                            addProduct();
                        }
                        break;

                    case "Remove":
                        System.out.println("Remove WebUser");
                        while(!webUsersList.containsKey(arg)){
                            System.out.println("Id doesn't exist. Please choose another id:");
                            arg = scanner.nextLine();
                        }
                        removeWebUser(arg);
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

    public static WebUser addWebUser(String login_id){
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        WebUser webUser = WebUser.webUserFactory(login_id, password, false);

        return webUser;
    }

    public static void removeWebUser(String login_id){
        WebUser wuToRemove = webUsersList.get(login_id);
        webUsersList.remove(login_id);
        //TODO: add deletions of products/payment/order. check if premiumAccount.
        if(wuToRemove.getCustomer().getAccount() instanceof PremiumAccount)
        {
            for (Product prod : ((PremiumAccount) wuToRemove.getCustomer().getAccount()).getProductsList()) {
                prod.setPremiumAccount(null);
            }
            ((PremiumAccount) wuToRemove.getCustomer().getAccount()).setProducts(null);
        }
        if(activeWebUser.equals(wuToRemove)) {activeWebUser=null;}
    }

    private static void logoutWebUser(String login_id){
        // written by Lior.
        if(activeWebUser == null){
            System.out.println("Currently no one is logged in");
            return;
        }
        WebUser userToLogout = webUsersList.get(login_id);
        if(userToLogout == null){
            System.out.println("User not exist");
            return;
        }
        if(activeWebUser.equals(userToLogout)) {
            activeWebUser = null;
            userToLogout.setState(UserState.Blocked);//i assume Blocked=LoggedOut
        }
        else {
            System.out.println("WebUser with id: " + login_id + " is not currently logged in");
            System.out.println("WebUser logged in id is: " + activeWebUser.getLogin_id());
        }
    }

    private static void loginWebUser(String login_id) {
        // written by Lior
        if(activeWebUser != null)
            logoutWebUser(activeWebUser.getLogin_id());//auto logout
        WebUser userToLogin = webUsersList.get(login_id);
        if(userToLogin == null){
            System.out.println("WebUser doesn't exist");
            return;
        }
        String webUserPassword = userToLogin.getPassword();
        System.out.println("Please enter password:");
        String typedPassword = scanner.nextLine();
        if(!typedPassword.equals(webUserPassword)){
            System.out.println("Password incorrect");
            return;
        }
        activeWebUser=userToLogin;
        userToLogin.setState(UserState.Active);
        System.out.println("WebUser "+login_id+" logged in successfully");
    }


    private static void linkProduct(String productName){
        // written by lior
        if(activeWebUser == null) {
            System.out.println("Please Login first");
            return;
        }
        Product prodToLink = productsList.get(productName);
        if(activeWebUser.getCustomer().getAccount() instanceof PremiumAccount && prodToLink != null){
            ((PremiumAccount) activeWebUser.getCustomer().getAccount()).getProductsList().add(prodToLink);
            prodToLink.setPremiumAccount((PremiumAccount) activeWebUser.getCustomer().getAccount());
        }
        else{
            System.out.println("Aborting: Active user is not premium or product doesn't exist");
        }

    }

    private static void deleteProduct(String productName){
        //changed by Lior
        Product prod = productsList.get(productName);
        if(prod == null){
            System.out.println("product doesn't exist");
            return;
        }
        prod.getSupplier().getProducts().remove(prod);//delete product from supplier product list
        productsList.remove(productName);
        //when removing product - delete all lineItems connected to him:
        List lineItemsOfProduct = prod.getLineItemsList();
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
        Product prodToAdd = productsList.get(productName);
        while(prodToAdd != null){
            System.out.println("Product name already exists. enter another name:");
            productName = scanner.nextLine();
            prodToAdd = productsList.get(productName);
        }
        System.out.println("Please enter supplier name:");
        String supplierName= scanner.nextLine();
        Supplier supplier = suppliersList.get(supplierName);
        if(supplier == null){
            System.out.println("Enter supplier ID: ");
            String supId = scanner.nextLine();
            supplier = new Supplier(supId,supplierName);
            suppliersList.put(supId, supplier);
        }
        System.out.println("please enter product id:");
        String productId = scanner.nextLine();
        Product product = new Product(productId, productName, supplier);
        supplier.getProducts().add(product);
        productsList.put(productName, product);
    }

    private static boolean productExists(String productName) {
        return productsList.containsKey(productName);
    }



    public static void makeOrder(){
        System.out.println("Please Enter UserName (login_id): ");
        String login_id = scanner.nextLine();
        WebUser curWebUser = null;
        for (WebUser webUser : webUsersList.values()) {
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
            Order newOrder = new Order(orderNum, orderDate, shippedDate, ship_toAddress, OrderStatus.New, total, activeWebUser.getCustomer().getAccount());
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
            newOrder.setAccount(activeWebUser.getCustomer().getAccount());
      }
//
  }
}
