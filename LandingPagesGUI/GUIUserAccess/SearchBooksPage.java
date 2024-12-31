package LandingPagesGUI.GUIUserAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.CustomLayoutManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access the button style methods
    JTextField searchTextField; // the search Text Field
    String searchedText; // the title of the book in book panel

    // Panels >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    JPanel scrollBar = instantiateScrollBar();
    JPanel bookPanel = instantiateBookPanel();
    JPanel displaySearch = new JPanel(); // display scroll bar if searching, or
    // display the bookPanel once done searching for the book

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
        northPanel.add(instantiateTextField(), BorderLayout.CENTER);
        // ========================================================================

        return this;
    }

    // switch what panel is displayed on the bottom of search bar
    private void displayPanel(JPanel panel) {
        displaySearch.removeAll();
        this.remove(displaySearch);
        displaySearch = panel;
        this.add(displaySearch, BorderLayout.CENTER);
    }

    // textfield for the search bar
    private JPanel instantiateTextField() {
        JPanel searchBar = new JPanel(new FlowLayout());

        searchBar.setBackground(GlobalVariables.lightestColor);

        JLabel searchLabel = new JLabel("Search Title:");
        searchBar.add(searchLabel); // Just a label for the textfield

        searchLabel.setForeground(GlobalVariables.mediumColor);
        searchLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));

        searchTextField = new JTextField();
        searchBar.add(searchTextField); // Where the user will write the text to search

        searchTextField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 24));
        searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchTextField.setForeground(GlobalVariables.darkestColor);

        searchTextField.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textfield gains focus(the caret is visible)
                // change border color
                searchTextField.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));

                displayPanel(instantiateScrollBar());
                revalidate();// inform the layout manager that something has changed in the displayPanel
                repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textfield lost focus
                // get rid of border
                searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });

        return searchBar;
    }

    // Scroll Bar to display Books
    private JPanel instantiateScrollBar() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(GlobalVariables.lightestColor);

        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new GridLayout(0, 1, 0, 0)); // Single-column layout
        scrollBarPanel.setBackground(GlobalVariables.lighterColor);

        availableBooks(scrollBarPanel);

        JScrollPane scrollPane = new JScrollPane(scrollBarPanel); // JScrollPane adds a scroll bar to a container
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Change how fast mouse wheel scrolls
        scrollPane // to make the size of the scroll pane small:
                .setBorder(BorderFactory.createEmptyBorder(0, GlobalVariables.width / 5, 0, GlobalVariables.width / 5));
        scrollPane.setBackground(GlobalVariables.lightestColor);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI());

        searchPanel.add(scrollPane);

        return searchPanel;
    }

    // Add the components of the scroll bar
    private void availableBooks(JPanel scrollBarPanel) {
        for (int i = 1; i <= 30; i++) {
            JButton button = new JButton(i + " Things I Hate About You");
            scrollBarPanel.add(button); // add this component to the scroll bar

            layoutManager.buttonStyleSearchSuggestions(button);
            button.setSize(new Dimension(GlobalVariables.width / 2, 20));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Book Panel displayed");

                    searchedText = button.getText(); // get the text of the button
                    searchTextField.setText(searchedText); // set the text field of the search bar the clicked button

                    displayPanel(instantiateBookPanel()); // Change the display Panel to Book Panel
                    revalidate(); // inform the layout manager that something has changed in the displayPanel
                    repaint(); // repaints the displayPanel, causing it to redraw itself(makes loading faster)
                }
            });
        }
    }

    // displays the book that was searched
    private JPanel instantiateBookPanel() {
        JPanel bookPanel = new JPanel(new BorderLayout());

        bookPanel.setBackground(GlobalVariables.lightestColor);

        // The title of the Book (North)
        JLabel title = new JLabel(
                "<html><body style='width: " + 420
                        + "px; text-align: center'>" + searchedText + "</body></html>");
        bookPanel.add(title, BorderLayout.NORTH);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(GlobalVariables.mediumColor);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        // A book image cuz why not (Center)
        JLabel bookImage = new JLabel(GlobalVariables.bookPlaceholderImage);
        bookPanel.add(bookImage, BorderLayout.CENTER);

        // Author and Description (South)
        JPanel southPanel = new JPanel(new BorderLayout());
        bookPanel.add(southPanel, BorderLayout.SOUTH);

        southPanel.setBackground(GlobalVariables.lightestColor);

        JLabel author = new JLabel(
                "<html><body style='width: " + 420
                        + "px; text-align: center'>by B.J. Maravillas</body></html>");
        southPanel.add(author, BorderLayout.CENTER);

        author.setHorizontalAlignment(SwingConstants.CENTER);
        author.setForeground(GlobalVariables.darkestColor);
        author.setFont(new Font("Monospaced", Font.BOLD, 12));

        // Description
        JLabel description = new JLabel(
                "<html><body style='width: " + 420
                        + "px; text-align: justify'>Love Romance and mfking Chris Rock holy sht that's kinda crazy, you should totally watch it holy sht it's chris rock in the flesh omg</body></html>");
        southPanel.add(description, BorderLayout.SOUTH);

        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setForeground(GlobalVariables.mediumColor);
        description.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Author

        return bookPanel;
    }

}

// Custom ScrollBar UI for modifying colors
class CustomScrollBarUI extends javax.swing.plaf.basic.BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        // Change the colors for the scroll bar
        this.thumbColor = GlobalVariables.mediumColor; // Color of the scroll bar thumb (handle)
        this.trackColor = GlobalVariables.lighterColor; // Color of the track
    }

    @Override
    protected JButton createDecreaseButton(int orientation) { // Decrease button(the icon at the bottom of scroll)
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0)); // Make the button size 0
        return button; // Hide decrease button
    }

    @Override
    protected JButton createIncreaseButton(int orientation) { // Increase button(the icon at the top of scroll)
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0)); // Make the button size 0
        return button; // Hide increase button
    }
}