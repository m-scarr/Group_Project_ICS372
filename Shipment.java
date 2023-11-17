import java.util.Date;

public class Shipment {
    public final int id;
    private ProductList productList;
    private final Date dateOrdered;
    private Date dateProcessed = null;

    public Shipment(ProductList productList) {
        this.id = GroceryStore.getInstance().getId("shipment");
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

    public void print() {
        System.out.println("Product ID: " + productList.get(0).productSupply.id);
        System.out.println("Product Name: " + productList.get(0).productSupply.getName());
        System.out.println("Shipment Quantity: " + productList.get(0).getQuantity());
        System.out.println("New Quantity (when shipment is processed): " + (productList.get(0).productSupply.getQuantity() + productList.get(0).getQuantity()));
    }
}
