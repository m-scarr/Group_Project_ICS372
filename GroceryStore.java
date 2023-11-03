import java.util.ArrayList;
import java.util.Scanner;

public class GroceryStore {
    private static GroceryStore instance = new GroceryStore();
    public static final double currentMembershipFee = 20;
    private ArrayList<ProductSupply> productSupplies = new ArrayList<ProductSupply>();
    private ArrayList<Shipment> shipments = new ArrayList<Shipment>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private ArrayList<Member> members = new ArrayList<Member>();
    private GroceryStore() {}
    public static GroceryStore getInstance() {
        return GroceryStore.instance;
    }
//----------------------------------------------------------------------------------------------------
    public void enrollMember() {
        // 1) enroll a member! you'll want to get all the info, then simply calling new Member(name, address, city, state, phoneNumber)
        // and it will automatically add itself to the member list.
    }

    public void removeMember(int id) {
        // 2) Remove a member: a member may be removed; the system would need the member’s id for this purpose. remember ArrayLists have a .remove method
    }

    public void listMembers() {
        //10) List all members. List name, id, and address of each member.
    }

    public ProductSupply getProductInfo(String name) {
        //6) given a product name, return the ProductSupply which contains all the product info.
        return null;
    }

    public void changePrice(int productSupplyId, double price) {
        //8) change price, you may have to go into the ProductSupply class and add a setPrice method.
    }

    public void listProducts() {
        //11) list all products, each product is in the productSupplies field.
    }

    public void addNewProductLine() {
        //4) Add product, get the info for the name, the price, the beginning quantity, and the minreorder quantity. you can then simply call
        //   new ProductSupply(name, price, quantity, minReorderQuantity) and everything else will be taken care of
    }
    public ArrayList<Member> searchMembers(String firstName, String lastName) {
        ArrayList<Member> foundMembers = new ArrayList<Member>();
        for (int i = 0; i < members.size(); i++) {
            if ((members.get(i).getFirstName().equalsIgnoreCase(firstName) && members.get(i).getLastName().equalsIgnoreCase(lastName)) ||
                    members.get(i).getLastName().equalsIgnoreCase(lastName)) {
                foundMembers.add(members.get(i));
            }
        }
        return foundMembers;
    }

    public void enterCheckoutMode() {
        Scanner scanner = new Scanner(System.in);
        ProductList cart = new ProductList();
        ProductSupply productSupply;
        boolean voidTransaction = false;
        while (true) {
            System.out.print("Enter a product ID (or nothing to complete checkout or \"void\" to void out transaction): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("void")) {
                voidTransaction = true;
                break;
            }
            if (input.isEmpty()) {
                break;
            }

            try {
                productSupply = this.getProductSupply(Integer.parseInt(input));
                if (productSupply!= null) {
                    cart.add(productSupply);
                } else {
                    System.out.println("Invalid input. Please enter a valid product ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }

        }
        scanner.close();
        if (!voidTransaction) {
            Transaction newTransaction = new Transaction(cart);
            System.out.println(newTransaction);
        }
    }

    //------------------------------------------------------------------

    public void addTransaction(Transaction transaction) {
        this.take(transaction.getProductList());
        transactions.add(transaction);
    }

    public void addShipment(Shipment newShipment) {
        shipments.add(newShipment);
    }

    public void receiveShipment(Shipment shipment) {
        shipment.received();
        for (int i = 0; i < productSupplies.size(); i++) {
            productSupplies.get(i).addToQuantity(shipment.getProductList().getQuantityOfProduct(productSupplies.get(i)));
        }
    }

    public void addMember(Member member) { members.add(member);}

    public void addProductSupply(ProductSupply productSupply) { productSupplies.add(productSupply); }
    private void take(ProductList productList) {
        for (int i = 0; i < productSupplies.size(); i++) {
            productSupplies.get(i).take(productList.getQuantityOfProduct(productSupplies.get(i)));
        }
    }

    private ProductSupply getProductSupply(int id) {
        for (int i = 0; i < productSupplies.size(); i++) {
            if (id==productSupplies.get(i).id) {
                return productSupplies.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new ProductSupply("Apple",.97,20,10);
        new ProductSupply("Banana",1.32,40,15);
        new ProductSupply("Carrot",1.97,30,12);
        GroceryStore.instance.enterCheckoutMode();
    }

}
