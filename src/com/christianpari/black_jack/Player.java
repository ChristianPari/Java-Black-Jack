package com.christianpari.black_jack;

public class Player {
  private String name;
  private Hand hand;
  private int score = 0;

  public Player (String name) { this.name = name; }

  public void peek() {
    String display = name + "'s Cards: ";

    for (var card : hand.getCards()) {
      display += card.getDisplay() + " ";
    }

    System.out.println(display + "\n");
  }

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

  public void takeCard(Card card) {
    hand.addCard(card);
    score += card.getValue();
  }

  public void clearHand() {
    hand.emptyHand();
    score = 0;
  }

  public String getName() { return name; }

  public int getScore() { return score; }

}
