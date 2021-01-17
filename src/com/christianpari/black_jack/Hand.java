package com.christianpari.black_jack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
  private List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
    cards.add(card);
  }

  public List<Card> getCards() { return cards; }

  public void emptyHand() { cards = new ArrayList<>(); }

}
