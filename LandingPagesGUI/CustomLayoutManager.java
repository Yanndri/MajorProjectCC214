package LandingPagesGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import LibGUI.LoginInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Base Layout for most pages
public class CustomLayoutManager {
    JFrame frame = new JFrame(); // Main frame

    // Class references
    TimeFrame clock = new TimeFrame(); // Time Frame class for a clock

    // Base Layout for the frame
    public JFrame createCanvas() {
        frame.setSize(GlobalVariables.width, GlobalVariables.height);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    // Base Layout for the main panel(The background desgin)
    public JPanel createMainPanel() {
        System.out.println(this + " > Created a Main Panel");
        JPanel mainPanel = new JPanel();
        // design at the top
        mainPanel.setBorder(BorderFactory.createMatteBorder(// this makes clones of an image(40 is the size of image)
                40, 0, 0, 0, GlobalVariables.squareImage)); // and the MatteBorder parallels the image infinitely
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(GlobalVariables.lightestColor);

        // North Panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        JPanel northPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);

        northPanel.setBackground(GlobalVariables.lightestColor);

        // Children
        addProfile(northPanel);
        addLogOutButton(northPanel);
        // =====================================

        return mainPanel;
    }

    // Add a Profile Picture
    public void addProfile(JPanel panel) {
        System.out.println(this + " > Added a Profile with username " + GlobalVariables.username);

        JPanel buttonContainer = new JPanel(new FlowLayout()); // For margin
        panel.add(buttonContainer, BorderLayout.WEST);

        buttonContainer.setBackground(GlobalVariables.lightestColor);

        JButton profileButton = new JButton(GlobalVariables.username, GlobalVariables.defaultProfileImage);
        buttonContainer.add(profileButton, BorderLayout.EAST);

        buttonStyleIconDependent(profileButton);
    }

    // Add a Log Out button(To go to Login Page)
    public void addLogOutButton(JPanel panel) {
        System.out.println(this + " > Added a Back Button");

        JPanel buttonContainer = new JPanel(new FlowLayout()); // For margin
        panel.add(buttonContainer, BorderLayout.EAST);

        buttonContainer.setBackground(GlobalVariables.lightestColor);

        JButton backButton = new JButton("Log Out", GlobalVariables.logOutImage);
        buttonContainer.add(backButton, BorderLayout.EAST);

        buttonStyleIconDependent(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // delete this frame
                new LoginInterface(); // the Login interface class goes to the Login Page
            }
        });
    }

    // Create a taskbar(like a menu, or navbar) design only
    public JPanel createTaskBarPanel() {
        System.out.println(this + " > Created a Task Bar");
        JPanel taskBar = new JPanel(new GridLayout(1, 0));
        taskBar.setPreferredSize(new Dimension(GlobalVariables.width, GlobalVariables.height / 3));
        // using setSize() doesnt work on Grid Layouts
        taskBar.setBackground(GlobalVariables.lightestColor);
        taskBar.setBorder(new EmptyBorder(0, 0, 0, 20));

        return taskBar;
    }

    // create a search bar(it takes a textfield parameter because you need a
    // textfield to check action events on the search bar)
    public JPanel createSearchBar(JTextField textField) {
        System.out.println(this + " > Created a Search Bar");
        JPanel searchBar = new JPanel(new FlowLayout());

        searchBar.setBackground(GlobalVariables.lightestColor);

        JLabel searchLabel = new JLabel("Search Title:");
        searchBar.add(searchLabel); // Just a label for the textfield

        searchLabel.setForeground(GlobalVariables.mediumColor);
        searchLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));

        searchBar.add(textField); // Where the user will write the text to search

        textField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 24));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textField.setForeground(GlobalVariables.darkestColor);

        return searchBar;
    }

    // Create an input field,
    // has parameter JLabel to customize what type of input field(ex: username)
    public void createInputField(JPanel panel, JLabel label) {
        System.out.println(this + " > Created an Input Field for " + label.getText());
        panel.add(label);

        JTextField textField = new JTextField();
        panel.add(textField);

        labelStyleDefault(label); // change the style of label
        textfieldStyleDefault(textField); // changes text field style(lol)
    }

    // LABEL STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void labelStyleDefault(JLabel label) {
        label.setForeground(GlobalVariables.mediumColor);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
    }

    // TEXTFIELD STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void textfieldStyleDefault(JTextField textField) {
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setPreferredSize(new Dimension(GlobalVariables.width / 3, 24));
        textField.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        textField.setForeground(GlobalVariables.darkestColor);
        textField.setCaretColor(GlobalVariables.darkestColor);
        textField.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textfield gains focus(the caret is visible)
                // change border color
                textField.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textfield lost focus
                // get rid of border
                textField.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
            }
        });
    }

    // TEXTAREA STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void textareaStyleDefault(JTextArea textArea) {
        textArea.setLineWrap(true); // makes sure the text doesnt go out of bounds
        textArea.setWrapStyleWord(true); // text wraps by word
        textArea.setCaretColor(GlobalVariables.darkestColor); // change the color of the caret
        textArea.setForeground(GlobalVariables.darkestColor);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        textArea.addFocusListener(new FocusAdapter() { // to check for any events related to focus
            @Override
            public void focusGained(FocusEvent e) { // When the textArea gains focus(the caret is visible)
                // change border color
                textArea.setBorder(BorderFactory.createLineBorder(GlobalVariables.mediumColor, 1));
            }

            @Override
            public void focusLost(FocusEvent e) { // When the textArea lost focus
                // get rid of border
                textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        });
    }

    // COMBO BOX STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void comboBoxStyleDefault(JComboBox<String> comboBox) {
        comboBox.setBackground(GlobalVariables.mediumColor);
        comboBox.setFont(new Font("Monospaced", Font.BOLD, 14));
        comboBox.setForeground(GlobalVariables.lightestColor);

        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton(); // create a new arrow button
                arrowButton.setBorder(BorderFactory.createEmptyBorder()); // remove the focus border
                return arrowButton; // replace the arrow button with this arrow button
            }
        });
    }

    // SCROLL PANE STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void scrollPaneStyleDefault(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Change how fast mouse wheel scrolls
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(GlobalVariables.lightestColor);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        this.scrollBarStyleDefault(verticalScrollBar); // Change the vertical scroll bar style

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        this.scrollBarStyleDefault(horizontalScrollBar); // Change the horizontal scroll bar style
    }

    // SCROLL BAR STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void scrollBarStyleDefault(JScrollBar scrollBar) {
        scrollBar.setUnitIncrement(16); // Change how fast mouse wheel scrolls
        scrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // Change the colors for the scroll bar
                this.thumbColor = GlobalVariables.mediumColor; // Color of the scroll bar thumb (handle)
                this.trackColor = GlobalVariables.lighterColor; // Color of the track
            }

            @Override
            protected JButton createDecreaseButton(int orientation) { // Decrease button (the icon at the bottom of
                                                                      // scroll)
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0)); // Make the button size 0
                return button; // Hide decrease button
            }

            @Override
            protected JButton createIncreaseButton(int orientation) { // Increase button (the icon at the top of scroll)
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0)); // Make the button size 0
                return button; // Hide increase button
            }
        });
    }

    // BUTTON STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void buttonStyleDefault(JButton button) {
        button.setBorder(new EmptyBorder(5, 10, 5, 10)); // not margin
        button.setBackground(GlobalVariables.mediumColor); // background color
        button.setForeground(GlobalVariables.lightestColor); // text color
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setContentAreaFilled(false); // Disable default content area fill
        button.setOpaque(true); // Make the button opaque to allow custom background colors
        button.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // set font of the button

        // check for Mouse input events on the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            // check if the mouse is hovering over a button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lighterColor); // Change background when mouse hovers
                button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
            }

            // check if the mouse was hovering over a button but exited
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // Restore original background when mouse exits
                button.setForeground(GlobalVariables.lightestColor); // Restore original text color when mouse exits
            }

            // check if mouse is holding press the button
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lightestColor); // Change color when button is pressed
                button.setForeground(GlobalVariables.darkestColor); // Change text color when mouse hovers
            }

            // check if the mouse was is holding press the button but exited
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // set to default color
                button.setForeground(GlobalVariables.lightestColor); // set to default color
            }
        });
    }

    // When a button is toggled off (Only for buttons with toggle methods)
    public void buttonToggledOff(JButton button) {
        button.setForeground(GlobalVariables.darkestColor);
        button.setBackground(GlobalVariables.lightestColor);
    }

    // When a button is toggled on (Only for buttons with toggle methods)
    public void buttonToggledOn(JButton button) {
        button.setForeground(GlobalVariables.lightestColor);
        button.setBackground(GlobalVariables.darkestColor);
    }

    // to change the style of Option buttons(when user hovers, font, etc.)
    public void buttonStyleSimplistic(JButton button) {
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
                if (button.getBackground() != GlobalVariables.darkestColor) {
                    button.setBackground(GlobalVariables.mediumColor); // Change color when button is pressed
                    button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
                }
            }

            // check if the mouse was is holding press the button but exited
            // @Override
            // public void mouseReleased(java.awt.event.MouseEvent evt) {
            // button.setBackground(Color.WHITE); // Reset to original color when released
            // }
        });

    }

    // change the style of buttons that only has an icon
    public void buttonStyleIconDependent(JButton button) {
        button.setBorder(new EmptyBorder(2, 5, 2, 5)); // not margin
        button.setBackground(GlobalVariables.lightestColor); // background color
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setContentAreaFilled(false); // Disable default content area fill
        button.setOpaque(true); // Make the button opaque to allow custom background colors
        button.setFont(new Font("Monospaced", Font.PLAIN, 16));// set font of the button
        button.setForeground(GlobalVariables.darkestColor); // text color
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // Puts the text below the icon
        button.setHorizontalTextPosition(SwingConstants.CENTER); // Centers the text horizontally

        // check for Mouse input events on the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            // check if the mouse is hovering over a button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lighterColor); // Change background when mouse hovers
            }

            // check if the mouse was hovering over a button but exited
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lightestColor); // Restore original background when mouse exits
            }

            // check if mouse is holding press the button
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // Change color when button is pressed
            }

            // check if the mouse was is holding press the button but exited
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lightestColor); // set to default color
            }
        });
    }

    // for search prediction buttons(yknow when ur searching in the search bar)
    public void buttonStyleSearchSuggestions(JButton button) {
        button.setFont(new Font("Monospaced", Font.PLAIN, 16)); // set font of the button
        button.setHorizontalAlignment(SwingConstants.LEFT); // put the text at center horizontally
        button.setSize(new Dimension(GlobalVariables.width / 4, 20)); // change size(important)
        button.setFocusPainted(false); // gets rid of the annoying stuff(cant explain it)
        button.setBorder(new EmptyBorder(5, 5, 5, 5)); // margin border
        button.setForeground(GlobalVariables.darkestColor);
        button.setBackground(GlobalVariables.lightestColor);
        button.setIcon(GlobalVariables.searchIcon);

        // check if the mouse is hovering over a button that is currently not toggled
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.mediumColor); // Change background when mouse hovers
                button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.lightestColor); // Restore original background when mouse exits
                button.setForeground(GlobalVariables.darkestColor); // Restore original text color when mouse exits
            }

            // check if mouse is holding press the button
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.darkestColor); // Change color when button is pressed
                button.setForeground(GlobalVariables.lightestColor); // Change text color when mouse hovers
            }

            // check if the mouse was is holding press the button but exited
            // @Override
            // public void mouseReleased(java.awt.event.MouseEvent evt) {
            // button.setBackground(Color.WHITE); // Reset to original color when released
            // }
        });
    }

    // SingleActionListener() Erases multiple Action Listeners
    // incase it has multiple instances
    /*
     * Works like this:
     * SingleActionListener(buttonLabelHere, new ActionListener() {
     * 
     * @Override
     * public void actionPerformed(ActionEvent e) {
     * //code here when the button is pressed
     * } });
     */
    public void SingleActionListener(JButton button, ActionListener listener) {
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener i : listeners) {
            button.removeActionListener(i);
        }
        button.addActionListener(listener);
    }
}