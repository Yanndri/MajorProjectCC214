public class Login {

    public void SignUp() {
        String gmail = "cyrandi639@gmail.com";

        if (checkGmail(gmail))
            System.out.println("Gmail already registered");
        else
            storeGmail(encrypt(gmail));
    }

    // return true if Gmail is already registered
    public boolean checkGmail(String gmail) {
        return false;
    }

    public void storeGmail(int key) {

    }

    public int encrypt(String unEncryptedString) {
        int key = 0;
        for (int i = 0; i < unEncryptedString.length(); i++) {
            int value = unEncryptedString.charAt(i);
            key += value;
        }
        return key;
    }
}
