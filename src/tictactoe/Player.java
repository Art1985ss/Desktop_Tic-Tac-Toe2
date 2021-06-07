package tictactoe;

import javax.swing.*;

public class Player extends JButton{
    private boolean human = true;
    private final Board board;

    public Player(String name, Board board) {
        super("Human");
        this.board = board;
        setName(name);
        this.addActionListener(action -> {
            human = !human;
            updateText();
        });
    }

    public void move() {
        if (!human) {
            Cell cell = board.getFree();
            if (cell != null) {
                cell.doClick();
            }
        } else {
            CellState cellState = CellListener.cellState;
            while (cellState.equals(CellListener.cellState)
                    && !board.getGameStatus().equals(GameStatus.NOT_STARTED)
                    && board.getGameStatus().canContinue()) {
                System.out.println("Waiting for " + getName() + " move");
            }
        }
    }

    public void setHuman(boolean human) {
        this.human = human;
        updateText();
    }

    private void updateText() {
        if (human) {
            setText("Human");
        } else {
            setText("Robot");
        }
    }
}
