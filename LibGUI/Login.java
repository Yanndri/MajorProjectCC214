package LibGUI;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Login {

    BufferedReader reader = null;

    HashTable accounts = new HashTable();

    public boolean storeAccount(Object newAccount) {

        User account = (User) newAccount;

        account.setKey(String.valueOf(encrypt(account.getIdentifier())));

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

    public boolean checkAccountCredentials(String identifier, String password) {

        if(identifier == null || password == null)

            return false;

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        if(existingAccount == null)

            return false;

        return (identifier == existingAccount.getIdentifier()) && (password == existingAccount.getPassword());

    }

    public boolean isIdentifierAvailable(String identifier) {

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        return existingAccount.getIdentifier() == identifier;
    }

            public void getAccounts() {
        try {
            reader = new BufferedReader(new FileReader("UserAccounts.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split("//");
                System.out.println(line);
                User user = new User(userDetails[0], userDetails[1], userDetails[2], Integer.parseInt(userDetails[3]),
                        userDetails[4], userDetails[5], userDetails[6], userDetails[7], userDetails[8],
                        Integer.parseInt(userDetails[9]));
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

    public static void main(String[] args) {
        Login login = new Login();
        login.getAccounts();
    }
}