import java.util.Date;

public class DelayedPayment extends Payment{

    private Date paymentDate;

    public DelayedPayment(float total) {
        super(total);
    }

    @Override
    public void showDetailsAndConnections() {
        //TODO: print all attributes and connections
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}
