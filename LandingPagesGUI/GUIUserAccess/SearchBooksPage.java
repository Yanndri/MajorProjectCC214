package LandingPagesGUI.GUIUserAccess;

import DataStructures.DLinkedList;
import DataStructures.DNode;
import DataStructures.BookLibrary;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GlobalVariables;
import Objects.Book; // NOTE
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access the button style methods

    // Search Bar
    JTextField searchTextField; // the search Text Field
    String searchedText; // the title of the button pressed in search suggestions
    String keyword; // get the text in the Search Bar(text field)

    // Search Filters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    String[] searchFilters = { "Title", "Author" }; // The search filter options
    JComboBox<String> searchComboBox = new JComboBox<>(searchFilters); // combo box for choosing what filter to use
    String searchFilter; // the filter that the user is using to search(either Title / Author)

    // Panels >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    JPanel displaySearch = new JPanel(); // put here the available books from search bar

    // returns the panel for the search Books Page
    public JPanel getSearchBooksPage() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // North Part (Header)
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new BorderLayout()); // to store components on the north side of the page
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>Library Management System<html>");
        northPanel.add(projectTitle, BorderLayout.NORTH);

        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        // Add the search text field(Search Bar)
        northPanel.add(SearchBar(), BorderLayout.CENTER);
        // ========================================================================

        return this;
    }

    // Add the Search Bar
    private JPanel SearchBar() {
        JPanel searchBar = new JPanel(new FlowLayout());

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
                // This is typically used for attributes like formatting changes
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

        return searchBar;
    }

    // switch what panel is displayed on the bottom of search bar
    private void displayPanel(JPanel panel) {
        displaySearch.removeAll();
        this.remove(displaySearch);
        displaySearch = panel;
        this.add(displaySearch, BorderLayout.CENTER);
    }

    // Update ScrollBar (Update the Available Books based on the search)
    private void updateScrollBar() {
        keyword = searchTextField.getText(); // get the text inside searchTextField / the Search Bar
        searchFilter = searchComboBox.getSelectedItem().toString(); // get what search filter is used
        displayPanel(instantiateScrollBar()); // display the search suggestions
        this.revalidate();// inform the layout manager that something has changed in the displayPanel
        this.repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
    }

    // Scroll Bar to display Available Books when searching
    private JPanel instantiateScrollBar() {
        JPanel searchPanel = new JPanel(new BorderLayout());
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

            // add this component to the scroll bar
            scrollBarPanel.add(createBookButton(book));

            bookNode = bookNode.getNext(); // to iterate to the linked list
        }
    }

    private JButton createBookButton(Book book) {
        JButton button = new JButton(
                "<html><div style='text-align: left;'>" + book.getTitle()
                        + "</div><div style='text-align: left;'>by: " + book.getAuthors() + "</div></html>");

        layoutManager.buttonStyleSearchSuggestions(button); // style the button to a suggested button
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Book Panel displayed");

                searchedText = book.getTitle(); // get the text of the button
                searchTextField.setText(searchedText); // set the text field of the search bar the clicked button

                displayPanel(instantiateBookPanel(book)); // Change the display Panel to Book Panel
                revalidate(); // inform the layout manager that something has changed in the displayPanel
                repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
            }
        });
        return button;
    }

    // displays the book that was searched
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

        // Borrow the book Button
        JButton borrowButton = new JButton("Borrow");
        bookPanel.add(borrowButton);

        layoutManager.buttonStyleDefault(borrowButton);

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

}