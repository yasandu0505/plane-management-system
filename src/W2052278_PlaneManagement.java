// Importing the Scanner class for user input
import java.util.Scanner;

// Main class
public class W2052278_PlaneManagement {

    // Scanner object for user input
    public static Scanner input = new Scanner(System.in);

    // Arrays to represent seat rows A, B, C, D
    private static int[] row_A = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] row_B = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] row_C = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static int[] row_D = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    // 2D array representing all seat rows
    private static final int[][] ALL_SEAT_ROWS = {row_A, row_B, row_C, row_D};

    // Array to store ticket data
    private static final Ticket[] TICKET_DATA = new Ticket[52];

    // Global variables for seat class, seat number, and continue input
    private static String seat_class;
    private static String continue_input;
    private static int seat_number;

    // Main method to execute the program
    public static void main(String[] args) {
        // Array containing valid menu options
        String[] user_choice_options = {"1", "2", "3", "4", "5", "6", "0"};
        String user_choice;

        System.out.println("\n---------- Welcome to the Plane Management System -----------");

        while (true) {
            // Flag for user input validation
            boolean user_input_validation = false;

            // Displaying the main menu options
            System.out.println("\n*************************************************************");
            System.out.println("*                     MENU OPTIONS                          *");
            System.out.println("*************************************************************\n");
            System.out.println("    1) Buy a seat");
            System.out.println("    2) Cancel a seat");
            System.out.println("    3) Find first available seat");
            System.out.println("    4) Show seating plan");
            System.out.println("    5) Print ticket information and total sales");
            System.out.println("    6) Search ticket");
            System.out.println("    0) Quit");
            System.out.println("\n*************************************************************");

            // Validate user input for menu choice
            while (true) {
                System.out.print("\nPlease select an option : ");
                user_choice = input.nextLine();

                for (String option : user_choice_options) {
                    if (option.equals(user_choice)) {
                        user_input_validation = true;
                        break;
                    }
                }

                if (user_input_validation) {
                    break;
                } else {
                    System.out.println("Invalid input (Select the correct option)");
                }
            }

            // Execute the corresponding functionality based on user choice
            switch (user_choice) {
                case "1":
                    buy_seat();
                    break;
                case "2":
                    cancel_seat();
                    break;
                case "3":
                    find_first_available();
                    break;
                case "4":
                    show_seating_plan();
                    break;
                case "5":
                    print_tickets_info();
                    break;
                case "6":
                    search_ticket();
                    break;
                case "0":
                    quit();
                    break;
                default:
                    System.out.println("Invalid input.......");
            }
        }
    }

    // Method for buying a seat
    public static void buy_seat() {
        // Variable to store seat price
        int price;

        System.out.println("\n*******************   Buying a seat   ************************");

        boolean continue_buying_seats = true;

        while (continue_buying_seats) {
            // getting the seat class
            seat_class = get_input_seat_class();

            // getting the seat number
            seat_number = get_input_seat_number();

            // Determine the row index based on seat class
            int num = seat_class.charAt(0) - 97;

            // Check if the seat is available
            if (ALL_SEAT_ROWS[num][seat_number - 1] == 0) {
                // Collect user information for the ticket
                System.out.print("\nEnter your first name : ");
                String user_name = input.nextLine();

                System.out.print("Enter your surname : ");
                String user_surname = input.nextLine();

                System.out.print("Enter your email : ");
                String user_email = input.nextLine();

                // Create a Person object with user information
                Person person = new Person(user_name, user_surname, user_email);

                // Determine the seat price based on seat number
                if (seat_number > 0 && seat_number <= 5) {
                    price = 200;
                } else if (seat_number >= 6 && seat_number <= 9) {
                    price = 150;
                } else {
                    price = 180;
                }

                // Display seat cost and reservation success message
                System.out.println("\nSeat cost : £" + price);
                System.out.println("Seat reserved successfully....");

                // Save the ticket information in the ticket_data array
                switch (seat_class) {
                    case "a":
                        TICKET_DATA[seat_number - 1] = new Ticket(seat_class, seat_number, price, person);
                        TICKET_DATA[seat_number - 1].save();
                        break;
                    case "b":
                        TICKET_DATA[14 + seat_number - 1] = new Ticket(seat_class, seat_number, price, person);
                        TICKET_DATA[14 + seat_number - 1].save();
                        break;
                    case "c":
                        TICKET_DATA[26 + seat_number - 1] = new Ticket(seat_class, seat_number, price, person);
                        TICKET_DATA[26 + seat_number - 1].save();
                        break;
                    case "d":
                        TICKET_DATA[38 + seat_number - 1] = new Ticket(seat_class, seat_number, price, person);
                        TICKET_DATA[38 + seat_number - 1].save();
                        break;
                    default:
                        System.out.println("Seat class is not valid");
                }

                // Mark the seat as reserved in the all_seat_rows array
                ALL_SEAT_ROWS[num][seat_number - 1] = 1;
            } else {
                System.out.println("\nSeat has already reserved....");
            }

            // Update individual row arrays in case of modifications
            row_A = ALL_SEAT_ROWS[0];
            row_B = ALL_SEAT_ROWS[1];
            row_C = ALL_SEAT_ROWS[2];
            row_D = ALL_SEAT_ROWS[3];

            // Prompt the user to continue buying seats
            continue_input = continue_process("buying");

            // Update the loop condition based on user input
            continue_buying_seats = continue_input.equals("y");
        }
    }

    // Method for canceling a seat reservation
    public static void cancel_seat() {
        System.out.println("\n*******************   Cancel a seat   ************************");

        boolean continue_cancelling_seats = true;

        while (continue_cancelling_seats) {
            // getting the seat class
            seat_class = get_input_seat_class();

            // getting the seat number
            seat_number = get_input_seat_number();

            // Determine the row index based on seat class
            int num = seat_class.charAt(0) - 97;

            // Check if the seat is reserved
            if (ALL_SEAT_ROWS[num][seat_number - 1] != 0) {
                System.out.println("\nSeat cancelled successfully.........");
                // Mark the seat as available in the all_seat_rows array
                ALL_SEAT_ROWS[num][seat_number - 1] = 0;
            } else {
                System.out.println("\nSeat already available....");
            }

            // Remove the ticket data for the canceled seat
            switch (seat_class) {
                case "a":
                    TICKET_DATA[seat_number - 1] = null;
                    break;
                case "b":
                    TICKET_DATA[14 + seat_number - 1] = null;
                    break;
                case "c":
                    TICKET_DATA[26 + seat_number - 1] = null;
                    break;
                case "d":
                    TICKET_DATA[38 + seat_number - 1] = null;
                    break;
                default:
                    System.out.println("Seat class is not valid");
            }

            // Update individual row arrays in case of modifications
            row_A = ALL_SEAT_ROWS[0];
            row_B = ALL_SEAT_ROWS[1];
            row_C = ALL_SEAT_ROWS[2];
            row_D = ALL_SEAT_ROWS[3];

            // Prompt the user to continue canceling seats
            continue_input = continue_process("cancelling");

            // Update the loop condition based on user input
            continue_cancelling_seats = continue_input.equals("y");
        }

    }

    // Method to find and display the first available seat
    public static void find_first_available() {
        System.out.println("\n**************  Find the first available seat  ***************");

        boolean found = false;

        // Loop through all_seat_rows array to find the first available seat
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < ALL_SEAT_ROWS[x].length; y++) {
                if (ALL_SEAT_ROWS[x][y] == 0) {
                    char letter = x == 0 ? 'A' : x == 1 ? 'B' : x == 2 ? 'C' : 'D';
                    System.out.println("\nFirst available seat at " + letter + (y + 1));
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
    }

    // Method to display the current seating plan
    public static void show_seating_plan() {
        System.out.println("\n*****************  Show the seating plan   ******************");

        // Array to represent seat row labels
        String[] row = {"A", "B", "C", "D"};
        System.out.println("\n");

        // Display the seating plan using 'O' for available seats and 'X' for reserved seats
        for (int i = 0; i < ALL_SEAT_ROWS.length; i++) {
            System.out.print("\t" + row[i] + "\t");
            for (int j = 0; j < ALL_SEAT_ROWS[i].length; j++) {
                if (j == 7) {
                    System.out.print("\t");
                }
                if (ALL_SEAT_ROWS[i][j] == 0) {
                    System.out.print("O  ");
                } else {
                    System.out.print("X  ");
                }
            }
            System.out.println("\n");
        }
    }

    // Method to print ticket information and total sales
    public static void print_tickets_info() {
        System.out.println("\n***************  Print ticket information  ******************");

        int count_null = 0;
        int total_price = 0;

        // Loop through the ticket_data array to print ticket information
        for (Ticket ticket : TICKET_DATA) {
            if (ticket != null) {
                System.out.println(" ");
                ticket.get_ticket_info();
                total_price += ticket.get_price();
                count_null++;
            }
        }

        // Display total sales if there are sold tickets
        if (count_null == 0) {
            System.out.println("\nNo ticket information found");
        } else {
            System.out.println("\nTotal sales : £" + total_price);
        }
    }

    // Method to search for ticket information
    public static void search_ticket() {
        System.out.println("\n***************  Search ticket information  ******************\n");

        boolean continue_searching_seats = true;

        while (continue_searching_seats) {
            // getting the seat class
            seat_class = get_input_seat_class();

            // getting the seat number
            seat_number = get_input_seat_number();

            // Calculate array index based on seat class
            int array_index = 0;

            switch (seat_class) {
                case "a":
                    array_index = seat_number - 1;
                    break;
                case "b":
                    array_index = 14 + seat_number - 1;
                    break;
                case "c":
                    array_index = 26 + seat_number - 1;
                    break;
                case "d":
                    array_index = 38 + seat_number - 1;
                    break;
                default:
                    System.out.println("Seat class is not valid");
            }

            // Display ticket information if the seat is sold
            if (TICKET_DATA[array_index] != null) {
                System.out.println(" ");
                TICKET_DATA[array_index].get_ticket_info();
            } else {
                System.out.println("\nSeat is available...");
            }

            // Prompt the user to continue searching seats
            continue_input = continue_process("searching");

            // Update the loop condition based on user input
            continue_searching_seats = continue_input.equals("y");
        }

    }

    // Method to validate seat class input
    public static boolean seat_class_validation(String seat_class) {
        if (!(seat_class.equals("a")) && !(seat_class.equals("b")) && !(seat_class.equals("c")) && !(seat_class.equals("d"))) {
            System.out.println("Invalid seat class (Choose correct seat row A , B , C , D)");
            return true;
        }
        return false;
    }

    // Method to validate seat number input
    public static boolean seat_number_validation(String seat_row, int seat_number) {
        if (seat_row.equals("a") || seat_row.equals("d")) {
            if (seat_number > 0 && seat_number <= 14) {
                return true;
            }
        } else {
            if (seat_number > 0 && seat_number <= 12) {
                return true;
            }
        }
        System.out.println("Invalid seat number (Enter in the range)\n");
        return false;
    }

    // Method to get the seat class from the user
    public static String get_input_seat_class(){

        do {
            System.out.print("\nWhat class you want : ");
            seat_class = input.nextLine().toLowerCase();
        } while ((seat_class_validation(seat_class)));

        return seat_class;
    }

    // Method to get the seat number from the user
    public static int get_input_seat_number(){
        do {
            System.out.print("What is the seat number you want : ");

            if (input.hasNextInt()) {
                seat_number = input.nextInt();
                input.nextLine();
            } else {
                input.nextLine();
                seat_number = -1;
            }
        } while (!(seat_number_validation(seat_class, seat_number)));

        return seat_number;
    }

    // Method to continue the process
    public static String continue_process(String prompt){
        do {
            System.out.print("\nDo you want to continue "+ prompt +" seats (y or n): ");
            continue_input = input.nextLine().toLowerCase();
            System.out.println(" ");
        } while (!(continue_input.equals("y") || continue_input.equals("n")));

        return continue_input;
    }

    // Method to quit the program
    public static void quit() {
        System.out.println("\nQuitting...........");
        System.exit(0);
    }
}
