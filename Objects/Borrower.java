package Objects;

import DataStructures.MyLinkedList;

public class Borrower extends User {
    String borrowerIdNumber;
    MyLinkedList borrowedBooks, requestedBooks;

    public Borrower(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, int age,
            String identifier, String password, String borrowerIdNumber) {
        super(firstName, lastName, middleName, address, gender, phoneNumber, age,
                identifier, password);
        this.borrowerIdNumber = borrowerIdNumber;
    }

    public Borrower() {
        this(null, null, null, null, null, null, 0, null, null, null);
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