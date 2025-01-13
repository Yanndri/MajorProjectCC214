package LandingPagesGUI;

import DataStructures.DLinkedList;
import DataStructures.DNode;
import DataStructures.BookLibrary;
import Objects.Book; // NOTE
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUIComponents {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access the button style methods

    // Search Bar
    JTextField searchTextField = new JTextField(""); // the search Text Field
    String searchedText; // the title of the button pressed in search suggestions
    String keyword; // get the text in the Search Bar(text field)

    // Search Filters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String[] searchFilters = { "Title", "Author" }; // The search filter options
    JComboBox<String> searchComboBox = new JComboBox<>(searchFilters); // combo box for choosing what filter to use
    String searchFilter; // the filter that the user is using to search(either Title / Author)

    // Panels >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public JPanel displayPanel = new JPanel(); // What panel is displayed at the center
    public JPanel buttonPanel = new JPanel(); // display here the buttons for input
    JPanel displaySearch = new JPanel(); // put here the available books from search bar

    // Returns the panel that should be displayed at the center
    // also needs a panel to display where the search bar is displayed
    public JPanel createSearchBar(JPanel searchBarPanel) {
        JPanel searchBar = new JPanel(new FlowLayout());
        searchBarPanel.add(searchBar, BorderLayout.CENTER);

        searchBar.setBackground(GlobalVariables.lightestColor);

        // Search Filter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JLabel searchLabel = new JLabel("Search by: ");
        searchBar.add(searchLabel); // Just a label for the textfield
        searchBar.add(searchComboBox);

        layoutManager.comboBoxStyleDefault(searchComboBox);
        // ====================================================

        searchLabel.setForeground(GlobalVariables.mediumColor);
        searchLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));

        searchTextField = new JTextField();
        searchBar.add(searchTextField); // Where the user will write the text to search

        layoutManager.textfieldStyleDefault(searchTextField);

        JButton clearIconButton = new JButton(GlobalVariables.clearIcon);
        searchBar.add(clearIconButton); // SEARCH BUTTON

        clearIconButton.setPreferredSize(
                new Dimension(GlobalVariables.clearIcon.getIconWidth(), GlobalVariables.clearIcon.getIconHeight()));
        clearIconButton.setIcon(null);

        layoutManager.buttonStyleIconDependent(clearIconButton);

        clearIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchTextField.setText("");
                clearIconButton.setIcon(null);
            }
        });
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { // When a character is inserted
                clearIconButton.setIcon(GlobalVariables.clearIcon);
                updateScrollBar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) { // When a character is removed
                if (searchTextField.getText().equals(""))
                    clearIconButton.setIcon(null);
                updateScrollBar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // typically used for attributes like formatting changes
                // We leave it empty for plain text
            }
        });

        searchTextField.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textfield gains focus(the caret is visible)
                if (!searchTextField.getText().equals("")) // if not equal to null
                    return;

                searchTextField.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
                updateScrollBar();
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textfield lost focus
                // get rid of border
                searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });

        // Ensure the searchTextField gains focus when the searchBarPanel is displayed
        searchBarPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                // Request focus for the searchTextField
                searchTextField.requestFocusInWindow();
            }
        });

        return searchBar;
    }

    // switch what panel is displayed on the bottom of search bar
    private void displayPanel(JPanel panel) {
        displaySearch.removeAll();
        displayPanel.remove(displaySearch);
        displayPanel.removeAll();
        displaySearch = panel;
        displayPanel.add(displaySearch, BorderLayout.CENTER);
    }

    // Update ScrollBar (Update the Available Books based on the search)
    private void updateScrollBar() {
        keyword = searchTextField.getText(); // get the text inside searchTextField / the Search Bar
        searchFilter = searchComboBox.getSelectedItem().toString(); // get what search filter is used
        displayPanel(instantiateScrollBar()); // display the search suggestions
        displayPanel.revalidate();// inform the layout manager that something has changed in the displayPanel
        displayPanel.repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
    }

    // Scroll Bar to display Available Books when searching
    public JPanel instantiateScrollBar() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        displayPanel.add(searchPanel);

        searchPanel.setBackground(GlobalVariables.lightestColor);

        JPanel scrollBarPanel = new JPanel(); // Put here the available books in the scroll bar
        scrollBarPanel.setLayout(new BoxLayout(scrollBarPanel, BoxLayout.Y_AXIS)); // components are arranged vertically
        scrollBarPanel.setBackground(GlobalVariables.lightestColor);

        availableBooks(scrollBarPanel);

        JScrollPane scrollPane = new JScrollPane(scrollBarPanel); // JScrollPane adds a scroll bar to a container
        searchPanel.add(scrollPane);

        layoutManager.scrollPaneStyleDefault(scrollPane);

        return searchPanel;
    }

    // Add the components of the scroll bar -- SEARCH RESULTS
    private void availableBooks(JPanel scrollBarPanel) {
        BookLibrary bookFetcher = new BookLibrary(); // class that have books
        bookFetcher.getBooks(); // get a list of books from LibraryTest (Doubly Linked List)

        DLinkedList<Book> List = null; // Store here the available books
        if (keyword.equals("")) {// if there is no text in textfield(no keywords)
            List = bookFetcher.bookshelf; // display all available books

        } else if (searchFilter.equals("Title")) { // if the search filter is set to Title
            List = bookFetcher.searchTitle(keyword); // search for the title of the book that contains the keyword

        } else if (searchFilter.equals("Author")) { // if the search filter is set to Author
            List = bookFetcher.searchAuthor(keyword); // search for the author/s of the book that contains the keyword
        }

        DNode<Book> bookNode;
        bookNode = List.head; // get the head of the list of books

        while (bookNode != null) { // loop until bookNode is null
            Book book = bookNode.getItem(); // get the item in the book node

            // add displayPanel component to the scroll bar
            scrollBarPanel.add(createBookButton(book));

            bookNode = bookNode.getNext(); // to iterate to the linked list
            displayPanel.revalidate();
            displayPanel.repaint();
        }
    }

    // Create interactive buttons for Books available
    private JButton createBookButton(Book book) {
        JButton button = new JButton(
                "<html><div style='text-align: left;'>" + book.getTitle()
                        + "</div><div style='text-align: left;'>by: " + book.getAuthors() + "</div></html>");

        layoutManager.buttonStyleSearchSuggestions(button); // style the button to a suggested button
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchedText = book.getTitle(); // get the text of the button
                searchTextField.setText(searchedText); // set the text field of the search bar the clicked button

                System.out.println("Display Book " + book.getTitle());

                if (GlobalVariables.userType.equals("Admin")) // Admin is editing books
                    displayPanel(instantiateInputFields(book)); // Display Panel for input fields
                if (GlobalVariables.userType.equals("User")) // User is checking books
                    displayPanel(instantiateBookPanel(book)); // Display Panel to Book Panel
                displayPanel.revalidate(); // inform the layout manager that something has changed in the displayPanel
                displayPanel.repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
            }
        });
        return button;
    }

    // displays the book that was searched (For Users)
    private JPanel instantiateBookPanel(Book book) {
        JPanel bookPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        bookPanel.setBackground(GlobalVariables.lightestColor);
        bookPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        // A book image cuz why not
        JLabel bookImage = new JLabel(GlobalVariables.bookPlaceholderImage);
        bookPanel.add(bookImage);

        JPanel bookInformationPanel = new JPanel();
        bookPanel.add(bookInformationPanel);

        bookInformationPanel.setLayout(new BoxLayout(bookInformationPanel, BoxLayout.Y_AXIS));
        bookInformationPanel.setBackground(GlobalVariables.lightestColor);

        // The title of the Book
        JLabel title = new JLabel(
                "<html><body style='width: " + 420
                        + "px; text-align: left'>" + searchedText + "</body></html>");
        bookInformationPanel.add(title);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(GlobalVariables.mediumColor);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        // Author
        JLabel author = new JLabel(
                "<html><body style='width: " + 420
                        + "px; text-align: left'>by " + book.getAuthors() + "</body></html>");
        bookInformationPanel.add(author);

        author.setHorizontalAlignment(SwingConstants.CENTER);
        author.setForeground(GlobalVariables.darkestColor);
        author.setFont(new Font("Monospaced", Font.BOLD, 12));

        // Description
        JLabel description = new JLabel(
                "<html><body style='width: " + GlobalVariables.width / 2
                        + "px; text-align: center'>" + book.getDescription() + "</body></html>");
        bookPanel.add(description);

        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setForeground(GlobalVariables.mediumColor);
        description.setFont(new Font("Monospaced", Font.PLAIN, 12));

        return bookPanel;
    }

    // for admin inputting data on a book (For Admins)
    public JPanel instantiateInputFields(Book book) {
        // INPUT FIELDS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        JPanel inputFieldsPanel = new JPanel(new GridLayout(1, 2, 0, 0));
        displayPanel.add(inputFieldsPanel, BorderLayout.CENTER);

        // 1st Column >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel inputFields = new JPanel(); // Add here the Title, Author, Publication Date Input Fields
        inputFieldsPanel.add(inputFields, BorderLayout.CENTER); // covers the center part of the west border

        inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.Y_AXIS)); // components are arranged vertically
        inputFields.setBackground(GlobalVariables.lightestColor);

        // Title Input
        JLabel titleLabel = new JLabel("Title");
        JTextField titleTextField = layoutManager.createInputField(inputFields, titleLabel); // input field for title

        // Author Input
        JLabel authorLabel = new JLabel("Author");
        JTextField authorTextField = layoutManager.createInputField(inputFields, authorLabel);

        // Publication Date Input
        JLabel publicationDateLabel = new JLabel("Publication Date");
        JTextField publicationDateTextField = layoutManager.createInputField(inputFields, publicationDateLabel);

        // 2nd Column >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel innerCenterPanel = new JPanel(new BorderLayout());
        inputFieldsPanel.add(innerCenterPanel, BorderLayout.CENTER);

        innerCenterPanel.setBackground(GlobalVariables.lightestColor);

        JPanel inputDescriptionPanel = new JPanel(); // put here the description input, total copies input, etc.
        innerCenterPanel.add(inputDescriptionPanel, BorderLayout.WEST);

        inputDescriptionPanel.setLayout(new BoxLayout(inputDescriptionPanel, BoxLayout.Y_AXIS));
        inputDescriptionPanel.setBackground(GlobalVariables.lightestColor);
        inputDescriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        // Total Copies Input
        JLabel totalCopiesLabel = new JLabel("Total Copies");
        JTextField totalCopiesLabelTextField = layoutManager.createInputField(inputDescriptionPanel, totalCopiesLabel);

        // Description Input(Text Area)
        JLabel descriptionLabel = new JLabel("Description (Optional)");
        inputDescriptionPanel.add(descriptionLabel);

        layoutManager.labelStyleDefault(descriptionLabel); // change the style of label

        JTextArea descriptionTextArea = new JTextArea();
        layoutManager.textareaStyleDefault(descriptionTextArea); // change the style of the text Area

        JScrollPane scrollPane = new JScrollPane(descriptionTextArea); // add a scroll bar for text area
        inputDescriptionPanel.add(scrollPane);

        layoutManager.scrollPaneStyleDefault(scrollPane);
        scrollPane.setPreferredSize(new Dimension(GlobalVariables.width / 3, GlobalVariables.height / 2));

        // Adding text to fields
        if (searchTextField.getText().isBlank()) {
            titleTextField.setText("");
            authorTextField.setText("");
            publicationDateTextField.setText("");
            totalCopiesLabelTextField.setText("");
            descriptionTextArea.setText("");
        } else if (book != null) {// check if there is no books passed(for add books)
            titleTextField.setText(book.getTitle());
            authorTextField.setText(book.getAuthors().toString());
            publicationDateTextField.setText(book.getPublicationDate());
            totalCopiesLabelTextField.setText(book.getTotalCopies() + "");
            descriptionTextArea.setText(book.getDescription());
        }

        // put in this panel the buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // FlowLayout that aligns to the right
        inputDescriptionPanel.add(buttonPanel);

        buttonPanel.setBackground(GlobalVariables.lightestColor);

        if (book != null) { // Remove button only shows when a book is passed
            // Remove Button (when user wants to delete the book)
            JButton removeButton = new JButton("Remove");
            buttonPanel.add(removeButton);

            layoutManager.buttonStyleDefault(removeButton); // change the style of button

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if (book != null) {
                        String title = book.getTitle();
                        int result = JOptionPane.showConfirmDialog(null,
                                "Are you sure to remove book " + title + " ? This action cannot be undone.",
                                "Book Removed", JOptionPane.OK_CANCEL_OPTION);
                        if (result == 0) {
                            System.out.println("Removing book: " + book.getTitle());
                            if (new BookLibrary().removeBook(book)) {
                                searchTextField.setText("");
                                titleTextField.setText("");
                                authorTextField.setText("");
                                publicationDateTextField.setText("");
                                totalCopiesLabelTextField.setText("");
                                descriptionTextArea.setText("");
                                JOptionPane.showMessageDialog(null, "The book ''" + title + "'' is removed successfully.",
                                        "Book Removed", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println(this + " > Removed Book:");
                                System.out.println(">> title: " + book.getTitle());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a book first.", "Book Removal", JOptionPane.OK_OPTION);
                    }
                }
            });
        }

        // Submit Button (when user is done with their inputs)
        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        layoutManager.buttonStyleDefault(submitButton); // change the style of button

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(this + " > Submitted Book:");
                System.out.println(">> title: " + titleTextField.getText());
                System.out.println(">> author: " + authorTextField.getText());
                System.out.println(">> publicationDate: " + publicationDateTextField.getText());
                System.out.println(">> description: " + descriptionTextArea.getText());
                System.out.println(">> totalCopiesLabel: " + totalCopiesLabelTextField.getText());
            }
        });
        // ===============================================================

        return inputFieldsPanel;
    }

}
