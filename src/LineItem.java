public class LineItem {
    private int quantity;
    private int price;

    private Product product;
    private ShoppingCart shoppingCart;
    private Order order;

    public LineItem(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;

        this.product = null;
        this.shoppingCart = null;
        this.order = null;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if(product != null && this.product == null)
            this.product = product;
        else{
            System.out.println("Couldn't connect lineItem to product");
        }
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCart != null && this.shoppingCart == null)
            this.shoppingCart = shoppingCart;
        else
            System.out.println("Couldn't connect lineItem to shoppingCart");
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if(order != null && this.order == null)
            this.order = order;
        else
            System.out.println("Couldn't connect lineItem to order");
    }

    public void delete() {
        if(this.shoppingCart != null) {
            if(!this.shoppingCart.removeLineItem(this))
                System.out.println("No connection to any shoppingCart");
            this.shoppingCart = null;
        }

        if(this.order != null && this.order.getStatus() != OrderStatus.Closed) {
            if(!this.order.removeLineItem(this))
                System.out.println("No connection to any Order");
            this.order = null;
        }
        if(this.product != null){
            if(!this.product.removeLineItem(this)){
                System.out.println("No Product for this line item exist anymore");
            }
            this.product = null;
        }
    }

    @Override
    public String toString() {
        return "LineItem: "+ product.getName() + "\nQuantity: " + this.quantity + "\nPrice: " + this.price;
    }
}
