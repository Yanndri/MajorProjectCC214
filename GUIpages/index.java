package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Here you can find the options Panel(buttons: homePage, searchBooks, borrowedBooks), and the mainPanel that displays the pages
public class index {
    JFrame frame;
    JPanel mainPanel; // optional, but just in case it has some uses (lmao)
    HomePage homePage = new HomePage();
    SearchBooksPage searchBooksPage = new SearchBooksPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    TimeFrame clock = new TimeFrame();
    JPanel currentPanel; // the current panel page is checked here

    public index() {
        initialize();
    }

    private void initialize() {
        // Frame initializors >>>>>>>>>>>>>>>>>>>
        System.out.println("Initialized");
        frame = new JFrame();
        frame.setSize(GlobalVariables.width, GlobalVariables.height);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ======================================

        // Main Panel initializers >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        mainPanel = new JPanel();
        frame.add(mainPanel);

        ImageIcon squareImage = new ImageIcon("GUIpages\\Images\\small square.png"); // design at the top
        mainPanel.setBorder(BorderFactory.createMatteBorder(
                40, 0, 0, 0, squareImage)); // this makes clones of an image(40 is the size of image)
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GlobalVariables.lightestColor);

        // childrens
        currentPanel = homePage.getHomePagePanel(); // get the Home Page Panel
        setActivePage(currentPanel);// set homePage as the active panel(puts the panel at center border layout)
        mainPanel.add(optionsPanel(), BorderLayout.SOUTH); // put the options panel down
        // mainPanel.add(clock.getTimeFramePanel(), BorderLayout.NORTH); // Clock?
        // =========================================================

        frame.setVisible(true);// ensure all components are initialized before making the frame visible
    }

    // display options here (home page, search books, borrowed books, etc?)
    private JPanel optionsPanel() {
        JPanel optionsPanel = new JPanel(new GridLayout(1, 3));
        optionsPanel.setPreferredSize(new Dimension(GlobalVariables.width, GlobalVariables.height / 3));
        // using setSize() doesnt work on Grid Layouts
        optionsPanel.setBackground(GlobalVariables.lightestColor);
        optionsPanel.setBorder(new EmptyBorder(20, 0, 0, 20));

        JButton homePageButton, searchBooksButton, borrowedBooksButton; // Create the main buttons
        homePageButton = new JButton("Home Page");
        searchBooksButton = new JButton("Search Books");
        borrowedBooksButton = new JButton("Borrowed Books");

        // change the style of buttons
        setOptionsButtonStyle(homePageButton);
        setOptionsButtonStyle(searchBooksButton);
        setOptionsButtonStyle(borrowedBooksButton);

        // since homePage is the first page to open once logged in
        homePageButton.setBackground(GlobalVariables.darkestColor); // set button background to darkest
        homePageButton.setForeground(GlobalVariables.lightestColor); // set button text to lightest for visual stuff

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(homePageButton, new ActionListener() { // listen for gui events on homePageButton

            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                homePageButton.setBackground(GlobalVariables.darkestColor); // set the background to darkest color
                homePageButton.setForeground(GlobalVariables.lightestColor); // set the text color to lightest color

                // do the opposite for other buttons
                searchBooksButton.setForeground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.darkestColor);
                searchBooksButton.setBackground(GlobalVariables.lightestColor);
                borrowedBooksButton.setBackground(GlobalVariables.lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = homePage.getHomePagePanel(); // get a new current panel(in this case home page)
                setActivePage(currentPanel); // pass it as the new active panel (puts the panel at center)
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(searchBooksButton, new ActionListener() { // listen for gui events on searchBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooksButton.setBackground(GlobalVariables.darkestColor);
                searchBooksButton.setForeground(GlobalVariables.lightestColor);

                homePageButton.setForeground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.darkestColor);
                homePageButton.setBackground(GlobalVariables.lightestColor);
                borrowedBooksButton.setBackground(GlobalVariables.lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = searchBooksPage.getSearchBooksPage();
                setActivePage(currentPanel); // pass it as the new active panel(puts the panel at center)
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(borrowedBooksButton, new ActionListener() {// listen for gui events on borrowedBookButton

            @Override
            public void actionPerformed(ActionEvent e) {
                borrowedBooksButton.setBackground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.lightestColor);

                homePageButton.setForeground(GlobalVariables.darkestColor);
                searchBooksButton.setForeground(GlobalVariables.darkestColor);
                homePageButton.setBackground(GlobalVariables.lightestColor);
                searchBooksButton.setBackground(GlobalVariables.lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = borrowedBooksPage.getBorrowedBooksPage();
                setActivePage(currentPanel); // pass it as the new active panel
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });
        // ====================================================================

        // instantiate the buttons as children of optionsPanel
        optionsPanel.add(homePageButton);
        optionsPanel.add(searchBooksButton);
        optionsPanel.add(borrowedBooksButton);

        return optionsPanel;
    }

    // change what panel should be displayed(based on what page)
    private void setActivePage(JPanel panel) {
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
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // set font of the button

        // check if the mouse is hovering over a button that is currently not toggled
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) { // check if button is currently on
                    button.setBackground(GlobalVariables.mediumColor); // Change background when mouse hovers
                    button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.lightestColor); // Restore original background when mouse exits
                    button.setForeground(GlobalVariables.darkestColor); // Restore original text color when mouse exits
                }
            }
        });

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
        new index();
    }
}
