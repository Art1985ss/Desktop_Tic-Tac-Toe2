package tictactoe;

import javax.swing.*;

public class GameService implements Runnable {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Player activePlayer;
    private final JLabel statusLabel;

    public GameService(Player player1, Player player2, Board board, JLabel statusLabel) {
        this.player1 = player1;
        this.player2 = player2;
        activePlayer = player1;
        this.board = board;
        this.statusLabel = statusLabel;
    }

    @Override
    public void run() {
        GameStatus gameStatus = board.getGameStatus();
        while (gameStatus.canContinue() && !Thread.currentThread().isInterrupted()) {
            activePlayer.move();
            activePlayer = activePlayer.equals(player1) ? player2 : player1;
            gameStatus = board.getGameStatus();
        }
    }
}
