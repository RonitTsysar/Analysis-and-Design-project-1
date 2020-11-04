import java.util.Date;

public class DelayedPayment extends Payment{

    private Date paymentDate;

    public DelayedPayment(float total,Account account) {
        super(total,account);
    }

    @Override
    public void showDetailsAndConnections() {
        System.out.println("----------Delayed Payment---------\n *** Attributes: ***");
        System.out.println("id: "+getPaymentId()+"\n"
                +"paid: "+getPaid()+"\n"
                +"total: "+getTotal()+"\n"
                +"details: "+getDetails()+"\n"
                +"payment date: "+paymentDate);
        System.out.println("*** Connections: ***");
        System.out.println("Order Number: "+getOrder().getNumber()+"\n"
                +"Account ID: "+getAccount().getId()+"\n\n");
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}
