public class Product {
    public final ProductSupply productSupply;
    private int quantity = 1;

    public Product(ProductSupply productSupply, int quantity) {
        this(productSupply);
        this.quantity = quantity;
    }
    public Product(ProductSupply productSupply) {
        this.productSupply = productSupply;
    }
    public boolean addToQuantity(int quantity) {
        if (this.quantity + quantity >= 0) {
            this.quantity += quantity;
            return true;
        }
        return false;
    }
    public int getQuantity() {
        return this.quantity;
    }

    public double getPrice() {
        return this.productSupply.getPrice();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productSupply.id=" + productSupply.id +
                ", productSupply.name=" + productSupply.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
