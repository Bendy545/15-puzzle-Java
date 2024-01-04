import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Board puzzleBoard;
    private final  TimerPanel timerPanel;
    public GamePanel() {
        setLayout(new BorderLayout());

        puzzleBoard = new Board();
        timerPanel = new TimerPanel();

        add(timerPanel, BorderLayout.NORTH);
        add(puzzleBoard, BorderLayout.CENTER);
    }

    public TimerPanel getTimerPanel() {
        return timerPanel;
    }

    public Board getPuzzleBoard() {
        return puzzleBoard;
    }
}
