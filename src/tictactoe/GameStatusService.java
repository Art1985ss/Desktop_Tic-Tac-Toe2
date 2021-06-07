package tictactoe;

import java.util.Arrays;

public class GameStatusService {
    private final CellState[][] cells;
    private final int size;

    public GameStatusService(Cell[][] arr, int size) {
        this.size = size;
        cells = new CellState[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = arr[row][col].getCellState();
            }
        }
    }

    public GameStatus update() {
        if (Arrays.stream(cells).flatMap(Arrays::stream).allMatch(cellState -> cellState.equals(CellState.EMPTY))) {
            return GameStatus.NOT_STARTED;
        }
        GameStatus gameStatus = horizontalCheck();
        if (gameStatus.canContinue()) {
            gameStatus = verticalCheck();
        }
        if (gameStatus.canContinue()) {
            gameStatus = horizontalCheck();
        }
        if (gameStatus.canContinue()) {
            gameStatus = obliquelyCheck();
        }
        if (gameStatus.canContinue()) {
            boolean filled = Arrays.stream(cells).flatMap(Arrays::stream).noneMatch(cellState -> cellState.equals(CellState.EMPTY));
            return filled ? GameStatus.DRAW : gameStatus;
        }
        return gameStatus;
    }

    private GameStatus horizontalCheck() {
        for (CellState[] cell : cells) {
            final CellState cellState = cell[0];
            if (cellState.equals(CellState.EMPTY)) {
                continue;
            }
            boolean equal = true;
            for (int col = 1; col < size; col++) {
                if (cellState != cell[col]) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                return determineWinner(cellState);
            }
        }
        return GameStatus.IN_PROGRESS;
    }

    private GameStatus verticalCheck() {
        for (int col = 0; col < size; col++) {
            final CellState cellState = cells[0][col];
            if (cellState.equals(CellState.EMPTY)) {
                continue;
            }
            boolean equal = true;
            for (int row = 1; row < size; row++) {
                if (cellState != cells[row][col]) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                return determineWinner(cellState);
            }
        }
        return GameStatus.IN_PROGRESS;
    }

    private GameStatus obliquelyCheck() {
        GameStatus gameStatus = GameStatus.IN_PROGRESS;
        CellState cellStateLeft = cells[0][0];
        CellState cellStateRight = cells[0][size - 1];
        boolean leftEquals = !cellStateLeft.equals(CellState.EMPTY);
        boolean rightEquals = !cellStateRight.equals(CellState.EMPTY);
        for (int left = 0; left < size && leftEquals; left++) {
            if (!cells[left][left].equals(cellStateLeft)) {
                leftEquals = false;
                break;
            }
        }
        if (leftEquals) {
            return determineWinner(cellStateLeft);
        }
        for (int right = 0; right < size && rightEquals; right++) {
            if (!cells[right][size - right - 1].equals(cellStateRight)) {
                rightEquals = false;
                break;
            }
        }
        if (rightEquals) {
            return determineWinner(cellStateRight);
        }
        return gameStatus;
    }

    private GameStatus determineWinner(CellState cellState) {
        if (cellState == CellState.X) {
            return GameStatus.X_WINS;
        } else {
            return GameStatus.O_WINS;
        }
    }
}
