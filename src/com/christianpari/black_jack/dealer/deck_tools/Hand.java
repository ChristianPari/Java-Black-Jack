package com.christianpari.black_jack.dealer.deck_tools;

import com.christianpari.black_jack.dealer.deck_tools.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
  private List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
    if (card.getValue() > 10) {
      card.setValue(10);
    }
    cards.add(card);
  }

  public List<Card> getCards() { return cards; }

  public void emptyHand() { cards = new ArrayList<>(); }

}
