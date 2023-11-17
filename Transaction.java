import java.util.Date;

public class Transaction {
    public final int id;
    private ProductList productList;
    public final Date date;
    public static final double tax = 0;
    public final double total;
    private Member member = null;
    public Transaction(ProductList productList) {
        id = GroceryStore.getInstance().getId("transaction");
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

    private Transaction(int id, String productList, int memberId, double total, String date) {
        this.id = id;
        this.productList = ProductList.load(productList);
        if (memberId != -1) {
            this.member = GroceryStore.getInstance().findMemberById(memberId);
            this.member.addTransaction(this);
        }
        this.total = total;
        this.date = new Date(Date.parse(date));
        GroceryStore.getInstance().loadTransaction(this);
    }

    public Date getDate() {
        return date;
    }

    public String getSaveString() {
        String saveString = "";
        saveString += this.id + "|";
        saveString += this.productList.getSaveString() + "|";
        saveString += (this.member == null ? "-1" : this.member.id) + "|";
        saveString += this.total + "|";
        saveString += this.date;
        return saveString;
    }

    public static Transaction load(String data) {
        String[] fields = data.split("\\|");

        if (fields.length == 5) {
            try {
                return new Transaction(Integer.parseInt(fields[0]),fields[1], Integer.parseInt(fields[2]), Double.parseDouble(fields[3]), fields[4]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void print() {
        System.out.println("Date: " + date);
        if (member != null) {
            System.out.println("Member ID: " + member.id);
            System.out.println("Member Name: " + member.getFirstName() + " " + member.getLastName());
        } else {
            System.out.println("-Non-member-");
        }
        System.out.printf("%-20s | %-10s | %-20s | %-12s\n", "Name", "Quantity", "Individual Price", "Total Price" );
        for (int i = 0; i < productList.size(); i++) {
                System.out.printf("%-20s | %-10s | %-20s | %-12s\n", productList.get(i).productSupply.getName(),
                        productList.get(i).getQuantity(), productList.get(i).getPrice(),
                        productList.get(i).getQuantity() * productList.get(i).getPrice());
        }
        System.out.println("Total: " + productList.getTotal());
    }
}
