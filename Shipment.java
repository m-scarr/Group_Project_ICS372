import java.util.ArrayList;
import java.util.Date;

public class Shipment {
    private static int count = 0;
    public final int id;
    private ProductList productList;
    private final Date dateOrdered;
    private Date dateProcessed = null;

    public Shipment(ProductList productList) {
        Shipment.count +=1;
        this.id = Shipment.count;
        this.productList = productList;
        this.dateOrdered = new Date();
        GroceryStore.getInstance().addShipment(this);
    }


    private Shipment(int id, String productList, String dateOrdered, String dateProcessed) {
        this.id = id;
        this.productList = ProductList.load(productList);
        this.dateOrdered = new Date(Date.parse(dateOrdered));
        if (!dateProcessed.equals("null")) {
            this.dateProcessed = new Date(Date.parse(dateProcessed));
        }
        GroceryStore.getInstance().addShipment(this);
    }

    public String getSaveString() {
        String saveString = "";
        saveString += this.id + "|";
        saveString += this.productList.getSaveString() + "|";
        saveString += this.dateOrdered + "|";
        saveString += (this.dateProcessed==null ? "null" : this.dateProcessed);
        return saveString;
    }

    public static Shipment load(String data) {
        String[] fields = data.split("\\|");

        if (fields.length == 4) {
            try {
                return new Shipment(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void process() {
        dateProcessed = new Date();
    }

    public boolean isProcessed() {
        return dateProcessed != null;
    }
    public ProductList getProductList() {
        return productList;
    }

    /*@Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", productList=" + productList +
                ", dateOrdered=" + dateOrdered +
                ", dateReceived=" + dateReceived +
                ", received=" + received +
                '}';
    }*/
}
