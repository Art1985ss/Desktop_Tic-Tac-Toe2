package tictactoe;

import javax.swing.*;

public class TicTacToe extends JFrame {
    private final Board board;
    private final UI ui;
    private Thread gameService;

    public TicTacToe() {
        setTitle("Tic tac toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        JMenuBar menuBar = new JMenuBar();

        board = new Board();

        Player player1 = new Player("ButtonPlayer1", board);
        Player player2 = new Player("ButtonPlayer2", board);

        StatusLabel statusLabel = new StatusLabel(player1, player2);
        statusLabel.setName("LabelStatus");
        board.setStatusService(statusLabel);
        board.updateGameStatus();


        JButton button = new JButton("Start");
        button.setName("ButtonStartReset");
        button.addActionListener(actionEvent -> {
            JButton btn = (JButton) actionEvent.getSource();
            if (btn.getText().equals("Start")) {
                btn.setText("Reset");
                board.setCellsEnabled(true);
                board.setGameStatus(GameStatus.IN_PROGRESS);
                player1.setEnabled(false);
                player2.setEnabled(false);
                //gameService = new Thread(new GameService(player1, player2, board, label));
                //gameService.start();

            } else {
                btn.setText("Start");
                board.setCellsEnabled(false);
                board.resetCells();
                player1.setEnabled(true);
                player2.setEnabled(true);
                //gameService.interrupt();
            }
            CellListener.cellState = CellState.X;
        });

        CommandPanel commandPanel = new CommandPanel(button, player1, player2);

        menuBar.add(new GameMenu(player1, player2, button));
        this.setJMenuBar(menuBar);

        ui = new UI(statusLabel);
        this.getContentPane().add(commandPanel);
        this.getContentPane().add(board);
        this.getContentPane().add(ui);

        setVisible(true);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    }

}