package com.christianpari.black_jack;

public class Player {
  private String name;
  private Hand hand = new Hand();
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

  public int getScore() { return score; }

}
