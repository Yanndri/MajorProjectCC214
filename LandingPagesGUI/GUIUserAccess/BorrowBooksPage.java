package LandingPagesGUI.GUIUserAccess;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;
import javax.swing.*;

public class BorrowBooksPage {
    public static JPanel createBorrowerPanel() {
        // Define the date-time formatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create the main panel
        JPanel borrowerPanel = new JPanel(new BorderLayout());

        // Top Panel: Real-time clock
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new BorderLayout());
        JLabel clockLabel = new JLabel("Current Time: ", SwingConstants.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        clockPanel.add(clockLabel, BorderLayout.CENTER);
        borrowerPanel.add(clockPanel, BorderLayout.NORTH);

        // Center Panel: User input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User name input
        JLabel nameLabel = new JLabel("Enter User Name:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // Birthdate input using JComboBox
        JLabel birthdateLabel = new JLabel("Select Birthdate:");
        String[] years = new String[101];
        for (int i = 0; i < 101; i++) {
            years[i] = String.valueOf(2025 - i); // Example: years from 2025 to 1925
        }
        JComboBox<String> yearComboBox = new JComboBox<>(years);

        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1); // Months from 01 to 12
        }
        JComboBox<String> monthComboBox = new JComboBox<>(months);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1); // Days from 01 to 31
        }
        JComboBox<String> dayComboBox = new JComboBox<>(days);

        inputPanel.add(birthdateLabel);
        inputPanel.add(yearComboBox);
        inputPanel.add(new JLabel("Month:"));
        inputPanel.add(monthComboBox);
        inputPanel.add(new JLabel("Day:"));
        inputPanel.add(dayComboBox);

        // Book number input
        JLabel bookNumberLabel = new JLabel("Enter Book Number:");
        JTextField bookNumberField = new JTextField();
        inputPanel.add(bookNumberLabel);
        inputPanel.add(bookNumberField);

        // Due date and time input using JComboBox
        JLabel dueDateLabel = new JLabel("Select Due Date:");
        JComboBox<String> dueYearComboBox = new JComboBox<>(years);
        JComboBox<String> dueMonthComboBox = new JComboBox<>(months);
        JComboBox<String> dueDayComboBox = new JComboBox<>(days);

        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i); // Hours from 00 to 23
        }
        JComboBox<String> hourComboBox = new JComboBox<>(hours);

        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i); // Minutes from 00 to 59
        }
        JComboBox<String> minuteComboBox = new JComboBox<>(minutes);

        String[] seconds = new String[60];
        for (int i = 0; i < 60; i++) {
            seconds[i] = String.format("%02d", i); // Seconds from 00 to 59
        }
        JComboBox<String> secondComboBox = new JComboBox<>(seconds);

        inputPanel.add(dueDateLabel);
        inputPanel.add(dueYearComboBox);
        inputPanel.add(new JLabel("Month:"));
        inputPanel.add(dueMonthComboBox);
        inputPanel.add(new JLabel("Day:"));
        inputPanel.add(dueDayComboBox);
        inputPanel.add(new JLabel("Time:"));
        inputPanel.add(hourComboBox);
        inputPanel.add(minuteComboBox);
        inputPanel.add(secondComboBox);

        borrowerPanel.add(inputPanel, BorderLayout.CENTER);

        // Bottom Panel: Buttons and actions
        JPanel buttonPanel = new JPanel();
        JButton setButton = new JButton("Set Due Date and Time");
        buttonPanel.add(setButton);
        borrowerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Use ScheduledExecutorService for updating the clock every second
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            SwingUtilities.invokeLater(() -> {
                clockLabel.setText("Current Time: " + now.format(dateTimeFormatter));
            });
        }, 0, 1, TimeUnit.SECONDS); // Update every second

        // Action listener for the "Set" button
        setButton.addActionListener(e -> {
            try {
                // Get the user's name
                String userName = nameField.getText().trim();
                if (userName.isEmpty()) {
                    throw new IllegalArgumentException("User name cannot be empty.");
                }

                // Calculate the user's age from the JComboBox values
                int birthYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
                int birthMonth = Integer.parseInt((String) monthComboBox.getSelectedItem());
                int birthDay = Integer.parseInt((String) dayComboBox.getSelectedItem());
                LocalDate birthLocalDate = LocalDate.of(birthYear, birthMonth, birthDay);
                long age = ChronoUnit.YEARS.between(birthLocalDate, LocalDate.now());

                // Get the book number
                String bookNumber = bookNumberField.getText().trim();
                if (bookNumber.isEmpty()) {
                    throw new IllegalArgumentException("Book number cannot be empty.");
                }

                // Get the due date and time from the JComboBox values
                int dueYear = Integer.parseInt((String) dueYearComboBox.getSelectedItem());
                int dueMonth = Integer.parseInt((String) dueMonthComboBox.getSelectedItem());
                int dueDay = Integer.parseInt((String) dueDayComboBox.getSelectedItem());
                int dueHour = Integer.parseInt((String) hourComboBox.getSelectedItem());
                int dueMinute = Integer.parseInt((String) minuteComboBox.getSelectedItem());
                int dueSecond = Integer.parseInt((String) secondComboBox.getSelectedItem());

                LocalDateTime dueDate = LocalDateTime.of(dueYear, dueMonth, dueDay, dueHour, dueMinute, dueSecond);

                // Show confirmation
                JOptionPane.showMessageDialog(borrowerPanel,
                        "User Name: " + userName + "\n" +
                                "Age: " + age + " years\n" +
                                "Borrowed Book Number: " + bookNumber + "\n" +
                                "Due Date and Time: " + dueDate.format(dateTimeFormatter),
                        "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                // Use ScheduledExecutorService to monitor the due date
                ScheduledExecutorService dueDateScheduler = Executors.newSingleThreadScheduledExecutor();
                dueDateScheduler.scheduleAtFixedRate(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println("Current Time: " + now.format(dateTimeFormatter)); // Debugging
                    System.out.println("Due Date: " + dueDate.format(dateTimeFormatter)); // Debugging

                    if (now.isAfter(dueDate)) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(borrowerPanel,
                                    "The due date has passed!\nUser: " + userName + "\nAge: " + age +
                                            "\nBorrowed Book Number: " + bookNumber +
                                            "\nDue Date: " + dueDate.format(dateTimeFormatter),
                                    "Due Date Alert", JOptionPane.WARNING_MESSAGE);
                        });
                        dueDateScheduler.shutdown(); // Stop the scheduler after the notification
                    }
                }, 0, 1, TimeUnit.SECONDS); // Check every second
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(borrowerPanel, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return borrowerPanel;
    }
}