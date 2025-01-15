package LibGUI;

import LandingPagesGUI.GUIAdminAcess.MainAdminPage;
import LandingPagesGUI.GUIUserAccess.MainUserPage;
import LandingPagesGUI.GlobalVariables;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class signinPanel extends JPanel {

    JPanel fieldJPanel, buttonJPanel, prevPanel, parent;
    JLabel userJLabel, passJLabel, bookIcon, messageLabel;
    JButton backButton, loginButton;
    JTextField userJField, passJField;

    public signinPanel(JPanel parent, JPanel prevPanel) {
        this.parent = parent;
        this.prevPanel = prevPanel;
    }

    public JPanel getLoginWindowPanel(String role) {
        // main panel
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 300));
        this.setMaximumSize(new Dimension(500, 300));
        this.setBackground(new Color(255, 255, 255, 128));
        this.setOpaque(true);

        // book Icon
        bookIcon = new JLabel(new ImageIcon(
                new ImageIcon("LibGUI\\images\\bookIcon.png")
                        .getImage()
                        .getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        bookIcon.setText("Welcome " + role + " !");
        bookIcon.setFont(new Font("Segoe UI", Font.BOLD, 35));
        bookIcon.setIconTextGap(20);
        bookIcon.setAlignmentX(LEFT_ALIGNMENT);

        // fieldPanel
        fieldJPanel = new JPanel();
        fieldJPanel.setLayout(new BoxLayout(fieldJPanel, BoxLayout.Y_AXIS));
        fieldJPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        fieldJPanel.setOpaque(true);
        fieldJPanel.setBackground(new Color(255, 255, 255, 128));
        fieldJPanel.setOpaque(false);

        // buttonPanel
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.X_AXIS));
        buttonJPanel.setPreferredSize(new Dimension(0, 70));
        buttonJPanel.setBackground(new Color(255, 255, 255, 128));
        buttonJPanel.setOpaque(false);

        // text Fields
        userJLabel = new JLabel("Username");
        userJLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        userJField = new JTextField();
        userJField.setAlignmentX(LEFT_ALIGNMENT);

        passJLabel = new JLabel("Password");
        passJLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        passJField = new JTextField();
        passJField.setAlignmentX(LEFT_ALIGNMENT);

        messageLabel = new JLabel();
        messageLabel.setPreferredSize(new Dimension(200, 15));
        messageLabel.setFont(new Font(null, Font.ITALIC, 10));
        messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageLabel.setForeground(Color.RED);

        // button
        backButton = new JButton("Back");
        backButton.setAlignmentY(TOP_ALIGNMENT);
        backButton.setFocusable(false);
        backButton.addActionListener(e -> switchToPreviousPanel());

        loginButton = new JButton("Login");
        loginButton.setAlignmentY(TOP_ALIGNMENT);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> logIn(userJField.getText(), passJField.getText(), role));

        // add component
        this.add(fieldJPanel, BorderLayout.CENTER);
        this.add(buttonJPanel, BorderLayout.SOUTH);

        // add components to the fieldPanel
        fieldJPanel.add(Box.createVerticalGlue());
        fieldJPanel.add(bookIcon);
        fieldJPanel.add(userJLabel);
        fieldJPanel.add(userJField);
        fieldJPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        fieldJPanel.add(passJLabel);
        fieldJPanel.add(passJField);
        fieldJPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        fieldJPanel.add(messageLabel);

        // add components to the buttonPanel
        buttonJPanel.add(Box.createHorizontalGlue());
        buttonJPanel.add(backButton);
        buttonJPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonJPanel.add(loginButton);
        buttonJPanel.add(Box.createHorizontalStrut(50));

        return this;
    }

    public void switchToPreviousPanel() {
        parent.remove(this);
        parent.add(prevPanel);
        parent.revalidate();
        parent.repaint();
    }

    public boolean logIn(String username, String password, String role) {
        Login accounts = new Login();
        System.out.println("Role: " + role);
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Incorrect username or password.");
            return false;
        } else {
            if (role.equals("User")) {
                if (accounts.checkAccountCredentials(username, password)) {
                    GlobalVariables.currentUser = accounts.accounts.getUser(accounts.encrypt(username));
                    GlobalVariables.username = GlobalVariables.currentUser.getIdentifier();
                    //GlobalVariables.username = username;
                    new MainUserPage();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(signinPanel.this);
                    if (frame != null) {
                        frame.dispose();
                    }
                    return true;
                } else {
                    messageLabel.setText("Incorrect username or password.");
                    return false;
                }
            } else if (role.equals("Admin")) {// change for admin
                if (accounts.checkAdminCredentials(username, password)) {
                    GlobalVariables.username = username;
                    new MainAdminPage();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(signinPanel.this);
                    if (frame != null) {
                        frame.dispose();
                    }
                    return true;
                } else {
                    messageLabel.setText("Incorrect username or password.");
                    return false;
                }
            }
            return false;
        }
    }
}
