import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel {
    private JLabel timerLabel;
    private Timer timer;
    private int seconds;

    public TimerPanel() {
        timerLabel = new JLabel("Time: 0 seconds");
        add(timerLabel);

        seconds = 0;

        timer = new Timer(1000, new ActionListener() {
            /**
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                updateTimerLabel();
            }
        });
    }


    public void startTimer() {
        timer.start();
    }

    public int getSeconds() {
        return seconds;
    }

    public void updateTimerLabel() {
        timerLabel.setText("Time: " + seconds + " seconds");
    }
}
