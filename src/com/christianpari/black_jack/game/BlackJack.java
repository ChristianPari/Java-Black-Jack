package com.christianpari.black_jack.game;

import com.christianpari.black_jack.deck.Card;
import com.christianpari.black_jack.person.Hand;
import com.christianpari.black_jack.person.Player;

public class BlackJack {
  // TODO: detect blackJack
  Table table = new Table();

  public void play() {
    // deal cards to actor
    // 1 face down 1 face up.
    table.createPlayers();
    table.getDeck().shuffle();
    deal();

    // display card;
    getPlayerBets();

    // display Actor cards and score
    runPlayersTurns();
    table.getDealer().revealHand();
    do {} while (!actorTurn(table.getDealer()));

    // determine if Player won.
    displayTable();
    for (Hand player : table.getPlayers()) {
      endRound(player);
    }

    table.cleanTable();

    // TODO: add ability to repeat game
    displayWallets();

  }

  private void getPlayerBets() {
    for (Hand player : table.getPlayers()) {
      setBet(player);
    }
  }
  private void runPlayersTurns() {
    for (int activeIndex = 0; activeIndex < table.getPlayers().size(); activeIndex++) {
      displayTable();
      Hand player = table.getPlayers().get(activeIndex);
      player.revealHand();
      do {} while (!actorTurn(player));
    }
  }

  private void displayWallets() {
    for (Hand hand : table.getPlayers()) {
      Player player = (Player) hand.getActor();
      System.out.println(player.getName() + "'s wallet:  " + player.getWallet());
    }
  }

  private void endRound(Hand player) {
    char result = didWin(player);
    switch (result) {
      case 'n':
        System.out.println(player.getName() + " looses " + player.getBet());
        ((Player) player.getActor()).adjustWallet(player.getBet() * -1);
        break;
      case 'y':
        System.out.println(player.getName() + " wins " + player.getBet());
        ((Player) player.getActor()).adjustWallet(player.getBet());
        break;
      case 'p':
        System.out.println(player.getName() + " pushes with dealer keeps " + player.getBet());
        break;
      default:
        System.out.println("Did win error returned " + result);
    }
  }

  private void setBet(Hand player) {
    player.setBet();
  }

  private boolean actorTurn(Hand hand) {
    displayHand(hand);
    return performAction(hand, getAction(hand));
  }

  private int getAction(Hand hand) {
    return hand.getAction();
  }

  final int HIT = 1, STAND = 2, DOUBLE = 3, SPLIT = 4;

  private boolean performAction(
    Hand hand,
    int action
  ) {
    switch (action) {
      case DOUBLE:
        System.out.println("Double");
        hand.doubleBet();

      case HIT:
        Card card = table.getDeck().draw(true);
        System.out.println(hand.getName() +" Hit and was dealt " + card);
        hand.addCard(card);
        if (didBust(hand.getScore())) {
          System.out.println("Busted " + hand.getScore());
          return true;
        }
        return action == DOUBLE;

      case STAND:
        System.out.println(hand.getName() + " Stood.");
        return true;

      case SPLIT:
        Card splitCard = hand.removeCard(1);
        Hand newHand = new Hand(hand.getActor());
        newHand.addCard(splitCard);
        hand.addCard(table.getDeck().draw(true));
        newHand.addCard(table.getDeck().draw(true));
        newHand.setBet(hand.getBet());
        table.getPlayers().add(newHand);
        return false;

      default:
        System.out.println("error! default case Stand");
        return true;
    }
  }

  private void deal() {
    for (int count = 0; count < 2; count++){
      for (Hand player : table.getPlayers()) {
        player.addCard(table.getDeck().draw(count != 0));
      }

      table.getDealer().addCard(table.getDeck().draw(count != 0));
    }
  }

  private void displayTable() {
    System.out.println(table.getDealer().getName() + ": " + table.getDealer());
    for (Hand player : table.getPlayers()) {
      System.out.println(player.getName() + ": " + player);
    }
  }

  private void displayHand(Hand hand) {
    System.out.println(hand);
    System.out.println(hand.getName() + " score is: " + hand.getScore());
  }

  private char didWin(Hand player) {
    int playerScore = player.getScore();
    int dealerScore = table.getDealer().getScore();

    if (didBust(playerScore)) {
      System.out.println(player.getName() + " busted!");
      return 'n';
    }

    if (didBust(dealerScore)) {
      System.out.println("Dealer busted!");
      return 'y';
    }

    System.out.println(player.getName() + " has " + playerScore + " | Dealer has " + dealerScore);
    if (playerScore < dealerScore) {
      System.out.println(player.getName() + " looses");
      return 'n';
    }

    if (playerScore == dealerScore) {
      System.out.println("Push!");
      return 'p';
    }

    return 'y';
  }

  private boolean didBust(int score) {return score > 21;}
}
