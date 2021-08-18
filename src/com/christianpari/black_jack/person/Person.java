package com.christianpari.black_jack.person;

public interface Person {
  String getName();
  int setBet();
  int getAction(int score, String query, int minChoice, int maxChoice);
}
