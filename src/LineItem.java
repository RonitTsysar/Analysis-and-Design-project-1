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
        throw new RuntimeException("Couldn't connect lineItem to product");
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if(shoppingCart != null && this.shoppingCart == null)
            this.shoppingCart = shoppingCart;
        throw new RuntimeException("Couldn't connect lineItem to shoppingCart");
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if(order != null && this.order == null)
            this.order = order;
        throw new RuntimeException("Couldn't connect lineItem to order");
    }

    @Override
    public String toString() {
        return "LineItem:" +
                "quantity=" + quantity +
                ", price=" + price +
                ", product=" + product +
                '}';
    }
}
