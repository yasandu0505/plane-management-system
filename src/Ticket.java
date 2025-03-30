import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Ticket {

    // Instance variables for ticket information
    private final String ROW;
    private final int SEAT_NUMBER;
    private final int PRICE;
    private final Person PERSON;

    // Constructor to initialize ticket information
    public Ticket(String row, int seat_number, int price, Person person) {
        this.ROW = row;
        this.SEAT_NUMBER = seat_number;
        this.PRICE = price;
        this.PERSON = person;
    }

    // Getter method for retrieving the ticket price
    public int get_price() {
        return PRICE;
    }

    // Method to display ticket information
    public void get_ticket_info() {
        System.out.println("Seat row : " + ROW);
        System.out.println("Seat number : " + SEAT_NUMBER);
        System.out.println("Price : £" + PRICE);
        System.out.println("Name : " + PERSON.get_name() + " " + PERSON.get_surname());
        System.out.println("Email : " + PERSON.getE_mail());
    }

    // Method to save reservation details to a text file
    public void save() {
        // Constructing the file path based on seat class and number
        String file_path = ROW.toUpperCase() + SEAT_NUMBER + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file_path)))) {
            // Writing ticket information to the file
            writer.write("Seat class : " + ROW.toUpperCase());
            writer.newLine();
            writer.write("Seat number : " + SEAT_NUMBER);
            writer.newLine();
            writer.write("Price : £" + PRICE);
            writer.newLine();
            writer.write("Name : " + PERSON.get_name() + " " + PERSON.get_surname());
            writer.newLine();
            writer.write("Email : " + PERSON.getE_mail());

            System.out.println("Reservation invoice saved successfully....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
