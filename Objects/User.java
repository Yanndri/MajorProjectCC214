package Objects;

import java.time.LocalDate;
import java.time.Period;

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
    int key;
    LocalDate dob;

    // First Name //Last Name //middleName //dob //address //gender
    // phoneNumber //identifier //password //key
    public User(String firstName, String lastName, String middleName, LocalDate dob, String address, String gender,
            String phoneNumber, String identifier, String password, int key) {
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
    }

    public User() {
        this(null, null, null, null, null, null, null, null, null, 0);
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
}
