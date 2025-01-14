package LandingPagesGUI.GUIUserAccess;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GlobalVariables;

public class BorrowBooksPage extends JPanel {
    public JPanel createBorrowerPanel() {
        CustomLayoutManager layoutManager = new CustomLayoutManager();
        // Define the date-time formatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Create the main panel
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // Top Panel: Real-time clock
        JPanel clockPanel = new JPanel();
        clockPanel.setBackground(GlobalVariables.lightestColor);
        clockPanel.setLayout(new BorderLayout());
        JLabel clockLabel = new JLabel("Current Time: ", SwingConstants.CENTER);
        layoutManager.labelStyleDefault(clockLabel);

        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        clockPanel.add(clockLabel, BorderLayout.CENTER);
        this.add(clockPanel, BorderLayout.NORTH);

        // Center Panel: User input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(GlobalVariables.lightestColor);
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // User name input
        JLabel nameLabel = new JLabel("Enter User Name:");
        layoutManager.labelStyleDefault(nameLabel);

        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        layoutManager.textfieldStyleDefault(nameField);

        // Birthdate input using JComboBox
        JLabel birthdateLabel = new JLabel("Select Birthdate:");

        layoutManager.labelStyleDefault(birthdateLabel);

        String[] years = new String[101];
        for (int i = 0; i < 101; i++) {
            years[i] = String.valueOf(2025 - i); // Example: years from 2025 to 1925
        }
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        layoutManager.comboBoxStyleLighter(yearComboBox);

        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1); // Months from 01 to 12
        }
        JComboBox<String> monthComboBox = new JComboBox<>(months);

        layoutManager.comboBoxStyleLighter(monthComboBox);

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1); // Days from 01 to 31
        }
        JComboBox<String> dayComboBox = new JComboBox<>(days);
        layoutManager.comboBoxStyleLighter(dayComboBox);

        JLabel birthMonthLabel = new JLabel("Birth Month:");
        layoutManager.labelStyleDefault(birthMonthLabel);

        JLabel birthDayLabel = new JLabel("Birth Day:");
        layoutManager.labelStyleDefault(birthDayLabel);

        inputPanel.add(birthdateLabel);
        inputPanel.add(yearComboBox);
        inputPanel.add(birthMonthLabel);
        inputPanel.add(monthComboBox);
        inputPanel.add(birthDayLabel);
        inputPanel.add(dayComboBox);

        // Book number input
        JLabel bookNumberLabel = new JLabel("Enter Book Number:");
        JTextField bookNumberField = new JTextField();
        layoutManager.textfieldStyleDefault(bookNumberField);
        inputPanel.add(bookNumberLabel);
        inputPanel.add(bookNumberField);

        layoutManager.labelStyleDefault(bookNumberLabel);

        // Due date and time input using JComboBox
        JLabel dueDateLabel = new JLabel("Select Due Date:");
        layoutManager.labelStyleDefault(dueDateLabel);
        JComboBox<String> dueYearComboBox = new JComboBox<>(years);
        layoutManager.comboBoxStyleLighter(dueYearComboBox);
        JComboBox<String> dueMonthComboBox = new JComboBox<>(months);
        layoutManager.comboBoxStyleLighter(dueMonthComboBox);
        JComboBox<String> dueDayComboBox = new JComboBox<>(days);
        layoutManager.comboBoxStyleLighter(dueDayComboBox);

        layoutManager.labelStyleDefault(dueDateLabel);

        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i); // Hours from 00 to 23
        }
        JComboBox<String> hourComboBox = new JComboBox<>(hours);
        layoutManager.comboBoxStyleLighter(hourComboBox);

        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i); // Minutes from 00 to 59
        }
        JComboBox<String> minuteComboBox = new JComboBox<>(minutes);
        layoutManager.comboBoxStyleLighter(minuteComboBox);

        String[] seconds = new String[60];
        for (int i = 0; i < 60; i++) {
            seconds[i] = String.format("%02d", i); // Seconds from 00 to 59
        }
        JComboBox<String> secondComboBox = new JComboBox<>(seconds);
        layoutManager.comboBoxStyleLighter(secondComboBox);

        JLabel dueMonthLabel = new JLabel("Due Month:");
        layoutManager.labelStyleDefault(dueMonthLabel);

        JLabel dueDayLabel = new JLabel("Due Day:");
        layoutManager.labelStyleDefault(dueDayLabel);

        JLabel dueTimeLabel = new JLabel("Due Time:");
        layoutManager.labelStyleDefault(dueTimeLabel);

        inputPanel.add(dueDateLabel);
        inputPanel.add(dueYearComboBox);
        inputPanel.add(dueMonthLabel);
        inputPanel.add(dueMonthComboBox);
        inputPanel.add(dueDayLabel);
        inputPanel.add(dueDayComboBox);
        inputPanel.add(dueTimeLabel);
        inputPanel.add(hourComboBox);
        inputPanel.add(minuteComboBox);
        inputPanel.add(secondComboBox);

        this.add(inputPanel, BorderLayout.CENTER);

        // Bottom Panel: Buttons and actions
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(GlobalVariables.lightestColor);
        JButton setButton = new JButton("Set Due Date and Time");
        buttonPanel.add(setButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        layoutManager.buttonStyleDefault(setButton);

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
                JOptionPane.showMessageDialog(this,
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
                            JOptionPane.showMessageDialog(this,
                                    "The due date has passed!\nUser: " + userName + "\nAge: " + age +
                                            "\nBorrowed Book Number: " + bookNumber +
                                            "\nDue Date: " + dueDate.format(dateTimeFormatter),
                                    "Due Date Alert", JOptionPane.WARNING_MESSAGE);
                        });
                        dueDateScheduler.shutdown(); // Stop the scheduler after the notification
                    }
                }, 0, 1, TimeUnit.SECONDS); // Check every second
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return this;
    }
}