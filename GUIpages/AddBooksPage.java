package GUIpages;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class AddBooksPage extends JPanel {
    // title, description, publicationDate, authors
    // private int totalCopies, borrowedCopies;
    // private MyLinkedList borrowers;
    // private QueueLinkedList requesters;

    public JPanel getAddBooksPage() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        JPanel bookContentPanel = new JPanel(new BorderLayout());
        this.add(bookContentPanel, BorderLayout.WEST);

        bookContentPanel.setBackground(GlobalVariables.lightestColor);

        JLabel headerLabel = new JLabel("Book Content");
        bookContentPanel.add(headerLabel, BorderLayout.NORTH);

        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setFont(new Font("Monospaced", Font.PLAIN, 32));
        headerLabel.setForeground(GlobalVariables.darkestColor);

        JPanel gridPanel = new JPanel(new GridLayout(0, 1));
        bookContentPanel.add(gridPanel, BorderLayout.CENTER);

        gridPanel.setBackground(GlobalVariables.lightestColor);

        // Title Input
        JLabel titleLabel = new JLabel("Title");
        gridPanel.add(titleLabel);

        JTextField titleTextField = new JTextField();
        gridPanel.add(titleTextField);

        changeLabelStyle(titleLabel); // change the style of label
        changeTextFieldStyle(titleTextField); // changes text field style(lol)

        // Author Input
        JLabel authorLabel = new JLabel("Author");
        gridPanel.add(authorLabel);

        JTextField authorTextField = new JTextField();
        gridPanel.add(authorTextField);

        changeLabelStyle(authorLabel);
        changeTextFieldStyle(authorTextField);

        // Publication Date Input
        JLabel publicationDateLabel = new JLabel("Publication Date");
        gridPanel.add(publicationDateLabel);

        JTextField publicationDateTextField = new JTextField();
        gridPanel.add(publicationDateTextField);

        changeLabelStyle(publicationDateLabel);
        changeTextFieldStyle(publicationDateTextField);

        // Center Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setBackground(GlobalVariables.lightestColor);

        // Description Input
        JLabel descriptionLabel = new JLabel("Description (Optional)");
        centerPanel.add(descriptionLabel, BorderLayout.NORTH);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));

        JTextArea descriptionTextArea = new JTextArea();
        centerPanel.add(descriptionTextArea, BorderLayout.CENTER);

        descriptionTextArea.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));
        descriptionTextArea.setForeground(GlobalVariables.darkestColor);

        changeLabelStyle(descriptionLabel); // change the style of label

        // ==========================================================

        // South Panel consists of buttons >>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel southPanel = new JPanel(new BorderLayout());
        bookContentPanel.add(southPanel, BorderLayout.SOUTH);

        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JButton submitButton = new JButton("Submit");
        southPanel.add(submitButton, BorderLayout.EAST);

        changeButtonStyle(submitButton); // change the style of button

        southPanel.setBackground(GlobalVariables.lightestColor);
        // end of South Panel =======================================

        JPanel eastPanel = new JPanel(new BorderLayout());
        this.add(eastPanel, BorderLayout.EAST);

        return this;
    }

    // change the style of labels
    private void changeLabelStyle(JLabel label) {
        label.setForeground(GlobalVariables.mediumColor);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
    }

    // change the style of text fields
    private void changeTextFieldStyle(JTextField textField) {
        textField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 24));
        textField.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));
        textField.setForeground(GlobalVariables.darkestColor);
    }

    // change the style of buttons
    private void changeButtonStyle(JButton button) {
        // border to have only specific sides have border lines while other sides dont
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
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
                if (button.getBackground() != GlobalVariables.darkestColor) { // check if button is toggled on
                    button.setBackground(GlobalVariables.lighterColor); // Change background when mouse hovers
                    button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
                }
            }

            // check if the mouse was hovering over a button but exited
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.mediumColor); // Restore original background when mouse exits
                    button.setForeground(GlobalVariables.lightestColor); // Restore original text color when mouse exits
                }
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
