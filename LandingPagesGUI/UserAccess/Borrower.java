package LandingPagesGUI.UserAccess;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Borrower {
    // Create a method to return the Borrower panel
    public static JPanel createBorrowerPanel() {
        // Define the date-time formatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Create the main panel for Borrower
        JPanel borrowPanel = new JPanel(new BorderLayout());

        // Top Panel: Real-time clock
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new BorderLayout());
        JLabel clockLabel = new JLabel("Current Time: ", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        clockPanel.add(clockLabel, BorderLayout.CENTER);
        borrowPanel.add(clockPanel, BorderLayout.NORTH);

        // Center Panel: User input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User name input
        JLabel nameLabel = new JLabel("Enter User Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // User birthdate input
        JLabel birthdateLabel = new JLabel("Enter Birthdate:");
        JSpinner birthdateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor birthdateEditor = new JSpinner.DateEditor(birthdateSpinner, "yyyy-MM-dd");
        birthdateSpinner.setEditor(birthdateEditor);
        inputPanel.add(birthdateLabel);
        inputPanel.add(birthdateSpinner);

        // Book number input
        JLabel bookNumberLabel = new JLabel("Enter Book Number:");
        JTextField bookNumberField = new JTextField();
        inputPanel.add(bookNumberLabel);
        inputPanel.add(bookNumberField);

        // Due date input
        JLabel dueDateLabel = new JLabel("Select Due Date:");
        JSpinner dueDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dueDateEditor = new JSpinner.DateEditor(dueDateSpinner, "yyyy-MM-dd");
        dueDateSpinner.setEditor(dueDateEditor);
        inputPanel.add(dueDateLabel);
        inputPanel.add(dueDateSpinner);

        // Due time input
        JLabel dueTimeLabel = new JLabel("Select Due Time:");
        JSpinner dueTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dueTimeEditor = new JSpinner.DateEditor(dueTimeSpinner, "HH:mm:ss");
        dueTimeSpinner.setEditor(dueTimeEditor);
        inputPanel.add(dueTimeLabel);
        inputPanel.add(dueTimeSpinner);

        borrowPanel.add(inputPanel, BorderLayout.CENTER);

        // Bottom Panel: Buttons and actions
        JPanel buttonPanel = new JPanel();
        JButton setButton = new JButton("Set Due Date and Time");
        buttonPanel.add(setButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 150, 10)); // Add padding around the panel
        borrowPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Timer for updating the real-time clock
        Timer clockTimer = new Timer(true);
        clockTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                SwingUtilities.invokeLater(() -> {
                    clockLabel.setText("Current Time: " + now.format(dateTimeFormatter));
                });
            }
        }, 0, 1000); // Update every second

        // Action listener for the "Set" button
        setButton.addActionListener(e -> {
            // Logic as in the original code...
        });

        return borrowPanel;
    }
}
