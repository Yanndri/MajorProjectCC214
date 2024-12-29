package GUIpages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimeFrame extends JPanel {

    Calendar calendar;
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    JLabel timeLabel = new JLabel();
    JLabel dayLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    String time, day, date;

    // returns a clock
    public JPanel getTimeFramePanel() {
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setTitle("Live Time");
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(400, 200)); // size (x,y)
        this.setBackground(GlobalVariables.lightestColor);
        // this.setResizable(false); // end of this

        timeLabel.setFont(new Font("Verdana", Font.BOLD, 16)); // time text fonts
        timeLabel.setForeground(GlobalVariables.darkestColor); // text color
        timeLabel.setBackground(GlobalVariables.lightestColor); // text background
        timeLabel.setOpaque(true); // end of time attributes

        dayLabel.setFont(new Font("Arial Black", Font.PLAIN, 16)); // day text font
        dayLabel.setForeground(GlobalVariables.mediumColor);

        dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12)); // date text font
        dateLabel.setForeground(GlobalVariables.mediumColor);

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        // this.setVisible(true);

        // Start the Timer to update the clock every second(tick tock)
        tick(); // intialize it first, but must be called inside the startClock function
        startClock();// runs the code to keep the clock running
        return this;
    }

    // This method starts the tick method using a Timer
    private void startClock() {
        // Create a new Timer that runs every 1000 milliseconds (1 second)
        Timer timer = new Timer(1000, e -> tick());
        timer.start(); // Start the timer
    }

    // function to be called every second
    public void tick() {
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);
    }

    // public static void main(String[] args) {
    // new TimeFrame();
    // }

}