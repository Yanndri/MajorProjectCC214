package LandingPagesGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import LibGUI.LoginInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    private LoginInterface loginInterface;

    // returns the panel for the home page
    public JPanel getHomePagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        JLabel projectTitle = new JLabel("<html>Library <br>Management <br>System<html>");
        this.add(projectTitle, BorderLayout.WEST);
        projectTitle.setVerticalAlignment(SwingConstants.NORTH);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 64));
        projectTitle.setForeground(GlobalVariables.mediumColor);

        // Book Icon >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        // to change the size of an image just do this 3 lines:
        Image getImage = GlobalVariables.bookImage.getImage(); // load an image using a relative path
        Image resizedBookImage = getImage.getScaledInstance(GlobalVariables.width / 4, GlobalVariables.width / 4,
                Image.SCALE_SMOOTH); // change the image size
        ImageIcon resizedBookIcon = new ImageIcon(resizedBookImage); // this is now the resized image

        JLabel bookImageIcon = new JLabel(resizedBookIcon); // Load image
        bookImageIcon.setVerticalAlignment(SwingConstants.NORTH);
        this.add(bookImageIcon, BorderLayout.EAST);
        // ==========================================================================

        return this;
    }

}
