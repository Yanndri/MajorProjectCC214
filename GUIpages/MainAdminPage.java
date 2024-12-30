package GUIpages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAdminPage {
    JFrame frame = new JFrame();
    JPanel mainPanel; // optional, but just in case it has some uses (lmao)
    JPanel currentPanel; // the current panel page is checked here

    // Panels instantiated from other classes >>>>>>>>>>>>>>>>>>>>
    AddBooksPage addBooksPage = new AddBooksPage();
    EditBooksPage editBooksPage = new EditBooksPage();
    TimeFrame clock = new TimeFrame();
    // Panels and Frames ==============================

    MainAdminPage() {
        instantiate();
    }

    private void instantiate() {
        // Frame initializors >>>>>>>>>>>>>>>>>>>
        System.out.println("Admin Page Instantiated");
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
        mainPanel.setBorder(BorderFactory.createMatteBorder(// this makes clones of an image(40 is the size of image)
                40, 0, 0, 0, squareImage)); // and the MatteBorder parallels the image infinitely
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GlobalVariables.lightestColor);
        // ================================================

        // South Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel southPanel = new JPanel(new BorderLayout());
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        JPanel clockPanelLayout = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // used so hte clock is right aligned
        southPanel.add(clockPanelLayout, BorderLayout.NORTH); // puts the clock panel on top of options

        clockPanelLayout.setBackground(GlobalVariables.lightestColor);

        clockPanelLayout.add(clock.getTimeFramePanel(), BorderLayout.NORTH); // Cock?(Clock)
        southPanel.add(optionsPanel(), BorderLayout.SOUTH); // put the options panel underneath Clock

        // adds the current panel to the main panel at the center border layout
        currentPanel = addBooksPage.getAddBooksPage(); // get the Home Page Panel
        setActivePage(currentPanel);// set addBooksPage as the active panel(puts the panel at center border layout)
        // =========================================================

        frame.setVisible(true);// ensure all components are initialized before making the frame visible
    }

    // display options here (home page, search books, borrowed books, etc?)
    private JPanel optionsPanel() {
        JPanel optionsPanel = new JPanel(new GridLayout(1, 0));
        optionsPanel.setPreferredSize(new Dimension(GlobalVariables.width, GlobalVariables.height / 3));
        // using setSize() doesnt work on Grid Layouts
        optionsPanel.setBackground(GlobalVariables.lightestColor);
        optionsPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

        // Create the main buttons
        JButton addBooksButton = new JButton("Add Books");
        JButton editBooksButton = new JButton("Edit Books");
        JButton borrowedBooksButton = new JButton("Borrowed Books");
        // instantiate the buttons as children of optionsPanel
        optionsPanel.add(addBooksButton);
        optionsPanel.add(editBooksButton);
        // optionsPanel.add(borrowedBooksButton);

        // change the style of buttons
        setOptionsButtonStyle(addBooksButton);
        setOptionsButtonStyle(editBooksButton);
        setOptionsButtonStyle(borrowedBooksButton);

        // since homePage is the first page to open once logged in
        addBooksButton.setBackground(GlobalVariables.darkestColor); // set button background to darkest
        addBooksButton.setForeground(GlobalVariables.lightestColor); // set button text to lightest for visual stuff

        // What to do when the buttons get clicked >>>>>>>>
        SingleActionListener(addBooksButton, new ActionListener() { // listen for gui events on addBooksButton

            @Override
            public void actionPerformed(ActionEvent e) { // when home page is clicked:
                addBooksButton.setBackground(GlobalVariables.darkestColor); // set the background to darkest color
                addBooksButton.setForeground(GlobalVariables.lightestColor); // set the text color to lightest color

                // do the opposite for other buttons
                editBooksButton.setForeground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.darkestColor);
                editBooksButton.setBackground(GlobalVariables.lightestColor);
                borrowedBooksButton.setBackground(GlobalVariables.lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = addBooksPage.getAddBooksPage(); // get a new current panel(in this case add books page)
                setActivePage(currentPanel); // pass it as the new active panel (puts the panel at center border layout)
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(editBooksButton, new ActionListener() { // listen for gui events on editBooksButton

            @Override
            public void actionPerformed(ActionEvent e) {
                editBooksButton.setBackground(GlobalVariables.darkestColor);
                editBooksButton.setForeground(GlobalVariables.lightestColor);

                addBooksButton.setForeground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.darkestColor);
                addBooksButton.setBackground(GlobalVariables.lightestColor);
                borrowedBooksButton.setBackground(GlobalVariables.lightestColor);

                mainPanel.remove(currentPanel); // remove the current panel
                currentPanel = editBooksPage.getEditBooksPage(); // get a new current panel(in this case add books page)
                setActivePage(currentPanel); // pass it as the new active panel (puts the panel at center border layout)
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });

        SingleActionListener(borrowedBooksButton, new ActionListener() {// listen for gui events on borrowedBookButton

            @Override
            public void actionPerformed(ActionEvent e) {
                borrowedBooksButton.setBackground(GlobalVariables.darkestColor);
                borrowedBooksButton.setForeground(GlobalVariables.lightestColor);

                addBooksButton.setForeground(GlobalVariables.darkestColor);
                editBooksButton.setForeground(GlobalVariables.darkestColor);
                addBooksButton.setBackground(GlobalVariables.lightestColor);
                editBooksButton.setBackground(GlobalVariables.lightestColor);

                // mainPanel.remove(currentPanel); // remove the current panel
                // currentPanel = borrowedBooksPage.getBorrowedBooksPage();
                // setActivePage(currentPanel); // pass it as the new active panel
                frame.revalidate(); // inform the layout manager that something has changed in the frame
                frame.repaint(); // repaints the frame, causing it to redraw itself.
            }
        });
        // ====================================================================

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