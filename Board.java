import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Board extends JPanel {

    private static final int NUMBER_OF_ROWS = 4;
    private static final int NUMBER_OF_COLUMNS = 4;
    private final JButton[][] board = new JButton[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
    private int emptyRow;
    private int emptyCol;

    private boolean timerStarted = false;
    private TimerPanel timerPanel;

    public void setTimerPanel(TimerPanel timerPanel) {
        this.timerPanel = timerPanel;
    }

    public Board() {
        setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
        int count = 1;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                JButton button = new JButton(count == NUMBER_OF_ROWS * NUMBER_OF_COLUMNS ?"": String.valueOf(count));
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setFocusable(false);
                button.addActionListener(new ButtonClickListener());
                button.setContentAreaFilled(false);
                button.setOpaque(true);
                add(button);
                board[i][j] = button;
                if (count == NUMBER_OF_ROWS * NUMBER_OF_COLUMNS) {
                    emptyRow = i;
                    emptyCol = j;
                }
                count++;
            }
        }
        shuffleTiles();
    }
    public JButton[][] getBoard(){
        return board;
    }

    public void shuffleTiles() {
        ArrayList<String> allTiles = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                allTiles.add(board[i][j].getText());
            }
        }

        Collections.shuffle(allTiles);

        int index = 0;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                board[i][j].setText(allTiles.get(index));
                if (allTiles.get(index).isEmpty()) {
                    emptyRow = i;
                    emptyCol = j;
                }
                index++;
            }
        }
        revalidate();
        repaint();
    }


    private class ButtonClickListener implements ActionListener{
        /**
         *
         * @param e the event to be processed
         */
    @Override
    public void actionPerformed(ActionEvent e) {
       JButton clickedButton = (JButton) e.getSource();
       System.out.println("Button clicked");
       moveTile(clickedButton);

         }
    }

    /**
     *
     * @param clickedButton
     */
    private void moveTile(JButton clickedButton) {

        int clickedRow = -1, clickedCol = -1;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                if (board[i][j] == clickedButton) {
                   clickedRow = i;
                   clickedCol = j;
                   break;
                    }
                }
            }

       if ((Math.abs(emptyRow - clickedRow) == 1 && emptyCol == clickedCol) || (Math.abs(emptyCol - clickedCol) == 1 && emptyRow == clickedRow)) {

           String tempText = board[emptyRow][emptyCol].getText();
           board[emptyRow][emptyCol].setText(clickedButton.getText());
           clickedButton.setText(tempText);
           emptyRow = clickedRow;
           emptyCol = clickedCol;
           revalidate();
           repaint();

           if (!timerStarted) {
               timerPanel.startTimer();
               timerStarted = true;
           }

           if (isWinningState()) {
               int seconds = timerPanel.getSeconds();
               JOptionPane.showMessageDialog(this,  "YOU WON. The puzzle was solved in: " + seconds + " seconds");

               System.exit(0);
           }
       }
    }

    /**
     *
     * @return
     */
        public boolean isWinningState() {
        int expectedValue = 1;
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                String buttonText = board[i][j].getText();
                if (!buttonText.isEmpty()) {
                    int currentValue = Integer.parseInt(buttonText);
                    if (currentValue != expectedValue) {
                        return false;
                    }
                    expectedValue++;
                }
            }
        }
        return true;
    }
}

