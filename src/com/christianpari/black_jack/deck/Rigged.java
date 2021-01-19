package com.christianpari.black_jack.deck;

import java.util.Scanner;

public class Rigged implements Deck {
  private final static String[] SUITS = {"♤", "♥", "♧", "♦"};

  @Override
  public void shuffle() {
    return;
  }

  @Override
  public Card draw(boolean facing) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Card Value: 1 - 13");
    int value = sc.nextInt();
    System.out.println("Card Suit: 1 - 4");
    String suit = sc.next();
    Card card = new Card(value, SUITS[Integer.parseInt(suit) - 1]);
    card.flip();
    return card;
  }

  public boolean isEmpty() {
    return false;
  }
}
