package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Board extends JPanel {
    private static final int SIZE = 3;
    private GameStatus gameStatus = GameStatus.NOT_STARTED;
    private final Cell[][] cells;
    private StatusLabel statusLabel;

    public Board() {
        super(new GridLayout(SIZE, SIZE));
        this.setPreferredSize(new Dimension(300, 300));
        cells = new Cell[SIZE][SIZE];
        fillCells();
        Arrays.stream(cells).flatMap(Arrays::stream).forEach(this::add);
        setCellsEnabled(false);
    }

    public void fillCells() {
        char A = 'A';
        for (int row = SIZE - 1, num = 1; row >= 0; row--, num++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = new Cell("" + ((char) (A + col)) + num);
                cell.addActionListener(new CellListener(this));
                cells[row][col] = cell;
            }
        }
    }

    public void resetCells() {
        Arrays.stream(cells).flatMap(Arrays::stream).forEach(cell -> cell.setCellState(CellState.EMPTY));
        updateGameStatus();
    }

    public void updateGameStatus() {
        if (Arrays.stream(cells).flatMap(Arrays::stream).allMatch(cell -> cell.getCellState() == CellState.EMPTY)) {
            gameStatus = GameStatus.NOT_STARTED;
        } else {
            gameStatus = new GameStatusService(cells, SIZE).update();
        }
        if (!gameStatus.canContinue()) {
            this.setCellsEnabled(false);
        }
        statusLabel.update(gameStatus);
    }

    public void setCellsEnabled(boolean enabled) {
        Arrays.stream(cells).flatMap(Arrays::stream).forEach(cell -> cell.setEnabled(enabled));
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        statusLabel.update(gameStatus);
    }

    public Cell getFree() {
        List<Cell> emptyCells = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getCellState().equals(CellState.EMPTY))
                .collect(Collectors.toList());
        if (emptyCells.isEmpty()) {
            return null;
        }
        return emptyCells.get(new Random().nextInt(emptyCells.size()));
    }

    public void setStatusService(StatusLabel statusLabel) {
        this.statusLabel = statusLabel;
    }
}
