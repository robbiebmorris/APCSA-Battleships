import java.util.*;

public class Board {
  static Node[][] board;
  static int boardSize = 10;
  static boolean ships = true;
  static List<Ships> shipList;

  public Board() {
    board = new Node[boardSize][boardSize];
    shipList = new ArrayList<Ships>();
    shipList.add(new Destroyer());
    shipList.add(new Cruiser());
    shipList.add(new Battleship());
    shipList.add(new AircraftCarrier());
    createEmptyBoard();
    placeShips(ships);
  }

  public void initializeShipTypes() {

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
      placeRandomShips();
    }
    placeUserShips();
  }

  public void placeRandomShips() {
    // 1a, 1b, 1c, 2d
    int randomRow;
    int randomCol;
    int[] shipTypes = { 3, 2, 1, 0, 0 };
    Random randomOrientation = new Random();

    int shipCounter = 0;
    while (true) {
      randomRow = (int) (Math.random() * 9);
      randomCol = (int) (Math.random() * 9);
      if (isPositionAvailable(shipTypes[shipCounter], randomRow, randomCol, randomOrientation.nextBoolean())) {
        System.out.println(randomRow + " " + randomCol + " was suitable for ship " + shipTypes[shipCounter]);
        placeShip(shipTypes[shipCounter], randomRow, randomCol, randomOrientation.nextBoolean());
        shipCounter++;
      }
      if (shipCounter == 5) {
        break;
      }
    }
  }

  public void placeUserShips() {

  }

  /*
   * If orientation is true, then the orientation of the ship is horizontal. If
   * false, the orientation of the ship is vertical
   */

  public void placeShip(int shipType, int row, int col, boolean orientation) {

    board[row][col] = shipList.get(shipType).symbol;
    for (int i = 1; i < shipList.get(shipType).length; i++) {
      if (orientation) {
        board[row][col + i] = shipList.get(shipType).symbol;
      } else {
        board[row + i][col] = shipList.get(shipType).symbol;
      }
    }
  }

  public boolean isPositionAvailable(int shipType, int rowInitial, int colInitial, Boolean orientation) {
    try {
      if (isCellFull(rowInitial, colInitial)) {
        return false;
      }
      for (int i = 1; i < shipList.get(shipType).length; i++) {
        if (orientation) {
          if (isCellFull(rowInitial, colInitial + i)) {
            return false;
          }
        } else {
          if (isCellFull(rowInitial + i, colInitial)) {
            return false;
          }
        }
      }
      return true;
    } catch (Exception ArrayIndexOutOfBoundsException) {
      System.out.println("caught out of bounds ship");
      return false;
    }
  }

  public boolean isCellFull(int row, int col) {
    if (board[row][col] != Node.EMPTY) {
      return false;
    } else {
      return true;
    }
  }

  public void boardStatistics() {

  }
}
