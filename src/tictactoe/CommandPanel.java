package tictactoe;

import javax.swing.*;
import java.awt.*;

public class CommandPanel extends JPanel {
    private final JButton resetStart;
    private final JButton player1;
    private final JButton player2;

    public CommandPanel(JButton resetStart, JButton player1, JButton player2) {
        this.resetStart = resetStart;
        this.player1 = player1;
        this.player2 = player2;

        this.add(player1);
        this.add(resetStart);
        this.add(player2);
        this.setLayout(new GridLayout(1, 3));
    }
}
