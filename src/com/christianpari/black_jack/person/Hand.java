package com.christianpari.black_jack.person;

import com.christianpari.black_jack.deck.Card;
import com.christianpari.black_jack.person.Dealer;
import com.christianpari.black_jack.person.Person;
import com.christianpari.black_jack.person.Player;

import java.util.ArrayList;
import java.util.List;

public class Hand {
  private List<Card> cards = new ArrayList<>();
  private int score;
  private boolean hasAce;
  private Person person;
  private int bet;

  public Hand(Person person) {
    this.person = person;
  }

  public Person getActor() { return person; }
  public String getName() { return person.getName(); }
  public int size() {
    return cards.size();
  }
  public void addCard(Card card) {
    cards.add(card);
  }
  public Card removeCard(int index) {
    return cards.remove(index);
  }

  public int getAction() {
    if (person instanceof Dealer) {
      return person.getAction(score, "", 0, 0);
    }
    Player player = (Player) person;
    int maxChoice = 2;
    String query = "What would you like to do? (1) HIT | (2) STAND";
    if (cards.size() == 2 && (bet * 2) <= player.getWallet()) {
      query += " | (3) DOUBLE";
      maxChoice = 3;
      if (isPair()) {
        query += " | (4) SPLIT";
        maxChoice = 4;
      }
    }
    query = query.trim();
    return person.getAction(score, query, 1, maxChoice);
  }

  @Override
  public String toString() {
    String output = "";
    for (var card : cards) {
      output += card + " ";
    }
    return output.trim();
  }

  public int getScore() {
    hasAce = false;
    score = 0;
    for (var card : cards) {
      score += determineValue(card.getValue());
      if (score > 21 && hasAce) {
        score -= 10;
        hasAce = false;
      }
    }
    return score;
  }

  private int determineValue(int card) {
    if (isAce(card) && (score + 11 <= 21)) {
      card = 11;
      hasAce = true;
    } else if (isFace(card)) {
      card = 10;
    }
    return card;
  }

  private boolean isAce(int value) { return value == 1; }
  private boolean isFace(int value) { return value > 10; }
  public void setBet() { bet = person.setBet(); }
  public void setBet(int bet) { this.bet = bet; }
  public int getBet() { return  bet; }
  public void doubleBet() { bet *= 2; }

  public void revealHand() {
    for (var card : cards) {
      if (card.isFaceDown()) card.flip();
    }
  }

  public boolean isPair() {
    if (cards.size() > 2) return false;
    return determineValue(cards.get(0).getValue()) == determineValue(cards.get(1).getValue());
  }
}
