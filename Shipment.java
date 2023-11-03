import java.util.ArrayList;
import java.util.Date;

public class Shipment {
    private static int count = 0;
    public final int id;
    private ProductList productList;
    private Date dateOrdered;
    private Date dateReceived;
    private boolean received = false;

    public Shipment(ProductList productList) {
        Shipment.count +=1;
        this.id = Shipment.count;
        this.productList = productList;
        this.dateOrdered = new Date();
        GroceryStore.getInstance().addShipment(this);
    }
    public void received() {
        dateReceived = new Date();
        received = true;
    }
    public ProductList getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", productList=" + productList +
                ", dateOrdered=" + dateOrdered +
                ", dateReceived=" + dateReceived +
                ", received=" + received +
                '}';
    }
}
