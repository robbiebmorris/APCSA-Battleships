import java.util.*;

public class Battleships {
  public static void main(String[] args) {

    // initialze board and scanner
    Board gameBoard = new Board();
    Scanner scan = new Scanner(System.in);

    // introduction method and function
    System.out.println("Welcome to BATTLESHIPS.");
    System.out.println("The ships have been placed on the board by the computer.");
    gameBoard.printPlayerViewBoard();

    // initialize variables for firing on
    int rowFired = 0;
    int colFired = 0;
    // if the game isnt over, keep looping over asking the user for an action
    while (!gameBoard.isGameOver()) {
      String input = scan.nextLine();
      //
      if (input.contains("fire")) {
        // parse string for coordinates
        String coordinates = input.replaceAll("\\D+", "");
        rowFired = Character.getNumericValue(coordinates.charAt(0));
        colFired = Character.getNumericValue(coordinates.charAt(1));
        // store coordinates in int array
        int[] newShot = { rowFired, colFired };
        // call fire function from board class
        gameBoard.fire(newShot[0], newShot[1]);
        // print board for player to see
        gameBoard.printPlayerViewBoard();

        // other conditionals for other user input

      } else if (input.equalsIgnoreCase("view ships")) {
        // call printboard function from board class
        gameBoard.printBoard();
      } else if (input.equalsIgnoreCase("view board")) {
        // call printplayerviewboard function from board class
        gameBoard.printPlayerViewBoard();
      } else if (input.equalsIgnoreCase("stats")) {
        // call stats function from board class
        gameBoard.boardStatistics();
      } else if (input.equalsIgnoreCase("help")) {
        // print out all the possible commands
        System.out.println("Possible Commands: ");
        System.out.println("view ships - shows the placement of all the ships ");
        System.out.println("view board - shows the user's board");
        System.out.println("fire row col - fires at cell coordinates row x, col y");
        System.out.println("stats - prints out game statistics");
        System.out.println("quit - exits program");
      } else if (input.equalsIgnoreCase("quit")) {
        break;
      } else {
        System.out.println("Illegal command");
      }
    }

    System.out.println("Game over!");
    // close scanner to prevent resource leak
    scan.close();
  }
}
