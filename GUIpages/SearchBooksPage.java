package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class SearchBooksPage {

    // returns the panel for the search Books Page
    public JPanel getSearchBooksPage() {
        JPanel borrowedBooksPage = new JPanel(new BorderLayout());
        borrowedBooksPage.setBorder(new EmptyBorder(20, 20, 0, 20));
        borrowedBooksPage.setBackground(GlobalVariables.lightestColor);

        // North Part (Header)
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new BorderLayout()); // to store components on the north side of the page
        borrowedBooksPage.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>Library Management System<html>");
        northPanel.add(projectTitle, BorderLayout.NORTH);

        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        JPanel searchBar = new JPanel(new FlowLayout());
        northPanel.add(searchBar, BorderLayout.CENTER);

        searchBar.setBackground(GlobalVariables.lightestColor);

        JLabel searchLabel = new JLabel("Search Title:");
        searchBar.add(searchLabel); // Just a label for the textfield

        searchLabel.setForeground(GlobalVariables.mediumColor);
        searchLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JTextField searchTextField = new JTextField();
        searchBar.add(searchTextField); // Where the user will write the text to search

        searchTextField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 24));
        searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchTextField.setForeground(GlobalVariables.darkestColor);
        // ========================================================================

        // Center Part (Content) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel centerPanel = new JPanel(); // to store components on the north side of the page
        borrowedBooksPage.add(centerPanel, BorderLayout.CENTER);
        borrowedBooksPage.add(instantiateScrollBar(), BorderLayout.CENTER); // add the scroll bar to the page

        centerPanel.setBackground(GlobalVariables.lightestColor);
        // ===========================================================================

        return borrowedBooksPage;
    }

    // Scroll Bar to display Books
    private JScrollPane instantiateScrollBar() {
        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new GridLayout(0, 1, 0, 0)); // Single-column layout
        scrollBarPanel.setBackground(GlobalVariables.lighterColor);

        availableBooks(scrollBarPanel);

        JScrollPane scrollPane = new JScrollPane(scrollBarPanel); // JScrollPane adds a scroll bar to a container
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Change how fast mouse wheel scrolls
        scrollPane // to make the size of the scroll pane small:
                .setBorder(BorderFactory.createEmptyBorder(0, GlobalVariables.width / 5, 0, GlobalVariables.width / 5));
        scrollPane.setBackground(GlobalVariables.lightestColor);

        return scrollPane;
    }

    // Add the components of the scroll bar
    private void availableBooks(JPanel scrollBarPanel) {
        for (int i = 1; i <= 30; i++) {
            JButton placeholderButton = new JButton("Kiss MY ASS " + i);
            scrollBarPanel.add(placeholderButton); // add this component to the scroll bar

            placeholderButton.setBackground(GlobalVariables.lighterColor);
            placeholderButton.setForeground(GlobalVariables.darkestColor);
            setSearchButtonsStyle(placeholderButton);
        }
    }

    private void setSearchButtonsStyle(JButton button) {
        button.setFont(new Font("Monospaced", Font.PLAIN, 16)); // set font of the button
        button.setForeground(GlobalVariables.darkestColor);
        button.setHorizontalAlignment(SwingConstants.CENTER); // put the text at center horizontally
        button.setSize(new Dimension(GlobalVariables.width / 2, 20)); // change size(important)
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setBorder(new EmptyBorder(5, 0, 5, 0)); // margin border

        // check if the mouse is hovering over a button that is currently not toggled
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) { // check if button is currently on
                    button.setBackground(GlobalVariables.mediumColor); // Change background when mouse hovers
                    button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.lighterColor); // Restore original background when mouse exits
                    button.setForeground(GlobalVariables.darkestColor); // Restore original text color when mouse exits
                }
            }
        });

    }
}
