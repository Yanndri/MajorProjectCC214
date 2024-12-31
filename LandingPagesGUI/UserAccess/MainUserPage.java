package LandingPagesGUI.UserAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.HomePage;
import LandingPagesGUI.LayoutManager;
import LandingPagesGUI.TimeFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Here you can find the taskbar, and the mainPanel that displays the pages
public class MainUserPage extends LayoutManager {
    JFrame frame = createCanvas();
    JPanel mainPanel = createMainPanel();
    JPanel currentPanel = new JPanel(); // the current panel page is checked here
    // This way you can edit the currentPanel(remove, setVisible(), etc.)

    // Panels instantiated from other classes >>>>>>>>>>>>>>>>>>>>
    HomePage homePage = new HomePage();
    SearchBooksPage searchBooksPage = new SearchBooksPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    TimeFrame clock = new TimeFrame();
    // Panels and Frames ==============================

    public MainUserPage() {
        initialize();
    }

    public void initialize() {
        frame.add(mainPanel);

        // South Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel southPanel = new JPanel(new BorderLayout());
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        JPanel clockPanelLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // used so hte clock is right aligned
        southPanel.add(clockPanelLayout, BorderLayout.NORTH); // puts the clock panel on top of options

        clockPanelLayout.setBackground(GlobalVariables.lightestColor);

        clockPanelLayout.add(clock.getTimeFramePanel(), BorderLayout.NORTH); // Cock?(Clock)
        southPanel.add(taskBar(), BorderLayout.SOUTH); // put the taskbar underneath Clock

        // childrens
        setActivePage(homePage.getHomePagePanel());// set homePage as the active panel
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

        // instantiate the buttons as children of taskBar
        taskBar.add(homePageButton);
        taskBar.add(searchBooksButton);
        taskBar.add(borrowedBooksButton);

        // change the style of buttons
        buttonStyleSimplistic(homePageButton);
        buttonStyleSimplistic(searchBooksButton);
        buttonStyleSimplistic(borrowedBooksButton);

        // since homePage is the first page to open once logged in
        homePageButton.setBackground(GlobalVariables.darkestColor); // set button background to darkest
        homePageButton.setForeground(GlobalVariables.lightestColor); // set button text to lightest for visual stuff

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(homePageButton, new ActionListener() { // listen for gui events on homePageButton

            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                if (currentPanel != homePage.getHomePagePanel()) {
                    buttonToggledOn(homePageButton);

                    buttonToggledOff(searchBooksButton);
                    buttonToggledOff(borrowedBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(homePage.getHomePagePanel());
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.
                }
            }
        });

        SingleActionListener(searchBooksButton, new ActionListener() { // listen for gui events on searchBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != searchBooksPage.getSearchBooksPage()) {
                    System.out.println("huhuh");
                    buttonToggledOn(searchBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(borrowedBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(searchBooksPage.getSearchBooksPage());
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.
                }
            }
        });

        SingleActionListener(borrowedBooksButton, new ActionListener() {// listen for gui events on borrowedBookButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != borrowedBooksPage.getBorrowedBooksPage()) {
                    buttonToggledOn(borrowedBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(searchBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(borrowedBooksPage.getBorrowedBooksPage());
                    mainPanel.revalidate(); // inform the layout manager that something has changed in the mainPanel
                    mainPanel.repaint(); // repaints the mainPanel, causing it to redraw itself.
                }
            }
        });
        // ====================================================================

        return taskBar;
    }

    // change what panel should be displayed(based on what page)
    private void setActivePage(JPanel panel) {
        currentPanel.removeAll(); // required to delete all components,
        // since at times components may get duplicated
        currentPanel = panel; // before instantiating another component
        mainPanel.add(currentPanel, BorderLayout.CENTER); // the panel that will be displayed on the page
    }

    // END OF GRAPHIC STUFF ===============================================

    // SingleActionListener() Erases multiple Action Listeners(idk, this was on my
    // last project, some cases
    // pressing a button runs two times)
    /*
     * Works like this:
     * SingleActionListener(buttonLabelHere, new ActionListener() {
     * 
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * //code here when the button is pressed
     * } });
     */
    private void SingleActionListener(JButton button, ActionListener listener) {
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener i : listeners) {
            button.removeActionListener(i);
        }
        button.addActionListener(listener);
    }

    public static void main(String[] args) {
        // MainFrame frame = new MainFrame();
        new MainUserPage();
    }
}
