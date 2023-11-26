//Колода карт
//
//Реализовать модель колоды карт. Колода должна перетасовываться, должен быть реализован механизм сдачи карт,
//то есть карта должна выбывать из колоды и поступать в любую стороннюю структуру.
//Должна быть возможность возвращения карты в колоду с контролем дублирования.


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Класс, представляющий карту
class Card {
    private final String suit;
    private final String rank;
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
// Класс, представляющий колоду карт
class Deck {
    private final List<Card> cards;
    public Deck() {
        this.cards = initializeDeck();
    }
    private List<Card> initializeDeck() {
        List<Card> newDeck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String rank : ranks) {
                newDeck.add(new Card(suit, rank));
            }
        }
        return newDeck;
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }
    public Card dealCard() {
        if (cards.isEmpty()) {
            System.out.println("No cards left in the deck.");
            return null;
        }
        return cards.remove(0);
    }
    public void returnCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        } else {
            System.out.println("Card is already in the deck.");
        }
    }
    @Override
    public String toString() {
        return "Deck: " + cards;
    }
}
public class CardDeckExample {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Original deck:");
        System.out.println(deck);
        deck.shuffle();
        System.out.println("\nShuffled deck:");
        System.out.println(deck);
        Card dealtCard = deck.dealCard();
        if (dealtCard != null) {
            System.out.println("\nDealt card: " + dealtCard);
        }
        Card returnedCard = new Card("Hearts", "2");
        deck.returnCard(returnedCard);
        System.out.println("\nDeck after returning a card:");
        System.out.println(deck);
    }
}
