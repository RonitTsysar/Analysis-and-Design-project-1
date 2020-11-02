import java.util.Date;


public abstract class Payment {
    private String paymentId;
    private Date paid;
    private float total;
    private String details;

    private Account account;
    private Order order;

    public Payment(String id, Date paid, float total, String details, Account account, Order order) {
        this.paymentId = id;
        //TODO: check if needed
        this.paid = paid;
        this.total = total;
        this.details = details;
        //until here
        this.account = account;
        this.order = order;
    }
}
