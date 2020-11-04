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
    private static int orderId = 1;

    private Account account;

    private List<Payment> payments;

    private List<LineItem> lineItems;

    //todo: setAccount(Account account)

    public Order(Account account) {
        this.number = "" + orderId;
        orderId++;
        this.status = OrderStatus.New;
        this.account = account;
        this.total = 0;
        this.payments = new ArrayList<>();
        this.lineItems = new ArrayList<>();

    }

    public String getNumber() {
        return number;
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
//    public void addImmediatePayment(Payment payment) {
//        payments.add(payment);
//    }
//
//    public void addDelayedPayment(String paymentID, Date paiddate, float total, String details, Date paymentDate) {
//        DelayedPayment payment= new DelayedPayment(paymentID, paiddate, total, null, account, this);
//        payment.setPaymentDate(paymentDate);
//        payments.add(payment);
//    }
    public boolean addPayment(Payment newPayment){
        if(newPayment != null){
            for (Payment payment : payments) {
                if(payment.getPaymentId().equals(newPayment.getPaymentId()))
                    return false;
            }
        }
        this.payments.add(newPayment);
        assert newPayment != null;
        newPayment.setOrder(this);
        return true;
    }

    public boolean addLineItem(LineItem lineItem){
        if(lineItem == null)
            return false;
        this.lineItems.add(lineItem);
        lineItem.setOrder(this);
        total += lineItem.getPrice() * lineItem.getQuantity();
        return true;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public void setShip_to(Address ship_to) {
        this.ship_to = ship_to;
    }

    public float getTotal() {
        return total;
    }

    public Account getAccount() {
        return account;
    }

    public void delete() {
        for (Payment payment : payments) {
            payment.delete();
        }
        this.payments = null;
        for (LineItem lineItem : lineItems) {
            lineItem.delete();
        }
    }

    public void showDetailsAndConnections() {
        //TODO: print all attributes and connections
    }

    @Override
    public String toString() {
        return "Order{" +
                "number='" + number + '\'' +
                ", ordered=" + ordered +
                ", shipped=" + shipped +
                ", ship_to=" + ship_to +
                ", status=" + status +
                ", total=" + total +
                ", account=" + account +
                ", payments=" + payments +
                ", lineItems=" + lineItems +
                '}';
    }
}
