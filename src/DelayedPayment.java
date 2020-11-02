import java.util.Date;

public class DelayedPayment extends Payment{

    private Date paymentDate;

    public DelayedPayment(String id, Date paid, float total, String details, Account account, Order order) {
        super(id, paid, total, details, account, order);
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
