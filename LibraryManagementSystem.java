import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LibraryManagementSystem {

    Login loginEssentials = new Login();
    JFrame frame;
    JPanel panel;

    LibraryManagementSystem() {
        frameConfig();
    }

    // initialize the main frame
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

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel); // Remove the first panel
                setActivePanel(signUpPage());
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
        dataPrivacyPanel.add(Box.createVerticalStrut(20));
        dataPrivacyPanel.add(scrollPane);
        dataPrivacyPanel.add(Box.createVerticalStrut(20));
        dataPrivacyPanel.add(buttons);
        dataPrivacyPanel.add(Box.createVerticalGlue());

        dataPrivacyPanel.setSize(new Dimension(600, 550));
        return dataPrivacyPanel;
    }

    public JPanel signUpPage() {

        // Variables needed for Sign Up

        // String for Blank Fields
        String messagePrompt = "*Field is Required.";

        JPanel signUpPage = new JPanel();
        signUpPage.setLayout(new BoxLayout(signUpPage, BoxLayout.Y_AXIS));
        signUpPage.setSize(new Dimension(600, 550));
        signUpPage.setVisible(true);

        // Title
        JPanel titleSignUp = new JPanel();
        JLabel titleText = new JLabel("Sign Up");
        titleText.setFont(new Font(null, Font.PLAIN, 35));
        titleSignUp.add(titleText);
        titleSignUp.setPreferredSize(new Dimension(250, 50));
        titleSignUp.setVisible(true);

        // Container for TextFields + messageLabels
        JPanel textFields = new JPanel();
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
        textFields.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textFields.setPreferredSize(new Dimension(550, 600));
        textFields.setVisible(true);

        // Container Panel for Phone Number and Sex at Birth
        JPanel numAndSaB = new JPanel();
        numAndSaB.setLayout(new BoxLayout(numAndSaB, BoxLayout.X_AXIS));
        numAndSaB.setAlignmentX(Component.LEFT_ALIGNMENT);
        numAndSaB.setPreferredSize(new Dimension(500, 75));
        numAndSaB.setVisible(true);

        // Panel for Phone Number
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.setLayout(new BoxLayout(phoneNumberPanel, BoxLayout.Y_AXIS));
        phoneNumberPanel.setPreferredSize(new Dimension(100, 100));
        phoneNumberPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        phoneNumberPanel.setVisible(true);

        // Panel for Sex at Birth
        JPanel sexAtBirthPanel = new JPanel();
        sexAtBirthPanel.setLayout(new BoxLayout(sexAtBirthPanel, BoxLayout.Y_AXIS));
        sexAtBirthPanel.setPreferredSize(new Dimension(300, 100));
        sexAtBirthPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        sexAtBirthPanel.setVisible(true);

        // Text fields [JLabel, Text Field, Message Label]

        // First Name JLabel
        JLabel labelFName = new JLabel("First Name");
        labelFName.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFName.setPreferredSize(new Dimension(75, 25));

        JTextField fieldFName = new JTextField();
        fieldFName.setPreferredSize(new Dimension(200, 25));
        fieldFName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelFName = new JLabel();
        mLabelFName.setPreferredSize(new Dimension(200, 15));
        mLabelFName.setFont(new Font(null, Font.ITALIC, 10));
        mLabelFName.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelFName.setForeground(Color.RED);

        // Middle Name JLabel
        JLabel labelMName = new JLabel("Middle Name (Type N/A if not Applicable)");
        labelMName.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelMName.setPreferredSize(new Dimension(75, 25));

        JTextField fieldMName = new JTextField();
        fieldMName.setPreferredSize(new Dimension(200, 25));
        fieldMName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelMName = new JLabel();
        mLabelMName.setPreferredSize(new Dimension(200, 15));
        mLabelMName.setFont(new Font(null, Font.ITALIC, 10));
        mLabelMName.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelMName.setForeground(Color.RED);

        // Last Name JLabel
        JLabel labelLName = new JLabel("Last Name");
        labelLName.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelLName.setPreferredSize(new Dimension(75, 25));

        JTextField fieldLName = new JTextField();
        fieldLName.setPreferredSize(new Dimension(200, 25));
        fieldLName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelLName = new JLabel();
        mLabelLName.setPreferredSize(new Dimension(200, 15));
        mLabelLName.setFont(new Font(null, Font.ITALIC, 10));
        mLabelLName.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelLName.setForeground(Color.RED);

        // Address JLabel
        JLabel labelAddress = new JLabel("Address");
        labelAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelAddress.setPreferredSize(new Dimension(75, 25));

        JTextField fieldAddress = new JTextField();
        fieldAddress.setPreferredSize(new Dimension(200, 25));
        fieldAddress.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelAddress = new JLabel();
        mLabelAddress.setPreferredSize(new Dimension(200, 15));
        mLabelAddress.setFont(new Font(null, Font.ITALIC, 10));
        mLabelAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelAddress.setForeground(Color.RED);

        // Phone Number JLabel
        JLabel labelPhoneNumber = new JLabel("Phone Number");
        labelPhoneNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelPhoneNumber.setPreferredSize(new Dimension(75, 25));

        JTextField fieldPhoneNumber = new JTextField();
        fieldPhoneNumber.setPreferredSize(new Dimension(200, 35));
        fieldPhoneNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelPhoneNumber = new JLabel();
        mLabelPhoneNumber.setPreferredSize(new Dimension(200, 15));
        mLabelPhoneNumber.setFont(new Font(null, Font.ITALIC, 10));
        mLabelPhoneNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelPhoneNumber.setForeground(Color.RED);

        // Sex At Birth JLabel
        JLabel labelSexAtBirth = new JLabel("Sex at Birth");
        labelSexAtBirth.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSexAtBirth.setPreferredSize(new Dimension(75, 25));

        JComboBox<String> choiceSex = new JComboBox<>(
                new String[] { "Assigned Male at Birth", "Assigned Female at Birth" });
        choiceSex.setPreferredSize(new Dimension(500, 25));
        choiceSex.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelSexAtBirth = new JLabel();
        mLabelSexAtBirth.setPreferredSize(new Dimension(200, 15));
        mLabelSexAtBirth.setFont(new Font(null, Font.ITALIC, 10));
        mLabelSexAtBirth.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelSexAtBirth.setForeground(Color.RED);

        // mLabelAddress.setText(messagePrompt);;
        // mLabelFName.setText(messagePrompt);
        // mLabelLName.setText(messagePrompt);
        // mLabelMName.setText(messagePrompt);
        // mLabelPhoneNumber.setText(messagePrompt);
        // mLabelSexAtBirth.setText(messagePrompt);
        // Adding components to containers

        // Phone Number
        phoneNumberPanel.add(Box.createVerticalGlue());
        phoneNumberPanel.add(labelPhoneNumber);
        phoneNumberPanel.add(Box.createVerticalStrut(15));
        phoneNumberPanel.add(fieldPhoneNumber);
        phoneNumberPanel.add(Box.createVerticalStrut(10));
        phoneNumberPanel.add(mLabelPhoneNumber);
        phoneNumberPanel.add(Box.createVerticalGlue());

        // Sex
        sexAtBirthPanel.add(Box.createVerticalGlue());
        sexAtBirthPanel.add(labelSexAtBirth);
        sexAtBirthPanel.add(Box.createVerticalStrut(10));
        sexAtBirthPanel.add(choiceSex);
        sexAtBirthPanel.add(Box.createVerticalStrut(10));
        sexAtBirthPanel.add(mLabelSexAtBirth);
        sexAtBirthPanel.add(Box.createVerticalGlue());

        // Phone Number and Sex
        numAndSaB.add(Box.createHorizontalGlue());
        numAndSaB.add(phoneNumberPanel);
        numAndSaB.add(Box.createHorizontalGlue());
        numAndSaB.add(sexAtBirthPanel);
        numAndSaB.add(Box.createHorizontalGlue());

        // Text Fields
        textFields.add(Box.createVerticalGlue());
        textFields.add(labelFName);
        textFields.add(Box.createVerticalStrut(10));
        textFields.add(fieldFName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(mLabelFName);
        textFields.add(Box.createVerticalStrut(15));
        textFields.add(labelMName);
        textFields.add(Box.createVerticalStrut(10));
        textFields.add(fieldMName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(mLabelMName);
        textFields.add(Box.createVerticalStrut(15));
        textFields.add(labelLName);
        textFields.add(Box.createVerticalStrut(10));
        textFields.add(fieldLName);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(mLabelLName);
        textFields.add(Box.createVerticalStrut(15));
        textFields.add(labelAddress);
        textFields.add(Box.createVerticalStrut(10));
        textFields.add(fieldAddress);
        textFields.add(Box.createVerticalStrut(5));
        textFields.add(mLabelAddress);
        textFields.add(Box.createVerticalStrut(15));
        textFields.add(numAndSaB);
        textFields.add(Box.createVerticalGlue());

        // Scrolling Configuration
        JScrollPane scrollPane = new JScrollPane(textFields);

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

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("User Pressed Proceed on Sign Up Page");
                frame.remove(panel); // Remove the first panel
                setActivePanel(signUpPage());
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

        // titleSignUp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // numAndSaB.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // sexAtBirthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // phoneNumberPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // buttons.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        signUpPage.add(Box.createVerticalGlue());
        signUpPage.add(titleSignUp);
        signUpPage.add(scrollPane);
        signUpPage.add(buttons);
        signUpPage.add(Box.createVerticalGlue());
        return signUpPage;
    }

    public void borrowerPage() {
        JOptionPane.showMessageDialog(null, "hey");
    }

    public void librarianPage() {

    }

    public void manageBorrowRequests() {
        System.out.println("Borrowing Requests");

    }

    // RUSS AKO GIBUTANG SA Main.java, adto i run ang main function.
}