package com.christianpari.black_jack;

import java.util.*;

public class BlackJack {
  private List<Player> players = new ArrayList<>();
  private Dealer dealer = new Dealer();
  private final int CARDS_PER_PLAYER = 2;

  public BlackJack (
    Deck deck
  ) {
    welcome();
    addPlayers();
    dealer.newDeck(deck);
  }

  private void welcome() {
    System.out.println("Welcome to BlackJack!\n" +
      "Try not to go over 21 but remember, you have to have\n" +
      "a higher score than the dealer to win your wager!\n" +
      "Good Luck!\n");
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
    while (players.size() > 1) {
      runRound();
    }
  }

  private void runRound() {
    playersBet();
    roundSetup();
    runTurns();
    dealersTurn();
    endRound();
  }

  private void playersBet() {
    for (var player : players) {
      player.makeBet();
    }
  }

  private void roundSetup() {
    dealer.shuffle();
    dealer.deal(players, CARDS_PER_PLAYER);
  }

  private void runTurns() {
    for (var player : players) {
      runTurn(player);
      Console.clearScreen();
    }
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
    String pName = player.getName();
    player.peek();
    System.out.println(pName + " BUSTED!\nYou lost your wager of $" + player.getWager());
    Console.getString("\nPress enter to end turn.","");
    player.clearHand();
    player.wagerLost();
  }

  private void dealersTurn() {
    while (true) {
      dealer.display();
      boolean takeAnother = dealer.stayOrHit();
      if (takeAnother) {
        Card newCard = dealer.giveCard();
        dealer.takeCard(newCard);
        if (dealer.getScore() > 21) {
          System.out.println("Dealer BUSTED!\n");
          dealer.display();
          dealer.clearHand();
          break;
        }
      } else {
        if (dealer.getScore() > 21) {
          System.out.println("Dealer BUSTED!\n");
          dealer.display();
          dealer.clearHand();
        }
        break;
      }
    }
  }

  private void endRound() {
    getWinner();
    roundResults();
  }

  private void getWinner() {
    List<Player> winners = displayScoresAndGetWinners();
    String output = "\n";
    if (winners.size() == 0) {
      output += "THERE WERE NO WINNERS, DEALER TAKES POT!";
    } else if (winners.size() == 1) {
      output += "THE WINNER IS ";
      Player player = winners.get(0);
      player.wagerWon();
      output += player.getName();
    } else {
      output += "THE WINNERS ARE:";
      for (var player : winners) {
        player.wagerWon();
        output += "\n" + player.getName();
      }
    }
    System.out.println(output.trim());
  }

  private List<Player> displayScoresAndGetWinners() {
    Map<Integer, Player> remainingPlayers = new HashMap<>();
    for (var player : players) {
      if (player.getScore() != 0) {
        remainingPlayers.put(player.getScore(), player);
      }
    }
    int dealerScore = dealer.getScore();

    List<Integer> scores = new ArrayList<>(remainingPlayers.keySet());
    scores.sort(Collections.reverseOrder());

    List<Player> winners = new ArrayList<>();

    if (dealerScore == 0) {
      winners.addAll(remainingPlayers.values());
    } else {
      for (int score : scores) {
        Player player = remainingPlayers.get(score);
        if (score > dealerScore) {
          winners.add(player);
        } else if (score == dealerScore) {
          player.tiedDealer();
        } else {
          player.wagerLost();
        }
      }
    }
    return winners;
  }

  private void roundResults() {
    String title = "\nROUND RESULTS\n";

    String dealerInfo = "DEALER'S SCORE: " + dealer.getScore() + "\n";
    dealer.clearHand();

    String playersInfo = "";
    List<Player> nextPlayers = new ArrayList<>();

    for (var player : players) {
      String playerInfo = "NAME: " + player.getName() +
        " | SCORE: " + player.getScore() +
        " | MONEY: " + player.getMoney() + "\n";

      playersInfo += playerInfo;
      player.clearHand();

      if (player.getMoney() >= 1) {
        nextPlayers.add(player);
      }
    }

    players = nextPlayers;

    System.out.println(title + dealerInfo + playersInfo);
  }

}
