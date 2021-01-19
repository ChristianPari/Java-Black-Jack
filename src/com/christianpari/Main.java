package com.christianpari;

import com.christianpari.black_jack.game.BlackJack;
import com.christianpari.black_jack.StandardDeck;

public class Main {

  public static void main(String[] args) {
    BlackJack game = new BlackJack(new StandardDeck());
    game.runGame();
  }
}
