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
                if (panel != null) {
                    adjustPanelLoc(frame, panel);
                }
            }
        });
        frame.add(panel);
        frame.setLayout(null);
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(800, 600));
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

        titleLabel.setBounds(75, 70, 270, 50);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

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
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel); // Remove the first panel
                setActivePanel(dataPrivacyPage());
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
        textDataPrivacy.setPreferredSize(new Dimension(50, 50));

        // Details for data privacy
        String detailsDataPrivacy = "&nbsp;&nbsp;&nbsp;&nbsp;In accordance to the Data Privacy Act of 2012, the personal information shared will only be used to create and for personal use of your account."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;All information collected during sign-up will not be shared with any third parties."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;We will use your data solely for the purpose of providing services related to your account and will not disclose it to external parties without your consent."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;Your data will be stored securely using industry-standard encryption methods to ensure confidentiality."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;You will always have the right to access, modify, or delete your personal information upon request."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;Any information provided voluntarily for promotional purposes will be handled according to your preferences and consent."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;We will retain your information only for as long as necessary to fulfill the services, and all data will be securely deleted when it is no longer required."
                + "<p>&nbsp;&nbsp;&nbsp;&nbsp;We will notify you in advance if there are any changes to this privacy policy or if any third-party service providers need access to your information for legal reasons.";
        ;

        // Container for details
        JTextPane textDataPrivacyDetails = new JTextPane();
        textDataPrivacyDetails.setContentType("text/html");
        textDataPrivacyDetails.setFont(new Font(null, Font.PLAIN, 12));
        textDataPrivacyDetails.setText(
                "<html><div style='text-align: justify;'>" + detailsDataPrivacy + "</div></html>");
        textDataPrivacyDetails.setAlignmentX(JComponent.CENTER_ALIGNMENT); // Center the label
        textDataPrivacyDetails.setEditable(false);
        textDataPrivacyDetails.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Scrolling Configuration
        JScrollPane scrollPane = new JScrollPane(textDataPrivacyDetails);

        scrollPane.setPreferredSize(new Dimension(600, 300));
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(0); // Set the vertical scroll to the top
        });

        // Container for buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        JButton cancelButton = new JButton("Cancel");
        JButton proceedButton = new JButton("Proceed");
        cancelButton.setPreferredSize(new Dimension(100, 25));
        cancelButton.setFocusable(false);

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

        proceedButton.setPreferredSize(new Dimension(100, 25));
        proceedButton.setFocusable(false);

        buttons.add(Box.createHorizontalGlue());
        buttons.add(cancelButton);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(proceedButton);
        buttons.add(Box.createHorizontalGlue());
        buttons.setPreferredSize(new Dimension(500, 25));
        buttons.setVisible(true);
        // textDataPrivacy.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // textDataPrivacyDetails.setBackground(Color.BLUE);

        dataPrivacyPanel.add(Box.createVerticalGlue());
        dataPrivacyPanel.add(textDataPrivacy);
        dataPrivacyPanel.add(Box.createVerticalStrut(20)); // Optional spacing between components
        dataPrivacyPanel.add(scrollPane);
        dataPrivacyPanel.add(Box.createVerticalStrut(20));
        dataPrivacyPanel.add(buttons);
        dataPrivacyPanel.add(Box.createVerticalGlue());

        dataPrivacyPanel.setSize(new Dimension(600, 550));
        return dataPrivacyPanel;
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