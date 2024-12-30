public class Librarian extends User{
    String librarianIdNumber;
    String username, password;

    Librarian(String firstName, String lastName, String middleName, String address, String gender, String emailAddress, String phoneNumber, String collegeDepartment, int age, String librarianIdNumber, String username, String password){
        super(firstName, lastName, middleName, address, gender, emailAddress, phoneNumber, collegeDepartment, age);
        this.librarianIdNumber = librarianIdNumber;
    }
}
