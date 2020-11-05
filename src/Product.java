import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private float price;



    private Supplier supplier;
    private PremiumAccount premiumAccount;
    private List<LineItem> lineItemsList;



    public Product(String id, String name, Supplier supplier,float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.premiumAccount = null;
        this.supplier = supplier;
        this.lineItemsList = new ArrayList<>();

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<LineItem> getLineItemsList() {
        return lineItemsList;
    }

    public String getId() {
        return id;
    }

    public void showLineItems(){
        if(this.lineItemsList.size() < 1)
            System.out.println("No Line Items");
        else{
            for (LineItem lineItem : lineItemsList) {
                System.out.println(lineItemsList.indexOf(lineItem)+1 + ". "  + "Quantity: " +lineItem.getQuantity() + " Price : " + lineItem.getPrice());
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        this.premiumAccount = premiumAccount;

    }

    @Override
    public String toString() {
        return "Product: " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void delete(){
        this.supplier.removeProduct(this);
        for (LineItem lineItem : lineItemsList) {
            lineItem.delete();
        }
        this.lineItemsList = null;// optional

        if(premiumAccount != null){
            if(!this.premiumAccount.removeProduct(this)) {
                System.out.println("This product wasn't in any Premium Account list");
            }
            premiumAccount = null;
        }
    }

    public void showDetailsAndConnections() {
        System.out.println("----------Product---------\n *** Attributes: ***");
        System.out.println("id: "+id+"\n"
                +"name: "+name+"\n"
                +"price: "+price);
        System.out.println("*** Connections: ***");
        if(premiumAccount != null) {
            System.out.println("Premium Account ID: " + premiumAccount.getId() + "\n"
                    + "Line Items: " + getLineItemListOf_id_quan_price() + "\n"
                    + "Supplier: " + supplier.getName() + "-" + supplier.getId() + "\n\n");
        }
        else{
            System.out.println("Premium Account ID: \n"
                    + "Line Items: " + getLineItemListOf_id_quan_price() + "\n"
                    + "Supplier: " + supplier.getName() + "-" + supplier.getId() + "\n\n");
        }
    }

    public String getLineItemListOf_id_quan_price() {
        String lineItemsDetails="";
        if(lineItemsList==null) return "Line Items list empty";
        for (LineItem li:lineItemsList) {
            lineItemsDetails+="id:"+id+" quantity:"+li.getQuantity()+" price:"+li.getPrice()+"\n";
        }
        return lineItemsDetails;
    }

    public boolean removeLineItem(LineItem lineItem) {
        if(!this.lineItemsList.remove(lineItem))
            return false;
        return true;
    }


    public boolean addLineItem(LineItem newLineItem) {
        if(this.lineItemsList.contains(newLineItem)){
            return false;
        }
        this.lineItemsList.add(newLineItem);
        newLineItem.setProduct(this);
        return true;
    }
}
