package GUIpages;

import java.awt.Color;

// Where variables that needs to be accessed globally by other classes on the same package
// (since the identifier is not public nor private only classes on the same package can access)
class GlobalVariables {
    // canvas size
    static int width = 1000;
    static int height = 700;

    // color palette
    static final Color darkestColor = Color.decode("#921A40"); // mainly used as text color
    static final Color mediumColor = Color.decode("#C75B7A");
    static final Color lighterColor = Color.decode("#D9ABAB");
    static final Color lightestColor = Color.decode("#F4D9D0"); // mainly used as the background color

}
