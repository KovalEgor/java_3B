package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class main {
    private JFrame frame;
    private JButton shuffleButton, dealButton, newGameButton;
    private JTextArea cardDisplay, playerDisplay;
    private List<List<Card>> matrices = new ArrayList<>();
    private static List<Card> currentDeck;
    private static int cardIndex = 0;
    private static JLabel cardsLeftLabel;
    private Player player1, player2;
    private Player currentPlayer;
    private JComboBox<String> playerSelector;

    public main() {
        initializeUI();
        initializeDecks();
    }

    private void initializeUI() {
        frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleDeck();
            }
        });

        dealButton = new JButton("Deal");
        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealCard();
            }
        });

        cardDisplay = new JTextArea(10, 30);
        cardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardDisplay);
        cardsLeftLabel = new JLabel("Cards left: 0");

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        playerDisplay = new JTextArea(5, 30);
        playerDisplay.setEditable(false);

        String[] playerNames = {"Player 1", "Player 2"};
        playerSelector = new JComboBox<>(playerNames);
        playerSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String playerName = (String) cb.getSelectedItem();
                currentPlayer = playerName.equals("Player 1") ? player1 : player2;
            }
        });

        frame.getContentPane().add(shuffleButton);
        frame.getContentPane().add(dealButton);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(cardsLeftLabel);
        frame.getContentPane().add(newGameButton);
        frame.getContentPane().add(new JLabel("Select Player:"));
        frame.getContentPane().add(playerSelector);
        frame.getContentPane().add(new JScrollPane(playerDisplay));

        frame.setVisible(true);
    }
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
    private void updateCardsLeftLabel() {
        if (currentDeck != null) {
            cardsLeftLabel.setText("Cards left: " + (currentDeck.size() - cardIndex));
        } else {
            cardsLeftLabel.setText("Cards left: 0");
        }
    }
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
    private void shuffleDeck() {
        long currentTime = System.currentTimeMillis() % 100;
        currentDeck = new ArrayList<>(matrices.get((int) currentTime));
        Collections.shuffle(currentDeck);
        cardIndex = 0;
        updateCardsLeftLabel();
        cardDisplay.setText(""); // Очистка области отображения карт
    }
    private void dealCard() {
        if (currentDeck != null && cardIndex < currentDeck.size()) {
            Card card = currentDeck.get(cardIndex);
            currentPlayer.addCard(card);
            shuffleButton.setEnabled(false);

            if (player1.getPoints() == 21 && player2.getPoints() == 21) {
                playerDisplay.setText("Draw!");
                shuffleButton.setVisible(false);
                dealButton.setVisible(false);
                return;
            }
            if (currentPlayer.getPoints() > 21) {
                Player winner = currentPlayer == player1 ? player2 : player1;
                playerDisplay.setText(winner.getName() + " wins!");
                shuffleButton.setVisible(false);
                dealButton.setVisible(false);
                return;
            }
            cardDisplay.setText(currentPlayer.toString());
            playerDisplay.setText("Player 1: " + player1.getPoints() + "\nPlayer 2: " + player2.getPoints());
            cardIndex++;
            updateCardsLeftLabel();
        }
    }
    static class Player {
        private final String name;
        private final List<Card> hand;
        private int points;

        public Player(String name) {
            this.name = name;
            hand = new ArrayList<>();
            points = 0;
        }

        public String getName() {
            return name;
        }

        public void addCard(Card card) {
            hand.add(card);
            points += card.getPoints();
            if (card.getRank().equals("Ace") && points > 21) {
                points -= 10;
            }
        }

        public void resetPoints() {
            points = 0;
            hand.clear();
        }

        public int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return name + " Points: " + points + "\n" + hand.stream().map(Card::toString)
                    .collect(Collectors.joining(", ")) + "\n";
        }
    }
    public static void main(String[] args) {
        new main();
    }
    static class Card {
        private final String suit; // Масть карты
        private final String rank; // Ранг карты

        public Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String getRank() {
            return rank;
        }

        public int getPoints() {
            switch (rank) {
                case "Ace":
                    return 11;
                case "King":
                    return 4;
                case "Queen":
                    return 3;
                case "Jack":
                    return 2;
                case "10":
                    return 10;
                default:
                    return Integer.parseInt(rank);
            }
        }
        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }
}