import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class CardShuffler {
    private JFrame frame;
    private JButton shuffleButton, dealButton;
    private JTextArea cardDisplay;
    private List<List<Card>> matrices = new ArrayList<>();
    private List<Card> currentDeck;
    private int cardIndex = 0;
    private JLabel cardsLeftLabel;

    public CardShuffler() {
        initializeUI();
        initializeDecks();
    }

    private void initializeUI() {
        frame = new JFrame("Card Shuffler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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

        cardDisplay = new JTextArea();
        cardDisplay.setEditable(false);

        cardsLeftLabel = new JLabel("Cards left: 0");

        frame.getContentPane().add(cardsLeftLabel);
        frame.getContentPane().add(shuffleButton);
        frame.getContentPane().add(dealButton);
        frame.getContentPane().add(cardDisplay);

        frame.setVisible(true);
    }

    private void initializeDecks() {
        // Инициализация 100 матриц с разными состояниями тасовки
    }

    private void shuffleDeck() {
        // Выбор матрицы на основе текущего времени
        long currentTime = System.currentTimeMillis() % 100;
        currentDeck = new ArrayList<>(matrices.get((int) currentTime));
        cardIndex = 0;
        updateCardsLeftLabel();
    }

    private void dealCard() {
        if (currentDeck != null && cardIndex < currentDeck.size()) {
            Card card = currentDeck.get(cardIndex);
            cardDisplay.append(card.toString() + "\n");
            cardIndex++;
            updateCardsLeftLabel();
        }
    }
    private void updateCardsLeftLabel() {
        if (currentDeck != null) {
            cardsLeftLabel.setText("Cards left: " + (currentDeck.size() - cardIndex));
        } else {
            cardsLeftLabel.setText("Cards left: 0");
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

    public static void main(String[] args) {
        new CardShuffler();
    }

    class Card {
        private String suit; // Масть карты
        private String rank; // Ранг карты

        public Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String getSuit() {
            return suit;
        }

        public String getRank() {
            return rank;
        }

        @Override
        public String toString() {
            return rank + " of " + suit;
        }
    }

        // Конструктор, геттеры, сеттеры, метод toString()
    }
}