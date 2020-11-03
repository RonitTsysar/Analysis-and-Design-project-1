import java.util.*;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static Map<String, WebUser> webUsersList = new HashMap(); //key: webUserId
    static Map<String, Supplier> suppliersList = new HashMap<>();//key: supplierName
    static Map<String, Product> productsList = new HashMap<>(); // key: productName , value: Product
    static WebUser activeWebUser;

    public static void SystemStartUp() {
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        suppliersList.put(mosheSupplier.getName(), mosheSupplier);

        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier);
        mosheSupplier.getProducts().add(bambaProduct);
        productsList.put(bambaProduct.getId(), bambaProduct);

        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier);
        mosheSupplier.getProducts().add(ramenProduct);
        productsList.put(ramenProduct.getId(), ramenProduct);

        //WebUser dani
        WebUser daniWU = WebUser.webUserFactory("Dani", "Dani123", true);
        Customer daniCustomer = Customer.customerFactory(null, null, null, null, daniWU, true);
        daniWU.addCustomer(daniCustomer);
        Account daniAccount = Account.accountFactory("Dani", null, daniCustomer);
        daniCustomer.addAccount(daniAccount);

        //WebUser Dana
        WebUser danaWU = WebUser.webUserFactory("Dana", "Dana123", true);
        Customer danaCustomer = Customer.customerFactory(null, null, null, null, danaWU, true);
        danaWU.setCustomer(danaCustomer);
        PremiumAccount danaAccount = PremiumAccount.PremiumAccountFactory("Dana", null, danaCustomer);
        danaCustomer.addAccount(danaAccount);

        webUsersList.put("Dana", danaWU);
        webUsersList.put("Dani", daniWU);
        danaAccount.getProductsList().add(bambaProduct);
        bambaProduct.setPremiumAccount(danaAccount);
    }

    public static void main(String[] args) {
        SystemStartUp();
        boolean stopProgram = false;

        try {
            while (true) {
                System.out.println("Please enter command: ");
                String command = scanner.nextLine();
                if (command.equals(""))
                    continue;

                ArrayList<String> command_list = new ArrayList<>(Arrays.asList(command.split(" ")));
                command = command_list.get(0);
                String type = command_list.get(1);
                String arg = "";

                if (command_list.size() > 2)
                    arg = command_list.get(2);

                switch (command) {
                    case "Add":
                        if (type.equals("WebUser")) {
                            while (webUsersList.containsKey(arg)) {
                                System.out.println("WebUser already exist! Please choose another id: ");
                                arg = scanner.nextLine();
                            }
                            webUsersList.put(arg, addWebUser(arg));
                        } else if (type.equals("Product")) {
                            System.out.println("Add Product");
                            addProduct();
                        }
                        break;

                    case "Remove":
                        System.out.println("Remove WebUser");
                        while (!webUsersList.containsKey(arg)) {
                            System.out.println("WebUser doesn't exist! Please choose another id: ");
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
                        makeOrder();
                        break;

                    case "Display":
                        System.out.println("Display order");
                        displayLastOrderOfActiveUser();
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
                        showObject(type);
                        break;
                }
            }
        } catch (Exception e) {
            scanner.close();
        }
    }

    private static void displayLastOrderOfActiveUser() {
        activeWebUser.getCustomer().getAccount().showLastOrder();
    }

    public static WebUser addWebUser(String login_id) {
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        WebUser webUser = WebUser.webUserFactory(login_id, password, false);
        System.out.println("WebUser Created Successfully!");
        return webUser;
    }

    public static void removeWebUser(String login_id) {
        WebUser wuToRemove = webUsersList.get(login_id);
        if (activeWebUser.equals(wuToRemove)) {
            activeWebUser = null;
        }
        wuToRemove.delete();
        webUsersList.remove(login_id);

        //TODO: I commented the following code because you don't need to delete products if you delete WebUser!
//        if (wuToRemove.getCustomer().getAccount() instanceof PremiumAccount) {
//            for (Product prod : ((PremiumAccount) wuToRemove.getCustomer().getAccount()).getProductsList()) {
//                prod.setPremiumAccount(null);
//            }
//            ((PremiumAccount) wuToRemove.getCustomer().getAccount()).setProducts(null);
//        }
    }

    private static void logoutWebUser(String login_id) {
        // written by Lior.
        if (activeWebUser == null) {
            System.out.println("There is no one logged in right now...");
            return;
        }
        WebUser userToLogout = webUsersList.get(login_id);
        if (userToLogout == null) {
            System.out.println("User not exist");
            return;
        }
        if (activeWebUser.equals(userToLogout)) {
            activeWebUser = null;
            userToLogout.setState(UserState.Blocked);//i assume Blocked=LoggedOut
        } else {
            System.out.println("WebUser with id: " + login_id + " is not currently logged in");
            System.out.println("WebUser logged in id is: " + activeWebUser.getLogin_id());
        }
    }

    private static void loginWebUser(String login_id) {
        // written by Lior
        if (activeWebUser != null)
            logoutWebUser(activeWebUser.getLogin_id());//auto logout
        WebUser userToLogin = webUsersList.get(login_id);
        if (userToLogin == null) {
            System.out.println("WebUser doesn't exist");
            return;
        }
        String webUserPassword = userToLogin.getPassword();
        System.out.println("Please enter password:");
        String typedPassword = scanner.nextLine();
        if (!typedPassword.equals(webUserPassword)) {
            System.out.println("Password incorrect");
            return;
        }
        activeWebUser = userToLogin;
        userToLogin.setState(UserState.Active);
        System.out.println("WebUser " + login_id + " logged in successfully");
    }


    private static void linkProduct(String productName) {
        // written by lior
        if (activeWebUser == null) {
            System.out.println("Please Login first");
            return;
        }
        Product prodToLink = productsList.get(productName);
        if (activeWebUser.getCustomer().getAccount() instanceof PremiumAccount && prodToLink != null) {
            ((PremiumAccount) activeWebUser.getCustomer().getAccount()).getProductsList().add(prodToLink);
            prodToLink.setPremiumAccount((PremiumAccount) activeWebUser.getCustomer().getAccount());
        } else {
            System.out.println("Aborting: Active user is not premium or product doesn't exist");
        }

    }

    private static void deleteProduct(String productName) {
        //changed by Lior
        Product prod = productsList.get(productName);
        if (prod == null) {
            System.out.println("product doesn't exist");
            return;
        }
        prod.getSupplier().getProducts().remove(prod);//delete product from supplier product list
        prod.delete();
        productsList.remove(productName);
    }


    private static void addProduct() {
        System.out.println("Please enter product name:");
        String productName = scanner.nextLine();
        Product prodToAdd = productsList.get(productName);
        while (prodToAdd != null) {
            System.out.println("Product name already exists. enter another name:");
            productName = scanner.nextLine();
            prodToAdd = productsList.get(productName);
        }
        System.out.println("Please enter supplier name:");
        String supplierName = scanner.nextLine();
        Supplier supplier = suppliersList.get(supplierName);
        if (supplier == null) {
            System.out.println("Enter supplier ID: ");
            String supId = scanner.nextLine();
            supplier = new Supplier(supId, supplierName);
            suppliersList.put(supId, supplier);
        }
        System.out.println("please enter product id:");
        String productId = scanner.nextLine();
        Product product = new Product(productId, productName, supplier);
        supplier.getProducts().add(product);
        productsList.put(productName, product);
    }


    // Dana & Roy
    public static void makeOrder() {
        Order newOrder = new Order(activeWebUser.getCustomer().getAccount());
        System.out.println("Please Enter Seller's User Name: ");
        String login_id = scanner.nextLine();
        WebUser sellerWebUser = null;
        for (String webUserID : webUsersList.keySet()) {
            if (webUserID.equals(login_id) && webUsersList.get(webUserID).getCustomer().getAccount().isPremium()) {
                sellerWebUser = webUsersList.get(webUserID);
            } else {
                throw new RuntimeException("Seller not found");
            }
        }
        // display products to sell
        if (sellerWebUser != null)
            sellerWebUser.getCustomer().getAccount().displayProductsToSell();

        // choosing products
        boolean finishOrder = false;
        while (!finishOrder) {
            System.out.println("Please type the product ID you want");
            String productID = scanner.nextLine();
            assert sellerWebUser != null;
            List<Product> productsListFromSeller = sellerWebUser.getCustomer().getAccount().getProducts();
            Product chosenProduct = null;
            for (Product product : productsListFromSeller) {
                if (product.getId().equals(productID)) {
                    chosenProduct = product;
                }
            }
            if (chosenProduct == null)
                throw new RuntimeException("Product not found");

            System.out.println("Here are the options for this product: ");
            chosenProduct.showLineItems();
            System.out.println("Please press your choice number.");
            int chosenOption = scanner.nextInt();
            LineItem newItem = chosenProduct.getLineItemsList().get(chosenOption - 1);
            activeWebUser.getCustomer().getAccount().getShoppingCart().addLineItem(newItem);
            newOrder.addLineItem(newItem);
            System.out.println("Do you want to choose another product? y/n");
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                finishOrder = true;
            }
        }
        newOrder.setOrdered(new Date());
        String shippingAddress = activeWebUser.getCustomer().getAccount().getBilling_address();
        newOrder.setShip_to(new Address(shippingAddress));
        activeWebUser.getCustomer().getAccount().setLastOrder(newOrder);
        System.out.println("Order Created");



        // payment
        System.out.println("How do you want to pay -\nImmediate Payment/ Delayed Payment ?\nPlease enter 1-Immediate or 2-Delayed");
        int paymentType = scanner.nextInt();

        float totalToBePayed = newOrder.getTotal();
        while (totalToBePayed > 0) {
            System.out.println("Please Enter the sum you want to pay from the total of: " + totalToBePayed);
            int partOfPay = scanner.nextInt();
            if (partOfPay <= newOrder.getTotal()) {
                totalToBePayed -= partOfPay;

                if (paymentType == 1) {
                    ImmediatePayment newImmediatePayment = new ImmediatePayment(partOfPay);
                    System.out.println("Do you want a phone confirmation ? y/n");
                    String phoneCofirmAns = scanner.nextLine();
                    boolean phoneConfirmation = false;
                    if (phoneCofirmAns.equals("y"))
                        phoneConfirmation = true;
                    newImmediatePayment.setPhoneConfirmation(phoneConfirmation);
                    newOrder.addPayment(newImmediatePayment);
                } else if (paymentType == 2) {
                    DelayedPayment newDelayedPayment = new DelayedPayment(partOfPay);
                    System.out.println("Please Enter the payment date in this format: <year(2 numbers)>,<month(0-11)>,<day(1-31)>");
                    String chosenDateStr = scanner.nextLine();
                    String[] splitDate = chosenDateStr.split(",");
                    //TODO: CHECK ABOUT MONTHS - JANU ......
                    Date date = new Date();
                    newDelayedPayment.setPaymentDate(date);
                    newOrder.addPayment(newDelayedPayment);
                }
            } else {
                System.out.println("You choose too much money to pay dude...");
            }
        }
    }


    public static void showObject(String objectId){
        for (String webUserId : webUsersList.keySet()) {
            WebUser curWebUser = webUsersList.get(webUserId);
            if(webUserId.equals(objectId)){
                curWebUser.showDetailsAndConnections();
            }
            else{
                Customer curCustomer = curWebUser.getCustomer();
                if(curWebUser.getCustomer().getId().equals(objectId)){
                    curCustomer.showDetailsAndConnections();
                }
                else{
                    Account curAccount = curCustomer.getAccount();
                    if(curAccount.getId().equals(objectId)){
                        curAccount.showDetailsAndConnections();
                    }
                    else{
                        for (Order curOrder : curAccount.getOrders()) {
                            if(curOrder.getNumber().equals(objectId)){
                                curOrder.showDetailsAndConnections();
                            }
                        }
                    }
                    for (Payment curPayment : curAccount.getPayments()) {
                        if(curPayment.getPaymentId().equals(objectId)){
                            curPayment.showDetailsAndConnections();
                        }
                    }
                }
            }
        }
        for (String supplierID : suppliersList.keySet()) {
            Supplier curSupplier = suppliersList.get(supplierID);
            if(supplierID.equals(objectId)){
                curSupplier.showDetailsAndConnections();
            }
            else{
                for (Product curProduct : curSupplier.getProducts()) {
                    if(curProduct.getId().equals(objectId)){
                        curProduct.showDetailsAndConnections();
                    }
                }
            }
        }
    }

    public void showAllObjects(){
        //todo: implement
    }
}
