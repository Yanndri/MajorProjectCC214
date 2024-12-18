import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeFrame extends JFrame{

    Calendar calendar;
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    JLabel timeLabel = new JLabel();
    JLabel dayLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    String time, day, date;


    TimeFrame(){ 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Live Time");
        this.setLayout(new FlowLayout());
        this.setSize(400,200); //size (x,y)
        this.setResizable(false); //end of frame

        timeLabel.setFont(new Font("Verdana",Font.BOLD,50)); //time text fonts
        timeLabel.setForeground(Color.BLACK); //text color
        timeLabel.setBackground(new Color(0xADD8E6)); //text background
        timeLabel.setOpaque(true); //end of time attributes

        dayLabel.setFont(new Font("Arial Black",Font.PLAIN,35)); //day text font

        dateLabel.setFont(new Font("Times New Roman",Font.PLAIN,30)); //date text font

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);

        tick();
    }

    public void tick(){
        while(true){
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        }
    }


}