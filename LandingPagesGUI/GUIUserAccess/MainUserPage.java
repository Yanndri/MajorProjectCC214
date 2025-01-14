package LandingPagesGUI.GUIUserAccess;

import javax.swing.*;

import LandingPagesGUI.*;
import Objects.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Here you can find the taskbar, and the mainPanel that displays the pages
public class MainUserPage extends CustomLayoutManager {

    User account;// current account that is logged in

    JFrame frame = createCanvas();
    JPanel mainPanel; // used as a replacement for frame
    JPanel currentPanel = new JPanel(); // the current panel page is checked here
    // This way you can edit the currentPanel(remove, setVisible(), etc.)

    // Panels instantiated from other classes >>>>>>>>>>>>>>>>>>>>
    HomePage homePage = new HomePage();
    SearchBooksPage searchBooksPage = new SearchBooksPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    BorrowBooksPage borrowBooksPage = new BorrowBooksPage();
    TimeFrame clock = new TimeFrame();
    // ==============================

    // instantiated Panels >>>>>>>>>>>>>>>>>>>>>>
    JPanel homePagePanel = homePage.getHomePagePanel();
    JPanel searchBooksPagePanel = searchBooksPage.getSearchBooksPage();
    JPanel borrowedBooksPagePanel = borrowedBooksPage.getBorrowedBooksPage();
    JPanel borrower = borrowBooksPage.createBorrowerPanel();
    JPanel clockPanel = clock.getTimeFramePanel();
    // =================================

    // Constructor where frame is maximized
    public MainUserPage(User account) {
        this.account = account;
        GlobalVariables.setUsername(account.getFirstName());
        GlobalVariables.setUserType("User");
        mainPanel = createMainPanel(account);
        initialize(); // Initialize the components
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

    // Create the TaskBar panel with buttons
    private JPanel taskBar() {
        System.out.println("Taskbar instantiated");
        JPanel taskBar = createTaskBarPanel();

        // Create the main buttons
        JButton homePageButton = new JButton("Home Page");
        JButton searchBooksButton = new JButton("Search Books");
        JButton borrowedBooksButton = new JButton("Borrowed Books");
        JButton borrowBookButton = new JButton("Borrow a Book");

        // Instantiate the buttons as children of taskBar
        taskBar.add(homePageButton);
        taskBar.add(searchBooksButton);
        taskBar.add(borrowedBooksButton);
        taskBar.add(borrowBookButton);

        // Change the style of buttons
        buttonStyleSimplistic(homePageButton);
        buttonStyleSimplistic(searchBooksButton);
        buttonStyleSimplistic(borrowedBooksButton);
        buttonStyleSimplistic(borrowBookButton);

        // since homePage is the first page to open once logged in
        homePageButton.setBackground(GlobalVariables.darkestColor); // set button background to darkest
        homePageButton.setForeground(GlobalVariables.lightestColor); // set button text to lightest for visual stuff

        // Button actions
        // Home Page Button
        SingleActionListener(homePageButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                if (currentPanel != homePagePanel) {// exit if the button is on already
                    buttonToggledOn(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowedBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(homePagePanel); // change the page
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.
                }
            }
        });

        // Search Books Button
        SingleActionListener(searchBooksButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != searchBooksPagePanel) {// exit if the button is on already
                    buttonToggledOn(searchBooksButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(borrowedBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(searchBooksPagePanel);
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.
                }
            }
        });

        // Borrowed Books Button
        SingleActionListener(borrowedBooksButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != borrowedBooksPagePanel) {// exit if the button is on already
                    buttonToggledOn(borrowedBooksButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowBookButton);
                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(borrowedBooksPagePanel);
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.

                }
            }
        });

        // Borrow Book Button
        SingleActionListener(borrowBookButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != borrower) {
                    buttonToggledOn(borrowBookButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowedBooksButton);
                    mainPanel.remove(currentPanel);
                    setActivePage(borrower); // Set Borrower panel as active panel
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        return taskBar;
    }

    // Change the active page being displayed
    private void setActivePage(JPanel panel) {
        currentPanel = panel; // Set the new panel as the active one
        mainPanel.add(currentPanel, BorderLayout.CENTER); // Add new panel to main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("Ryan");
        new MainUserPage(user);
    }
}
