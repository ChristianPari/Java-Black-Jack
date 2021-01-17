package com.christianpari.black_jack;

import java.util.*;

public class BlackJack {
  private List<Player> players = new ArrayList<>();
  private Dealer dealer = new Dealer();
  private final int CARDS_PER_PLAYER = 2;

  public BlackJack (
    Deck deck
  ) {
    addPlayers();
    dealer.newDeck(deck);
  }

  private void addPlayers() {
    int numOfPlayers = Console.getInt(
      "How many players?",
      "Number of Players: ",
      1,
      6
    );

    for (int count = 0; count < numOfPlayers; count++) {
      String playerName = Console.getString(
        "\nPlayer " + (count + 1) + "'s Name?",
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
    dealer.deal(players, CARDS_PER_PLAYER);
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

      if (takeAnotherCard) {
        Card newCard = dealer.giveCard();
        player.takeCard(newCard);
        if (player.getScore() > 21) {
          busted(player);
          break;
        }
      } else {
        if (player.getScore() > 21) {
          busted(player);
        }
        break;
      }
    }
  }

  private void busted(Player player) {
    System.out.println(player.getName() + " BUSTED!");
    Console.getString("\nPress enter to end turn.","");
    player.clearHand();
  }

  private void endRound() {
    getWinner();
  }

  private void getWinner() {
    List<Player> winners = displayScoresAndGetWinners();
    String output = "";
    if (winners.size() == 0) {
      output = "THERE WERE NO WINNERS, DEALER TAKES POT!";
    } else if (winners.size() == 1) {
      output = "THE WINNER IS ";
    } else {
      output = "THE WINNERS ARE:";
    }
    for (var player : winners) {
      output += "\n" + player.getName();
    }
    System.out.println(output);
  }

  private List<Player> displayScoresAndGetWinners() {
    String title = "\nSCORES";
    String scoresString = "";

    Map<Integer, Player> playerScores = new HashMap<>();
    for (var player : players) {
      playerScores.put(player.getScore(), player);
    }

    List<Integer> scores = new ArrayList<>(playerScores.keySet());
    scores.sort(Collections.reverseOrder());

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
      scoresString += playerName + " : " + score + "\n";
    }

    System.out.println(title + "\n" + scoresString.trim() + "\n");

    return winners;
  }

}
