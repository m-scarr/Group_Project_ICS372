import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GroceryStore {
    private static int memberIdCount = 0;
    private static int productSupplyIdCount = 0;
    private static int transactionIdCount = 0;
    private static int shipmentIdCount = 0;
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
        String fName = Input.getString("Please enter the first name: ");
        String lName = Input.getString("Please enter the last name: ");
        String address = Input.getString("Please enter the address: ");
        String city = Input.getString("Please enter the city name: ");
        String state = Input.getString("Please enter the state: ");
        String zip = Input.getString("Please enter the zip: ");
        String phone = Input.getString("Please enter the phone number: ");
        new Member(fName, lName, address, city, state, zip, phone);
        char choice = Input.getChar("Registration successful!\nPress y to register another member or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            enrollMember();
        }
    }

    public int getId(String type) {
        if (type.equals("member")) {
            memberIdCount += 1;
            return memberIdCount;
        } else if (type.equals("productSupply")) {
            productSupplyIdCount += 1;
            return productSupplyIdCount;
        } else if (type.equals("shipment")) {
            shipmentIdCount += 1;
            return shipmentIdCount;
        } else {
            transactionIdCount += 1;
            return transactionIdCount;
        }
    }

    public void removeMember() {
        Member member = null;
        while (member == null) {
            member = findMemberById(Input.getInt("Enter a member ID: "));
            if (member == null) {
                System.out.println("Invalid member ID, try again.");
            }
        }
        members.remove(member);
        char choice = Input.getChar("Removal successful.\nPress y to remove another member or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            removeMember();
        }
    }

    public void listMembers() {
        //10) List all members. List name, id, and address of each member
        System.out.println("===================================================");
        System.out.println("Members");
        System.out.println("===================================================");
        for (Member member : members) {
            System.out.println("ID: " +  member.id);
            System.out.println("Name: " + member.getName());
            System.out.println("Address: " + member.getAddress());
            System.out.println("---------------------------------------------------");
        }
        char choice = Input.getChar("Press y to list all members again or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            listMembers();
        }
    }

    public void changeProductPrice() {
        ProductSupply product = null;
        while (product == null) {
            product = findProductSupplyById(Input.getInt("Enter a product ID: "));
            if (product == null) {
                System.out.println("Invalid product ID, try again.");
            }
        }
        double newPrice = Input.getDouble("Enter a new price for " + product.getName() + ": ");
        product.setPrice(newPrice);
        char choice = Input.getChar("Product Name: " + product.getName() +" | New Price: " + product.getPrice() + "\nChange successful.\nPress y to change another price or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            changeProductPrice();
        }
    }

    public void printTransactionsByMember() {
        Member member = null;
        while (member == null) {
            member = findMemberById(Input.getInt("Enter a member ID: "));
            if (member == null) {
                System.out.println("Invalid member ID, try again.");
            }
        }
        Date startDate = Input.getDate("Enter the start date (mm/dd/yyyy): ");
        Date endDate = Input.getDate("Enter the end date (mm/dd/yyyy): ");
        while (endDate.before(startDate)) {
            endDate = Input.getDate("That date was before the start date.\nEnter the end date (mm/dd/yyyy): ");
        }
        ArrayList<Transaction> results = member.getTransactions(startDate, endDate);
        for (int i = 0; i < results.size(); i++) {
            results.get(i).print();
            System.out.println("----------");
        }
        char choice = Input.getChar("Press y to look at more member transactions or anything else to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            printTransactionsByMember();
        }
    }
    public void searchMembers() {
        char choice = 'y';
        String lastName = "";
        ArrayList<Member> foundMembers;
        while (choice == 'y' || choice == 'Y') {
            lastName = Input.getString("Enter the member's last name: ");
            foundMembers = this.searchMembersByLastName(lastName);
            System.out.println("Search Results");
            System.out.println("--------------");
            for (int i = 0; i < foundMembers.size(); i++) {
                System.out.println("ID: " + foundMembers.get(i).id);
                System.out.println("Name: " + foundMembers.get(i).getFirstName() + " " + foundMembers.get(i).getLastName());
                System.out.println("Address: " + foundMembers.get(i).getAddress());
                System.out.println("Fee Paid: " + foundMembers.get(i).feePaid);
                System.out.println("--------------");
            }
            choice = Input.getChar("press Y to attempt another search or any other key to return to the main menu: ");
        }
    }

    public void getProductInfo() {
        //6) given a product name, return the ProductSupply which contains all the product info.
        String productName;
        char choice = 'y';
        int i = this.productSupplies.size();
        while (i == this.productSupplies.size() && (choice == 'y' || choice == 'Y')) {
            productName = Input.getString("Please enter a product name to get all it's information: ");
            i = 0;
            while (this.productSupplies.size() > 0 && i < this.productSupplies.size() && !this.productSupplies.get(i).getName().equalsIgnoreCase(productName)) {
                i++;
            }
            if (i == this.productSupplies.size()) {
                choice = Input.getChar("No product by that name was found, press Y to attempt another search or any other key to return to the main menu: ");
            }
        }
        if (i < this.productSupplies.size()) {
            System.out.println("-------------");
            System.out.println("ID: " + productSupplies.get(i).id);
            System.out.println("Name: " + productSupplies.get(i).getName());
            System.out.println("Price: " + productSupplies.get(i).getPrice());
            System.out.println("Quantity: " + productSupplies.get(i).getQuantity());
            System.out.println("-------------");
            choice = Input.getChar("Press y to search for another item, and any other key to return to the main menu: ");
            if (choice == 'y' || choice == 'Y') {
                getProductInfo();
            }
        }
    }

    public void listOutstandingOrders() {
        System.out.println("ORDERS");
        System.out.printf("%-20s | %-5s | %-10s\n", "Name", "ID", "Quantity" );
        for (int i = 0; i < shipments.size(); i++) {
            if (!shipments.get(i).isProcessed()) {
                System.out.printf("%-20s | %-5s | %-10s\n",shipments.get(i).getProductList().get(0).productSupply.getName(), shipments.get(i).getProductList().get(0).productSupply.id, shipments.get(i).getProductList().get(0).getQuantity());
            }
        }
        char choice = Input.getChar("Press y to list the orders again or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            listOutstandingOrders();
        }
    }

    public void listProducts() {
        //11) list all products, each product is in the productSupplies field.
        System.out.printf("%-20s | %-10s | %-10s | %-30s\n", "Name", "Quantity", "Price", "Minimum Reorder Quantity" );
        for (ProductSupply P: productSupplies) {
            System.out.printf("%-20s | %-10s | %-10s | %-30s\n", P.getName(), P.getQuantity(), P.getPrice(), P.getMinReorderQuantity());
        }
        char choice = Input.getChar("Press y to list the products again or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            listProducts();
        }
    }

    public void addNewProductLine() {
        //4) Add product, get the info for the name, the price, the beginning quantity, and the minreorder quantity. you can then simply call
        //   new ProductSupply(name, price, quantity, minReorderQuantity) and everything else will be taken care of
        String productName;
        double price;
        int beginingQuantity;
        int minReorderQuantiity;
        char choice = 'y';
        while (choice == 'y') {
            productName = Input.getString("Enter product name: ");
            price = Input.getDouble("Enter product price: ");
            beginingQuantity = Input.getInt("Enter product beginning quantity: ");
            minReorderQuantiity = Input.getInt("Enter product minreorder quantity: ");
            ProductSupply Product = new ProductSupply(productName, price, beginingQuantity, minReorderQuantiity);
            System.out.println("You have successfully added " + Product.getName());
            choice = Input.getChar("Press y to add another product or any other key to return to the main menu: ");
        }
    }

    public void checkout() {
        ProductList cart = new ProductList();
        boolean voidTransaction = false;
        int memberId = -1;
        Member member = null;
        while (memberId!=0 && member == null) {
            memberId =  Input.getInt("Enter a member ID or 0 for non-member: ");
            if (memberId != 0) {
                member = findMemberById(memberId);
                if (member == null) {
                    System.out.println("Invalid member ID, try again.");
                }
            }
        }
        int productId = -1;
        ProductSupply productSupply;
        while (productId != 0) {
            productId = Input.getInt("Enter a product ID or 0 to finish: ");
            if (productId > 0) {
                productSupply = this.getProductSupply(productId);
                if (productSupply != null) {
                    cart.add(productSupply);
                } else {
                    System.out.println("Please enter a valid product ID.");
                }
            } else if (productId < 0) {
                System.out.println("Please enter a valid product ID.");
            }
        }
        char choice = Input.getChar("Press y to finish the transaction or any other key to void it and return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            Transaction newTransaction = ((member == null) ?new Transaction(cart):new Transaction(cart, member));
            newTransaction.print();
        }
    }

    public void processShipments() {
        char choice = 'y';
        int i = 0;
        while (shipments.size() > 0 && i < shipments.size() && choice != 'n' && choice != 'N') {
            shipments.get(i).print();
            choice = Input.getChar("Press Y to process this shipment, N to return to the main menu, or any other key to view the next shipment: ");
            if (choice == 'y' || choice == 'Y') {
                processShipment(shipments.get(i));
            }
            i++;
        }
        choice = Input.getChar("Press y to process shipments again or any other key to return to the main menu: ");
        if (choice == 'y' || choice == 'Y') {
            processShipments();
        }
    }

    //------------------------------------------------------------------

    public Member findMemberById(int id) {
        int i = 0;
        while (i < members.size() && members.get(i).id != id) {
            i++;
        }
        return (i < members.size() ? members.get(i) : null);
    }

    public ProductSupply findProductSupplyById(int id) {
        int i = 0;
        while (i < productSupplies.size() && productSupplies.get(i).id != id) {
            i++;
        }
        return (i < productSupplies.size() ? productSupplies.get(i) : null);
    }

    private ArrayList<Member> searchMembersByLastName(String lastName) {
        ArrayList<Member> foundMembers = new ArrayList<Member>();
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getLastName().equalsIgnoreCase(lastName)) {
                foundMembers.add(members.get(i));
            }
        }
        return foundMembers;
    }

    public void addTransaction(Transaction transaction) {
        this.take(transaction.getProductList());
        transactions.add(transaction);
    }

    public void loadTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void addShipment(Shipment newShipment) {
        shipments.add(newShipment);
    }

    private void processShipment(Shipment shipment) {
        shipment.process();
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
            if (id == productSupplies.get(i).id) {
                return productSupplies.get(i);
            }
        }
        return null;
    }

    public void saveAs() {
        String saveFile = Input.getString("Please enter a file name: ");
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write("MEMBERS-" + memberIdCount + "\n");
            for (int i = 0; i < members.size(); i++) {
                writer.write(members.get(i).getSaveString() + "\n");
            }
            writer.write("PRODUCTS-" + productSupplyIdCount + "\n");
            for (int i = 0; i < productSupplies.size(); i++) {
                writer.write(productSupplies.get(i).getSaveString() + "\n");
            }
            writer.write("SHIPMENTS-" + shipmentIdCount + "\n");
            for (int i = 0; i < shipments.size(); i++) {
                writer.write(shipments.get(i).getSaveString() + "\n");
            }
            writer.write("TRANSACTIONS-" + transactionIdCount + "\n");
            for (int i = 0; i < transactions.size(); i++) {
                writer.write(transactions.get(i).getSaveString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public void loadFrom() {
        File loadFile = Input.getFile("Please enter a file name: ");
        members.clear();
        transactions.clear();
        shipments.clear();
        productSupplies.clear();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loadFile.getName()));
            line = reader.readLine();
            memberIdCount = Integer.parseInt(line.split("-")[1]);
            System.out.println(memberIdCount);
            while (!line.contains("PRODUCTS-")) {
                line = reader.readLine();
                if (!line.contains("PRODUCTS-")) {
                    Member.load(line);
                    System.out.println(line);
                } else {
                    productSupplyIdCount = Integer.parseInt(line.split("-")[1]);
                    System.out.println(productSupplyIdCount);
                }
            }
            while (!line.contains("SHIPMENTS-")) {
                line = reader.readLine();
                if (!line.contains("SHIPMENTS-")) {
                    ProductSupply.load(line);
                    System.out.println(line);
                } else {
                    shipmentIdCount = Integer.parseInt(line.split("-")[1]);
                    System.out.println(shipmentIdCount);
                }
            }
            while (!line.contains("TRANSACTIONS-")) {
                line = reader.readLine();
                if (!line.contains("TRANSACTIONS-")) {
                    Shipment.load(line);
                    System.out.println(line);
                } else {
                    transactionIdCount = Integer.parseInt(line.split("-")[1]);
                    System.out.println(transactionIdCount);
                }
            }
            while (line!=null && !line.isEmpty()) {
                line = reader.readLine();
                Transaction.load(line);
                System.out.println(line);
            }
        } catch(Exception e) {

        }
    }

    public static void main(String[] args) {
        new ProductSupply("Apple",.97,20,10);
        new ProductSupply("Banana",1.32,40,15);
        new ProductSupply("Carrot",1.97,30,12);
        new Member("Michael", "Scarr","1243 Nowhere st.", "Nowhereville", "MN", "55123", "123-456-7890");
        new Member("John", "Scarr","0987 Notastreet rd.", "Nothington", "MN", "55103", "123-456-7890");
        new Member("Eric", "Johnson","5467 Null terrace", "Nopetropolis", "MN", "55102", "123-456-7890");
        CommandLineInterface.start();
    }

}
