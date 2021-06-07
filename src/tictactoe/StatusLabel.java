package tictactoe;

import javax.swing.*;

public class StatusLabel extends JLabel {
    private final Player player1;
    private final Player player2;

    public StatusLabel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void update(GameStatus gameStatus) {
        if (gameStatus.equals(GameStatus.IN_PROGRESS)) {
            String playerName = CellListener.cellState.equals(CellState.X) ? player1.getText() : player2.getText();
            this.setText("The turn of " + playerName + " Player (" + CellListener.cellState + ")");
            return;
        }
        if (gameStatus.equals(GameStatus.O_WINS)) {
            this.setText("The " + player2.getText() + " Player " + CellState.O + " wins");
        } else if (gameStatus.equals(GameStatus.X_WINS)) {
            this.setText("The " + player1.getText() + " Player " + CellState.X + " wins");
        } else {
            this.setText(gameStatus.toString());
        }
    }


}
