package LibGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class signinPanel extends JPanel {

    JPanel fieldJPanel, buttonJPanel, prevPanel, parent;
    JLabel userJLabel, passJLabel, bookIcon;
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
        this.setBackground(new java.awt.Color(255, 255, 255, 128));
        this.setOpaque(true);

        // book Icon
        bookIcon = new JLabel(new ImageIcon(
                new ImageIcon("C:\\Users\\Ashee\\Desktop\\asdqwae\\LibGUI\\bookIcon.png")
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
        fieldJPanel.setBackground(new java.awt.Color(255, 255, 255, 128));
        fieldJPanel.setOpaque(false);

        // buttonPanel
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BoxLayout(buttonJPanel, BoxLayout.X_AXIS));
        buttonJPanel.setPreferredSize(new Dimension(0, 70));
        buttonJPanel.setBackground(new java.awt.Color(255, 255, 255, 128));
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

        // button
        backButton = new JButton("Back");
        backButton.setAlignmentY(TOP_ALIGNMENT);
        backButton.setFocusable(false);
        backButton.addActionListener(e -> switchToPreviousPanel());

        loginButton = new JButton("Login");
        loginButton.setAlignmentY(TOP_ALIGNMENT);
        loginButton.setFocusable(false);

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

        // add components to the buttonPanel
        buttonJPanel.add(Box.createHorizontalGlue());
        buttonJPanel.add(backButton);
        buttonJPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonJPanel.add(loginButton);
        buttonJPanel.add(Box.createHorizontalStrut(50));

        return this;
    }

    public void switchToPreviousPanel() {
        parent.remove(this); // Remove the current panel
        parent.add(prevPanel); // Add the previous panel
        parent.revalidate(); // Refresh layout
        parent.repaint(); // Redraw UI
    }
}
