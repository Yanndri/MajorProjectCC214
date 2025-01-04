package LibGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import DataStructures.Node;
import Objects.User;

public class Login {
    BufferedReader reader = null;
    HashTest accounts = new HashTest();

    public Login() {
        getAccounts();
    }

    public boolean storeAccount(Object newAccount) {
        User account = (User) newAccount;
        account.setKey(encrypt(account.getIdentifier()));
        accounts.insert(account);
        return true;
    }

    // encrypts a string so that it is unreadable
    public int encrypt(String unEncryptedString) {
        int key = 0;
        for (int i = 0; i < unEncryptedString.length(); i++) {
            int value = unEncryptedString.charAt(i); // replaces each character to int
            key += value; // and add them all together
        }
        return key;
    }

    public boolean checkAccountCredentials(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return false;

        int key = encrypt(username);

        User existingAccount = accounts.getUser(key);
        if (existingAccount == null)
            return false;
        return username.equals(existingAccount.getIdentifier()) &&
                password.equals(existingAccount.getPassword());
    }

    public boolean isIdentifierAvailable(String identifier) {
        return false;
    }

    // public boolean isIdentifierAvailable(String identifier) {
    // int key = encrypt(identifier);
    // User existingAccount = (User) accounts.getAccount(key);
    // return existingAccount.getIdentifier().equals(identifier);
    // }

    public void getAccounts() {
        try {
            reader = new BufferedReader(
                    new FileReader("LibGUI\\UserAccounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("//");
                System.out.println(line);
                User user = new User(userDetails[0], userDetails[1], userDetails[2], Integer.parseInt(userDetails[3]),
                        userDetails[4], userDetails[5], userDetails[6], userDetails[7], userDetails[8],
                        0);
                storeAccount(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateFile() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( // CHANGE IT TO fiLE PATH
                    new FileWriter("LibGUI\\UserAccounts.txt"));
            for (int i = 0; i < accounts.items.length; i++) {
                if (accounts.items[i] != null) {
                    Node currNode = accounts.items[i];
                    while (currNode != null) {
                        User user = (User) currNode.getItem();
                        writer.write(user.getFirstName() + "//" + user.getLastName() + "//" + user.getMiddleName()
                                + "//" + user.getAge() + "//" + user.getAddress() + "//" + user.getGender() + "//"
                                + user.getPassword() + "//" + user.getIdentifier() + "//" + user.getPassword() + "//"
                                + user.getKey());
                        System.out.println("User " + user.getFirstName() + " successfully added.");
                        currNode = currNode.getLink();
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.getAccounts();

        login.storeAccount(new User("Asheerah", "Stop", "Bautro", 75, "Kangkong, Cordova", "Bayot", "09123456789",
                "asheerahbokiboki", "jedgo123", login.encrypt("asheerahbokiboki")));
        login.storeAccount(new User("Test", "TEST1", "Test2", 69, "TestADD", "attack Helicopter", "09123213",
                "TESTUSER", "TESTPASS", login.encrypt("TESTUSER")));

        login.updateFile();
        System.out.println("YEAAAAAAAAAAAAAAAYYYYYYY!!!!1");
    }
}