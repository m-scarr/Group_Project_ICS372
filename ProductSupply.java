public class ProductSupply {
    public final int id;
    private static int count = 0;
    private String name;
    private double price;
    private int quantity;
    private int minReorderQuantity;
    private boolean reorder = false;

    public ProductSupply(String name, double price, int quantity, int minReorderQuantity) {
        ProductSupply.count += 1;
        this.id = ProductSupply.count;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.minReorderQuantity = minReorderQuantity;
        GroceryStore.getInstance().addProductSupply(this);
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

    private void quantityTrigger() {
        if (reorder != (quantity <= minReorderQuantity)) {
            reorder = (quantity <= minReorderQuantity);
            //this is only triggered if reorder has changed
            if (reorder) {
                ProductList productList = new ProductList();
                productList.add(this, this.minReorderQuantity * 2);
                Shipment newShipment = new Shipment(productList);
                GroceryStore.getInstance().receiveShipment(newShipment);
            }
        }
    }

    public double getPrice() {
        return price;
    }

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
