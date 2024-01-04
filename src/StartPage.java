import javax.swing.*;
import java.awt.*;


public class StartPage extends JFrame {

    public StartPage() {
        setTitle("Puzzle game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JButton startButton = new JButton("Start 15 Puzzle");
        JButton leaveButton = new JButton("Leave");

        Dimension buttonSize = new Dimension(150, 50);
        startButton.setPreferredSize(buttonSize);
        leaveButton.setPreferredSize(buttonSize);
        leaveButton.setFocusable(false);
        startButton.setFocusable(false);

        startButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> createGameFrame());
                });
        leaveButton.addActionListener(e -> System.exit(0));

            JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
            buttonPanel.add(startButton);
            buttonPanel.add(leaveButton);

            add(buttonPanel, BorderLayout.CENTER);

            pack();
            setSize(400, 400);
            setLocationRelativeTo(null);
            setVisible(true);

        }


    private void createGameFrame() {
        JFrame gameFrame = new JFrame("Puzzle Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        TimerPanel timerPanel = gamePanel.getTimerPanel();
        gamePanel.getPuzzleBoard().setTimerPanel(timerPanel);
        gameFrame.setContentPane(gamePanel);

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

    }
}
