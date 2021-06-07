package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private CellState cellState;

    public Cell(String name) {
        super(CellState.EMPTY.toString());
        this.cellState = CellState.EMPTY;
        this.setFocusPainted(false);
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        this.setName("Button" + name);
        this.setVisible(true);
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
        this.setText(cellState.toString());
    }
}
