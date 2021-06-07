package tictactoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JMenu implements ActionListener {
    private final Player player1;
    private final Player player2;
    private final JButton startButton;

    public GameMenu(Player player1, Player player2, JButton startButton) {
        super("Game menu");
        this.player1 = player1;
        this.player2 = player2;
        this.startButton = startButton;
        this.setName("MenuGame");

        JMenuItem item = new JMenuItem("Human vs Human");
        item.setName("MenuHumanHuman");
        item.addActionListener(this);
        this.add(item);
        item = new JMenuItem("Human vs Robot");
        item.setName("MenuHumanRobot");
        item.addActionListener(this);
        this.add(item);
        item = new JMenuItem("Robot vs Human");
        item.setName("MenuRobotHuman");
        item.addActionListener(this);
        this.add(item);
        item = new JMenuItem("Robot vs Robot");
        item.setName("MenuRobotRobot");
        item.addActionListener(this);
        this.add(item);
        this.addSeparator();
        item = new JMenuItem("Exit");
        item.setName("MenuExit");
        item.addActionListener(this);
        this.add(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        String name = item.getName();
        if (name.equals("MenuExit")) {
            System.exit(0);
        }
        if (startButton.getText().equals("Reset")) {
            startButton.doClick();
        }
        if (name.equals("MenuHumanHuman")) {
            player1.setHuman(true);
            player2.setHuman(true);
        }
        if (name.equals("MenuHumanRobot")) {
            player1.setHuman(true);
            player2.setHuman(false);
        }
        if (name.equals("MenuRobotHuman")) {
            player1.setHuman(false);
            player2.setHuman(true);
        }
        if (name.equals("MenuRobotRobot")) {
            player1.setHuman(false);
            player2.setHuman(false);
        }
        startButton.doClick();

    }
}
