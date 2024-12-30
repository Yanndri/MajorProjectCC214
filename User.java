public class User{

    String firstName, lastName, middleName, address, gender, emailAddress, phoneNumber, collegeDepartment;
    int age;
    
    public User(String firstName, String lastName, String middleName, String address, String gender, String emailAddress, String phoneNumber, String collegeDepartment, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.gender = gender;
        this.emailAddress = emailAddress; 
        this.phoneNumber = phoneNumber;
        this.collegeDepartment = collegeDepartment;
    }

    public User(){}; //test
}
