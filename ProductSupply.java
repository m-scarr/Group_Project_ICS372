public class ProductSupply {
    public final int id;
    private String name;
    private double price;
    private int quantity;
    private int minReorderQuantity;
    private boolean reorder = false;

    public ProductSupply(String name, double price, int quantity, int minReorderQuantity) {
        this.id = GroceryStore.getInstance().getId("productSupply");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.minReorderQuantity = minReorderQuantity;
        GroceryStore.getInstance().addProductSupply(this);
    }

    private ProductSupply(int id, String name, double price, int quantity, int minReorderQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.minReorderQuantity = minReorderQuantity;
        GroceryStore.getInstance().addProductSupply(this);
    }

    public String getSaveString() {
        String saveString = "";
        saveString += this.id + "|";
        saveString += this.name + "|";
        saveString += this.price + "|";
        saveString += this.quantity + "|";
        saveString += this.minReorderQuantity;
        return saveString;
    }

    public static ProductSupply load(String data) {
        String[] fields = data.split("\\|");
        if (fields.length == 5) {
            try {
                return new ProductSupply(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public int getQuantity() {
        return quantity;
    }

    public boolean addToQuantity(int quantity) {
        if (this.quantity + quantity >= 0) {
            this.quantity += quantity;
            this.quantityTrigger();
            return true;
        }
        return false;
    }

    public Product take(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            this.quantityTrigger();
            return new Product(this, quantity);
        }
        return null;
    }

    public int getMinReorderQuantity() {
        return minReorderQuantity;
    }

    private void quantityTrigger() {
        if (reorder != (quantity <= minReorderQuantity)) {
            reorder = (quantity <= minReorderQuantity);
            //this is only triggered if reorder has changed
            if (reorder) {
                ProductList productList = new ProductList();
                productList.add(this, this.minReorderQuantity * 2);
                new Shipment(productList);
            }
        }
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) { this.price = price; }
    public String getName() { return name; }
    @Override
    public String toString() {
        return "ProductSupply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", minReorderQuantity=" + minReorderQuantity +
                '}';
    }
}
