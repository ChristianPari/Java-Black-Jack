package com.christianpari.black_jack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Console {
  // variables
  static private Scanner scanner = new Scanner(System.in);

  // methods
  public static int getChoice(String[] choices) {
    String display = "Choose from the following:\n";
    for (int count = 1; count <= choices.length; count++) {
      display += "(" + count + ") " + choices[count - 1] + " ";
    }
    int choice = getInt(
      display.trim(),
      "Choice: ",
      1,
      choices.length
    );
    return choice;
  }

  public static int getChoice(
    String query,
    String[] choices
  ) {
    String display = query + "\n";
    for (int count = 1; count <= choices.length; count++) {
      display += "(" + count + ") " + choices[count - 1] + " ";
    }
    int choice = getInt(
      display.trim(),
      "Choice: ",
      1,
      choices.length
    );
    return choice;
  }

  public static List<Integer> getListIntegers(
    String prompt,
    String inputStarter,
    int min,
    int max
  ) {
    List<Integer> intChoices = new ArrayList<>();

    while (true) {
      System.out.println(prompt);
      System.out.print(inputStarter);
      String choices = scanner.nextLine().trim();
      List<String> choicesList = Arrays.asList(choices.split("\\s+"));

      for (String choice : choicesList) {
        if (!choice.matches("[" + min + "-" + max + "]")) {
          System.out.println("Please provide a valid choice input...");
          intChoices.clear();
          break;
        }

        int numericalChoice = Integer.parseInt(choice);
        intChoices.add(numericalChoice);
      }

      if (intChoices.size() == choicesList.size()) {
        break;
      }
    }

    return intChoices;
  }

  public static String getString(
    String prompt,
    String inputStarter
  ) {
    System.out.println(prompt);
    System.out.print(inputStarter);
    String input = scanner.nextLine();
    return input;
  }

  public static int getInt(
    String prompt,
    String inputStarter,
    int min,
    int max
  ) {
    int choice = 0;
    System.out.println(prompt);
    System.out.print(inputStarter);

    while (true) {
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < min || choice > max) {
          System.out.println("Please enter a valid input...\n");
          System.out.print(inputStarter);
        } else {
          break;
        }
      } catch (Exception e) {
        scanner.next();
        System.out.println("Please enter a valid input...\n");
        System.out.print(inputStarter);
      }
    }
    return choice;
  }

  public static String separator(int length) {
    var builder = new StringBuilder();
    for (int count = 0; count < length; count++) {
      builder.insert(count, "-");
    }
    return builder.toString();
  }

  public static void clearScreen() {
    int lines = 50;
    while (lines > 0) {
      System.out.println("\n");
      lines--;
    }
    scanner.nextLine();
  }
}
