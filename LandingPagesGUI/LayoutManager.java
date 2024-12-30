package LandingPagesGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Base Layout for most pages
public class LayoutManager {
    // JPanel mainPanel; // The MAIN PANEL(has a border layout)
    // JPanel currentPanel; // The panel that is currently displayed on the center
    // of main Panel
    // // (the center of the Main Panel takes the whole screen
    // // so that indicates that anything in the center is the most important)

    // Class references
    TimeFrame clock = new TimeFrame(); // Time Frame class for a clock

    // Base Layout for the frame
    public JFrame createCanvas() {
        JFrame frame = new JFrame();

        frame = new JFrame();
        frame.setSize(GlobalVariables.width, GlobalVariables.height);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return frame;
    }

    // Base Layout for the main panel(The background desgin)
    public JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        // design at the top
        mainPanel.setBorder(BorderFactory.createMatteBorder(// this makes clones of an image(40 is the size of image)
                40, 0, 0, 0, GlobalVariables.squareImage)); // and the MatteBorder parallels the image infinitely
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GlobalVariables.lightestColor);

        return mainPanel;
    }

    public JPanel createTaskBarPanel() {
        JPanel taskBar = new JPanel(new GridLayout(1, 0));
        taskBar.setPreferredSize(new Dimension(GlobalVariables.width, GlobalVariables.height / 3));
        // using setSize() doesnt work on Grid Layouts
        taskBar.setBackground(GlobalVariables.lightestColor);
        taskBar.setBorder(new EmptyBorder(0, 0, 0, 20));

        return taskBar;
    }

    // to change the style of Option buttons(when user hovers, font, etc.)
    public void buttonStyleSimplistic(JButton button) {
        // border to have only specific sides have border lines while other sides dont
        button.setBorder(new MatteBorder(1, 1, 0, 0, GlobalVariables.darkestColor));
        button.setBackground(GlobalVariables.lightestColor); // background color
        button.setForeground(GlobalVariables.darkestColor); // text color
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setContentAreaFilled(false); // Disable default content area fill
        button.setOpaque(true); // Make the button opaque to allow custom background colors
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // set font of the button

        // check for Mouse input events on the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {

            // check if the mouse is hovering over a button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("Bruh");
                if (button.getBackground() != GlobalVariables.darkestColor) { // check if button is toggled on
                    System.out.println("Brsad");
                    button.setBackground(GlobalVariables.lighterColor); // Change background when mouse hovers
                    button.setForeground(GlobalVariables.darkestColor); // Change text color when mouse hovers
                }
            }

            // check if the mouse was hovering over a button but exited
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.lightestColor); // Restore original background when mouse exits
                    button.setForeground(GlobalVariables.darkestColor); // Restore original text color when mouse exits
                }
            }

            // check if mouse is holding press the button
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.mediumColor); // Change color when button is pressed
                    button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
                }
            }

            // check if the mouse was is holding press the button but exited
            // @Override
            // public void mouseReleased(java.awt.event.MouseEvent evt) {
            // button.setBackground(Color.WHITE); // Reset to original color when released
            // }
        });

    }

    // When a button is toggled off (Only for buttons with toggle methods)
    public void buttonToggledOff(JButton button) {
        button.setForeground(GlobalVariables.darkestColor);
        button.setBackground(GlobalVariables.lightestColor);
    }

    // When a button is toggled on (Only for buttons with toggle methods)
    public void buttonToggledOn(JButton button) {
        button.setForeground(GlobalVariables.lightestColor);
        button.setBackground(GlobalVariables.darkestColor);
    }

}
