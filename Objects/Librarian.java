package Objects;

import java.time.LocalDate;

public class Librarian extends User {

    public Librarian(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, LocalDate dob, String librarianIdNumber, String position, String identifier,
            String password) {
        super(firstName, lastName, middleName, address, gender, phoneNumber, dob,
                identifier, password);
    }

    public Librarian() {
        this(null, null, null, null, null, null, dob, null, null, null, null);
    }

    // Approve/Decline Book Request
    // View Borrower Account Overview
    // Add Books
    // Remove Books
    // Edit Books
    // toString
}
