package LandingPagesGUI.GUIAdminAcess;

import DataStructures.BookLibrary;
import DataStructures.DLinkedList;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GlobalVariables;
import Objects.Book;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Admin Page to add books
public class AddBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access component styles
    BookLibrary bookshelf = new BookLibrary();

    // title, description, publicationDate, authors
    // private int totalCopies, borrowedCopies;
    // private MyLinkedList borrowers;
    // private QueueLinkedList requesters;

    public JPanel getAddBooksPagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // North Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 0));
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

        JPanel inputFields = new JPanel(); // Add here the Title, Author, Publication Date Input Fields
        westPanel.add(inputFields, BorderLayout.CENTER); // covers the center part of the west border

        inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.Y_AXIS)); // components are arranged vertically
        inputFields.setBackground(GlobalVariables.lightestColor);

        // Title Input
        JLabel titleLabel = new JLabel("Title");
        JTextField titleField = layoutManager.createInputField(inputFields, titleLabel); // create an input field for
                                                                                         // title

        // Author Input
        JLabel authorLabel = new JLabel("Author");
        JTextField authorField = layoutManager.createInputField(inputFields, authorLabel); // create an input field for
                                                                                           // Author

        // Publication Date Input
        JLabel publicationDateLabel = new JLabel("Publication Date");
        JTextField publicationDateField = layoutManager.createInputField(inputFields, publicationDateLabel); // create
                                                                                                             // an input
                                                                                                             // field
                                                                                                             // for Date

        // =======================================

        // Center Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);

        centerPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputAreaPanel = new JPanel(); // The West side of the Center Panel
        centerPanel.add(inputAreaPanel, BorderLayout.WEST);

        inputAreaPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputDescriptionPanel = new JPanel(); // put here the description input, total copies input, etc.
        centerPanel.add(inputDescriptionPanel, BorderLayout.WEST);

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

        layoutManager.scrollPaneStyleDefault(scrollPane);
        scrollPane.setPreferredSize(new Dimension(GlobalVariables.width / 3, GlobalVariables.height / 9));

        // Total Copies Input
        JLabel totalCopiesLabel = new JLabel("Total Copies");
        JTextField copiesTextField = layoutManager.createInputField(inputDescriptionPanel, totalCopiesLabel);

        // Submit Button (when user is done with their inputs)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FlowLayout that aligns to the right
        inputDescriptionPanel.add(buttonPanel);

        buttonPanel.setBackground(GlobalVariables.lightestColor);

        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        layoutManager.buttonStyleDefault(submitButton); // change the style of button

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authorField.getText().isBlank() || titleField.getText().isBlank()
                        || publicationDateField.getText().isBlank() || copiesTextField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error",
                            JOptionPane.ERROR_MESSAGE); // check if the fields are empty/blank
        
                } else {
                    if (descriptionTextArea.getText().isBlank())
                        descriptionTextArea.setText("No Description");
        
                    String[] authorsArray = authorField.getText().split("[,&]");
                    DLinkedList<String> authors = new DLinkedList<>();
                    for (String author : authorsArray) {
                        if (!author.equals("No Author/s") && !author.isBlank()) {
                            authors.addLast(author.trim()); // trim to delete the leading and trailing white spaces
                        }
                    }
        
                    try {
                        int noOfCopies = Integer.parseInt(copiesTextField.getText());
                        Book book = new Book(authors, titleField.getText(), descriptionTextArea.getText(),
                        publicationDateField.getText(), noOfCopies, null);
                        bookshelf.addBook(book);
                        bookshelf.updateFile(book, true);
                        JOptionPane.showMessageDialog(null, "Book Added Successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE); 

                        // clear the fields after submission
                        authorField.setText("");
                        titleField.setText("");
                        publicationDateField.setText("");
                        copiesTextField.setText("");
                        descriptionTextArea.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer for the number of copies.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        
        // ===============================================================

        return this;
    }

}
