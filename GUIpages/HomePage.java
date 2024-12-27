package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HomePage {
    // color palette
    private final Color darkestColor = Color.decode("#921A40");
    private final Color mediumColor = Color.decode("#C75B7A");
    private final Color lighterColor = Color.decode("#D9ABAB");
    private final Color lightestColor = Color.decode("#F4D9D0");

    // display stuff here when on homePage
    public JPanel getHomePagePanel() {
        JPanel homePagePanel = new JPanel(new BorderLayout());
        homePagePanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        homePagePanel.setBackground(lightestColor);

        JLabel projectTitle = new JLabel("<html>Library <br>Management <br>System<html>");
        homePagePanel.add(projectTitle, BorderLayout.WEST);
        projectTitle.setVerticalAlignment(SwingConstants.NORTH);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 64));
        projectTitle.setForeground(mediumColor);

        ImageIcon bookImage = new ImageIcon("GUIpages\\Images\\book.png"); // image of a book
        // to change the size of an image just do this 3 lines:
        Image getImage = bookImage.getImage(); // load an image using a relative path
        Image resizedBookImage = getImage.getScaledInstance(320, 320, Image.SCALE_SMOOTH); // change the image size
        ImageIcon resizedBookIcon = new ImageIcon(resizedBookImage); // this is now the resized image

        JLabel bookImageIcon = new JLabel(resizedBookIcon); // Load image
        bookImageIcon.setVerticalAlignment(SwingConstants.NORTH);
        homePagePanel.add(bookImageIcon, BorderLayout.EAST);

        return homePagePanel;
    }
}
