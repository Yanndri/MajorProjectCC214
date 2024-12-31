package LandingPagesGUI.GUIAdminAcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GlobalVariables;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Admin Page to edit books
public class EditBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access component styles
    JTextField searchTextField = new JTextField(); // the search text field when searching for a book
    // title, description, publicationDate, authors
    // private int totalCopies, borrowedCopies;
    // private MyLinkedList borrowers;
    // private QueueLinkedList requesters;

    public JPanel getEditBooksPagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // North Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 0));
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel headerLabel = new JLabel("Edit Book");
        northPanel.add(headerLabel, BorderLayout.CENTER);

        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.PLAIN, 32));
        headerLabel.setForeground(GlobalVariables.darkestColor);

        // search bar(Search Books to Edit)
        searchBar();
        JPanel searchBar = layoutManager.createSearchBar(searchTextField);
        northPanel.add(searchBar, BorderLayout.SOUTH);
        // =============================================

        // West Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel westPanel = new JPanel(new BorderLayout());
        this.add(westPanel, BorderLayout.WEST);

        westPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputFieldsPanel = new JPanel(); // covers the center part of the west border
        westPanel.add(inputFieldsPanel, BorderLayout.CENTER);

        inputFieldsPanel.setBackground(GlobalVariables.lightestColor);

        JPanel boxLayoutPanel = new JPanel(); // make another panel that doesnt cover the whole west border
        inputFieldsPanel.add(boxLayoutPanel); // so the components doesnt fill the whole panel when using box layout

        boxLayoutPanel.setLayout(new BoxLayout(boxLayoutPanel, BoxLayout.Y_AXIS)); // components are arranged vertically
        boxLayoutPanel.setBackground(GlobalVariables.lightestColor);

        // Title Input
        JLabel titleLabel = new JLabel("Title");
        createInputField(boxLayoutPanel, titleLabel); // create an input field for title

        // Author Input
        JLabel authorLabel = new JLabel("Author");
        createInputField(boxLayoutPanel, authorLabel); // create an input field for Author

        // Publication Date Input
        JLabel publicationDateLabel = new JLabel("Publication Date");
        createInputField(boxLayoutPanel, publicationDateLabel); // create an input field for Date

        // =======================================

        // Center Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputAreaPanel = new JPanel(); // The West side of the Center Panel
        centerPanel.add(inputAreaPanel, BorderLayout.WEST);

        inputAreaPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputDescriptionPanel = new JPanel(); // put here the description input, total copies input, etc.
        inputAreaPanel.add(inputDescriptionPanel);

        inputDescriptionPanel.setLayout(new BoxLayout(inputDescriptionPanel, BoxLayout.Y_AXIS));
        inputDescriptionPanel.setBackground(GlobalVariables.lightestColor);
        inputDescriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        // Description Input(Text Area)
        JLabel descriptionLabel = new JLabel("Description (Optional)");
        inputDescriptionPanel.add(descriptionLabel);

        layoutManager.labelStyleDefault(descriptionLabel); // change the style of label

        JTextArea descriptionTextArea = new JTextArea();
        layoutManager.textareaStyleDefault(descriptionTextArea); // change the style of the text Area

        JScrollPane scrollPane = new JScrollPane(descriptionTextArea); // add a scroll bar for text area
        inputDescriptionPanel.add(scrollPane);

        scrollPane.setPreferredSize(new Dimension(GlobalVariables.width / 3, GlobalVariables.height / 9));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Total Copies Input
        JLabel totalCopiesLabel = new JLabel("Total Copies");
        createInputField(inputDescriptionPanel, totalCopiesLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FlowLayout that aligns to the right
        inputDescriptionPanel.add(buttonPanel);

        buttonPanel.setBackground(GlobalVariables.lightestColor);

        // Submit Button (when user is done with their inputs)
        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        layoutManager.buttonStyleDefault(submitButton); // change the style of button
        // ===============================================================

        return this;
    }

    // The search bar for searching books
    private void searchBar() {
        searchTextField.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textfield gains focus(the caret is visible)
                // change border color
                System.out.println("sadsa");
                searchTextField.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textfield lost focus
                // get rid of border
                searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });
    }

    // create an input field with a label and it's text field
    private void createInputField(JPanel panel, JLabel label) {
        panel.add(label);

        JTextField textField = new JTextField();
        panel.add(textField);

        layoutManager.labelStyleDefault(label); // change the style of label
        layoutManager.textfieldStyleDefault(textField); // changes text field style(lol)
    }
}
