package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class BorrowedBooksPage {

    // returns the panel for the borrowed Books Page
    public JPanel getBorrowedBooksPage() {
        JPanel borrowedBooksPage = new JPanel(new BorderLayout());
        borrowedBooksPage.setBorder(new EmptyBorder(20, 20, 0, 20));
        borrowedBooksPage.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>You're a Poopy Stinky Bear<html>");
        borrowedBooksPage.add(projectTitle, BorderLayout.NORTH);

        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        return borrowedBooksPage;
    }
}
