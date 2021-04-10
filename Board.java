import java.util.*;

public class Board {
  static Node[][] board;
  static Node[][] playerViewBoard;
  static int boardSize = 10;
  static boolean ships = true;
  static List<Ships> shipList;
  static List<Node> shipStorage;

  static int shotsFired;
  static int shotsHit;
  static int shotsMissed;

  public Board() {
    board = new Node[boardSize][boardSize];
    playerViewBoard = new Node[boardSize][boardSize];
    shotsFired = 0;
    shotsHit = 0;
    shotsMissed = 0;
    createEmptyBoard();
    createPlayerViewBoard();
    placeShips(ships);
  }

  public void createEmptyBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = Node.EMPTY;
      }
    }
  }

  public void createPlayerViewBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        playerViewBoard[row][col] = Node.EMPTY;
      }
    }
  }

  public void printBoard() {
    System.out.println("   0  1  2  3  4  5  6  7  8  9");
    for (int row = 0; row < boardSize; row++) {
      System.out.print(row + "  ");
      for (int col = 0; col < boardSize; col++) {
        System.out.print(board[row][col].getNode() + "  ");
      }
      System.out.println();
    }
  }

  public void printPlayerViewBoard() {
    System.out.println("   0  1  2  3  4  5  6  7  8  9");
    for (int row = 0; row < boardSize; row++) {
      System.out.print(row + "  ");
      for (int col = 0; col < boardSize; col++) {
        System.out.print(playerViewBoard[row][col].getNode() + "  ");
      }
      System.out.println();
    }
  }

  public void placeShips(boolean random) {
    if (random) {
      placeRandomShips();
    } else {
      placeUserShips();
    }
  }

  public void placeRandomShips() {
    // 1a, 1b, 1c, 2d
    int randomRow;
    int randomCol;
    boolean thisOrientation;
    // create ship objects for new ships
    shipList = new ArrayList<Ships>();
    shipList.add(new AircraftCarrier());
    shipList.add(new Battleship());
    shipList.add(new Cruiser());
    shipList.add(new Destroyer());
    shipList.add(new Destroyer());

    Random rand = new Random();

    int shipCounter = 0;
    while (true) {
      randomRow = rand.nextInt(9);
      randomCol = rand.nextInt(9);
      thisOrientation = rand.nextBoolean();

      if (isPositionAvailable(shipList.get(shipCounter), randomRow, randomCol, thisOrientation)) {
        placeShip(shipList.get(shipCounter), randomRow, randomCol, thisOrientation, shipCounter);
        shipList.get(shipCounter).setCoordinates(randomRow, randomCol, thisOrientation);
        shipCounter++;
      }
      if (shipCounter == 5) {
        break;
      }
    }
  }

  public void placeUserShips() {
    // if extra time lol
  }

  /*
   * If orientation is true, then the orientation of the ship is horizontal. If
   * false, the orientation of the ship is vertical
   */

  public void placeShip(Ships ship, int row, int col, boolean orientation, int shipNumber) {
    board[row][col] = ship.symbol;
    for (int i = 1; i < ship.length; i++) {
      if (orientation) {
        board[row][col + i] = ship.symbol;
      } else {
        board[row + i][col] = ship.symbol;
      }
    }
  }

  public boolean isPositionAvailable(Ships ship, int rowInitial, int colInitial, Boolean orientation) {
    try {
      if (board[rowInitial][colInitial] != Node.EMPTY) {
        return false;
      }
      for (int i = 1; i < ship.length; i++) {
        if (orientation) {
          if (board[rowInitial][colInitial + i] != Node.EMPTY) {
            return false;
          }
        } else {
          if (board[rowInitial + i][colInitial] != Node.EMPTY) {
            return false;
          }
        }
      }
      return true;
    } catch (Exception ArrayIndexOutOfBoundsException) {
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

  public void fire(int row, int col) {
    shotsFired++;
    if (playerViewBoard[row][col] != Node.EMPTY) {
      System.out.println("You already fired there!");
    } else if (board[row][col] == Node.EMPTY) {
      playerViewBoard[row][col] = Node.BLANK;
      shotsMissed++;
      System.out.println("Miss.");
    } else {
      playerViewBoard[row][col] = Node.HIT;
      shotsHit++;
      System.out.println("Hit!");
    }

    if (isShipSunk()) {
      System.out.println("You sunk a ship!");
    }
  }

  public boolean isShipSunk() {
    for (int i = 0; i < shipList.size(); i++) {
      for (int j = 1; j < shipList.get(i).getLength(); j++) {
        if (shipList.get(i).getOrientation()) {
          if (playerViewBoard[shipList.get(i).getRow()][shipList.get(i).getCol() + j] == Node.HIT
              && j == shipList.get(i).getLength() - 1) {
            shipList.remove(i);
            return true;
          }
        } else {
          if (playerViewBoard[shipList.get(i).getRow() + j][shipList.get(i).getCol()] == Node.HIT
              && j == shipList.get(i).getLength() - 1) {
            shipList.remove(i);
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isGameOver() {
    if (shipList.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void boardStatistics() {
    System.out.println("Number of missiles fired: " + shotsFired);
    float hitRatio;
    if (shotsFired == 0) {
      System.out.println("Hit ratio: Undef");
    } else {
      hitRatio = (float) (shotsHit) / (float) (shotsFired) * 100;
      System.out.println("Hit ratio: " + hitRatio + "%");
    }
    int shipsSunk = 5 - shipList.size();
    System.out.println("Number of ships sunk: " + shipsSunk);
  }
}
