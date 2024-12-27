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
    JPanel currentPanel;

    private final int width = 1000;
    private final int height = 700;

    // color palette
    private final Color darkestColor = Color.decode("#921A40");
    private final Color mediumColor = Color.decode("#C75B7A");
    private final Color lighterColor = Color.decode("#D9ABAB");
    private final Color lightestColor = Color.decode("#F4D9D0");

    public index() {
        initialize();
    }

    private void initialize() {
        // Frame initializors >>>>>>>>>>>>>>>>>>>
        System.out.println("Initialized");
        frame = new JFrame();
        frame.setSize(width, height);
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
        mainPanel.setBackground(lightestColor);

        // childrens
        currentPanel = homePage.getHomePagePanel(); // get the Home Page Panel
        setActivePage(currentPanel);// the page that opens first once logged in
        mainPanel.add(optionsPanel(), BorderLayout.SOUTH);
        // =========================================================

        frame.setVisible(true);// ensure all components are initialized before making the frame visible
    }

    // display options here (home page, search books, borrowed books, etc?)
    private JPanel optionsPanel() {
        JPanel optionsPanel = new JPanel(new GridLayout(1, 3));
        optionsPanel.setPreferredSize(new Dimension(width, height / 3));// using setSize() doesnt work on Grid Layouts
        optionsPanel.setBackground(lightestColor);
        optionsPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

        JButton homePageButton, searchBooksButton, borrowedBooksButton; // Create the main buttons
        homePageButton = new JButton("Home Page");
        searchBooksButton = new JButton("Search Books");
        borrowedBooksButton = new JButton("Borrowed Books");

        // change the style of buttons
        setOptionsButtonStyle(homePageButton);
        setOptionsButtonStyle(searchBooksButton);
        setOptionsButtonStyle(borrowedBooksButton);

        // since homePage is the first page to open once logged in
        homePageButton.setBackground(darkestColor); // set button background to darkest(to tell the user is on homepage)
        homePageButton.setForeground(lightestColor); // set button text to lightest for visual stuff

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(homePageButton, new ActionListener() { // listen for gui events on homePageButton

            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                homePageButton.setBackground(darkestColor); // set the background to darkest color
                homePageButton.setForeground(lightestColor); // set the text color to lightest color

                // do the opposite for other buttons
                searchBooksButton.setForeground(darkestColor);
                borrowedBooksButton.setForeground(darkestColor);
                searchBooksButton.setBackground(lightestColor);
                borrowedBooksButton.setBackground(lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = homePage.getHomePagePanel(); // get a new current panel(in this case home page)
                setActivePage(currentPanel); // pass it as the new active panel
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(searchBooksButton, new ActionListener() { // listen for gui events on searchBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooksButton.setBackground(darkestColor);
                searchBooksButton.setForeground(lightestColor);

                homePageButton.setForeground(darkestColor);
                borrowedBooksButton.setForeground(darkestColor);
                homePageButton.setBackground(lightestColor);
                borrowedBooksButton.setBackground(lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = searchBooksPage.getSearchBooksPage();
                setActivePage(currentPanel); // pass it as the new active panel
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(borrowedBooksButton, new ActionListener() {// listen for gui events on borrowedBookButton

            @Override
            public void actionPerformed(ActionEvent e) {
                borrowedBooksButton.setBackground(darkestColor);
                borrowedBooksButton.setForeground(lightestColor);

                homePageButton.setForeground(darkestColor);
                searchBooksButton.setForeground(darkestColor);
                homePageButton.setBackground(lightestColor);
                searchBooksButton.setBackground(lightestColor);

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
        button.setBorder(new MatteBorder(1, 1, 0, 0, darkestColor)); // border
        button.setBackground(lightestColor); // background color
        button.setForeground(darkestColor); // text color
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // set font of the button

        // check if the mouse is hovering over a button that is currently not toggled
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != darkestColor) { // check if button is currently on
                    button.setBackground(mediumColor); // Change background when mouse hovers
                    button.setForeground(lightestColor); // Change text color when mouse hovers
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground() != darkestColor) {
                    button.setBackground(lightestColor); // Restore original background when mouse exits
                    button.setForeground(darkestColor); // Restore original text color when mouse exits
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
