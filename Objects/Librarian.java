package Objects;

public class Librarian extends User {

    public Librarian(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, int age, String librarianIdNumber, String position, String identifier,
            String password) {
        super(firstName, lastName, middleName, address, gender, phoneNumber, age,
                identifier, password);
    }

    public Librarian() {
        this(null, null, null, null, null, null, 0, null, null, null, null);
    }

    // setters
    public void setLibrarianIDNumber(String librarianIdNumber) {
        this.librarianIdNumber = librarianIdNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // getters
    public String getLibrarianIDNumber() {
        return librarianIdNumber;
    }

    public String getPosition() {
        return position;
    }

    // Approve/Decline Book Request
    // View Borrower Account Overview
    // Add Books
    // Remove Books
    // Edit Books
    // toString
}
