package LandingPagesGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    // Base Layout for the main panel(The background desgin)
    public JPanel createMainPanel() {
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
        addProfilePicture(northPanel);
        addBackButton(northPanel);
        // =====================================

        return mainPanel;
    }

    // Add a Profile Picture
    private void addProfilePicture(JPanel panel) {
        System.out.println("Added a Back Button");

        JPanel userPanel = new JPanel(new FlowLayout());
        panel.add(userPanel, BorderLayout.EAST);

        userPanel.setBackground(GlobalVariables.lightestColor);

        JLabel userLabel = new JLabel("Admin");
        userPanel.add(userLabel); // Just a label for the textfield

        userLabel.setForeground(GlobalVariables.mediumColor);
        userLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JButton profileButton = new JButton(GlobalVariables.defaultProfileImage);
        userPanel.add(profileButton, BorderLayout.EAST);

        buttonStyleIconDependent(profileButton);
    }

    // Add a Back button
    private void addBackButton(JPanel panel) {
        System.out.println("Added a Back Button");

        JButton backButton = new JButton(GlobalVariables.backArrowImage);
        panel.add(backButton, BorderLayout.WEST);

        buttonStyleIconDependent(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginInterface(); // the Login interface class goes to the Login Page
            }
        });
    }

    // Create a taskbar(like a menu, or navbar) design only
    public JPanel createTaskBarPanel() {
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
        System.out.println("Search Bar added");
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

    // LABEL STYLES
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void labelStyleDefault(JLabel label) {
        label.setForeground(GlobalVariables.mediumColor);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
    }
    // LABEL STYLES END =================================================

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
    // TEXTFIELD STYLES END =================================================

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
    // TEXTAREA STYLES END =================================================

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
        System.out.println("Set button to simplistic style");
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
        button.setBorder(new EmptyBorder(5, 10, 5, 10)); // not margin
        button.setBackground(GlobalVariables.lightestColor); // background color
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
        button.setBorder(new EmptyBorder(5, 48, 5, 5)); // margin border
        button.setForeground(GlobalVariables.darkestColor);
        button.setBackground(GlobalVariables.lightestColor);

        // check if the mouse is hovering over a button that is currently not toggled
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GlobalVariables.darkestColor); // Change background when mouse hovers
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
    // BUTTON STYLES END =======================================================

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
