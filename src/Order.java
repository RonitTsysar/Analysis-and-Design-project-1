import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;

    private List<Payment> payments;
    private List<LineItem> lineItems;

    //todo: setAccount(Account account)

    public Order(String number, Date ordered, Date shipped, Address ship_to, OrderStatus status, float total, Account account) {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.ship_to = ship_to;
        this.status = status;
        this.total = total;

        this.payments = new ArrayList<>();
        this.lineItems = new ArrayList<>();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
