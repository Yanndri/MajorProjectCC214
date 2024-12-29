package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HomePage extends JPanel {

    // returns the panel for the home page
    public JPanel getHomePagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>Library <br>Management <br>System<html>");
        this.add(projectTitle, BorderLayout.WEST);
        projectTitle.setVerticalAlignment(SwingConstants.NORTH);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 64));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        // Book Icon >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        ImageIcon bookImage = new ImageIcon("GUIpages\\Images\\book.png"); // image of a book
        // to change the size of an image just do this 3 lines:
        Image getImage = bookImage.getImage(); // load an image using a relative path
        Image resizedBookImage = getImage.getScaledInstance(GlobalVariables.width / 3, GlobalVariables.width / 3,
                Image.SCALE_SMOOTH); // change the image size
        ImageIcon resizedBookIcon = new ImageIcon(resizedBookImage); // this is now the resized image

        JLabel bookImageIcon = new JLabel(resizedBookIcon); // Load image
        bookImageIcon.setVerticalAlignment(SwingConstants.NORTH);
        this.add(bookImageIcon, BorderLayout.EAST);
        // ==========================================================================

        return this;
    }

}
