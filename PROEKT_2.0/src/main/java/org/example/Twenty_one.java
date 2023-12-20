//Этот код реализует игру "21" с использованием графического интерфейса на Java и включает в себя все необходимые компоненты и методы для функционирования игры.
package org.example;
//импорт необходимых библиотек

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
            Class.forName("org.sqlite.JDBC"); // Загрузка драйвера
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить драйвер SQLite JDBC: " + e.getMessage());
            return null;
        }
        String url = "jdbc:sqlite:src/main/java/org/example/rating.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
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
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());
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
    //Метод initializeUI() инициализирует элементы пользовательского интерфейса, такие как окно, кнопки, текстовые поля и др.
    private void initializeUI() {
//
        frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 390);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.white);
//
        shuffleButton = new JButton("Перемешать");
        shuffleButton.addActionListener(e -> shuffleDeck());
        shuffleButton.setBackground(Color.LIGHT_GRAY);
        shuffleButton.setForeground(Color.BLACK);
//
        dealButton = new JButton("Сдать");
        dealButton.addActionListener(e -> dealCard());
        dealButton.setBackground(Color.LIGHT_GRAY);
        dealButton.setForeground(Color.BLACK);
//
        cardDisplay = new JTextArea(10, 30);
        cardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardDisplay);
        cardsLeftLabel = new JLabel("Карт осталось: 0");
//
        JButton newGameButton = new JButton("Новая игра");
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
        JButton rulesButton = new JButton("Правила игры");
        rulesButton.addActionListener(e -> showRules());
        rulesButton.setBackground(Color.LIGHT_GRAY);
        rulesButton.setForeground(Color.BLACK);
        frame.getContentPane().add(rulesButton);
//
        currentPlayerLabel = new JLabel("Текущий игрок: ");
        frame.getContentPane().add(currentPlayerLabel);
//
        startNewGame();
        frame.setVisible(true);
//
        JButton ratingButton = new JButton("Рейтинг");
        ratingButton.setBackground(Color.LIGHT_GRAY);
        ratingButton.setForeground(Color.BLACK);
        ratingButton.addActionListener(e -> showRating());
        frame.getContentPane().add(ratingButton);
    }
    //Метод initializeDecks() создает и инициализирует колоду карт для игры.
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
    //Метод updateCardsLeftLabel() обновляет текстовую метку, отображающую количество оставшихся карт в колоде.
    private void updateCardsLeftLabel() {
        if (currentDeck != null) {
            cardsLeftLabel.setText("Осталось карт: " + (currentDeck.size() - cardIndex));
        } else {
            cardsLeftLabel.setText("Осталось карт: 0");
        }
    }
    //Метод startNewGame() начинает новую игру, сбрасывая счет игроков и подготавливая интерфейс для начала игры.
    private void startNewGame() {
        String name1 = JOptionPane.showInputDialog(frame, "Введите имя Игрока 1");
        String name2 = JOptionPane.showInputDialog(frame, "Введите имя Игрока 2");
        player1 = new Player(name1 == null || name1.isEmpty() ? "Игрок 1" : name1);
        player2 = new Player(name2 == null || name2.isEmpty() ? "Игрок 2" : name2);
        currentPlayer = player1; // Установка первого введенного игрока как текущего
        currentPlayerLabel.setText("Текущий игрок: " + currentPlayer.getName());
        player1.resetPoints();
        player2.resetPoints();
        playerDisplay.setText("");
        cardDisplay.setText("Начало новой игры. Перемешайте и сдайте.");
        shuffleButton.setEnabled(true);
        shuffleButton.setVisible(true);
        dealButton.setVisible(true);
        shuffleDeck();
    }
    //Метод shuffleDeck() перемешивает колоду карт и обновляет текущую колоду.
    private void shuffleDeck() {
        if (!matrices.isEmpty()) {
            long currentTime = System.currentTimeMillis() % 100;
            currentDeck = new ArrayList<>(matrices.get((int) currentTime));
            Collections.shuffle(currentDeck);
            cardIndex = 0;
            updateCardsLeftLabel();
            cardDisplay.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Ошибка: Колода не инициализирована", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Метод dealCard() раздает карту текущему игроку, обновляет интерфейс и проверяет условия завершения игры.
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
                playerDisplay.setText("Оба игрока проиграли.");
            } else if (player1.getPoints() == 21 && player2.getPoints() == 21) {
                playerDisplay.setText("Ничья! У обоих игроков 21 очко.");
            } else {
                winner = player1.getPoints() <= 21 ? player1 : player2;
                playerDisplay.setText(winner.getName() + " выиграл в двадцать одно!");
            }
        } else {
            cardDisplay.setText(currentPlayer.toString());
            playerDisplay.setText("Игрок 1: " + player1.getPoints() + "\nИгрок 2: " + player2.getPoints());
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
        currentPlayerLabel.setText("Текущий игрок: " + currentPlayer.getName());
    }
    private void showRating() {
        String[] options = {"Имя", "Количество игр", "Процент побед"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Выберите тип сортировки:",
                "Сортировка рейтинга",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        String orderBy = switch (choice) {
            case 1 -> // Количество игр
                    "games_played DESC";
            case 2 -> // Процент побед
                    "(CAST(games_won AS DOUBLE) / games_played) DESC";
            default -> // Имя
                    "name";
        };
        try (Connection conn = this.connect()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM players ORDER BY " + orderBy)) {

                StringBuilder sb = new StringBuilder("Рейтинг игроков:\n");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int gamesPlayed = rs.getInt("games_played");
                    int gamesWon = rs.getInt("games_won");
                    double winRate = gamesPlayed > 0 ? (double) gamesWon / gamesPlayed * 100 : 0;

                    sb.append(name).append(" - Игр: ").append(gamesPlayed)
                            .append(", Побед: ").append(gamesWon)
                            .append(", Выигрыш: ").append(String.format("%.2f%%", winRate)).append("\n");
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
                        Правила игры в 21 (Блэк джек):
                        - Цель игры - набрать 21 очко или ближайшее к нему количество очков, но не больше.
                        - Все карты от 2 до 10 имеют номинальное значение.
                        - Валет, Дама и Король оцениваются в 10 очков.
                        - Туз может быть как 1, так и 11 очков, в зависимости от ситуации.
                        - Игроки по очереди берут карты из колоды, чтобы улучшить свою комбинацию.
                        - Если игрок набирает более 21 очка, он проигрывает (перебор).
                        - Если оба игрока набрали более 21 очка, объявляется ничья.
                        - Если один из игроков набирает ровно 21 очко, он выигрывает (Блэк джек).
                        """,
                "Правила игры", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        Twenty_one game = new Twenty_one(); // Создание экземпляра игры
        game.createNewTable(); // Вызов метода для создания таблицы в базе данных
    }
}