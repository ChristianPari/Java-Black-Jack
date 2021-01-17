package com.christianpari.black_jack.dealer;

import com.christianpari.black_jack.dealer.deck_tools.Hand;
import com.christianpari.black_jack.Player;
import com.christianpari.black_jack.dealer.deck_tools.Card;
import com.christianpari.black_jack.dealer.deck_tools.Deck;

import java.util.List;

public class Dealer {
  private final String DEALER = "Dealer";
  private Deck deck;
  private Hand hand = new Hand();
  private int score;

  public void newDeck(Deck deck) { this.deck = deck; }

  public void shuffle() { deck.shuffle(); }

  public void deal(
    List<Player> players,
    int cardsPerPlayer
  ) {
    int totalPlayers = players.size();
    for (int cardCount = 1; cardCount <= cardsPerPlayer; cardCount++) {
      for (int playerCounter = 0; playerCounter <= totalPlayers; playerCounter++) {
        Card card = deck.draw();
        if (playerCounter == totalPlayers) {
          hand.addCard(card);
        } else {
          players.get(playerCounter).takeCard(card);
        }
      }
    }
  }

  public Card giveCard() { return deck.draw(); }

  public void display() {
    String display = DEALER + "'s Cards: ";

    for (var card : hand.getCards()) {
      display += card.getDisplay() + " ";
    }

    System.out.println(display + "\n");
  }

  public boolean stayOrHit() {
    checkForAce();
    if (score < 17) {
      return true;
    } else {
      return false;
    }
  }

  private void checkForAce() {
    for (var card : hand.getCards()) {
      int cardValue = card.getValue();
      if (cardValue == 1) {
        int aceAs11 = (score - 1) + 11;
        if (aceAs11 >= 17 && aceAs11 <= 21) {
          card.changeAceValue();
          changeScore();
        }
      }
    }
  }

  public void takeCard(Card card) {
    hand.addCard(card);
    changeScore();
  }


  public void changeScore() {
    score = 0;
    for (var card : hand.getCards()) {
      score += card.getValue();
    }
  }

  public void clearHand() {
    hand.emptyHand();
    score = 0;
  }

  public int getScore() { return score; }

}
