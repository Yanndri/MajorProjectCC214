package LandingPagesGUI.GUIUserAccess;

import javax.swing.*;

import LandingPagesGUI.HomePage;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.TimeFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Here you can find the taskbar, and the mainPanel that displays the pages
public class MainUserPage extends CustomLayoutManager {
    JFrame frame = createCanvas();
    JPanel mainPanel = createMainPanel(); // used as a replacement for frame
    JPanel currentPanel = new JPanel(); // the current panel page is checked here
    // This way you can edit the currentPanel(remove, setVisible(), etc.)

    // Panels instantiated from other classes >>>>>>>>>>>>>>>>>>>>
    HomePage homePage = new HomePage();
    SearchBooksPage searchBooksPage = new SearchBooksPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    TimeFrame clock = new TimeFrame();
    // ==============================

    // instantiated Panels >>>>>>>>>>>>>>>>>>>>>>
    JPanel homePagePanel = homePage.getHomePagePanel();
    JPanel searchBooksPagePanel = searchBooksPage.getSearchBooksPage();
    JPanel borrowedBooksPagePanel = borrowedBooksPage.getBorrowedBooksPage();
    JPanel clockPanel = clock.getTimeFramePanel();
    // =================================

    public MainUserPage() {
        initialize();
    }

    public void initialize() {
        frame.add(mainPanel);

        // South Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel southPanel = new JPanel(new BorderLayout());
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add a Clock
        JPanel clockPanelLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // used so hte clock is right aligned
        southPanel.add(clockPanelLayout, BorderLayout.NORTH); // puts the clock panel on top of options

        clockPanelLayout.setBackground(GlobalVariables.lightestColor);
        clockPanelLayout.add(clockPanel, BorderLayout.NORTH); // Cock?(Clock)

        // Add a Taskbar
        southPanel.add(taskBar(), BorderLayout.SOUTH); // put the taskbar underneath Clock

        // Set a starting active page
        setActivePage(homePagePanel);// set homePage as the active panel
        // =========================================================

        frame.setVisible(true);// ensure all components are initialized before making the frame visible
    }

    // display options here (home page, search books, borrowed books, etc?)
    private JPanel taskBar() {
        System.out.println("Taskbar instantiated");
        JPanel taskBar = createTaskBarPanel();

        // Create the main buttons
        JButton homePageButton = new JButton("Home Page");
        JButton searchBooksButton = new JButton("Search Books");
        JButton borrowedBooksButton = new JButton("Borrowed Books");
        JButton borrowBookButton = new JButton("Borrow a Book"); // Add this button for Borrowing a Book

        // Instantiate the buttons as children of taskBar
        taskBar.add(homePageButton);
        taskBar.add(searchBooksButton);
        taskBar.add(borrowedBooksButton);
        taskBar.add(borrowBookButton); // Add Borrow a Book button to taskbar

        // Change the style of buttons
        buttonStyleSimplistic(homePageButton);
        buttonStyleSimplistic(searchBooksButton);
        buttonStyleSimplistic(borrowedBooksButton);
        buttonStyleSimplistic(borrowBookButton); // Apply style to the new button

        // Set the active button style
        homePageButton.setBackground(GlobalVariables.darkestColor);
        homePageButton.setForeground(GlobalVariables.lightestColor);

        // Button actions
        SingleActionListener(homePageButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != homePage.getHomePagePanel()) {
                    buttonToggledOn(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowedBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel);
                    setActivePage(homePage.getHomePagePanel());
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        SingleActionListener(searchBooksButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != searchBooksPage.getSearchBooksPage()) {
                    buttonToggledOn(searchBooksButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(borrowedBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel);
                    setActivePage(searchBooksPage.getSearchBooksPage());
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        SingleActionListener(borrowedBooksButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != borrowedBooksPagePanel) {// exit if the button is on already
                    buttonToggledOn(borrowedBooksButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel);
                    setActivePage(borrowedBooksPage.getBorrowedBooksPage());
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        // Action for Borrow a Book button
        SingleActionListener(borrowBookButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and display the Borrower form when the button is clicked
                Borrower.main(new String[0]); // This calls the static main method of the Borrower class
            }
        });

        return taskBar;
    }

    // change what panel should be displayed(based on what page)
    private void setActivePage(JPanel panel) {
        currentPanel = panel; // replace the current panel
        mainPanel.add(currentPanel, BorderLayout.CENTER); // the panel that will be displayed on the page
    }

    public static void main(String[] args) {
        // MainFrame frame = new MainFrame();
        new MainUserPage();
    }
}
