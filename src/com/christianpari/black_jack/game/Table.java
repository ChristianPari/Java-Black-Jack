package com.christianpari.black_jack.game;

import com.christianpari.black_jack.console.Console;
import com.christianpari.black_jack.deck.Deck;
import com.christianpari.black_jack.deck.Standard;
import com.christianpari.black_jack.person.Dealer;
import com.christianpari.black_jack.person.Hand;
import com.christianpari.black_jack.person.Player;

import java.util.ArrayList;
import java.util.List;

public class Table {
  private Hand dealer = new Hand(new Dealer());
  private Hand player = new Hand(new Player());
  private List<Hand> players = new ArrayList<>();
  private int numOfPlayers = -1;
  private Deck deck = new Standard();

  public Deck getDeck() { return deck; }
  public Hand getDealer() { return dealer; }
  public Hand getPlayer() { return player; }
  public List<Hand> getPlayers() { return players; }
  public int getNumOfPlayers() { return numOfPlayers; }

  public void createPlayers() {
    do {
      numOfPlayers = Console.getInt("How many players?");
    } while (numOfPlayers < 0 || numOfPlayers > 4);

    if (numOfPlayers == 0) {
      System.out.println("Quitting no players...");
      System.exit(0);
    }

    for (int count = 0; count < numOfPlayers; count++) {
      players.add(new Hand(new Player()));
    }
  }

  public void cleanTable() {
    while (players.size() > numOfPlayers) {
      players.remove(players.size() - 1);
    }
  }
}
