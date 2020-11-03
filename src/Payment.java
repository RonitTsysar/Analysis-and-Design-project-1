import java.util.Date;


public abstract class Payment {
    private String paymentId;
    private Date paid;
    private float total;
    private String details;

    private Account account;
    private Order order;
    private static int idCounter=1;

    public Payment(float total) {
        this.paymentId = "" + idCounter;
        this.total = total;
        this.details = "";
        idCounter++;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.account = order.getAccount();
    }

    public String getPaymentId() {
        return paymentId;
    }
}
