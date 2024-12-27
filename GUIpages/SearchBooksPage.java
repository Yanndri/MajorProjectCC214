package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class SearchBooksPage {
    // color palette
    private final Color darkestColor = Color.decode("#921A40");
    private final Color mediumColor = Color.decode("#C75B7A");
    private final Color lighterColor = Color.decode("#D9ABAB");
    private final Color lightestColor = Color.decode("#F4D9D0");

    public JPanel getSearchBooksPage() {
        JPanel borrowedBooksPage = new JPanel(new BorderLayout());
        borrowedBooksPage.setBorder(new EmptyBorder(20, 20, 0, 20));
        borrowedBooksPage.setBackground(lightestColor);

        JLabel projectTitle = new JLabel("<html>Library Management System<html>");
        borrowedBooksPage.add(projectTitle, BorderLayout.NORTH);
        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(mediumColor);

        JPanel searchBar = new JPanel(new FlowLayout());
        borrowedBooksPage.add(searchBar);

        searchBar.setBackground(lightestColor);

        JLabel searchLabel = new JLabel("Search Title");
        searchBar.add(searchLabel);

        searchLabel.setForeground(mediumColor);
        searchLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JTextField searchTextField = new JTextField();
        searchBar.add(searchTextField);

        searchTextField.setPreferredSize(new Dimension(320, 24));
        searchTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // searchTextField.setBackground(lightestColor);
        searchTextField.setForeground(darkestColor);

        return borrowedBooksPage;
    }
}
