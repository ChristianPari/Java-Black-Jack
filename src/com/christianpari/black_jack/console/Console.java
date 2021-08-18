package com.christianpari.black_jack.console;

import java.util.Scanner;

public class Console {
  static Scanner scanner = new Scanner(System.in);

  public static int getInt(String text) {
    System.out.println(text);
    String input = scanner.nextLine();
    return Integer.parseInt(input);
  }

  public static String getString(String text) {
    System.out.println(text);
    return scanner.nextLine();
  }
}
