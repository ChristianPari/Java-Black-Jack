package com.christianpari.black_jack.dealer.deck_tools;

import java.util.*;

public class StandardDeck implements Deck {
  // VARIABLES
  private Map<String, String> suits = new HashMap<>();
  private int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
  private List<Card> cards = new ArrayList<>();

  // CONSTRUCTORS
  public StandardDeck() {
    generateDeckSuits();
    fillDeck();
  }

  public StandardDeck(
    Map<String, String> suits
  ) {
    this.suits = suits;
    fillDeck();
  }

  public StandardDeck(
    Map<String, String> suits,
    int[] values
  ) {
    this.suits = suits;
    this.values = values;
    fillDeck();
  }

  public StandardDeck(
    List<Card> cards
  ) {
    this.cards = cards;
  }

  // INITIALIZING METHODS
  private void generateDeckSuits() {
    suits.put("SPADE", "\u2664");
    suits.put("HEART", "\u2661");
    suits.put("CLUB", "\u2667");
    suits.put("DIAMOND", "\u2662");
  }

  private void fillDeck() {
    for (var suit : suits.values()) {
      for (var value : values) {
        cards.add(new Card(value, suit));
      }
    }
  }

  // USE METHODS
  @Override
  public void shuffle() {
    Collections.shuffle(cards);
  }

  @Override
  public Card draw() {
    return cards.remove(cards.size() - 1);
  }

}
