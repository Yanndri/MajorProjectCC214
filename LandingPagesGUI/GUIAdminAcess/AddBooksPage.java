package LandingPagesGUI.GUIAdminAcess;

import DataStructures.BookLibrary;
import DataStructures.DLinkedList;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GUIComponents;
import LandingPagesGUI.GlobalVariables;
import Objects.Book;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Admin Page to add books
public class AddBooksPage extends JPanel {
    CustomLayoutManager layoutManager = new CustomLayoutManager(); // used here to access component styles
    GUIComponents guiComponents = new GUIComponents();
    BookLibrary bookshelf = new BookLibrary();

    // title, description, publicationDate, authors
    // private int totalCopies, borrowedCopies;
    // private MyLinkedList borrowers;
    // private QueueLinkedList requesters;

    public JPanel getAddBooksPagePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.setBackground(GlobalVariables.lightestColor);

        // North Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 24, 0));
        this.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        JLabel headerLabel = new JLabel("Add Book");
        northPanel.add(headerLabel, BorderLayout.CENTER);

        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setFont(new Font("Monospaced", Font.PLAIN, 32));
        headerLabel.setForeground(GlobalVariables.darkestColor);
        // =============================================

        // Input fields
        JPanel inputFields = guiComponents.instantiateInputFields(null);
        this.add(inputFields, BorderLayout.CENTER);

        return this;
    }

}
