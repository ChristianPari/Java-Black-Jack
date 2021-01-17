package com.christianpari.black_jack;

import com.christianpari.black_jack.dealer.deck_tools.Card;
import com.christianpari.black_jack.dealer.deck_tools.Hand;

public class Player {
  private String name;
  private Hand hand = new Hand();
  private int money;
  private int wager = 0;
  private int score = 0;

  public Player (String name) {
    this.name = name;
    money = 10;
  }

  public Player (
    String name,
    int startingMoney
  ) {
    this.name = name;
    money = startingMoney;
  }

  public int makeBet() {
    wager = Console.getInt(
      "\n" + name + " what would you like to bet?",
      "Bet: ",
      1,
      money
    );
    return wager;
  }

  public void wagerLost() {
    money -= wager;
    wager = 0;
  }

  public void wagerWon() {
    money += wager;
    wager = 0;
  }

  public void tiedDealer() {
    wager = 0;
  }

  public void peek() {
    String display = "\n" + name + "'s Cards: ";

    for (var card : hand.getCards()) {
      display += card.getDisplay() + " ";
    }

    System.out.println(display + "\n");
  }

  public boolean stayOrHit(String[] choices) {
    checkForAce();
    if (score > 21) {
      return false;
    }
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
        int choice = Integer.parseInt(choices[choiceIdx]);
        if (cardValue != choice) {
          card.changeAceValue();
          changeScore();

        }
        System.out.println("\n".trim());
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

  public String getName() { return name; }
  public int getMoney() { return money; }
  public int getWager() { return wager; }
  public int getScore() { return score; }

}
