package tictactoe;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    private final JLabel gameStatusLabel;

    public UI(JLabel gameStatusLabel) {
        this.setPreferredSize(new Dimension(450, 0));
        this.setLayout(new GridLayout(1, 2));
        this.gameStatusLabel = gameStatusLabel;
        this.add(gameStatusLabel);
    }

    public JLabel getGameStatusLabel() {
        return gameStatusLabel;
    }
}
