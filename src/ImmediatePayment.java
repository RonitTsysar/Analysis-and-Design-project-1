import java.util.Date;

public class ImmediatePayment extends Payment {
    private boolean phoneConfirmation;

    public ImmediatePayment(float total,Account account) {
        super(total,account);
    }

    @Override
    public void showDetailsAndConnections() {
        System.out.println("----------Immediate Payment---------\n *** Attributes: ***");
        System.out.println("id: "+getPaymentId()+"\n"
                +"paid: "+getPaid()+"\n"
                +"total: "+getTotal()+"\n"
                +"details: "+getDetails()+"\n"
                +"phone confirmation: "+phoneConfirmation);
        System.out.println("*** Connections: ***");
        System.out.println("Order Number: "+getOrder().getNumber()+"\n"
                +"Account ID: "+getAccount().getId()+"\n\n");
    }

    public void setPhoneConfirmation(boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }


}
