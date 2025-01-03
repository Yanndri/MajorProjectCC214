package LandingPagesGUI.GUIUserAccess;

import java.awt.*;
import java.time.LocalDate; // import the LocalDate class
import java.time.LocalDateTime; // import the LocalDateTime class
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Borrower {

    public static void main(String[] args) {
        // Define the date-time formatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width; // Width of the screen
        int screenHeight = screenSize.height; // Height of the screen

        // Create the main frame
        JFrame frame = new JFrame("Library Due Date Notifier");
        // frame.setSize(500, 450);
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // Top Panel: Real-time clock
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new BorderLayout());
        JLabel clockLabel = new JLabel("Current Time: ", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        clockPanel.add(clockLabel, BorderLayout.CENTER);
        frame.add(clockPanel, BorderLayout.NORTH);

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

        frame.add(inputPanel, BorderLayout.CENTER);

        // Bottom Panel: Buttons and actions
        JPanel buttonPanel = new JPanel();
        JButton setButton = new JButton("Set Due Date and Time");
        buttonPanel.add(setButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 150, 10)); // Add padding around the panel

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

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
            try {
                // Get the user's name
                String userName = nameField.getText().trim();
                if (userName.isEmpty()) {
                    throw new IllegalArgumentException("User name cannot be empty.");
                }

                // Calculate the user's age
                Date birthDate = (Date) birthdateSpinner.getValue();
                LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long age = ChronoUnit.YEARS.between(birthLocalDate, LocalDate.now());

                // Get the book number
                String bookNumber = bookNumberField.getText().trim();
                if (bookNumber.isEmpty()) {
                    throw new IllegalArgumentException("Book number cannot be empty.");
                }

                // Get the due date and time
                Date selectedDate = (Date) dueDateSpinner.getValue();
                Date selectedTime = (Date) dueTimeSpinner.getValue();
                LocalDateTime dueDate = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault())
                        .withHour(selectedTime.getHours())
                        .withMinute(selectedTime.getMinutes())
                        .withSecond(selectedTime.getSeconds());

                // Show confirmation
                JOptionPane.showMessageDialog(frame,
                        "User Name: " + userName + "\n" +
                                "Age: " + age + " years\n" +
                                "Borrowed Book Number: " + bookNumber + "\n" +
                                "Due Date and Time: " + dueDate.format(dateTimeFormatter),
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                // Start a timer to monitor the due date
                Timer dueDateTimer = new Timer(true);
                dueDateTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        LocalDateTime now = LocalDateTime.now();
                        if (now.isAfter(dueDate)) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(frame,
                                        "The due date has passed!\nUser: " + userName + "\nAge: " + age +
                                                "\nBorrowed Book Number: " + bookNumber +
                                                "\nDue Date: " + dueDate.format(dateTimeFormatter),
                                        "Due Date Alert", JOptionPane.WARNING_MESSAGE);
                            });
                            dueDateTimer.cancel(); // Stop the timer after the notification
                        }
                    }
                }, 0, 1000); // Check every second
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
