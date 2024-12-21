import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LibraryManagementSystem {

    Login loginEssentials = new Login();
    JFrame frame;
    JPanel panel;

    public void frameConfig() {

        frame = new JFrame("Library Management System");

        // setActivePanel(welcomePage());
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
                setActivePanel(logInPage());
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

    public JPanel logInPage() {
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
        String idPlaceholder = "Enter username";
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

        String passwordPlaceholder = "Enter password";

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
                    loginPanel.setVisible(false);
                    borrowerPage();
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

    // public JPanel dataPrivacyPage(){
    // JPanel dataPrivacyPanel = new JPanel();
    // JLabel textDataPrivacy = new JLabel("Data Privacy");

    // textDataPrivacy.setPreferredSize(new Dimension(250, 5));
    // textDataPrivacy.setFont(new Font(null, Font.BOLD, 35));

    // JLabel textDataPrivacyDetails = new JLabel("<html>In accordance to the Data
    // Privacy Act of 2012, the personal information shared will only be used to
    // create and personal use of your account. All information collected during
    // sign up will not be shared with any third parties.</html>");
    // textDataPrivacyDetails.setPreferredSize(new Dimension(300, 250));
    // textDataPrivacyDetails.setFont(new Font(null, Font.PLAIN, 12));

    // dataPrivacyPanel.setLayout(new BoxLayout(dataPrivacyPanel,
    // BoxLayout.Y_AXIS));
    // dataPrivacyPanel.add(textDataPrivacy);
    // dataPrivacyPanel.add(textDataPrivacyDetails);
    // dataPrivacyPanel.setPreferredSize(new Dimension(420, 500));
    // dataPrivacyPanel.setVisible(true);

    // JScrollPane scrollPane = new JScrollPane(dataPrivacyPanel);
    // scrollPane.setPreferredSize(new Dimension(420, 420));
    // scrollPane.setVisible(true);

    // JPanel finalPanel = new JPanel();
    // finalPanel.add(scrollPane);
    // finalPanel.setLayout(null);
    // finalPanel.setSize(420, 420);
    // finalPanel.setVisible(true);
    // return finalPanel;
    // }
    public JPanel dataPrivacyPage() {
        // Create the main panel that will hold all the components
        JPanel dataPrivacyPanel = new JPanel();
        dataPrivacyPanel.setLayout(new BoxLayout(dataPrivacyPanel, BoxLayout.Y_AXIS));

        // Data Privacy title label
        JLabel textDataPrivacy = new JLabel("Data Privacy");
        textDataPrivacy.setFont(new Font(null, Font.BOLD, 35));
        textDataPrivacy.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

        // Data Privacy details label
        JLabel textDataPrivacyDetails = new JLabel(
                "<html>In accordance to the Data Privacy Act of 2012, the personal information shared will only be used to create and for personal use of your account. "
                        +
                        "All information collected during sign-up will not be shared with any third parties.</html>");
        textDataPrivacyDetails.setFont(new Font(null, Font.PLAIN, 12));
        textDataPrivacyDetails.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

        // Add labels to the dataPrivacyPanel
        dataPrivacyPanel.add(textDataPrivacy);
        dataPrivacyPanel.add(Box.createVerticalStrut(20)); // Optional spacing between components
        dataPrivacyPanel.add(textDataPrivacyDetails);

        // Create the JScrollPane to make the content scrollable
        JScrollPane scrollPane = new JScrollPane(dataPrivacyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(420, 420)); // Preferred size for the scroll pane

        // Create a final JPanel to return
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        finalPanel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the panel

        return finalPanel; // Return the JPanel containing the JScrollPane
    }

    public void borrowerPage() {
        JOptionPane.showMessageDialog(null, "hey");
    }

    public void librarianPage() {

    }

    public void manageBorrowRequests() {
        System.out.println("Borrowing Requests");

    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();

        system.frameConfig();

    }
}