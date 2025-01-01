package LibGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class signupPanel {

    JPanel parent, prevPanel;
    JPanel dataPrivacyPanel;
    JPanel signUpPage;
    Login accounts;

    public signupPanel(JPanel parent, JPanel prevPanel, Login accounts) {
        this.parent = parent;
        this.prevPanel = prevPanel;
        this.accounts = accounts;// Receives the user accounts in the main class
    }

    public JPanel dataPrivacyPage() {
        // Create the main panel that will hold all the components
        dataPrivacyPanel = new JPanel();
        dataPrivacyPanel.setLayout(new BoxLayout(dataPrivacyPanel, BoxLayout.Y_AXIS));
        dataPrivacyPanel.setBackground(new java.awt.Color(245, 222, 179));

        // Data Privacy title label
        JLabel textDataPrivacy = new JLabel("Data Privacy");
        textDataPrivacy.setFont(new Font("Times New Roman", Font.BOLD, 35));
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

        // Container for details
        JTextPane textDataPrivacyDetails = new JTextPane();
        textDataPrivacyDetails.setContentType("text/html");
        textDataPrivacyDetails.setFont(new Font(null, Font.PLAIN, 12));
        textDataPrivacyDetails.setText(
                "<html><div style='text-align: justify;'>" + detailsDataPrivacy + "</div></html>");
        textDataPrivacyDetails.setAlignmentX(JComponent.CENTER_ALIGNMENT); // Center the label
        textDataPrivacyDetails.setEditable(false);
        textDataPrivacyDetails.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        textDataPrivacyDetails.setBackground(new java.awt.Color(245, 222, 179));
        textDataPrivacyDetails.setOpaque(true);
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
        cancelButton.addActionListener(e -> privacySwitchToPreviousPanel());
        cancelButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        proceedButton.setPreferredSize(new Dimension(100, 25));
        proceedButton.setFocusable(false);
        proceedButton.addActionListener(e -> privacySwitchToSignUpPanel());
        proceedButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        buttons.add(Box.createHorizontalGlue());
        buttons.add(cancelButton);
        buttons.add(Box.createHorizontalStrut(10));
        buttons.add(proceedButton);
        buttons.add(Box.createHorizontalGlue());
        buttons.setPreferredSize(new Dimension(500, 25));
        buttons.setOpaque(false);

        dataPrivacyPanel.add(Box.createVerticalGlue());
        dataPrivacyPanel.add(textDataPrivacy);
        // dataPrivacyPanel.add(Box.createVerticalStrut(20));
        dataPrivacyPanel.add(scrollPane);
        // dataPrivacyPanel.add(Box.createVerticalStrut(20));
        dataPrivacyPanel.add(buttons);
        dataPrivacyPanel.add(Box.createVerticalGlue());

        dataPrivacyPanel.setPreferredSize(new Dimension(500, 300));
        dataPrivacyPanel.setMaximumSize(new Dimension(500, 300));

        return dataPrivacyPanel;
    }

    public JPanel signUpPage() {

        // String for Blank Fields
        String messagePrompt = "*Field is Required.";

        signUpPage = new JPanel();
        signUpPage.setLayout(new BoxLayout(signUpPage, BoxLayout.Y_AXIS));
        signUpPage.setPreferredSize(new Dimension(500, 300));
        signUpPage.setMaximumSize(new Dimension(500, 300));
        signUpPage.setBackground(new java.awt.Color(245, 222, 179));
        signUpPage.setOpaque(true);

        // Title
        JPanel titleSignUp = new JPanel();
        JLabel titleText = new JLabel("Sign Up");
        titleText.setFont(new Font("Playfair Display", Font.BOLD, 35));
        titleSignUp.add(titleText);
        titleSignUp.setOpaque(false);
        // titleSignUp.setPreferredSize(new Dimension(250, 50));

        // Container for TextFields + messageLabels
        JPanel textFields = new JPanel();
        textFields.setBackground(new java.awt.Color(245, 222, 179));
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
        textFields.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        textFields.setPreferredSize(new Dimension(500, 400));
        textFields.setMaximumSize(new Dimension(500, 400));

        // Container Panel for Phone Number and Sex at Birth
        JPanel numAndSaB = new JPanel();
        numAndSaB.setLayout(new BoxLayout(numAndSaB, BoxLayout.X_AXIS));
        numAndSaB.setAlignmentX(Component.LEFT_ALIGNMENT);
        numAndSaB.setPreferredSize(new Dimension(500, 75));
        numAndSaB.setOpaque(false);

        // Panel for Phone Number
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.setLayout(new BoxLayout(phoneNumberPanel, BoxLayout.Y_AXIS));
        phoneNumberPanel.setPreferredSize(new Dimension(200, 0));
        phoneNumberPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        phoneNumberPanel.setOpaque(false);

        // Panel for Sex at Birth
        JPanel sexAtBirthPanel = new JPanel();
        sexAtBirthPanel.setLayout(new BoxLayout(sexAtBirthPanel, BoxLayout.Y_AXIS));
        // sexAtBirthPanel.setPreferredSize(new Dimension(300, 1000));
        // sexAtBirthPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        sexAtBirthPanel.setOpaque(false);

        // DOB panel
        JPanel dobPanel = new JPanel();
        dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
        dobPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
        monthPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
        dayPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel yearPanel = new JPanel();
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
        yearPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // user & pass panel
        JPanel identifierJPanel = new JPanel();
        identifierJPanel.setLayout(new BoxLayout(identifierJPanel, BoxLayout.PAGE_AXIS));
        // identifierJPanel.setPreferredSize(new Dimension(500,100));
        // identifierJPanel.setMaximumSize(new Dimension(500,100));
        // identifierJPanel.setBackground(Color.blue);
        identifierJPanel.setOpaque(false);
        identifierJPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // User & pass text Fields
        JLabel userJLabel = new JLabel("Username");
        userJLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        userJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userJLabel.setPreferredSize(new Dimension(75, 25));

        JTextField userJField = new JTextField();
        userJField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelUser = new JLabel();
        mLabelUser.setPreferredSize(new Dimension(200, 15));
        mLabelUser.setFont(new Font(null, Font.ITALIC, 10));
        mLabelUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelUser.setForeground(Color.RED);

        JLabel passJLabel = new JLabel("Password");
        passJLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        passJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passJLabel.setPreferredSize(new Dimension(75, 25));

        JTextField passJField = new JTextField();
        passJField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mLabelPass = new JLabel();
        mLabelPass.setPreferredSize(new Dimension(200, 15));
        mLabelPass.setFont(new Font(null, Font.ITALIC, 10));
        mLabelPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        mLabelPass.setForeground(Color.RED);

        // Text fields [JLabel, Text Field, Message Label]
        // First Name JLabel
        JLabel labelFName = new JLabel("First Name");
        labelFName.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFName.setPreferredSize(new Dimension(75, 25));

        JTextField fieldFName = new JTextField();
        fieldFName.setPreferredSize(new Dimension(80, 25));
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
        fieldPhoneNumber.addKeyListener(new KeyAdapter() { // limits 11 characters
            @Override
            public void keyReleased(KeyEvent evt) {
                if (fieldPhoneNumber.getText().length() > 11)
                    fieldPhoneNumber.setText(fieldPhoneNumber.getText().substring(0, 11));
            }
        });

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

        // Date of Birth
        JLabel labelDOB = new JLabel("Date of Birth");
        labelDOB.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDOB.setPreferredSize(new Dimension(75, 25));

        JLabel labelMonth = new JLabel("Month");
        labelMonth.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelMonth.setPreferredSize(new Dimension(75, 25));

        JLabel labelDay = new JLabel("Day");
        labelDay.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDay.setPreferredSize(new Dimension(75, 25));

        JLabel labelYear = new JLabel("Year");
        labelYear.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelYear.setPreferredSize(new Dimension(75, 25));

        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        JComboBox<String> choiceMonth = new JComboBox<>(months);
        choiceMonth.setPreferredSize(new Dimension(500, 25));
        choiceMonth.setAlignmentX(Component.LEFT_ALIGNMENT);

        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        JComboBox<Integer> choiceDay = new JComboBox<>(days);
        choiceMonth.setPreferredSize(new Dimension(500, 25));
        choiceMonth.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Create a combo box for years (e.g., 1900 to current year)
        int currentYear = Year.now().getValue();
        Integer[] years = new Integer[150]; // 200 years range
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear - i;
        }
        JComboBox<Integer> choiceYear = new JComboBox<>(years);
        choiceMonth.setPreferredSize(new Dimension(500, 25));
        choiceMonth.setAlignmentX(Component.LEFT_ALIGNMENT);

        monthPanel.add(labelMonth);
        monthPanel.add(choiceMonth);

        dayPanel.add(labelDay);
        dayPanel.add(choiceDay);

        yearPanel.add(labelYear);
        yearPanel.add(choiceYear);

        dobPanel.add(monthPanel);
        dobPanel.add(dayPanel);
        dobPanel.add(yearPanel);
        dobPanel.setOpaque(true);

        choiceMonth.addActionListener(e -> updateDays(choiceDay, choiceMonth, choiceYear));
        choiceYear.addActionListener(e -> updateDays(choiceDay, choiceMonth, choiceYear));

        // ADD COMPONENTS TO USER & PASS PANEL
        identifierJPanel.add(Box.createVerticalGlue());
        identifierJPanel.add(userJLabel);
        identifierJPanel.add(userJField);
        identifierJPanel.add(mLabelUser);
        identifierJPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        identifierJPanel.add(passJLabel);
        identifierJPanel.add(passJField);
        identifierJPanel.add(mLabelPass);

        // Phone Number
        phoneNumberPanel.add(labelPhoneNumber);
        phoneNumberPanel.add(fieldPhoneNumber);
        phoneNumberPanel.add(mLabelPhoneNumber);

        // Sex
        sexAtBirthPanel.add(labelSexAtBirth);
        sexAtBirthPanel.add(choiceSex);
        sexAtBirthPanel.add(mLabelSexAtBirth);

        // Phone Number and Sex
        numAndSaB.add(phoneNumberPanel);
        numAndSaB.add(sexAtBirthPanel);

        // Text Fields
        textFields.add(labelFName);
        textFields.add(fieldFName);
        textFields.add(mLabelFName);
        textFields.add(labelMName);
        textFields.add(fieldMName);
        textFields.add(mLabelMName);
        textFields.add(labelLName);
        textFields.add(fieldLName);
        textFields.add(mLabelLName);
        textFields.add(labelDOB);
        textFields.add(dobPanel);
        textFields.add(labelAddress);
        textFields.add(fieldAddress);
        textFields.add(mLabelAddress);
        textFields.add(numAndSaB);
        textFields.add(identifierJPanel);

        // Scrolling Configuration
        JScrollPane scrollPane = new JScrollPane(textFields);
        scrollPane.setPreferredSize(new Dimension(500, 700));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(0); // Set the vertical scroll to the top
        });

        // Container for buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setOpaque(false);

        JButton cancelButton = new JButton("Cancel");
        JButton proceedButton = new JButton("Proceed");
        cancelButton.setPreferredSize(new Dimension(100, 25));
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(e -> signupCancel());

        proceedButton.setPreferredSize(new Dimension(100, 25));
        proceedButton.setFocusable(false);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flg = 0;

                if (isFieldEmpty(fieldFName)) {
                    mLabelFName.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(fieldMName)) {
                    mLabelMName.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(fieldLName)) {
                    mLabelLName.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(fieldAddress)) {
                    mLabelAddress.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(fieldPhoneNumber)) {
                    mLabelPhoneNumber.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(userJField)) {
                    mLabelUser.setText(messagePrompt);
                    flg = 1;
                }
                if (isFieldEmpty(passJField)) {
                    mLabelPass.setText(messagePrompt);
                    flg = 1;
                }
                if (flg == 0) {
                    if (isUserExisting(userJField.getText()))
                        mLabelUser.setText("Username is already taken.");
                    else
                        createAccount();
                }
            }
        });

        buttons.add(cancelButton);
        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttons.add(proceedButton);
        buttons.setPreferredSize(new Dimension(500, 25));

        signUpPage.add(Box.createVerticalGlue());
        signUpPage.add(titleSignUp);
        signUpPage.add(scrollPane);
        signUpPage.add(buttons);
        signUpPage.add(Box.createVerticalGlue());

        return signUpPage;
    }

    private static void updateDays(JComboBox<Integer> dayComboBox, JComboBox<String> monthComboBox,
            JComboBox<Integer> yearComboBox) {
        int selectedMonth = monthComboBox.getSelectedIndex() + 1; // Months are 0-indexed
        int selectedYear = (int) yearComboBox.getSelectedItem();

        int daysInMonth = java.time.YearMonth.of(selectedYear, selectedMonth).lengthOfMonth();

        dayComboBox.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }
    }

    public boolean isFieldEmpty(JTextField field) {
        if (field.getText().isEmpty())
            return true;
        return false;
    }

    public boolean isUserExisting(String username) {
        if (accounts.isIdentifierAvailable(username))
            return true;
        return false;
    }

    public void privacySwitchToPreviousPanel() {
        parent.remove(dataPrivacyPanel);
        parent.add(prevPanel);
        parent.revalidate();
        parent.repaint();
    }

    public void privacySwitchToSignUpPanel() {
        parent.remove(dataPrivacyPanel);
        parent.add(signUpPage());
        parent.revalidate();
        parent.repaint();
    }

    public void signupCancel() { // check if fields are not empty
        parent.remove(signUpPage);
        parent.add(prevPanel);
        parent.revalidate();
        parent.repaint();
    }

    public void createAccount() {
        accounts.createAccount();
    }

}
