// Coretico workspace only

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Login implements ActionListener {

    boolean logIn;
    int flg;
    HashTable accounts = new HashTable();

    public void logIn() {

        JFrame frame = new JFrame("Library Management System");
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        JTextField identifierField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel userIDLabel = new JLabel("User ID:");
        JLabel userPasswordLabel = new JLabel("Password:");
        JLabel title = new JLabel("Log In");
        JLabel messageLabel = new JLabel();

        title.setBounds(150, 25, 250, 50);
        title.setFont(new Font(null, Font.PLAIN, 35));

        userIDLabel.setBounds(50, 125, 75, 25);
        userPasswordLabel.setBounds(50, 175, 75, 25);

        messageLabel.setBounds(125, 275, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 10));

        identifierField.setBounds(125, 125, 200, 25);
        String idPlaceholder = "Enter email or username";
        identifierField.setText(idPlaceholder);
        identifierField.setForeground(Color.GRAY);
        identifierField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Remove placeholder when the field gains focus
                if (identifierField.getText().equals(idPlaceholder)) {
                    identifierField.setText("");
                    identifierField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder when the field loses focus and is empty
                if (identifierField.getText().isEmpty()) {
                    identifierField.setText(idPlaceholder);
                    identifierField.setForeground(Color.GRAY);
                }
            }
        });

        String passwordPlaceholder = "Enter Password";

        passwordField.setBounds(125, 175, 200, 25);
        passwordField.setText("Enter Password");
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Remove placeholder when the field gains focus
                if (String.valueOf(passwordField.getPassword()).equals(passwordPlaceholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore placeholder when the field loses focus and is empty
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(passwordPlaceholder);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0); // Disable masking
                }
            }
        });

        loginButton.setBounds(225, 225, 100, 25);
        loginButton.setFocusable(false);

        String identifier = identifierField.getText();
        String password = String.valueOf(passwordField.getPassword());

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkAccountCredentials(identifier, password)) {
                    frame.dispose();
                    // borrowerOptions();
                } else {
                    messageLabel.setText("Incorrect username or password.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        cancelButton.setBounds(125, 225, 100, 25);
        cancelButton.setFocusable(false);

        frame.add(title);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(identifierField);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(cancelButton);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    public boolean storeAccount(Object newAccount){
        
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

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        return (identifier == existingAccount.getIdentifier()) && (password == existingAccount.getPassword());

    }

    public boolean isIdentifierAvailable(String identifier){

        int key = encrypt(identifier);

        User existingAccount = (User) accounts.getAccount(key);

        return existingAccount.getIdentifier() == identifier;
    }

    public static void main(String[] args) {

        Login login = new Login();

        login.logIn();

        // Login login = new Login();
        // String identifier, password;
        // int flg = 0;
        // Scanner scan = new Scanner(System.in);

        // while (flg != -1) {

        // System.out.println("\n\n\tLibrary Management System");
        // while (flg != 1 && flg != 2) {
        // System.out.println("\n\n\tLogin or Sign Up\n\n\tEnter 1 for Login, 2 for Sign
        // up: ");
        // flg = scan.nextInt();
        // }

        // switch (flg) {
        // case 1:
        // System.out.print("\n\n\tEnter Username or Email: ");
        // identifier = scan.next();
        // System.out.print("\n\n\tEnter Password: ");
        // password = scan.next();
        // if(login.checkAccountCredentials(identifier, password))
        // login.borrowerOptions();
        // else
        // System.out.print("Incorrect username or password. Please try again.");
        // break;
        // case 2: login.setUpAccount();

        // default:
        // System.out.print("Invalid input please try again.");
        // }

        // }

        // }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}