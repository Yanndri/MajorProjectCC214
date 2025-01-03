package Objects;

import DataStructures.DLinkedList;
import DataStructures.DNode;

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
    int age, key;
    DLinkedList<Book> borrowedBooks;
    // Date of Birth

    // firstName//lastName//middleName//age//address//gender//phoneNumber//identifier//password//key;

    public User(String firstName, String lastName, String middleName, int age, String address, String gender,
            String phoneNumber, String identifier, String password, int key, DLinkedList<Book> borrowedBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.identifier = identifier;
        this.password = password;
        this.key = key;
        this.borrowedBooks = borrowedBooks;
    }

    public User() {
        this(null, null, null, 0, null, null, null, null, null, 0, null);
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

    public void setAge(int age) {
        this.age = age;
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
        return age;
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
