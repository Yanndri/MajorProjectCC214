package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class BorrowedBooksPage extends JPanel {

    // returns the panel for the borrowed Books Page
    public JPanel getBorrowedBooksPage() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>You're a Poopy Stinky Bear<html>");
        this.add(projectTitle, BorderLayout.NORTH);

        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        return this;
    }
}
