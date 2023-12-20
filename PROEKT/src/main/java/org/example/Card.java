package org.example;

public class Card {
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
        return switch (rank) {
            case "Ace" -> 11;
            case "King", "Queen", "Jack" -> 10;
            case "10" -> 10;
            default -> Integer.parseInt(rank);
        };
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}