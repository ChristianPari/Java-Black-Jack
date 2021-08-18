package com.christianpari.black_jack.person;

public class Dealer implements Person {
  static String NAME = "Dealer";
  static int DECISION = 17;
  static int HIT = 1;
  static int STAND = 2;

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public int getAction(
    int score,
    String query,
    int minChoice,
    int maxChoice
  ) {
    System.out.println("Dealer is deciding");
    System.out.println(score < DECISION ? "Dealer Hit" : "Dealer Stands");
    return score < DECISION ? HIT : STAND;
  }

  @Override
  public int setBet() {
    return 0;
  }
}
