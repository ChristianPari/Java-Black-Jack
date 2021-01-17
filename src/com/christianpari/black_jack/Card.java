package com.christianpari.black_jack;

public class Card {
  private int value;
  private String suit;
  private String display = "";

  public Card(
    int value,
    String suit
  ) {
    this.value = value;
    this.suit = (value == 0) ? "" : suit; // since JOKERS are a value of 0, don't need suit
    display = getDisplay(value);
  }

  private String getDisplay(int value) {
    String display = suit + "";
    switch (value) {
      case 0:
        return "JOKER";

      case 1:
        display += "ACE";
        break;

      case 11:
        display += "JACK";
        break;

      case 12:
        display += "QUEEN";
        break;

      case 13:
        display += "KING";
        break;

      default:
        display += Integer.toString(value);
    }

    display += suit;
    return display;
  }

  public int getValue() { return value; }
  public void setValue(int value) {
    this.value = value;
  }

  public String getDisplay() { return display; }

  public void changeAceValue() {
    if (value == 1 || value == 11) {
      value = (value == 1) ? 11 : 1;
    }
  }

}
