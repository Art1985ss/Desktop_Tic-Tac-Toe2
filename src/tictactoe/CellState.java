package tictactoe;

public enum CellState {
    EMPTY(" "),
    X("X"),
    O("O");
    private final String string;

    CellState(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
