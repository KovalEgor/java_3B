//Этот код реализует игру "21" с использованием графического интерфейса на Java и включает в себя все необходимые компоненты и методы для функционирования игры.
package org.example;
//импорт необходимых библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.example.Card;
import org.example.Player;

public class Twenty_one {
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

        initializeUI();
        initializeDecks();
    }
    //Метод initializeUI() инициализирует элементы пользовательского интерфейса, такие как окно, кнопки, текстовые поля и др.
    private void initializeUI() {
        frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 390);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.white);

        shuffleButton = new JButton("Перемешать");
        shuffleButton.addActionListener(e -> shuffleDeck());
        shuffleButton.setBackground(Color.LIGHT_GRAY);
        shuffleButton.setForeground(Color.BLACK);

        dealButton = new JButton("Сдать");
        dealButton.addActionListener(e -> dealCard());
        dealButton.setBackground(Color.LIGHT_GRAY);
        dealButton.setForeground(Color.BLACK);

        cardDisplay = new JTextArea(10, 30);
        cardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardDisplay);
        cardsLeftLabel = new JLabel("Карт осталось: 0");

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setForeground(Color.BLACK);
        newGameButton.addActionListener(e -> startNewGame());

        player1 = new Player("Игрок 1");
        player2 = new Player("Игрок 2");
        currentPlayer = player1;
        playerDisplay = new JTextArea(5, 30);
        playerDisplay.setEditable(false);
        playerDisplay.setBackground(Color.white);

        String[] playerNames = {"Игрок 1", "Игрок 2"};
        JComboBox<String> playerSelector = new JComboBox<>(playerNames);
        playerSelector.addActionListener(this::actionPerformed);
        playerSelector.setBackground(Color.LIGHT_GRAY);
        playerSelector.setForeground(Color.BLACK);

        frame.getContentPane().add(shuffleButton);
        frame.getContentPane().add(dealButton);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(cardsLeftLabel);
        frame.getContentPane().add(newGameButton);
        frame.getContentPane().add(new JLabel("Select Player:"));
        frame.getContentPane().add(playerSelector);
        frame.getContentPane().add(new JScrollPane(playerDisplay));
        frame.setVisible(true);
        JButton rulesButton = new JButton("Правила игры");
        rulesButton.addActionListener(e -> showRules());
        rulesButton.setBackground(Color.LIGHT_GRAY);
        rulesButton.setForeground(Color.BLACK);
        frame.getContentPane().add(rulesButton);
    }
    //Метод initializeDecks() создает и инициализирует колоду карт для игры.
    private void initializeDecks() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        // Создаем базовую колоду
        List<Card> baseDeck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                baseDeck.add(new Card(rank, suit));
            }
        }
        // Генерируем 100 тасованных колод
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
        player1.resetPoints();
        player2.resetPoints();
        playerDisplay.setText("");
        cardDisplay.setText("New game started. Shuffle and deal.");
        shuffleButton.setEnabled(true);
        shuffleButton.setVisible(true);
        dealButton.setVisible(true);

        shuffleDeck();
    }
    //Метод shuffleDeck() перемешивает колоду карт и обновляет текущую колоду.
    private void shuffleDeck() {
        try {
            long currentTime = System.currentTimeMillis() % 100;
            currentDeck = new ArrayList<>(matrices.get((int) currentTime));
            Collections.shuffle(currentDeck);
            cardIndex = 0;
            updateCardsLeftLabel();
            cardDisplay.setText(""); // Очистка области отображения карт
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, "Ошибка при перемешивании колоды: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Метод dealCard() раздает карту текущему игроку, обновляет интерфейс и проверяет условия завершения игры.
    private void dealCard() {
        if (currentDeck != null && cardIndex < currentDeck.size()) {
            Card card = currentDeck.get(cardIndex);
            currentPlayer.addCard(card);
            shuffleButton.setEnabled(false);
            if (player1.getPoints() == 21 || player2.getPoints() == 21) {
                if (player1.getPoints() == player2.getPoints()) {
                    playerDisplay.setText("Draw! Both players have Blackjack.");
                } else {
                    Player winner = player1.getPoints() == 21 ? player1 : player2;
                    playerDisplay.setText(winner.getName() + " wins with Blackjack!");
                }
                shuffleButton.setVisible(false);
                dealButton.setVisible(false);
                return;
            }
            if (currentPlayer.getPoints() > 21) {
                if (player1.getPoints() > 21 && player2.getPoints() > 21) {
                    playerDisplay.setText("Draw! Both players have busted.");
                } else {
                    Player winner = currentPlayer == player1 ? player2 : player1;
                    playerDisplay.setText(winner.getName() + " wins!");
                }
                shuffleButton.setVisible(false);
                dealButton.setVisible(false);
                return;
            }
            cardDisplay.setText(currentPlayer.toString());
            playerDisplay.setText("Игрок 1: " + player1.getPoints() + "\nИгрок 2: " + player2.getPoints());
            cardIndex++;
            updateCardsLeftLabel();
        }
    }
    //Метод showRules() отображает правила игры в диалоговом окне.
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
    //Метод actionPerformed(ActionEvent e) обрабатывает события выбора игрока из выпадающего списка.
    private void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String playerName = (String) cb.getSelectedItem();
        assert playerName != null;
        currentPlayer = playerName.equals("Игрок 1") ? player1 : player2;
    }
    public static void main(String[] args) {
        new Twenty_one();
    }
}