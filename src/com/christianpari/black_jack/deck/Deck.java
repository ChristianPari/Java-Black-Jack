package com.christianpari.black_jack.deck;

public interface Deck {
  void shuffle();
  Card draw(boolean facing);
  boolean isEmpty();
}
