public class Borrower extends User {
    String borrowerIdNumber;
    MyLinkedList borrowedBooks, requestedBooks;

    public Borrower(String firstName, String lastName, String middleName, String address, String gender,
            String phoneNumber, int age, String emailAddress,
            String username, String password, String borrowerIdNumber) {
        super(firstName, lastName, middleName, address, gender, phoneNumber, age, emailAddress,
                username, password);
        this.borrowerIdNumber = borrowerIdNumber;
    }

    public Borrower(){
        this(null, null, null, null, null, null, 0, null, null, null, null);
    }

    //setters
    public void setBorrowerIdNumber(String borrowerIdNumber){
        this.borrowerIdNumber = borrowerIdNumber;
    }

    //getters
    public String getBorrowerIdNumber(){
        return borrowerIdNumber;
    }

    //Borrow/Request Books

    //Return Books

    //Cancel Request

    //toString
}