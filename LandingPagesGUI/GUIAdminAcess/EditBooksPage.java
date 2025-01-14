package LandingPagesGUI.GUIAdminAcess;

import DataStructures.BookLibrary;
import DataStructures.DLinkedList;
import DataStructures.DNode;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GUIComponents;
import LandingPagesGUI.GlobalVariables;
import Objects.Book;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// title, description, publicationDate, authors
// private int totalCopies, borrowedCopies;
// private MyLinkedList borrowers;
// private QueueLinkedList requesters;

// Admin Page to edit books
public class EditBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access component styles

    // Search Bar
    GUIComponents guiComponents = new GUIComponents();
    String searchedText; // the title of the button pressed in search suggestions

    public JPanel getEditBooksPagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // Add here the search bar and title >>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 0));
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel headerLabel = new JLabel("Edit Book");
        northPanel.add(headerLabel, BorderLayout.CENTER);

        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.PLAIN, 32));
        headerLabel.setForeground(GlobalVariables.darkestColor);

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
