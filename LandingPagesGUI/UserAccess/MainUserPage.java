package LandingPagesGUI.UserAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.HomePage;
import LandingPagesGUI.LayoutManager;
import LandingPagesGUI.TimeFrame;
import LandingPagesGUI.SearchBooksPage;
import LandingPagesGUI.BorrowedBooksPage;
import LandingPagesGUI.Borrower;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUserPage extends LayoutManager {
    private JFrame frame = createCanvas();
    private JPanel mainPanel = createMainPanel();
    private JPanel currentPanel = new JPanel(); // The current panel page is checked here
    // Panels instantiated from other classes
    private HomePage homePage = new HomePage();
    private SearchBooksPage searchBooksPage = new SearchBooksPage();
    private BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    private TimeFrame clock = new TimeFrame();

    // Constructor where frame is maximized
    public MainUserPage() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximizing the frame
        initialize();  // Initialize the components
    }

    private void initialize() {
        frame.add(mainPanel);
        
        // South Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel southPanel = new JPanel(new BorderLayout());
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        JPanel clockPanelLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Used so the clock is right aligned
        southPanel.add(clockPanelLayout, BorderLayout.NORTH); // Puts the clock panel on top of options

        clockPanelLayout.setBackground(GlobalVariables.lightestColor);
        clockPanelLayout.add(clock.getTimeFramePanel(), BorderLayout.NORTH); // Clock panel
        southPanel.add(taskBar(), BorderLayout.SOUTH); // Taskbar at the bottom

        // Set HomePage as the active page initially
        setActivePage(homePage.getHomePagePanel());

        // Display the frame
        frame.setVisible(true); 
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

        // Set the active button style
        homePageButton.setBackground(GlobalVariables.darkestColor);
        homePageButton.setForeground(GlobalVariables.lightestColor);

        // Button actions
        // Home Page Button
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

        // Search Books Button
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

        // Borrowed Books Button
        SingleActionListener(borrowedBooksButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != borrowedBooksPage.getBorrowedBooksPage()) {
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

        // Borrow Book Button
        SingleActionListener(borrowBookButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel borrowerPanel = Borrower.createBorrowerPanel();  // Get the panel from Borrower class
                if (currentPanel != borrowerPanel) {
                    buttonToggledOn(borrowBookButton);
                    buttonToggledOff(homePageButton);
                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowedBooksButton);
                    mainPanel.remove(currentPanel);
                    setActivePage(borrowerPanel); // Set Borrower panel as active panel
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        return taskBar;
    }

    // Change the active page being displayed
    private void setActivePage(JPanel panel) {
        currentPanel.removeAll(); // Clear previous panel content
        currentPanel = panel; // Set the new panel as the active one
        mainPanel.add(currentPanel, BorderLayout.CENTER); // Add new panel to main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Ensures only one ActionListener is added to the button to avoid multiple action triggers
    private void SingleActionListener(JButton button, ActionListener listener) {
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener i : listeners) {
            button.removeActionListener(i);
        }
        button.addActionListener(listener);
    }

    // Main method to run the program
    public static void main(String[] args) {
        new MainUserPage();
    }
}
