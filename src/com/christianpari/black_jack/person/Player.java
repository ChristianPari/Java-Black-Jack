package com.christianpari.black_jack.person;

import com.christianpari.black_jack.console.Console;

public class Player implements Person {
  static int HIT = 1,
    STAND = 2,
    DOUBLE = 2,
    SPLIT = 4;

  private String name;
  private int wallet;

  public Player() {
    name = Console.getString("What is your name?");
    wallet = 1000;
  }

  @Override
  public String getName() { return name; }

  public int setBet() {
    int bet;
    do {
      System.out.println(name + "'s Wallet: " + wallet);
      bet = Console.getInt("What is your bet?");
      if (validateBet(bet)) {
        System.out.println("Invalid bet.");
      }
    } while (validateBet(bet));
    return bet;
  }

  private boolean validateBet(int bet) { return bet > wallet && bet > 0; }

  public void adjustWallet(int amount) { wallet += amount; }

  public int getWallet() { return wallet; }

  public void adjustWallet(
    int amount,
    String method
  ) {
    switch (method) {
      case "win":
        wallet += amount;
        break;

      case "lose":
        wallet -= amount;
        break;
    }
  }

  @Override
  public int getAction(
    int score,
    String query,
    int minChoice,
    int maxChoice
  ) {
    int choice;
    do {
      choice = Console.getInt(query);
    } while (choice < minChoice || choice > maxChoice);

    return choice;
  }
}
