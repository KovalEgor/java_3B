//���� ��� ��������� ���� "21" � �������������� ������������ ���������� �� Java � �������� � ���� ��� ����������� ���������� � ������ ��� ���������������� ����.
package org.example;
//������ ����������� ���������

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Twenty_one {

    private JLabel currentPlayerLabel;
    private JButton shuffleButton;
    private JButton dealButton;
    private JTextArea cardDisplay, playerDisplay;
    private final List<List<Card>> matrices = new ArrayList<>();
    private static List<Card> currentDeck;
    private static int cardIndex = 0;
    private static JLabel cardsLeftLabel;
    private Player player1, player2;
    private Player currentPlayer;
    private JFrame frame;

    public Twenty_one() {
        initializeDecks();
        initializeUI();
    }
    private Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC"); // �������� ��������
        } catch (ClassNotFoundException e) {
            System.err.println("�� ������� ��������� ������� SQLite JDBC: " + e.getMessage());
            return null;
        }
        String url = "jdbc:sqlite:src/main/java/org/example/rating.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("������ ����������� � ���� ������: " + e.getMessage());
        }
        return conn;
    }
    private void createNewTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS players (
                 name text PRIMARY KEY,
                 games_played integer NOT NULL,
                 games_won integer NOT NULL
                );""";

        try (Connection conn = this.connect()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.println("������ ��� �������� �������: " + e.getMessage());
        }
    }
    private void updatePlayerStats(String playerName, boolean won) {
        String sql = "INSERT INTO players (name, games_played, games_won) VALUES(?, 1, ?) "
                + "ON CONFLICT(name) DO UPDATE SET "
                + "games_played = games_played + 1, "
                + "games_won = games_won + CASE WHEN excluded.games_won = 1 THEN 1 ELSE 0 END;";

        try (Connection conn = this.connect()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerName);
                pstmt.setInt(2, won ? 1 : 0);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //����� initializeUI() �������������� �������� ����������������� ����������, ����� ��� ����, ������, ��������� ���� � ��.
    private void initializeUI() {
//
        frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 390);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.white);
//
        shuffleButton = new JButton("����������");
        shuffleButton.addActionListener(e -> shuffleDeck());
        shuffleButton.setBackground(Color.LIGHT_GRAY);
        shuffleButton.setForeground(Color.BLACK);
//
        dealButton = new JButton("�����");
        dealButton.addActionListener(e -> dealCard());
        dealButton.setBackground(Color.LIGHT_GRAY);
        dealButton.setForeground(Color.BLACK);
//
        cardDisplay = new JTextArea(10, 30);
        cardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardDisplay);
        cardsLeftLabel = new JLabel("���� ��������: 0");
//
        JButton newGameButton = new JButton("����� ����");
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setForeground(Color.BLACK);
        newGameButton.addActionListener(e -> startNewGame());
//
        playerDisplay = new JTextArea(5, 30);
        playerDisplay.setEditable(false);
        playerDisplay.setBackground(Color.white);
//
        frame.getContentPane().add(shuffleButton);
        frame.getContentPane().add(dealButton);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(cardsLeftLabel);
        frame.getContentPane().add(newGameButton);
        frame.getContentPane().add(new JScrollPane(playerDisplay));
//
        JButton rulesButton = new JButton("������� ����");
        rulesButton.addActionListener(e -> showRules());
        rulesButton.setBackground(Color.LIGHT_GRAY);
        rulesButton.setForeground(Color.BLACK);
        frame.getContentPane().add(rulesButton);
//
        currentPlayerLabel = new JLabel("������� �����: ");
        frame.getContentPane().add(currentPlayerLabel);
//
        startNewGame();
        frame.setVisible(true);
//
        JButton ratingButton = new JButton("�������");
        ratingButton.setBackground(Color.LIGHT_GRAY);
        ratingButton.setForeground(Color.BLACK);
        ratingButton.addActionListener(e -> showRating());
        frame.getContentPane().add(ratingButton);
    }
    //����� initializeDecks() ������� � �������������� ������ ���� ��� ����.
    private void initializeDecks() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        List<Card> baseDeck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                baseDeck.add(new Card(rank, suit));
            }
        }
        for (int i = 0; i < 100; i++) {
            List<Card> shuffledDeck = new ArrayList<>(baseDeck);
            Collections.shuffle(shuffledDeck);
            matrices.add(shuffledDeck);
        }
    }
    //����� updateCardsLeftLabel() ��������� ��������� �����, ������������ ���������� ���������� ���� � ������.
    private void updateCardsLeftLabel() {
        if (currentDeck != null) {
            cardsLeftLabel.setText("�������� ����: " + (currentDeck.size() - cardIndex));
        } else {
            cardsLeftLabel.setText("�������� ����: 0");
        }
    }
    //����� startNewGame() �������� ����� ����, ��������� ���� ������� � ������������� ��������� ��� ������ ����.
    private void startNewGame() {
        String name1 = JOptionPane.showInputDialog(frame, "������� ��� ������ 1");
        String name2 = JOptionPane.showInputDialog(frame, "������� ��� ������ 2");
        player1 = new Player(name1 == null || name1.isEmpty() ? "����� 1" : name1);
        player2 = new Player(name2 == null || name2.isEmpty() ? "����� 2" : name2);
        currentPlayer = player1; // ��������� ������� ���������� ������ ��� ��������
        currentPlayerLabel.setText("������� �����: " + currentPlayer.getName());
        player1.resetPoints();
        player2.resetPoints();
        playerDisplay.setText("");
        cardDisplay.setText("������ ����� ����. ����������� � ������.");
        shuffleButton.setEnabled(true);
        shuffleButton.setVisible(true);
        dealButton.setVisible(true);
        shuffleDeck();
    }
    //����� shuffleDeck() ������������ ������ ���� � ��������� ������� ������.
    private void shuffleDeck() {
        if (!matrices.isEmpty()) {
            long currentTime = System.currentTimeMillis() % 100;
            currentDeck = new ArrayList<>(matrices.get((int) currentTime));
            Collections.shuffle(currentDeck);
            cardIndex = 0;
            updateCardsLeftLabel();
            cardDisplay.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "������: ������ �� ����������������", "������", JOptionPane.ERROR_MESSAGE);
        }
    }
    //����� dealCard() ������� ����� �������� ������, ��������� ��������� � ��������� ������� ���������� ����.
    private void dealCard() {
        if (currentDeck != null && cardIndex < currentDeck.size()) {
            Card card = currentDeck.get(cardIndex++);
            currentPlayer.addCard(card);
            updateGameStatus();
        }
    }
    private void updateGameStatus() {
        shuffleButton.setEnabled(false);
        boolean gameEnded = false;
        Player winner = null;

        if (player1.getPoints() >= 21 || player2.getPoints() >= 21) {
            gameEnded = true;
            if (player1.getPoints() > 21 && player2.getPoints() > 21) {
                playerDisplay.setText("��� ������ ���������.");
            } else if (player1.getPoints() == 21 && player2.getPoints() == 21) {
                playerDisplay.setText("�����! � ����� ������� 21 ����.");
            } else {
                winner = player1.getPoints() <= 21 ? player1 : player2;
                playerDisplay.setText(winner.getName() + " ������� � �������� ����!");
            }
        } else {
            cardDisplay.setText(currentPlayer.toString());
            playerDisplay.setText("����� 1: " + player1.getPoints() + "\n����� 2: " + player2.getPoints());
            switchPlayers();
        }

        if (gameEnded) {
            shuffleButton.setVisible(false);
            dealButton.setVisible(false);
            updatePlayerStats(player1.getName(), winner == player1);
            updatePlayerStats(player2.getName(), winner == player2);
        }
    }
    private void switchPlayers() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        currentPlayerLabel.setText("������� �����: " + currentPlayer.getName());
    }
    private void showRating() {
        String[] options = {"���", "���������� ���", "������� �����"};
        int choice = JOptionPane.showOptionDialog(frame,
                "�������� ��� ����������:",
                "���������� ��������",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        String orderBy = switch (choice) {
            case 1 -> // ���������� ���
                    "games_played DESC";
            case 2 -> // ������� �����
                    "(CAST(games_won AS DOUBLE) / games_played) DESC";
            default -> // ���
                    "name";
        };
        try (Connection conn = this.connect()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM players ORDER BY " + orderBy)) {

                StringBuilder sb = new StringBuilder("������� �������:\n");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int gamesPlayed = rs.getInt("games_played");
                    int gamesWon = rs.getInt("games_won");
                    double winRate = gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0;

                    sb.append(name).append(" - ���: ").append(gamesPlayed)
                            .append(", �����: ").append(gamesWon)
                            .append(", �������: ").append(String.format("%.2f%%", winRate)).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void showRules() {
        JOptionPane.showMessageDialog(frame,
                """
                        ������� ���� � 21 (���� ����):
                        - ���� ���� - ������� 21 ���� ��� ��������� � ���� ���������� �����, �� �� ������.
                        - ��� ����� �� 2 �� 10 ����� ����������� ��������.
                        - �����, ���� � ������ ����������� � 10 �����.
                        - ��� ����� ���� ��� 1, ��� � 11 �����, � ����������� �� ��������.
                        - ������ �� ������� ����� ����� �� ������, ����� �������� ���� ����������.
                        - ���� ����� �������� ����� 21 ����, �� ����������� (�������).
                        - ���� ��� ������ ������� ����� 21 ����, ����������� �����.
                        - ���� ���� �� ������� �������� ����� 21 ����, �� ���������� (���� ����).
                        """,
                "������� ����", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        Twenty_one game = new Twenty_one(); // �������� ���������� ����
        game.createNewTable(); // ����� ������ ��� �������� ������� � ���� ������
    }
}