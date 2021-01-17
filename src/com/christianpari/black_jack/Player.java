package com.christianpari.black_jack;

import java.util.ArrayList;
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
    String decision = choices[Console.getChoice(choices) - 1];
    if (decision.equalsIgnoreCase("stay")) {
      return false;
    } else {
      return true;
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
