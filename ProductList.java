import java.util.ArrayList;

public class ProductList {
    private ArrayList<Product> content = new ArrayList<Product>();
    public ProductList() {}

    private ProductList(ArrayList<Product> content) {
        this.content = content;
    }

    public int size() { return content.size(); }
    public Product get(int i) { return content.get(i); }

    public static ProductList load(String data) {
        String[] products = data.substring(1, data.length() - 1).split(",");
        int productId;
        int quantity;
        ArrayList<Product> newContent = new ArrayList<Product>();
        for (int i = 0; i < products.length; i++) {
            productId = Integer.parseInt(products[i].split(" x")[0]);
            quantity = Integer.parseInt(products[i].split(" x")[1]);
            newContent.add(new Product(GroceryStore.getInstance().findProductSupplyById(productId), quantity));
        }
        return new ProductList(newContent);
    }

    public String getSaveString() {
        String saveString = "[";
        for (int i = 0; i < content.size();i++) {
            saveString += content.get(i).productSupply.id + " x"+content.get(i).getQuantity()+(i < content.size() - 1? "," : "]");
        }
        return saveString;
    }
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
