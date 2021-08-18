package com.christianpari.black_jack.deck;

public class Card {
  private int value;
  private String suit;
  private boolean faceDown = true;

  public Card(
    int value,
    String suit
  ) {
    this.value = value;
    this.suit = suit;
  }

  @Override
  public String toString() {
    String display = "";
    switch (value) {
      case 0:
        display = "JOKER";
        break;

      case 1:
        display = "ACE";
        break;

      case 11:
        display = "JACK";
        break;

      case 12:
        display = "QUEEN";
        break;

      case 13:
        display = "KING";
        break;

      default:
        display = value == 10 ? Integer.toString(value) : " " + value;
    }

    return display + suit;
  }

  public int getValue() {
    return value;
  }

  public void flip() { faceDown = !faceDown; }
  public boolean isFaceDown() { return faceDown; }
}
