import java.util.ArrayList;
import java.util.Date;

public class Member {
    private static int count = 0;
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
        Member.count += 1;
        id = Member.count;
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

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + lastName;
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
