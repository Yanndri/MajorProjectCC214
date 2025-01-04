package Objects;

<<<<<<< HEAD
import DataStructures.DLinkedList;
import DataStructures.DNode;
=======
import java.time.LocalDate;
import java.time.Period;
>>>>>>> main

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
<<<<<<< HEAD
    int age, key;
    DLinkedList<Book> borrowedBooks;
    // Date of Birth

    // firstName//lastName//middleName//age//address//gender//phoneNumber//identifier//password//key;

    public User(String firstName, String lastName, String middleName, int age, String address, String gender,
            String phoneNumber, String identifier, String password, int key, DLinkedList<Book> borrowedBooks) {
=======
    int key;
    LocalDate dob;

    public User(String firstName, String lastName, String middleName, LocalDate dob, String address, String gender,
            String phoneNumber, String identifier, String password, int key) {
>>>>>>> main
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
    }

    public User() {
<<<<<<< HEAD
        this(null, null, null, 0, null, null, null, null, null, 0, null);
=======
        this(null, null, null, null, null, null, null, null, null, 0);
>>>>>>> main
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

    public void addBorrowedBook(Book book) {
        if (borrowedBooks == null)
            borrowedBooks = new DLinkedList<>();
        borrowedBooks.addFront(book);
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

    public String getBorrowedBooks() {
        StringBuilder sb = new StringBuilder();
        DNode<Book> currNode;
        for (currNode = borrowedBooks.head; currNode != null; currNode = currNode.getNext()) {
            sb.append(currNode.getItem().toString()).append(", ");
            if (currNode.getNext() == borrowedBooks.tail) {
                sb.append("& ");
                sb.append(currNode.getItem().getTitle());
                break;
            }
        }
        return sb.toString();

    }
}
