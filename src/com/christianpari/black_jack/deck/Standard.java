package com.christianpari.black_jack.deck;

import java.util.*;

public class Standard implements Deck {
  private final static String[] SUITS = {"♤", "♥", "♧", "♦"};
  private final static int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private List<Card> cards;

  public Standard() {
    cards = new ArrayList<>();
    for (var suit : SUITS) {
      for (var value : VALUES) {
        cards.add(new Card(value, suit));
      }
    }
  }

  public void shuffle() { Collections.shuffle(cards);}

  public Card draw(boolean facing) {
    Card card = cards.remove(cards.size() - 1);
    if (facing) card.flip();
    return card;
  }

  public boolean isEmpty() {
    if (cards.size() == 0) return true;
    return false;
  }

}
