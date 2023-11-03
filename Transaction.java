import java.util.Date;

public class Transaction {
    private static int count = 0;
    public final int id;
    private ProductList productList;
    public final Date date;
    public static final double tax = 0;
    public final double total;
    private Member member = null;
    public Transaction(ProductList productList) {
        Transaction.count += 1;
        id = Transaction.count;
        this.productList = productList;
        total = this.productList.getTotal() * (1 + Transaction.tax);
        this.date = new Date();
        GroceryStore.getInstance().addTransaction(this);
    }
    public Transaction(ProductList productList, Member member) {
        this(productList);
        this.member = member;
        this.member.addTransaction(this);
    }

    public ProductList getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "productList=" + productList +
                ", date=" + date +
                ", total=" + total +
                ", member=" + member +
                '}';
    }
}
