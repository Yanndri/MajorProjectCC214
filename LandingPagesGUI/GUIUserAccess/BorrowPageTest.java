package LandingPagesGUI.GUIUserAccess;
import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.GUIComponents;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BorrowPageTest extends JPanel {
    //CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access the button style methods
    // Search Bar
    GUIComponents guiComponents = new GUIComponents();
    //String searchedText; // the title of the button pressed in search suggestions
    // returns the panel for the search Books Page

    public JPanel getBorrowedBooksPageTest() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);
        // Add here the search bar and title >>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new BorderLayout()); // to store components on the north side of the page
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(GlobalVariables.lightestColor);
        JLabel projectTitle = new JLabel("<html>Library Management System<html>");
        northPanel.add(projectTitle, BorderLayout.NORTH);
        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setFont(new Font("Monospaced", Font.PLAIN, 32));
        projectTitle.setForeground(GlobalVariables.mediumColor);
        JPanel displayPanel = new JPanel(new BorderLayout()); // panel to display the scroll bar
        this.add(displayPanel, BorderLayout.CENTER); // set it to center of the panel
        displayPanel.setBackground(GlobalVariables.lightestColor);
        guiComponents.displayPanel = displayPanel; // set the panel for guiComponents to use be this panel
        // Add the search Bar
        guiComponents.createSearchBar(northPanel); // pass where the search bar will be put
        // ========================================================================
        return this;
    }
}