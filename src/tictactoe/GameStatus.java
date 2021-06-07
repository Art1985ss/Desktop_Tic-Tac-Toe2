package tictactoe;

public enum GameStatus {
    NOT_STARTED("Game is not started", true),
    IN_PROGRESS("Game in progress", true),
    X_WINS("X wins" ,false),
    O_WINS("O wins", false),
    DRAW("Draw", false);
    private final String message;
    private final boolean canContinue;

    GameStatus(String message, boolean canContinue) {
        this.message = message;
        this.canContinue = canContinue;
    }

    public boolean canContinue() {
        return canContinue;
    }

    @Override
    public String toString() {
        return message;
    }
}
