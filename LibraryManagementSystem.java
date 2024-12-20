import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LibraryManagementSystem {

    Login loginEssentials = new Login();
    JFrame frame;
    JPanel panel;

    public void frameConfig() {

        frame = new JFrame("Library Management System");

        setActivePanel(welcomePage());
        adjustPanelLoc(frame, panel);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JPanel activePanel = getActivePanel(); // Method to get the active panel
                if (activePanel != null) {
                    Dimension newSize = frame.getSize();
                    int newX = (newSize.width - activePanel.getWidth()) / 2;
                    int newY = (newSize.height - activePanel.getHeight()) / 2;
                    activePanel.setLocation(newX, newY);
                }
            }
        });

        frame.add(panel);
        frame.setLayout(null);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // // Add ComponentListener to the frame to detect resizing
    // frame.addComponentListener(new ComponentAdapter() {
    // @Override
    // public void componentResized(ComponentEvent e) {
    // // Get the current size of the frame
    // Dimension frameSize = frame.getSize();

    // // Calculate new panel size based on the frame's width and height
    // int x = (frameSize.width - panel.getWidth()) / 2; // Panel width is half of
    // the frame's width
    // int y = (frameSize.height - panel.getHeight()) / 2; // Panel height is a
    // quarter of the frame's height

    // // Update the panel's bounds (position and size)
    // panel.setLocation(x, y);

    // }
    // });

    public void setActivePanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getActivePanel() {
        return panel;
    }

    // Method to center a panel inside the frame
    private static void adjustPanelLoc(JFrame frame, JPanel panel) {
        Dimension frameSize = frame.getSize();
        Dimension panelSize = panel.getSize();

        // Calculate center position
        int x = (frameSize.width - panelSize.width) / 2;
        int y = (frameSize.height - panelSize.height) / 2;

        // Update the panel's location
        panel.setLocation(x, y);
    }

    public JPanel welcomePage() {
        JPanel welcomePagePanel = new JPanel();
        JLabel titleLabel = new JLabel("Library Management System");
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        titleLabel.setBounds(80, 70, 270, 50);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));

        loginButton.setBounds(150, 160, 100, 35);
        loginButton.setFocusable(false);

        signUpButton.setBounds(150, 210, 100, 35);
        signUpButton.setFocusable(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel); // Remove the first panel
                setActivePanel(logIn());
                adjustPanelLoc(frame, panel);
                frame.add(panel); // Add the second panel
                frame.revalidate(); // Refresh the frame layout
                frame.repaint(); // Repaint to ensure the new panel is shown
            }
        });

        welcomePagePanel.add(titleLabel);
        welcomePagePanel.add(loginButton);
        welcomePagePanel.add(signUpButton);
        welcomePagePanel.setLayout(null);
        welcomePagePanel.setSize(420, 420);
        welcomePagePanel.setVisible(true);
        return welcomePagePanel;
    }

    public JPanel logIn() {
        JPanel loginPanel = new JPanel();

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        JTextField identifierField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel userIdentifierLabel = new JLabel("User ID:");
        JLabel userPasswordLabel = new JLabel("Password:");
        JLabel title = new JLabel("Log In");
        JLabel messageLabel = new JLabel();

        title.setBounds(150, 25, 250, 50);
        title.setFont(new Font(null, Font.PLAIN, 35));

        userIdentifierLabel.setBounds(50, 125, 75, 25);
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
                if (loginEssentials.checkAccountCredentials(identifier, password)) {
                    // frame.dispose();
                    loginPanel.setVisible(false);
                    borrowerOptions();
                } else {
                    messageLabel.setText("Incorrect username or password.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel); // Remove the first panel
                setActivePanel(welcomePage());
                adjustPanelLoc(frame, panel);
                frame.add(panel); // Add the second panel
                frame.revalidate(); // Refresh the frame layout
                frame.repaint(); // Repaint to ensure the new panel is shown
            }
        });

        cancelButton.setBounds(125, 225, 100, 25);
        cancelButton.setFocusable(false);

        loginPanel.add(title);
        loginPanel.add(userIdentifierLabel);
        loginPanel.add(userPasswordLabel);
        loginPanel.add(messageLabel);
        loginPanel.add(identifierField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(cancelButton);
        loginPanel.setLayout(null);
        loginPanel.setSize(420, 420);
        loginPanel.setVisible(true);
        return loginPanel;
    }

    public void signUp() {

    }

    public void borrowerOptions() {
        JOptionPane.showMessageDialog(null, "hey");
    }

    public void librarianOptions() {

    }

    public void manageBorrowRequests() {
        System.out.println("Borrowing Requests");

    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();

        system.frameConfig();

    }
}