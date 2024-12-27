package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class BorrowedBooksPage {
    // color palette
    private final Color darkestColor = Color.decode("#921A40");
    private final Color mediumColor = Color.decode("#C75B7A");
    private final Color lighterColor = Color.decode("#D9ABAB");
    private final Color lightestColor = Color.decode("#F4D9D0");

    public JPanel getBorrowedBooksPage() {
        JPanel borrowedBooksPage = new JPanel(new BorderLayout());
        borrowedBooksPage.setBorder(new EmptyBorder(20, 20, 0, 20));
        borrowedBooksPage.setBackground(lightestColor);

        JLabel projectTitle = new JLabel("<html>You're a Poopy Stinky Bear<html>");
        borrowedBooksPage.add(projectTitle, BorderLayout.NORTH);

        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(mediumColor);

        return borrowedBooksPage;
    }
}
