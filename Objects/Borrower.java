package Objects;

import java.time.LocalDate;

import DataStructures.MyLinkedList;

public class Borrower extends User {
    String borrowerIdNumber;
    MyLinkedList borrowedBooks, requestedBooks;

    public Borrower(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, LocalDate dob,
            String identifier, String password, int key) {
        super(firstName, lastName, middleName, dob, address, gender, phoneNumber,
                identifier, password, key);
        this.borrowerIdNumber = borrowerIdNumber;
    }

    public Borrower() {
        this(null, null, null, null, null, null, null, null, null, 0);
    }

    // setters
    public void setBorrowerIdNumber(String borrowerIdNumber) {
        this.borrowerIdNumber = borrowerIdNumber;
    }

    // getters
    public String getBorrowerIdNumber() {
        return borrowerIdNumber;
    }

    // Borrow/Request Books

    // Return Books

    // Cancel Request

    // toString
}