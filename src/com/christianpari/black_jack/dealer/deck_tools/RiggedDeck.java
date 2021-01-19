package com.christianpari.black_jack.dealer.deck_tools;

import com.christianpari.black_jack.dealer.deck_tools.Card;
import com.christianpari.black_jack.dealer.deck_tools.Deck;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RiggedDeck implements Deck {
  private Map<String, String> suits = new HashMap<>();

  public RiggedDeck() {
    generateDeckSuits();
  }

  private void generateDeckSuits() {
    suits.put("SPADE", "\u2664");
    suits.put("HEART", "\u2661");
    suits.put("CLUB", "\u2667");
    suits.put("DIAMOND", "\u2662");
    suits.put("na", "");
  }

  @Override
  public void shuffle() {
    return;
  }

  @Override
  public Card draw() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Card Value: 0 - 13");
    int value = sc.nextInt();
    sc.nextLine();
    System.out.println("Card Suit: 'SPADE' 'HEART' 'CLUB' 'DIAMOND' 'na'");
    String suit = sc.nextLine();
    return new Card(value, suits.get(suit));
  }
}
