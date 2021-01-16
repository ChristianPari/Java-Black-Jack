package com.christianpari.black_jack;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private List<Card> cards;
  private int score = 0;

  public Player (String name) { this.name = name; }

  public void peek() {
    String display = "Cards: ";

    if (cards.size() == 0) {
      display = "You currently have no cards";
    }

    for (var card : cards) {
      display += card.toString() + " ";
    }

    System.out.println(display);
  }

  public boolean stayOrHit(String[] choices) {
    String decision = choices[Console.getChoice(choices)];
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
