import java.util.*;

import static java.lang.Integer.parseInt;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, WebUser> webUsersList = new HashMap<>(); //key: webUserId
    static Map<String, Supplier> suppliersList = new HashMap<>();//key: supplierName --> key: supplierID
    static Map<String, Product> productsList = new HashMap<>(); // key: productName -> key: productID
    static WebUser activeWebUser;

    public static void SystemStartUp() {
        Supplier mosheSupplier = new Supplier("123", "Moshe");
        //suppliersList.put(mosheSupplier.getName(), mosheSupplier);
        suppliersList.put(mosheSupplier.getId(), mosheSupplier);

        Product bambaProduct = new Product("Bamba", "Bamba", mosheSupplier,5);
        mosheSupplier.getProducts().add(bambaProduct);
        productsList.put(bambaProduct.getId(), bambaProduct);

        Product ramenProduct = new Product("Ramen", "Ramen", mosheSupplier,10);
        mosheSupplier.getProducts().add(ramenProduct);
        productsList.put(ramenProduct.getId(), ramenProduct);

        //WebUser dani
        WebUser daniWU = WebUser.webUserFactory("Dani", "Dani123", true);
        Customer daniCustomer = Customer.customerFactory("Dani", new Address("brener 10"), "0526655443", "danishovevani@gmail.com", daniWU, true);
        daniWU.addCustomer(daniCustomer);
        Account daniAccount = Account.accountFactory("Dani", "brener 10", daniCustomer);
        daniCustomer.addAccount(daniAccount);

        //WebUser Dana
        WebUser danaWU = WebUser.webUserFactory("Dana", "Dana123", true);
        Customer danaCustomer = Customer.customerFactory("Dana", new Address("brener 10"), "0526655442", "danabanana@gmail.com", danaWU, true);
        danaWU.setCustomer(danaCustomer);
        PremiumAccount danaAccount = PremiumAccount.PremiumAccountFactory("Dana", "brener 10", danaCustomer);
        danaCustomer.addAccount(danaAccount);

        webUsersList.put(danaWU.getLogin_id(), danaWU);
        webUsersList.put(daniWU.getLogin_id(), daniWU);

        danaAccount.getProductsList().add(bambaProduct);
        bambaProduct.setPremiumAccount(danaAccount);
    }

    public static void main(String[] args) {
        SystemStartUp();


        try {
            while (true) {
                System.out.println("Please enter command: ");
                String command = scanner.nextLine();
                if (command.equals(""))
                    continue;


                ArrayList<String> command_list = new ArrayList<>(Arrays.asList(command.split(" ")));
                command = command_list.get(0);
                String type = "";
                if (command_list.size() > 1)
                    type = command_list.get(1);
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
                            addProduct();
                        }
                        break;

                    case "Remove":
                        while (!webUsersList.containsKey(arg)) {
                            System.out.println("WebUser doesn't exist! Please choose another id: ");
                            arg = scanner.nextLine();
                        }
                        removeWebUser(arg);
                        break;

                    case "Login":
                        loginWebUser(arg);
                        break;

                    case "Logout":
                        logoutWebUser(arg);
                        break;

                    case "Make":
                        makeOrder();
                        break;

                    case "Display":
                        displayLastOrderOfActiveUser();
                        break;

                    case "Link":
                        linkProduct(arg);
                        break;

                    case "Delete":
                        deleteProduct(arg);
                        break;

                    case "ShowAllObjects":
                        showAllObjects();
                        break;

                    case "ShowObjectId":
                        showObject(type);
                        break;

                    default:
                        System.out.println("invalid input");
                }
            }
        }
        catch (Exception e) {
            scanner.close();
        }
    }

    private static void displayLastOrderOfActiveUser() {
        if(activeWebUser.getCustomer().getAccount().getLastOrder() != null)
            activeWebUser.getCustomer().getAccount().showLastOrder();
        else{
            System.out.println("No Orders made by current user...");
        }
    }

    public static WebUser addWebUser(String login_id) {
        System.out.println("Please enter password: ");
        String password = scanner.nextLine();
        WebUser webUser = WebUser.webUserFactory(login_id, password, false);
        System.out.println("WebUser Created Successfully!");
        return webUser;
    }

    public static void removeWebUser(String login_id) {
        WebUser webUserToRemove = webUsersList.get(login_id);
        if (activeWebUser != null && activeWebUser.equals(webUserToRemove)){
            activeWebUser = null;
        }
        webUserToRemove.delete();
        webUsersList.remove(login_id);
        System.out.println("Web User " + webUserToRemove.getLogin_id() + " has Successfully removed!");
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
            System.out.println("Web User Logged Out Successfully!");
        }
        else {
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
        boolean passwordCorrect = false;
        while(!passwordCorrect){
            System.out.println("Please enter password:");
            String typedPassword = scanner.nextLine();
            if (!typedPassword.equals(webUserPassword)) {
                System.out.println("Password incorrect, Please try again!");
            }
            else{
                passwordCorrect = true;
            }
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

        String prodId = "";
        for (Product product : productsList.values()) {
            if(product.getName().equals(productName))
                prodId = product.getId();
        }

        Product prodToLink = productsList.get(prodId);
        Account curActiveAccount = activeWebUser.getCustomer().getAccount();
        if (curActiveAccount instanceof PremiumAccount && prodToLink != null && prodToLink.getPremiumAccount() == null) {
            ((PremiumAccount)curActiveAccount).addProduct(prodToLink);
            boolean answer = false;
            while(!answer){
                System.out.println("Please enter the selling options of this product in this format: <Quantity>,<Price>");
                String lineItemStr = scanner.nextLine();
                String[] splitLineItem = lineItemStr.split(",");
                LineItem newLineItem = new LineItem(parseInt(splitLineItem[0]), parseInt(splitLineItem[1]));
                prodToLink.addLineItem(newLineItem);
                System.out.println("New LineItem was added Successfully to this product");
                System.out.println("Do you want to add another line item? y/n");
                String ans = scanner.nextLine();
                if(ans.equals("n")) answer = true;
            }
            System.out.println("Product - "+ productName + " linked successfully to "+ activeWebUser.getLogin_id());
        } else {
            System.out.println("Aborting: Active user is not premium or product doesn't exist or product already connected to other account");
        }

    }

    private static void deleteProduct(String productName) {
        // changed by Dana & Roy
        String prodId = "";
        for (Product product : productsList.values()) {
            if(product.getName().equals(productName))
                prodId = product.getId();
        }

        Product prod = productsList.get(prodId);
        if (prod == null) {
            System.out.println("product doesn't exist");
            return;
        }
        prod.delete();
        productsList.remove(prodId);
    }


    private static void addProduct() {
        System.out.println("Please enter product name:");
        String productName = scanner.nextLine();
        List<String> prodNames = new ArrayList<>();

        for (Product product : productsList.values()) {
            prodNames.add(product.getName());
        }
        while(prodNames.contains(productName)){
            System.out.println("Product name already exists. enter another name:");
            productName = scanner.nextLine();
        }

        System.out.println("Please enter supplier name:");
        String supplierName = scanner.nextLine();

        String supplierId = "";
        for (Supplier supp : suppliersList.values()) {
            if(supplierName.equals(supp.getName()))
                supplierId = supp.getId();
        }

        Supplier supplier = null;
        if (supplierId.equals("")) {
            System.out.println("Enter supplier ID: ");
            String supId = scanner.nextLine();
            supplier = new Supplier(supId, supplierName);
            suppliersList.put(supId, supplier);
        }
        else{
            supplier = suppliersList.get(supplierId);
        }

        System.out.println("please enter product id:");
        String productId = scanner.nextLine();
        System.out.println("please enter product price per unit:");
        float productPrice = Float.parseFloat(scanner.nextLine());
        Product product = new Product(productId, productName, supplier, productPrice);

        supplier.getProducts().add(product);
        productsList.put(productId, product);
        System.out.println("Product added Successfully!");
    }

    // Dana & Roy
    public static void makeOrder() {
        if(activeWebUser == null){
            System.out.println("Please Login first");
            return;
        }
        Account curAccount = activeWebUser.getCustomer().getAccount();
        Order newOrder = new Order(curAccount);
        System.out.println("Please Enter Seller's User Name: ");
        String login_id = scanner.nextLine();
        WebUser sellerWebUser = null;
        for (String webUserID : webUsersList.keySet()) {
            if (webUserID.equals(login_id) && webUsersList.get(webUserID).getCustomer().getAccount().isPremium()) {
                sellerWebUser = webUsersList.get(webUserID);
                break;
            } else {
                System.out.println("Seller with this ID Doesn't exist");
                System.out.println("Sorry ! Please try again to Make new Order");
                curAccount.removeOrder(newOrder);
                return;
            }
        }
        // display products to sell
        if(sellerWebUser == null)
            return;
        if(sellerWebUser.getCustomer().getAccount().getProducts().size() < 1)
        {
            System.out.println("This Seller have no products to sell");
            curAccount.removeOrder(newOrder);
            return;
        }

        System.out.println("Here are the products this Seller sells");
        sellerWebUser.getCustomer().getAccount().displayProductsToSell();

        // choosing product
        boolean orderInProc = true;
        while (orderInProc) {
            System.out.println("Please type the product name you want");
            String productName = scanner.nextLine();
            assert sellerWebUser != null;
            List<Product> productsListFromSeller = sellerWebUser.getCustomer().getAccount().getProducts();
            Product chosenProduct = null;
            for (Product product : productsListFromSeller) {
                if (product.getName().equals(productName)) {
                    chosenProduct = product;
                }
            }
            if (chosenProduct == null){
                System.out.println("There is no such product, try again to make an order");
                curAccount.removeOrder(newOrder);
                return;
            }

            System.out.println("Here are the options for this product: ");
            if(!chosenProduct.showLineItems()){
                orderInProc = false;
                continue;
            }
            System.out.println("Please press your choice number.");
            int chosenOption = parseInt(scanner.nextLine());
            LineItem newItem = null;
            try{
                newItem = chosenProduct.getLineItemsList().get(chosenOption - 1);
            }catch (IndexOutOfBoundsException e){
                System.out.println("No such Line Item");
            }
            curAccount.getShoppingCart().addLineItem(newItem);
            newOrder.addLineItem(newItem);
            System.out.println("Do you want to choose another product? y/n");

            String answer = scanner.nextLine();
            if (answer.equals("n")) {
                orderInProc = false;
            }
        }
        if(newOrder.getLineItems().size() < 1){
            System.out.print("Order Empty - ");
            System.out.println("Please try again");
            curAccount.removeOrder(newOrder);
            return;
        }
        newOrder.setOrdered(new Date());
        String shippingAddress = curAccount.getBilling_address();
        newOrder.setShip_to(new Address(shippingAddress));
        curAccount.addOrder(newOrder);
        curAccount.setLastOrder(newOrder);
        System.out.println("Order Created");

        // payment
        System.out.println("How do you want to pay -\nImmediate Payment/ Delayed Payment ?\nPlease enter 1-Immediate or 2-Delayed");
        int paymentType = scanner.nextInt();
        scanner.nextLine();

        float totalToBePayed = newOrder.getTotal();
        while (totalToBePayed > 0) {
            System.out.println("Please Enter the sum you want to pay from the total of: " + totalToBePayed);
            int partOfPay = scanner.nextInt();
            scanner.nextLine();
            if (partOfPay <= newOrder.getTotal()) {
                totalToBePayed -= partOfPay;

                if (paymentType == 1) {
                    ImmediatePayment newImmediatePayment = new ImmediatePayment(partOfPay, activeWebUser.getCustomer().getAccount());
                    System.out.println("Do you want a phone confirmation ? y/n");
                    String phoneCofirmAns = scanner.nextLine();
                    boolean phoneConfirmation = false;
                    if (phoneCofirmAns.equals("y"))
                        phoneConfirmation = true;
                    newImmediatePayment.setPhoneConfirmation(phoneConfirmation);
                    newOrder.addPayment(newImmediatePayment);
                } else if (paymentType == 2) {
                    DelayedPayment newDelayedPayment = new DelayedPayment(partOfPay,activeWebUser.getCustomer().getAccount());
                    System.out.println("Please Enter the payment date in this format: <year(4 numbers)>,<month(1-12)>,<day(1-31)>");
                    String chosenDateStr = scanner.nextLine();
                    String[] splitDate = chosenDateStr.split(",");
                    Date date = new GregorianCalendar(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]) - 1,
                                                      Integer.parseInt(splitDate[2])).getTime();
                    newDelayedPayment.setPaymentDate(date);
                    newOrder.addPayment(newDelayedPayment);
                }
            } else {
                System.out.println("You choose too much money to pay dude...");
            }
        }
        System.out.println("Thank you for shopping with us! Bye Bye...");
    }

    // created by Roy & Dana
    public static void showObject(String objectId){
        Customer c = null;
        Account a = null;
        List<Payment> payments = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        boolean ifExists = false;

        Product prod = productsList.get(objectId);
        Supplier s = suppliersList.get(objectId);
        WebUser wu = webUsersList.get(objectId);

        if(wu != null){
            wu.showDetailsAndConnections();
            c = wu.getCustomer();
            a = c.getAccount();
            payments = a.getPayments();
            orders = a.getOrders();
            ifExists = true;
        }
        if(c != null && c.getId().equals(objectId)) {
            c.showDetailsAndConnections();
            ifExists = true;
        }

        if(a != null && a.getId().equals(objectId)) {
            a.showDetailsAndConnections();
            ifExists = true;
        }

        if(prod != null){
            prod.showDetailsAndConnections();
            ifExists = true;
        }
        if(s != null){
            s.showDetailsAndConnections();
            ifExists = true;
        }

        for (Payment payment:payments){
            if(payment.getPaymentId().equals(objectId))
                payment.showDetailsAndConnections();
                ifExists = true;
        }
        for (Order order:orders) {
            if(order.getNumber().equals(objectId))
                order.showDetailsAndConnections();
                ifExists = true;
        }

        if(!ifExists)
            System.out.println("There is no object in the system with the current id.");
    }

    public static void showAllObjects(){
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        ArrayList<LineItem> lineItems = new ArrayList<>();

        System.out.println("****** WEB USERS ******");
        for (String webUserId : webUsersList.keySet()) {
            WebUser curWebUser = webUsersList.get(webUserId);
            System.out.println("WEB USER: "+curWebUser.getLogin_id());
            //collect other derived data:
            accounts.add(curWebUser.getCustomer().getAccount());
            customers.add(curWebUser.getCustomer());
            shoppingCarts.add(curWebUser.getCustomer().getAccount().getShoppingCart());
        }
        System.out.println("\n" + "****** CUSTOMERS ******");
        for (Customer customer:customers) {
            System.out.println("CUSTOMER: "+customer.getId());
        }
        System.out.println("\n" + "****** ACCOUNTS ******");
        for (Account account:accounts) {
            System.out.println("ACCOUNT: "+account.getId());
        }
        System.out.println("\n" + "****** SHOPPING CARTS ******");
        for (ShoppingCart sc:shoppingCarts) {
            System.out.println("SHOPPING CART of: "+sc.getWebUser().getLogin_id()+" Created: "+sc.getCreated());

        }
        System.out.println("\n" + "****** PRODUCTS ******");
        for (String productName : productsList.keySet()) {
            Product product = productsList.get(productName);
            System.out.println("PRODUCT: "+productName);
        }
        System.out.println("\n" + "****** SUPPLIERS ******");
        for (String supplierName : suppliersList.keySet()) {
            Supplier supplier = suppliersList.get(supplierName);
            System.out.println("SUPPLIER: "+supplierName);
        }
        System.out.println("\n" + "****** ORDERS ******");
        for (Account a : accounts) {
            for (Order o:a.getOrders()) {
                System.out.println("ORDER: "+o.getNumber());
            }
        }
        System.out.println("\n" +"****** PAYMENTS ******");
        for (Account a : accounts) {
            for (Payment p:a.getPayments()) {
                System.out.println("PAYMENT: "+p.getPaymentId());
            }
        }
        System.out.println();
    }
}
