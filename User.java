public class User {

    String firstName, lastName, middleName, address, gender, phoneNumber, identifier, password, key;
    int age;
    // Date of Birth

    public User(String firstName, String lastName, String middleName, String address, String gender, String phoneNumber,
            int age, String identifier, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.identifier = identifier;
        this.password = password;
    }

    public User() {
        this(null, null, null, null, null, null, 0, null, null);
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

    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setKey(String key){
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
        return age;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public String getKey(){
        return key;
    }
    
    //Search

    @Override
    public String toString(){
        return getKey();
    }
}
