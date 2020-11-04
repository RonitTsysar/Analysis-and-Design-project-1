import java.util.Date;


public abstract class Payment {
    private String paymentId;
    private Date paid;
    private float total;
    private String details;

    private Account account;
    private Order order;
    private static int idCounter=1;

    public Payment(float total,Account account) {
        this.paymentId = "" + idCounter;
        this.total = total;
        this.details = "";
        idCounter++;
        this.account=account;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.account = order.getAccount();
    }

    public Date getPaid() {
        return paid;
    }

    public float getTotal() {
        return total;
    }

    public String getDetails() {
        return details;
    }

    public Account getAccount() {
        return account;
    }

    public Order getOrder() {
        return order;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void delete(){
        this.account = null;
        this.order = null;
    }

    public abstract void showDetailsAndConnections();

}
