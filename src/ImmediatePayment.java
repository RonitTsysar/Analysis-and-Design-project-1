import java.util.Date;

public class ImmediatePayment extends Payment {
    private boolean phoneConfirmation;

    public ImmediatePayment(float total) {
        super(total);
    }

    public void setPhoneConfirmation(boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }
}
