import java.util.ArrayList;
import java.util.Date;

public class Member {
    public final int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    public final Date dateJoined;
    public final double feePaid;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Member(String firstName,String lastName, String address, String city, String state,String zip, String phoneNumber) {
        id = GroceryStore.getInstance().getId("member");
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.feePaid = GroceryStore.currentMembershipFee;
        this.dateJoined = new Date();
        GroceryStore.getInstance().addMember(this);
    }

    private Member(int id,String firstName,String lastName, String address, String city, String state,String zip, String phoneNumber, double feePaid, String dateJoined) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.feePaid = feePaid;
        this.dateJoined = new Date(Date.parse(dateJoined));
        GroceryStore.getInstance().addMember(this);
    }

    public String getSaveString() {
        String saveString = "";
        saveString += this.id + "|";
        saveString += this.firstName + "|";
        saveString += this.lastName + "|";
        saveString += this.address + "|";
        saveString += this.city + "|";
        saveString += this.state + "|";
        saveString += this.zip + "|";
        saveString += this.phoneNumber + "|";
        saveString += this.feePaid + "|";
        saveString += this.dateJoined;
        return saveString;
    }

    public static Member load(String data) {
        String[] fields = data.split("\\|");

        if (fields.length == 10) {
            try {
                return new Member(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], Double.parseDouble(fields[8]), fields[9]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions(Date startDate, Date endDate) {
        ArrayList<Transaction> results = new ArrayList<Transaction>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDate().after(startDate) && transactions.get(i).getDate().before(endDate)) {
                results.add(transactions.get(i));
            }
        }
        return results;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    public String getAddress() {
        return address + ", " + city + ", " + state + ", " + zip;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateJoined=" + dateJoined +
                ", feePaid=" + feePaid +
                '}';
    }
}
