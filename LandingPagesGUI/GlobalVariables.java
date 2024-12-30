package LandingPagesGUI;

import java.awt.Color;

import javax.swing.ImageIcon;

// Where variables that needs to be accessed globally
public class GlobalVariables {
    // canvas size
    public static int width = 1000;
    public static int height = 700;

    // color palette
    public static final Color darkestColor = Color.decode("#921A40"); // mainly used as text color
    public static final Color mediumColor = Color.decode("#C75B7A");
    public static final Color lighterColor = Color.decode("#D9ABAB");
    public static final Color lightestColor = Color.decode("#F4D9D0"); // mainly used as the background color

    // images
    public static ImageIcon squareImage = new ImageIcon("LandingPagesGUI\\Images\\small square.png");
    public static ImageIcon bookImage = new ImageIcon("LandingPagesGUI\\Images\\book.png");
}
