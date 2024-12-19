public class Librarian extends User {
    String librarianIdNumber;
    String position;

    public Librarian(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, int age, String librarianIdNumber, String position, String emailAddress,
            String username, String password) {
        super(firstName, lastName, middleName, address, gender, phoneNumber, age, emailAddress,
                username, password);
        this.librarianIdNumber = librarianIdNumber;
        this.position = position;
    }

    public Librarian() {
        this(null, null, null, null, null, null, 0, null, null, null, null, null);
    }

    // setters
    public void setLibrarianIDNumber(String librarianIdNumber) {
        this.librarianIdNumber = librarianIdNumber;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
