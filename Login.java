import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import DataStructures.HashTable;
import Objects.User;

public class Login {

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

        if (identifier == null || password == null)

            return false;

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        if (existingAccount == null)

            return false;

        return (identifier == existingAccount.getIdentifier()) && (password == existingAccount.getPassword());

    }

    public boolean isIdentifierAvailable(String identifier) {

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        return existingAccount.getIdentifier() == identifier;
    }

    public static void main(String[] args) {

    }
}