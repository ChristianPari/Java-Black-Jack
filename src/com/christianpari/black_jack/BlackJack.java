package com.christianpari.black_jack;

import java.util.List;

public class BlackJack {
  private List<Player> players;
  private Dealer dealer = new Dealer();

  public BlackJack (
    int numOfPlayers,
    Deck deck
  ) {
    addPlayers(numOfPlayers);
    dealer.newDeck(deck);
  }

  public void runGame() {
    // will make this run until players decide to stop
      // thinking of implementing a betting system..
    runRound();
  }

  private void runRound() {
    dealer.shuffle();
    dealer.deal(players, 2);
    for (var player : players) {
      runTurn(player);
    }
    endRound();
  }

  private void runTurn(Player player) {
    while (true) {
      player.peek();
      boolean takeAnotherCard = player.stayOrHit(new String[] {
        "Stay",
        "Hit Me"
        });

      if (takeAnotherCard) {
        Card newCard = dealer.giveCard();
        player.takeCard(newCard);
        if (player.getScore() > 21) {
          System.out.println(player.getName() + "busted!");
          player.clearPlayer();
          break;
        }
      } else {
        break;
      }
    }
  }

  private void endRound() {
    // make a way for the scores to be displayed highest to lowest
    // declare the winner of the round
  }

  private void addPlayers(int numOfPlayers) {
    for (int count = 0; count < numOfPlayers; count++) {
      String playerName = Console.getString(
        "Player " + (count + 1) + "'s Name?",
        "Name: "
      );
      players.add(new Player(playerName));
    }
  }

}
