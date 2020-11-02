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

    private Account account;

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

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<LineItem> getLineItems(){
        return lineItems;
    }
//
//    public void addLineItem(int quantity, int price) {
//
//    }

    public void addImmediatePayment(String paymentID, Date paidDate, float total, String details, boolean phoneConfirmation) {
        ImmediatePayment payment= new ImmediatePayment(paymentID, paidDate, total, details, account, this);
        payment.setPhoneConfirmation(phoneConfirmation);
        payments.add(payment);
    }

    public void addDelayedPayment(String paymentID, Date paiddate, float total, String details, Date paymentDate) {
        DelayedPayment payment= new DelayedPayment(paymentID, paiddate, total, null, account, this);
        payment.setPaymentDate(paymentDate);
        payments.add(payment);
    }
}
