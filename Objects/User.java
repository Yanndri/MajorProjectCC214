package Objects;

import DataStructures.Node;
import DataStructures.QueueLinkedList;
import java.time.LocalDate;
import java.time.Period;

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
    int key;
    LocalDate dob;
    QueueLinkedList borrowedBooks;

    public User(String firstName, String lastName, String middleName, LocalDate dob, String address, String gender,
            String phoneNumber, String identifier, String password, int key, QueueLinkedList borrowedBooks) {
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
<<<<<<< Updated upstream
        // this.borrowedBooks = borrowedBooks;
=======
        this.borrowedBooks = borrowedBooks;
>>>>>>> Stashed changes
    }

    public User() {
        this(null, null, null, null, null, null, null, null, null, 0, null);
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

    // public void addBorrowedBook(Book book) {
    //     if (borrowedBooks == null)
    //         borrowedBooks = new DLinkedList<>();
    //     borrowedBooks.addFront(book);
    // }

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

<<<<<<< Updated upstream
    // public String getBorrowedBooks() {
    //     StringBuilder sb = new StringBuilder();
    //     DNode<Book> currNode;
    //     for (currNode = borrowedBooks.head; currNode != null; currNode = currNode.getNext()) {
    //         sb.append(currNode.getItem().toString()).append(", ");
    //         if (currNode.getNext() == borrowedBooks.tail) {
    //             sb.append("& ");
    //             sb.append(currNode.getItem().getTitle());
    //             break;
    //         }
    //     }
    //     return sb.toString();

    // }
=======
    public void addBorrowedBook(Book book) {
        if (book != null) {
            if (borrowedBooks == null)
                borrowedBooks = new QueueLinkedList();
            borrowedBooks.enqueue(book);
        }
    }

    public String getBorrowedBooks() {
        if(borrowedBooks != null){
        StringBuilder sb = new StringBuilder();
        Node currNode;
        for (currNode = borrowedBooks.head; currNode != null; currNode = currNode.getLink()) {
            sb.append(currNode.getItem().toString()).append(", ");
            if (currNode.getLink() == borrowedBooks.tail) {
                sb.append("& ");
                Book currBook = (Book) currNode.getItem();
                sb.append(currBook.getTitle());
                break;
            }
        }
        return sb.toString();
    }
        else{
            return "No Borrowed Book/s";
        }
    }
>>>>>>> Stashed changes
}
