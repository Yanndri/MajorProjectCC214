package LandingPagesGUI.GUIAdminAcess;

import javax.swing.*;

import LandingPagesGUI.GlobalVariables;
import LandingPagesGUI.HomePage;
import LandingPagesGUI.CustomLayoutManager;
import LandingPagesGUI.TimeFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAdminPage extends CustomLayoutManager {
    JFrame frame = createCanvas();
    JPanel mainPanel = createMainPanel();
    JPanel currentPanel = new JPanel(); // the current panel page is checked here
    // This way you can edit the currentPanel(remove, setVisible(), etc.)

    // Panel classes >>>>>>>>>>>>>>>>>>>>
    HomePage homePage = new HomePage();
    AddBooksPage addBooksPage = new AddBooksPage();
    EditBooksPage editBooksPage = new EditBooksPage();
    TimeFrame clock = new TimeFrame();
    // ==============================

    // instantiated Panels >>>>>>>>>>>>>>>>>>>>>>
    JPanel homePagePanel = homePage.getHomePagePanel();
    JPanel addBooksPagePanel = addBooksPage.getAddBooksPagePanel();
    JPanel editBooksPagePanel = editBooksPage.getEditBooksPagePanel();
    JPanel clockPanel = clock.getTimeFramePanel();
    // =================================

    MainAdminPage() {
        initialize();
    }

    private void initialize() {
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
        JButton addBooksButton = new JButton("Add Books");
        JButton editBooksButton = new JButton("Edit Books");

        // instantiate the buttons as children of taskBar
        taskBar.add(homePageButton);
        taskBar.add(addBooksButton);
        taskBar.add(editBooksButton);

        // change the style of buttons
        buttonStyleSimplistic(homePageButton);
        buttonStyleSimplistic(addBooksButton);
        buttonStyleSimplistic(editBooksButton);

        // since homePage is the first page to open once logged in
        homePageButton.setBackground(GlobalVariables.darkestColor); // set button background to darkest
        homePageButton.setForeground(GlobalVariables.lightestColor); // set button text to lightest for visual stuff

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(homePageButton, new ActionListener() { // listen for gui events on homePageButton

            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                if (currentPanel != homePagePanel) {
                    buttonToggledOn(homePageButton);

                    buttonToggledOff(editBooksButton);
                    buttonToggledOff(addBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(homePagePanel);
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
            }
        });

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(addBooksButton, new ActionListener() { // listen for gui events on addBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != addBooksPagePanel) {
                    buttonToggledOn(addBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(editBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(addBooksPagePanel);
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
            }
        });

        SingleActionListener(editBooksButton, new ActionListener() { // listen for gui events on editBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != editBooksPagePanel) {
                    buttonToggledOn(editBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(addBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(editBooksPagePanel);
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
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
        new MainAdminPage();
    }
}