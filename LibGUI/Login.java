package LibGUI;

import DataStructures.Node;
import Objects.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Login {
    BufferedReader reader = null;
    public HashTest accounts = new HashTest();

    public Login() {
        getUserAccounts();
    }

    public boolean storeAccount(User newAccount) {
        accounts.insert(newAccount);
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

    public User getUserAccount(String username, String password) {
        if (username.isEmpty() || password.isEmpty())
            return null;

        if (checkAccountCredentials(username, password)) {

            int key = encrypt(username);

            User account = accounts.getUser(key);

            if (account != null)
                return account;
            else
                return null;
        }

        return null;
    }

    public boolean checkAdminCredentials(String username, String password) {
        String[] adminCredentials = getAdminCredentials();

        return username.equals(adminCredentials[0]) && password.equals(adminCredentials[1]);
    }

    public boolean isIdentifierAvailable(String identifier) {
        int key = encrypt(identifier);
        User existingAccount = (User) accounts.getUser(key);
        if (existingAccount == null)
            return true;
        return !existingAccount.getIdentifier().equals(identifier);
    }

    public void getUserAccounts() {
        try {
            reader = new BufferedReader(
                    new FileReader("LibGUI\\UserAccounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("//");
                System.out.println(line);
                User user = new User(userDetails[0], userDetails[1], userDetails[2], LocalDate.parse(userDetails[3]),
                        userDetails[4], userDetails[5], userDetails[6], userDetails[7], userDetails[8],
                        encrypt(userDetails[7]));
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

    public String[] getAdminCredentials() {
        try {
            reader = new BufferedReader(
                    new FileReader("LibGUI\\adminAccount.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminCredentials = line.split("//");
                System.out.println(line);
                return adminCredentials;
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
        return null;
    }

    public void updateAdminCredentials(String password, String username) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            // File path
            String filePath = "LibGUI\\adminAccount.txt";

            // Read the single line from the file
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            if (line != null && line.contains("//")) {
                int delimiterIndex = line.indexOf("//");
                String currentUsername = line.substring(0, delimiterIndex);
                String currentPassword = line.substring(delimiterIndex + 2);

                // Determine the new content based on the provided arguments
                String updatedLine = line;
                if (username.isBlank() && !password.isBlank()) { // Change password
                    updatedLine = currentUsername + "//" + password;
                } else if (!username.isBlank() && password.isBlank()) { // Change username
                    updatedLine = username + "//" + currentPassword;
                } else if (!username.isBlank() && !password.isBlank()) { // Change both
                    updatedLine = username + "//" + password;
                }

                // Write the updated line back to the file
                writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(updatedLine);
            } else {
                System.err.println("Invalid file format or file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
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
                                + "//" + user.getDOB() + "//" + user.getAddress() + "//" + user.getGender() + "//"
                                + user.getPhoneNumber() + "//" + user.getIdentifier() + "//" + user.getPassword() + "//"
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

    // public static void main(String[] args) {
    // Login login = new Login();
    // login.getUserAccounts();

    // login.storeAccount(new User("Asheerah", "Stop", "Bautro",
    // LocalDate.parse("2005-09-23"), "Kangkong, Cordova", "Bayot", "09123456789",
    // "asheerahbokiboki", "jedgo123", login.encrypt("asheerahbokiboki")));
    // login.storeAccount(new User("Test", "TEST1", "Test2",
    // LocalDate.parse("1992-06-12"), "TestADD", "attack Helicopter", "09123213",
    // "TESTUSER", "TESTPASS", login.encrypt("TESTUSER")));

    // login.updateFile();
    // System.out.println("YEAAAAAAAAAAAAAAAYYYYYYY!!!!1");
    // }
}