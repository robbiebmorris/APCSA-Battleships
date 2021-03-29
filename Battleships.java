import java.util.*;

public class Battleships {
  public static void main(String[] args) {
    Board gameBoard = new Board();
    Scanner scan = new Scanner(System.in);

    System.out.println("Welcome to BATTLESHIPS.");
    System.out.println("The ships have been placed on the board by the computer.");
    gameBoard.printPlayerViewBoard();

    int rowFired = 0;
    int colFired = 0;
    while (true) {
      String input = scan.nextLine();
      if (input.contains("fire")) {

        String coordinates = input.replaceAll("\\D+", "");
        rowFired = Character.getNumericValue(coordinates.charAt(0));
        colFired = Character.getNumericValue(coordinates.charAt(1));
        int[] newShot = { rowFired, colFired };
        gameBoard.fire(newShot[0], newShot[1]);
        gameBoard.printPlayerViewBoard();

      } else if (input.equalsIgnoreCase("view ships")) {
        gameBoard.printBoard();
      } else if (input.equalsIgnoreCase("view board")) {
        gameBoard.printPlayerViewBoard();
      } else if (input.equalsIgnoreCase("stats")) {

      } else if (input.equalsIgnoreCase("help")) {
        System.out.println("Possible Commands: ");
        System.out.println("view ships - shows the placement of all the ships ");
        System.out.println("view board - shows the user's board");
        System.out.println("fire x y - fires at cell coordinates row x, col y");
        System.out.println("stats - prints out game statistics");
      } else if (input.equalsIgnoreCase("quit")) {
        break;
      } else {
        System.out.println("Illegal command");
      }
    }
    scan.close();
  }
}
