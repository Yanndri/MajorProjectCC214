package LandingPagesGUI.GUIAdminAcess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import LandingPagesGUI.GlobalVariables;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.Book;

public class AddBooksPage extends JPanel {
    // title, description, publicationDate, authors
    // private int totalCopies, borrowedCopies;
    // private MyLinkedList borrowers;
    // private QueueLinkedList requesters;

    public JPanel getAddBooksPagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // North Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel headerLabel = new JLabel("Add Book");
        northPanel.add(headerLabel, BorderLayout.CENTER);

        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setFont(new Font("Monospaced", Font.PLAIN, 32));
        headerLabel.setForeground(GlobalVariables.darkestColor);
        // =============================================

        // West Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel westPanel = new JPanel(new BorderLayout());
        this.add(westPanel, BorderLayout.WEST);

        westPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputFieldsPanel = new JPanel(); // this covers the whole West Border(since it's center)
        westPanel.add(inputFieldsPanel, BorderLayout.CENTER);

        inputFieldsPanel.setBackground(GlobalVariables.lightestColor);

        JPanel boxLayoutPanel = new JPanel(); // make another panel that doesnt cover the whole west border
        inputFieldsPanel.add(boxLayoutPanel); // so the components doesnt fill the whole panel

        boxLayoutPanel.setLayout(new BoxLayout(boxLayoutPanel, BoxLayout.Y_AXIS)); // components are arranged vertically
        boxLayoutPanel.setBackground(GlobalVariables.lightestColor);

        // Title Input
        JLabel titleLabel = new JLabel("Title");
        boxLayoutPanel.add(titleLabel);

        JTextField titleTextField = new JTextField();
        boxLayoutPanel.add(titleTextField);

        changeLabelStyle(titleLabel); // change the style of label
        changeTextFieldStyle(titleTextField); // changes text field style(lol)

        // Author Input
        JLabel authorLabel = new JLabel("Author");
        boxLayoutPanel.add(authorLabel);

        JTextField authorTextField = new JTextField();
        boxLayoutPanel.add(authorTextField);

        changeLabelStyle(authorLabel);
        changeTextFieldStyle(authorTextField);

        // Publication Date Input
        JLabel publicationDateLabel = new JLabel("Publication Date");
        boxLayoutPanel.add(publicationDateLabel);

        JTextField publicationDateTextField = new JTextField();
        boxLayoutPanel.add(publicationDateTextField);

        changeLabelStyle(publicationDateLabel);
        changeTextFieldStyle(publicationDateTextField);

        // =======================================

        // Center Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputAreaPanel = new JPanel();
        centerPanel.add(inputAreaPanel, BorderLayout.WEST);

        inputAreaPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputDescriptionPanel = new JPanel();
        inputAreaPanel.add(inputDescriptionPanel);

        inputDescriptionPanel.setLayout(new BoxLayout(inputDescriptionPanel, BoxLayout.Y_AXIS));
        inputDescriptionPanel.setBackground(GlobalVariables.lightestColor);
        inputDescriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        // Description Input(Text Area)
        JLabel descriptionLabel = new JLabel("Description (Optional)");
        inputDescriptionPanel.add(descriptionLabel);

        JTextArea descriptionTextArea = new JTextArea();
        changeTextAreaStyle(descriptionTextArea); // change the style of the text Area

        JScrollPane scrollPane = new JScrollPane(descriptionTextArea); // add the textArea to the scroll Pane
        inputDescriptionPanel.add(scrollPane);

        scrollPane.setPreferredSize(new Dimension(GlobalVariables.width / 3, GlobalVariables.height / 7));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        changeLabelStyle(descriptionLabel); // change the style of label

        // Total Copies Input
        JLabel totalCopiesLabel = new JLabel("Total Copies");
        inputDescriptionPanel.add(totalCopiesLabel);

        JTextField totalCopiesTextField = new JTextField();
        inputDescriptionPanel.add(totalCopiesTextField);

        changeLabelStyle(totalCopiesLabel);
        changeTextFieldStyle(totalCopiesTextField);

        // Submit Button
        JButton submitButton = new JButton("Submit");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FlowLayout that aligns to the right
        buttonPanel.add(submitButton);

        buttonPanel.setBackground(GlobalVariables.lightestColor);

        inputDescriptionPanel.add(buttonPanel);

        changeButtonStyle(submitButton); // change the style of button
        // ===============================================================

        return this;
    }

    // change the style of labels
    private void changeLabelStyle(JLabel label) {
        label.setForeground(GlobalVariables.mediumColor);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
    }

    // change the style of text fields
    private void changeTextFieldStyle(JTextField textField) {
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 32));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setForeground(GlobalVariables.darkestColor);
        textField.setCaretColor(GlobalVariables.darkestColor);
        textField.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textfield gains focus(the caret is visible)
                // change border color
                textField.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textfield lost focus
                // get rid of border
                textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });
    }

    private void changeTextAreaStyle(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(GlobalVariables.darkestColor);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        textArea.setCaretColor(GlobalVariables.darkestColor);

        textArea.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textArea gains focus(the caret is visible)
                // change border color
                textArea.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textArea lost focus
                // get rid of border
                textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });
    }

    // change the style of buttons
    private void changeButtonStyle(JButton button) {
        button.setBorder(new EmptyBorder(5, 10, 5, 10)); // not margin
        button.setBackground(GlobalVariables.mediumColor); // background color
        button.setForeground(GlobalVariables.lightestColor); // text color
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setContentAreaFilled(false); // Disable default content area fill
        button.setOpaque(true); // Make the button opaque to allow custom background colors
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // set font of the button

        // check for Mouse input events on the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            // check if the mouse is hovering over a button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lighterColor); // Change background when mouse hovers
                button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
            }

            // check if the mouse was hovering over a button but exited
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // Restore original background when mouse exits
                button.setForeground(GlobalVariables.lightestColor); // Restore original text color when mouse exits
            }

            // check if mouse is holding press the button
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lightestColor); // Change color when button is pressed
                button.setForeground(GlobalVariables.darkestColor); // Change text color when mouse hovers
            }

            // check if the mouse was is holding press the button but exited
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // set to default color
                button.setForeground(GlobalVariables.lightestColor); // set to default color
            }
        });
    }

}
