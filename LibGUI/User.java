package LibGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password;
    int key;
    LocalDate dob;

    public User(String firstName, String lastName, String middleName, String month, int day, int year, String address,
            String gender,
            String phoneNumber, String identifier, String password, int key) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        setDOB(day, month, year);
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.identifier = identifier;
        this.password = password;
        this.key = key;
    }

    public User() {
        this(null, null, null, null, 0, 0, null, null, null, null, null, 0);
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

    public void setDOB(int day, String monthString, int year){
        
        Month monthInt = Month.valueOf(monthString.toUpperCase()); // Convert to uppercase to match enum constants

        dob = LocalDate.of(year, monthInt, day);

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
        
        LocalDate today = LocalDate.now(); 

        int age = Period.between(dob, today).getYears();

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
}
