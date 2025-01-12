package LibGUI;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LoginInterface extends JFrame {

    JPanel castPanel, loginPanel, buttonPanel, signupPanel;
    JLabel bgLabel, logoLabel, welcomeLabel;
    JButton adminButton, userButton, signupButton;
    signinPanel signin;
    signupPanel signup;
    Login accounts = new Login();

    public LoginInterface() {

        this.setTitle("Library Management System");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // centers the frame

        // background label
        bgLabel = new JLabel(new ImageIcon("LibGUI\\images\\libraryBg.jpg"));
        bgLabel.setLayout(new BorderLayout());

        // logoLabel attributes
        logoLabel = new JLabel(new ImageIcon(
                new ImageIcon("LibGUI\\images\\ctuLogo.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_DEFAULT))); // image resizing

        logoLabel.setText("Library Management System");
        logoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setFont(new Font("Playfair Display", Font.BOLD, 48));
        logoLabel.setForeground(Color.WHITE);

        // welcome label
        welcomeLabel = new JLabel("Welcome !");
        welcomeLabel.setFont(new Font("Arial Black", Font.BOLD, 69));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setOpaque(false);

        // transparent panel
        castPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // set transparency
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // 50% transparency
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };

        castPanel.setLayout(new BoxLayout(castPanel, BoxLayout.Y_AXIS)); // arrange components vertically
        castPanel.setOpaque(false); // ensure transparency

        // login panel
        loginPanel = new JPanel();
        loginPanel.setOpaque(true);
        loginPanel.setBackground(new java.awt.Color(255, 255, 255, 128));
        loginPanel.setPreferredSize(new Dimension(500, 300));
        loginPanel.setMaximumSize(new Dimension(500, 300)); // prevent stretching
        loginPanel.setLayout(new BorderLayout());

        // button panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(250, 150));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // signupButton Panel
        signupPanel = new JPanel();
        signupPanel.setOpaque(false);
        signupPanel.setPreferredSize(new Dimension(150, 80));
        signupPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));

        // Admin Button
        adminButton = new JButton(new ImageIcon(
                new ImageIcon("LibGUI\\images\\icon-admin-8.jpg")
                        .getImage()
                        .getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        adminButton.setPreferredSize(new Dimension(110, 100));
        adminButton.setMaximumSize(new Dimension(110, 100));
        adminButton.setFocusable(false);
        adminButton.setText("Admin Login");
        adminButton.addActionListener(e -> switchToSigninPanel("Admin"));
        adminButton.setVerticalTextPosition(JLabel.BOTTOM);
        adminButton.setHorizontalTextPosition(JLabel.CENTER);

        // hover highlight
        adminButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminButton.setBackground(Color.orange);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminButton.setBackground(UIManager.getColor("control")); // "control" is the default color
            }
        }); // end of method

        // User Button
        userButton = new JButton(new ImageIcon(
                new ImageIcon("LibGUI\\images\\icon-admin-8.jpg")
                        .getImage()
                        .getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        userButton.setPreferredSize(new Dimension(110, 100));
        userButton.setMaximumSize(new Dimension(110, 100));
        userButton.setFocusable(false);
        userButton.setText("User Login");
        userButton.addActionListener(e -> switchToSigninPanel("User"));
        userButton.setVerticalTextPosition(JLabel.BOTTOM);
        userButton.setHorizontalTextPosition(JLabel.CENTER);

        // hover highlight
        userButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userButton.setBackground(Color.orange);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                userButton.setBackground(UIManager.getColor("control")); // "control" is the default color
            }
        }); // end of method

        // Sign Up Button
        signupButton = new JButton("Sign Up");
        signupButton.setPreferredSize(new Dimension(230, 25));
        signupButton.setFocusable(false);
        signupButton.addActionListener(e -> switchToSignupPanel());

        // hover highlight
        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(Color.orange);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(UIManager.getColor("control")); // "control" is the default color
            }
        }); // end of method

        /*
         * =============================================================================
         * =====================================
         */

        // add componenets to signupPnael
        signupPanel.add(signupButton);

        // add components to buttonPanel
        buttonPanel.add(Box.createHorizontalGlue()); // filler
        buttonPanel.add(adminButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // space between buttons
        buttonPanel.add(userButton);
        buttonPanel.add(Box.createHorizontalGlue());

        // add components to loginPanel
        loginPanel.add(welcomeLabel, BorderLayout.NORTH);
        loginPanel.add(buttonPanel, BorderLayout.CENTER);
        loginPanel.add(signupPanel, BorderLayout.SOUTH);

        // add components to castPanel
        castPanel.add(Box.createVerticalStrut(5)); // adds spacing at the top
        castPanel.add(logoLabel);
        castPanel.add(Box.createVerticalStrut(30));
        castPanel.add(loginPanel);

        // add castPanel to bgLabel
        bgLabel.add(castPanel, BorderLayout.CENTER);
        this.setContentPane(bgLabel);

        this.setVisible(true);
        signin = new signinPanel(castPanel, loginPanel);
    }

    private void switchToSigninPanel(String role) {
        castPanel.remove(loginPanel);
        signin = new signinPanel(castPanel, loginPanel);
        castPanel.add(signin.getLoginWindowPanel(role));
        castPanel.revalidate();
        castPanel.repaint();
    }

    private void switchToSignupPanel() {
        castPanel.remove(loginPanel);
        signup = new signupPanel(castPanel, loginPanel, accounts);
        castPanel.add(signup.dataPrivacyPage());
        castPanel.revalidate();
        castPanel.repaint();
    }

    public static void main(String[] args) {
        new LoginInterface();
    }
}
