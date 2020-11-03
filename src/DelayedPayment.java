import java.util.Date;

public class DelayedPayment extends Payment{

    private Date paymentDate;

    public DelayedPayment(float total) {
        super(total);
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
