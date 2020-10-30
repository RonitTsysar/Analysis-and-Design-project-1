import java.util.Date;

public class Order {
    enum OrderStatus {
        New,
        Hold,
        Shipped,
        Delivered,
        Closed
    }

    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;
}
