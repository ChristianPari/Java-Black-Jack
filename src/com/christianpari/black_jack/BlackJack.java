package com.christianpari.black_jack;

import java.util.*;

public class BlackJack {
  private List<Player> players = new ArrayList<>();
  private Dealer dealer = new Dealer();

  public BlackJack (
    int numOfPlayers,
    Deck deck
  ) {
    addPlayers(numOfPlayers);
    dealer.newDeck(deck);
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
      Console.clearScreen();
    }
    endRound();
  }

  private void runTurn(Player player) {
    Console.getString("\n" + player.getName() + " press enter once you are ready...", "");
    while (true) {
      player.peek();
      boolean takeAnotherCard = player.stayOrHit(new String[] {
        "Stay",
        "Hit Me"
        });
      System.out.println("\n".trim());

      if (takeAnotherCard) {
        Card newCard = dealer.giveCard();
        player.takeCard(newCard);
        if (player.getScore() > 21) {
          System.out.println(player.getName() + " BUSTED!");
          player.clearHand();
          break;
        }
      } else {
        break;
      }
    }
  }

  private void endRound() {
    getWinner();
  }

  private void getWinner() {
    List<Player> winners = displayScoresAndGetWinners();
    String output = (winners.size() > 1) ? "THE WINNERS ARE...\n" : "THE WINNER IS...";
    for (var player : winners) {
      output += player.getName() + "\n";
    }
    System.out.println(output);
  }

  private List<Player> displayScoresAndGetWinners() {
    // todo: currently trying to figure out why scores arent print all players ands scores
    String title = "\nSCORES";
    String scoresString = "";

    Map<Integer, Player> playerScores = new HashMap<>();
    for (var player : players) {
      playerScores.put(player.getScore(), player);
    }

    List<Integer> scores = new ArrayList<>(playerScores.keySet());
    Collections.reverse(scores);

    int highestScore = scores.get(0);
    List<Player> winners = new ArrayList<>();
    winners.add(playerScores.get(highestScore));

    for (int count = 0; count < scores.size(); count++) {
      int score = scores.get(count);
      Player player = playerScores.get(score);
      if (count > 0 && score == highestScore) {
        winners.add(player);
      }
      String playerName = player.getName();
      scoresString += playerName.toUpperCase() + " : " + score + "\n";
    }

    System.out.println(title + "\n" + scoresString.trim() + "\n");

    return winners;
  }

}
