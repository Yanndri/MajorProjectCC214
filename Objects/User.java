package Objects;

import java.time.LocalDate;
import java.time.Period;

import DataStructures.Node;
import DataStructures.QueueLinkedList;

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
    int key;
    LocalDate dob;
    QueueLinkedList borrowedBooks, requestedBooks;

    public User(String firstName, String lastName, String middleName, LocalDate dob, String address, String gender,
            String phoneNumber, String identifier, String password, int key, QueueLinkedList borrowedBooks, QueueLinkedList requestedBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.identifier = identifier;
        this.password = password;
        this.key = key;
        this.borrowedBooks = borrowedBooks;
        this.requestedBooks = requestedBooks;
    }

    public User(String firstName, String lastName, String middleName, LocalDate dob, String address, String gender,
    String phoneNumber, String identifier, String password, int key){
        this(firstName, lastName, middleName, dob, address, gender, phoneNumber, identifier, password, key, new QueueLinkedList(), new QueueLinkedList());
    }

    public User() {
        this(null, null, null, null, null, null, null, null, null, 0, new QueueLinkedList(), new QueueLinkedList());
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        int age;

        LocalDate currenDate = LocalDate.now();

        age = Period.between(dob, currenDate).getYears();

        return age;
    }

    public LocalDate getDOB() {
        return dob;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public int getKey() {
        return key;
    }

    public void addBorrowedBook(Book book) {
        if (book != null) {
            if (borrowedBooks == null)
                borrowedBooks = new QueueLinkedList();
            borrowedBooks.enqueue(book);
        }
    }

    public void addRequestedBook(Book book) {
        if (book != null) {
            if (requestedBooks == null)
                requestedBooks = new QueueLinkedList();
            requestedBooks.enqueue(book);
        }
    }

    public String getBorrowedBooksString() {
        if (borrowedBooks != null) {
            StringBuilder sb = new StringBuilder();
            Node currNode;
            Book currBook;
            for (currNode = borrowedBooks.head; currNode != null; currNode = currNode.getLink()) {
                currBook = (Book) currNode.getItem();
                sb.append(currBook.getBookId()).append(", ");
                if (currNode.getLink() == borrowedBooks.tail) {
                    sb.append("& ");
                    sb.append(currBook.getBookId());
                    break;
                }
            }
            return sb.toString();
        } else {
            return "No Borrowed Book/s";
        }
    }

    public String getRequestedBooksString() {
        if (requestedBooks != null) {
            StringBuilder sb = new StringBuilder();
            Node currNode;
            Book currBook;
            for (currNode = requestedBooks.head; currNode != null; currNode = currNode.getLink()) {
                currBook = (Book) currNode.getItem();
                sb.append(currBook.getBookId()).append(", ");
                if (currNode.getLink() == requestedBooks.tail) {
                    sb.append("& ");
                    sb.append(currBook.getBookId());
                    break;
                }
            }
            return sb.toString();
        } else {
            return "No Book/s Requests";
        }
    }

}
