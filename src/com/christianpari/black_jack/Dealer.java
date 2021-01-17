package com.christianpari.black_jack;

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
    for (int playerCounter = 0; playerCounter <= players.size(); playerCounter++) {
      for (int count = 1; count <= cardsPerPlayer; count++) {
        Card card = deck.draw();

        if (playerCounter == players.size()) { // player 0 is the dealer
          hand.addCard(card);
        } else {
          players.get(playerCounter).takeCard(card);
        }
      }
    }
  }

  public Card giveCard() { return deck.draw(); }

  public boolean stayOrHit(String[] choices) {
    checkForAce();
    String decision = choices[Console.getChoice(choices) - 1];
    if (decision.equalsIgnoreCase("stay")) {
      return false;
    } else {
      return true;
    }
  }

  private void checkForAce() {
    for (var card : hand.getCards()) {
      int cardValue = card.getValue();
      if (cardValue == 1 || cardValue == 11) {
        String[] choices = {"1", "11"};
        int choiceIdx = Console.getChoice(
          "What would you like your ACE to be valued at?",
          choices) - 1;
        if (cardValue != Integer.parseInt(choices[choiceIdx])) {
          card.changeAceValue();
        }
        System.out.println("\n".trim());
      }
    }
  }

  public void clearHand() {
    hand.emptyHand();
    score = 0;
  }

  public int getScore() { return score; }

}
