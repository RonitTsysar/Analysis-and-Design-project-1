import java.util.Date;

public class ImmediatePayment extends Payment {
    private boolean phoneConfirmation;

    public ImmediatePayment(float total) {
        super(total);
    }

    @Override
    public void showDetailsAndConnections() {
        //TODO: print all attributes and connections

    }

    public void setPhoneConfirmation(boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }


}
