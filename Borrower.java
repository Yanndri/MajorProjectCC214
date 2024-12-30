public class Borrower extends User{
    String borrowerIdNumber;
    String username, password;

    Borrower(String firstName, String lastName, String middleName, String address, String gender, String emailAddress, String phoneNumber, String collegeDepartment, int age, String borrowerIdNumber, String username, String password){
        super(firstName, lastName, middleName, address, gender, emailAddress, phoneNumber, collegeDepartment, age);
        this.borrowerIdNumber = borrowerIdNumber;
    }
    
}