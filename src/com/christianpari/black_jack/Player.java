package com.christianpari.black_jack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
  private String name;
  private List<Card> cards = new ArrayList<>();
  private int score = 0;

  public Player (String name) { this.name = name; }

  public void peek() {
    String display = name + "'s Cards: ";

    for (var card : cards) {
      display += card.getDisplay() + " ";
    }

    System.out.println("\n" + display + "\n");
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
    for (var card : cards) {
      int cardValue = card.getValue();
      if (cardValue == 1 || cardValue == 11) {
        String[] choices = {"1", "11"};
        int choiceIdx = Console.getChoice(
          "What would you like your ACE to be valued at?",
            choices) - 1;
        if (cardValue != Integer.parseInt(choices[choiceIdx])) {
          card.changeAceValue();
        }
      }
    }
  }

  public void takeCard(Card card) {
    cards.add(card);
    score += card.getValue();
  }

  public void clearPlayer() {
    cards = new ArrayList<>();
    score = 0;
  }

  public String getName() { return name; }

  public int getScore() { return score; }

}
