import java.util.Random;

public class Board {
  Node[][] board;
  static int boardSize = 10;
  static boolean ships = true;

  public Board() {
    board = new Node[boardSize][boardSize];
    createEmptyBoard();
    placeShips(ships);
  }

  public void createEmptyBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = Node.EMPTY;
      }
    }
  }

  public void printBoard() {
    for (int row = 0; row < boardSize; row++) {
      System.out.println();
      for (int col = 0; col < boardSize; col++) {
        System.out.print(board[row][col].getNode() + " ");
      }
    }
  }

  public void placeShips(boolean random) {
    if (random) {
      placeRandomShips(5);
    }
    placeUserShips();
  }

  public void placeRandomShips(int numberOfShips) {
    // 1a, 1b, 1c, 2d
    int randomRow;
    int randomCol;
    Random randomOrientation = new Random();

    for (int i = 0; i < numberOfShips; i++) {
      randomRow = (int) (Math.random() * 10);
      randomCol = (int) (Math.random() * 10);
      if (board[randomRow][randomCol] == Node.EMPTY) {
        placeShip(i, randomRow, randomCol, randomOrientation.nextBoolean());
      } else {
        continue;
      }
    }
    /*
     * placeAircraftCarrier(0, 0, false); placeBattleship(0, 5, true);
     * placeCruiser(3, 3, true); placeDestroyer(6, 2, false);
     */
  }

  public void placeUserShips() {

  }

  /*
   * If orientation is true, then the orientation of the ship is horizontal. If
   * false, the orientation of the ship is vertical
   */

  public void placeAircraftCarrier(int row, int col, boolean orientation) {
    try {
      board[row][col] = Node.A;
      for (int i = 1; i <= 4; i++) {
        if (orientation) {
          board[row][col + i] = Node.A;
        } else {
          board[row + i][col] = Node.A;
        }
      }
      // temporary
    } catch (Exception arrayIndexOutOfBoundsException) {
      System.out.println("That location is not possible.");
    }
  }

  public void placeBattleship(int row, int col, boolean orientation) {
    try {
      board[row][col] = Node.B;
      for (int i = 1; i <= 3; i++) {
        if (orientation) {
          board[row][col + i] = Node.B;
        } else {
          board[row + i][col] = Node.B;
        }
      }
      // temporary
    } catch (Exception arrayIndexOutOfBoundsException) {
      System.out.println("That location is not possible.");
    }
  }

  public void placeCruiser(int row, int col, boolean orientation) {
    try {
      board[row][col] = Node.C;
      for (int i = 1; i <= 2; i++) {
        if (orientation) {
          board[row][col + i] = Node.C;
        } else {
          board[row + i][col] = Node.C;
        }
      }
      // temporary
    } catch (Exception arrayIndexOutOfBoundsException) {
      System.out.println("That location is not possible.");
    }
  }

  public void placeDestroyer(int row, int col, boolean orientation) {
    try {
      board[row][col] = Node.D;
      if (orientation) {
        board[row][col + 1] = Node.D;
      } else {
        board[row + 1][col] = Node.D;
      }
    } catch (Exception arrayIndexOutOfBoundsException) {
      System.out.println("That location is not possible.");
    }
  }

  public void placeShip(int index, int row, int col, boolean orientation) {
    try {
      if (index == 0) {
        placeAircraftCarrier(row, col, orientation);
      } else if (index == 1) {
        placeBattleship(row, col, orientation);
      } else if (index == 2) {
        placeCruiser(row, col, orientation);
      } else if (index >= 3) {
        placeDestroyer(row, col, orientation);
      }
    } catch (Exception arrayIndexOutOfBoundsException) {
      placeShip(index, row, col, orientation);
    }
  }

  public void boardStatistics() {

  }
}
