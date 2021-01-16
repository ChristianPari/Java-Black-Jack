package com.christianpari.black_jack;

import java.util.List;

public class Dealer {
  private final String DEALER = "Dealer";
  private Deck deck;

  public void newDeck(Deck deck) { this.deck = deck; }

  public void shuffle() { deck.shuffle(); }

  public void deal(
    List<Player> players,
    int cardsPerPlayer
  ) {
    for (var player : players) {
      for (int count = 1; count <= cardsPerPlayer; count++) {
        Card card = deck.draw();
        player.takeCard(card);
      }
    }
  }

  public Card giveCard() { return deck.draw(); }

}
