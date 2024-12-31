package LandingPagesGUI.AdminAcess;

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

public class MainAdminPage extends LayoutManager {
    JFrame frame = createCanvas();
    JPanel mainPanel = createMainPanel();
    JPanel currentPanel = new JPanel(); // the current panel page is checked here
    // This way you can edit the currentPanel(remove, setVisible(), etc.)

    // Panels instantiated from other classes >>>>>>>>>>>>>>>>>>>>
    AddBooksPage addBooksPage = new AddBooksPage();
    EditBooksPage editBooksPage = new EditBooksPage();
    HomePage homePage = new HomePage();
    TimeFrame clock = new TimeFrame();
    // Panels and Frames ==============================

    MainAdminPage() {
        initialize();
    }

    private void initialize() {
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
        JPanel taskBar = new JPanel(new GridLayout(1, 0));
        taskBar.setPreferredSize(new Dimension(GlobalVariables.width, GlobalVariables.height / 3));
        // using setSize() doesnt work on Grid Layouts
        taskBar.setBackground(GlobalVariables.lightestColor);
        taskBar.setBorder(new EmptyBorder(0, 0, 0, 20));

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
                if (currentPanel != homePage.getHomePagePanel()) {
                    buttonToggledOn(homePageButton);

                    buttonToggledOff(editBooksButton);
                    buttonToggledOff(addBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(homePage.getHomePagePanel());
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
            }
        });

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(addBooksButton, new ActionListener() { // listen for gui events on addBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != addBooksPage.getAddBooksPage()) {
                    buttonToggledOn(addBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(editBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(addBooksPage.getAddBooksPage());
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
            }
        });

        SingleActionListener(editBooksButton, new ActionListener() { // listen for gui events on editBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPanel != editBooksPage.getEditBooksPage()) {
                    buttonToggledOn(editBooksButton);

                    buttonToggledOff(homePageButton);
                    buttonToggledOff(addBooksButton);

                    mainPanel.remove(currentPanel); // remove the current panel
                    setActivePage(editBooksPage.getEditBooksPage());
                    frame.revalidate(); // inform the layout manager that something has changed in the frame
                    frame.repaint(); // repaints the frame, causing it to redraw itself.
                }
            }
        });

        return taskBar;
    }

    // change what panel should be displayed(based on what page)
    private void setActivePage(JPanel panel) {
        currentPanel.removeAll(); // required to delete all components,
        // since at times components may get duplicated
        currentPanel = panel; // before instantiating another component
        mainPanel.add(currentPanel, BorderLayout.CENTER); // the panel that will be displayed on the page
    }

    // Graphic Stuff Only >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // to change the style of Option buttons(when user hovers, font, etc.)
    private void setOptionsButtonStyle(JButton button) {
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
                if (button.getBackground() != GlobalVariables.darkestColor) { // check if button is toggled on
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
                button.setBackground(GlobalVariables.mediumColor); // Change color when button is pressed
                button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
            }

            // check if the mouse was is holding press the button but exited
            // @Override
            // public void mouseReleased(java.awt.event.MouseEvent evt) {
            // button.setBackground(Color.WHITE); // Reset to original color when released
            // }
        });

    }

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
     * }
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
        new MainAdminPage();
    }
}