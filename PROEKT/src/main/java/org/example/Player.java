package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
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