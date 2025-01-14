package LandingPagesGUI;

import java.awt.Color;
import javax.swing.ImageIcon;

// Where variables that needs to be accessed globally
public class GlobalVariables {
    // User Information
    public static String username;
    public static String userType = "User"; // checks wether it's an admin or user

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
    public static ImageIcon backArrowImage = new ImageIcon("LandingPagesGUI\\Images\\BackArrow.png");
    public static ImageIcon logOutImage = new ImageIcon("LandingPagesGUI\\Images\\LogOut.png");
    public static ImageIcon defaultProfileImage = new ImageIcon("LandingPagesGUI\\Images\\DefaultProfile.png");
    public static ImageIcon bookPlaceholderImage = new ImageIcon("LandingPagesGUI\\Images\\BookPlaceholder.png");
    public static ImageIcon searchIcon = new ImageIcon("LandingPagesGUI\\Images\\searchIcon.png");
    public static ImageIcon clearIcon = new ImageIcon("LandingPagesGUI\\Images\\Clear.png");

    public static void setUserType(String newUserType) {
        userType = newUserType;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
}