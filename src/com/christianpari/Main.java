package com.christianpari;

import com.christianpari.black_jack.BlackJack;
import com.christianpari.black_jack.dealer.deck_tools.StandardDeck;

public class Main {

  public static void main(String[] args) {
    BlackJack game = new BlackJack(new StandardDeck());
    game.runGame();
  }
}
