//���� ��� ��������� ���� "21" � �������������� ������������ ���������� �� Java � �������� � ���� ��� ����������� ���������� � ������ ��� ���������������� ����.
package org.example;
//������ ����������� ���������
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
    //����� initializeUI() �������������� �������� ����������������� ����������, ����� ��� ����, ������, ��������� ���� � ��.
    private void initializeUI() {
        frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 390);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.white);

        shuffleButton = new JButton("����������");
        shuffleButton.addActionListener(e -> shuffleDeck());
        shuffleButton.setBackground(Color.LIGHT_GRAY);
        shuffleButton.setForeground(Color.BLACK);

        dealButton = new JButton("�����");
        dealButton.addActionListener(e -> dealCard());
        dealButton.setBackground(Color.LIGHT_GRAY);
        dealButton.setForeground(Color.BLACK);

        cardDisplay = new JTextArea(10, 30);
        cardDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cardDisplay);
        cardsLeftLabel = new JLabel("���� ��������: 0");

        JButton newGameButton = new JButton("����� ����");
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setForeground(Color.BLACK);
        newGameButton.addActionListener(e -> startNewGame());

        player1 = new Player("����� 1");
        player2 = new Player("����� 2");
        currentPlayer = player1;
        playerDisplay = new JTextArea(5, 30);
        playerDisplay.setEditable(false);
        playerDisplay.setBackground(Color.white);

        String[] playerNames = {"����� 1", "����� 2"};
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
        JButton rulesButton = new JButton("������� ����");
        rulesButton.addActionListener(e -> showRules());
        rulesButton.setBackground(Color.LIGHT_GRAY);
        rulesButton.setForeground(Color.BLACK);
        frame.getContentPane().add(rulesButton);
    }
    //����� initializeDecks() ������� � �������������� ������ ���� ��� ����.
    private void initializeDecks() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        // ������� ������� ������
        List<Card> baseDeck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                baseDeck.add(new Card(rank, suit));
            }
        }
        // ���������� 100 ���������� �����
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
        player1.resetPoints();
        player2.resetPoints();
        playerDisplay.setText("");
        cardDisplay.setText("New game started. Shuffle and deal.");
        shuffleButton.setEnabled(true);
        shuffleButton.setVisible(true);
        dealButton.setVisible(true);

        shuffleDeck();
    }
    //����� shuffleDeck() ������������ ������ ���� � ��������� ������� ������.
    private void shuffleDeck() {
        try {
            long currentTime = System.currentTimeMillis() % 100;
            currentDeck = new ArrayList<>(matrices.get((int) currentTime));
            Collections.shuffle(currentDeck);
            cardIndex = 0;
            updateCardsLeftLabel();
            cardDisplay.setText(""); // ������� ������� ����������� ����
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, "������ ��� ������������� ������: " + e.getMessage(), "������", JOptionPane.ERROR_MESSAGE);
        }
    }
    //����� dealCard() ������� ����� �������� ������, ��������� ��������� � ��������� ������� ���������� ����.
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
            playerDisplay.setText("����� 1: " + player1.getPoints() + "\n����� 2: " + player2.getPoints());
            cardIndex++;
            updateCardsLeftLabel();
        }
    }
    //����� showRules() ���������� ������� ���� � ���������� ����.
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
    //����� actionPerformed(ActionEvent e) ������������ ������� ������ ������ �� ����������� ������.
    private void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String playerName = (String) cb.getSelectedItem();
        assert playerName != null;
        currentPlayer = playerName.equals("����� 1") ? player1 : player2;
    }
    public static void main(String[] args) {
        new Twenty_one();
    }
}