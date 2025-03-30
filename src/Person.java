public class Person {

    // Declaring attributes of the person
    private final String NAME;
    private final String SURNAME;
    private final String E_MAIL;

    // Constructor to initialize person attributes
    public Person(String name, String surname, String e_mail) {
        this.NAME = name;
        this.SURNAME = surname;
        this.E_MAIL = e_mail;
    }

    // Getter method to retrieve the person's name
    public String get_name() {
        return NAME;
    }

    // Getter method to retrieve the person's surname
    public String get_surname() {
        return SURNAME;
    }

    // Getter method to retrieve the person's email
    public String getE_mail() {
        return E_MAIL;
    }
}
