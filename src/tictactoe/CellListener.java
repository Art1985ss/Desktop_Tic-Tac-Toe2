package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellListener implements ActionListener {
    public static CellState cellState = CellState.X;
    private final Board board;

    public CellListener(Board board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!board.getGameStatus().canContinue()) {
            return;
        }
        Cell cell = actionEvent.getSource() instanceof Cell ? (Cell) actionEvent.getSource() : null;
        if (cell == null) {
            return;
        }
        if (cell.getCellState() == CellState.EMPTY) {
            cell.setCellState(cellState);
            cellState = cellState == CellState.X ? CellState.O : CellState.X;
        }
        board.updateGameStatus();
    }
}
