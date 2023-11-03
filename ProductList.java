import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> content = new ArrayList<Product>();
    public ProductList() {}

    public void add(ProductSupply productSupply, int quantity) {
        for (int i = 0; i < content.size();i++) {
            if (productSupply.id == content.get(i).productSupply.id) {
                content.get(i).addToQuantity(Math.abs(quantity));
                return;
            }
        }
        content.add(new Product(productSupply, quantity));
    }

    public void add(ProductSupply productSupply) {
        for (int i = 0; i < content.size();i++) {
            if (productSupply.id == content.get(i).productSupply.id) {
                content.get(i).addToQuantity(1);
                return;
            }
        }
        content.add(new Product(productSupply));
    }

    public int getQuantityOfProduct(ProductSupply productSupply) {
        for (int i =0; i < content.size();i++) {
            if (content.get(i).productSupply.id == productSupply.id) {
                return content.get(i).getQuantity();
            }
        }
        return 0;
    }

    public double getTotal() {
        double total = 0;
        for (int i =0; i < content.size();i++) {
            total += content.get(i).getQuantity() * content.get(i).getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        String _content = "[\n";
        for (int i = 0; i < content.size(); i++) {
            _content += content.get(i).toString() + ",\n";
        }
        _content += "]\n";
        return "ProductList{" +
                "content=" + _content +
                "total=" + this.getTotal() +
                '}';
    }
}
