public class CommandLineInterface {
    private Runnable[] choices = new Runnable[15];
    private static CommandLineInterface instance = null;
    public static CommandLineInterface start() {
        if (instance==null) {
            CommandLineInterface.instance = new CommandLineInterface();
        }
        return CommandLineInterface.instance;
    }

    private CommandLineInterface() {
        choices[0] = () -> {
            GroceryStore.getInstance().enrollMember();
        };
        choices[1] = () -> {
            GroceryStore.getInstance().removeMember();
        };
        choices[2] = () -> {
            GroceryStore.getInstance().searchMembers();
        };
        choices[3] = () -> {
            GroceryStore.getInstance().addNewProductLine();
        };
        choices[4] = () -> {
            GroceryStore.getInstance().checkout();
        };
        choices[5] = () -> {
            GroceryStore.getInstance().getProductInfo();
        };
        choices[6] = () -> {
            GroceryStore.getInstance().processShipments();
        };
        choices[7] = () -> {
            GroceryStore.getInstance().changeProductPrice();
        };
        choices[8] = () -> {
            GroceryStore.getInstance().printTransactionsByMember();
        };
        choices[9] = () -> {
            GroceryStore.getInstance().listMembers();
        };
        choices[10] = () -> {
            GroceryStore.getInstance().listProducts();
        };
        choices[11] = () -> {
            GroceryStore.getInstance().listOutstandingOrders();
        };
        choices[12] = () -> {
            GroceryStore.getInstance().saveAs();
        };
        choices[13] = () -> {
            GroceryStore.getInstance().loadFrom();
        };
        choices[14] = () -> {
            System.exit(0);
        };
        this.mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("1. Enroll a new member");
            System.out.println("2. Remove a member");
            System.out.println("3. Retrieve member info");
            System.out.println("4. Add new Product line");
            System.out.println("5. Checkout mode");
            System.out.println("6. Retrieve product info");
            System.out.println("7. Process shipment");
            System.out.println("8. Change product price");
            System.out.println("9. Print the transactions of a members");
            System.out.println("10. List all members");
            System.out.println("11. List all products");
            System.out.println("12. List all outstanding orders");
            System.out.println("13. Save members, products, transactions and shipments");
            System.out.println("14. Load members, products, transactions and shipments");
            System.out.println("15. Exit");

            int choice = Input.getInt("Enter your choice: ");

            choices[choice - 1].run();
        }
    }
}
