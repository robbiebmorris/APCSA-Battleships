import java.util.*;

public class Board {
  static Node[][] board;
  static Node[][] playerViewBoard;
  static int boardSize = 10;
  static boolean ships = true;
  static List<Ships> shipList;
  static List<Node> shipStorage;

  // initialize statistics function
  static int shotsFired;
  static int shotsHit;
  static int shotsMissed;

  // constructor methods to setup the board
  public Board() {
    // initialize boards
    board = new Node[boardSize][boardSize];
    playerViewBoard = new Node[boardSize][boardSize];
    shotsFired = 0;
    shotsHit = 0;
    shotsMissed = 0;
    // initialize board
    createEmptyBoard();
    // initialize player board
    createPlayerViewBoard();
    // randomly place ships
    placeShips(ships);
  }

  // iterate over 2 dimensional array and fill with -.
  public void createEmptyBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = Node.EMPTY;
      }
    }
  }

  // repeat but for different board
  public void createPlayerViewBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        playerViewBoard[row][col] = Node.EMPTY;
      }
    }
  }

  // function to print out the board to console
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

  // function to print out the player board to console
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

  // support for future addition of user placed ships and 2 player mode.
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

    // create arraylist to store all ship objects
    shipList = new ArrayList<Ships>();
    shipList.add(new AircraftCarrier());
    shipList.add(new Battleship());
    shipList.add(new Cruiser());
    shipList.add(new Destroyer());
    shipList.add(new Destroyer());

    // use Random object to find both random boolean values and random ineteger
    // values
    Random rand = new Random();

    int shipCounter = 0;
    // loop until an exit condition is met
    while (true) {
      randomRow = rand.nextInt(9);
      randomCol = rand.nextInt(9);
      thisOrientation = rand.nextBoolean();

      // check if the randomly generated values don't overlap and are in bounds
      if (isPositionAvailable(shipList.get(shipCounter), randomRow, randomCol, thisOrientation)) {
        // if values work, place the ship there and store it's coordinates
        placeShip(shipList.get(shipCounter), randomRow, randomCol, thisOrientation, shipCounter);
        shipList.get(shipCounter).setCoordinates(randomRow, randomCol, thisOrientation);
        // increment ship counter
        shipCounter++;
      }
      if (shipCounter == 5) {
        // break when the ship counter reaches the total number of ships
        break;
      }
    }
  }

  public void placeUserShips() {
    // for some time in the future
  }

  /*
   * If orientation is true, then the orientation of the ship is horizontal. If
   * false, the orientation of the ship is vertical
   */

  // function to place a certain type of ship in a specified location and
  // orientation on the board
  public void placeShip(Ships ship, int row, int col, boolean orientation, int shipNumber) {
    // place initial positional point
    board[row][col] = ship.symbol;
    // place the rest of the ship using for loop ove rthe length and adding to
    // either col or row depending on orientation
    for (int i = 1; i < ship.length; i++) {
      if (orientation) {
        board[row][col + i] = ship.symbol;
      } else {
        board[row + i][col] = ship.symbol;
      }
    }
  }

  // function to check if a posiition is feasible (not overlapping and in bounds)
  public boolean isPositionAvailable(Ships ship, int rowInitial, int colInitial, Boolean orientation) {
    // try catch to hand random values that exceed the boundary
    try {
      // if the initial coordinates are already occupied, it is not feasible
      if (board[rowInitial][colInitial] != Node.EMPTY) {
        return false;
      }
      // check other coordinates of the ship to see if they are occupied using for
      // loop and adding to col or row depending on the orientation. if occupied,
      // return false
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
      // otherwise, return true
      return true;
    } catch (Exception ArrayIndexOutOfBoundsException) {
      // return false if exception detected
      return false;
    }
  }

  // helper function to check if a cell has something occupying it or not
  public boolean isCellFull(int row, int col) {
    if (board[row][col] != Node.EMPTY) {
      return false;
    } else {
      return true;
    }
  }

  // function to handle user missile fires
  public void fire(int row, int col) {
    // increment shots fire counter for statistics
    shotsFired++;
    if (playerViewBoard[row][col] != Node.EMPTY) {
      System.out.println("You already fired there!");
    } else if (board[row][col] == Node.EMPTY) {
      // set the player board to 'O'
      playerViewBoard[row][col] = Node.BLANK;
      // increment shots missed counter for statistics
      shotsMissed++;
      System.out.println("Miss.");
    } else {
      // set the player board to 'X'
      playerViewBoard[row][col] = Node.HIT;
      // increment shots hit counter for statistics
      shotsHit++;
      System.out.println("Hit!");
    }

    // if the isShipSunk function returns true, then print this out
    if (isShipSunk()) {
      System.out.println("You sunk a ship!");
    }
  }

  // function to detect if ship is sunk
  public boolean isShipSunk() {

    int nodeCounter = 0;
    // iterate over each ship
    for (int i = 0; i < shipList.size(); i++) {
      // iterate over the length of the current ship
      for (int j = 0; j < shipList.get(i).getLength(); j++) {
        // if the orientation is horizontal,
        if (shipList.get(i).getOrientation()) {
          // the ship is only sunk if the node at the END of the ship has been sunk and
          // the function has gotten through all of
          if (playerViewBoard[shipList.get(i).getRow()][shipList.get(i).getCol() + j] == Node.HIT) {
            // increment counter of number of nodes of ship that have been hit
            nodeCounter++;
            // if the counter reaches the lenght of the ship, then the ship is sunk
            if (nodeCounter == shipList.get(i).getLength()) {
              // remove the ship that has been sunk from the list of ships to be checking
              shipList.remove(i);
              return true;
            }
          }
          // if the orientation is vertical
        } else {
          if (playerViewBoard[shipList.get(i).getRow() + j][shipList.get(i).getCol()] == Node.HIT) {
            // increment counter of number of nodes of ship that have been hit
            nodeCounter++;
            // if the counter reaches the lenght of the ship, then the ship is sunk
            if (nodeCounter == shipList.get(i).getLength()) {
              // remove the ship that has been sunk from the list of ships to be checking
              shipList.remove(i);
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  // check if every ship is sunk, or in other words if the ship list is empty
  // (because every time a ship is sunk, it is removed from the list)
  public boolean isGameOver() {
    if (shipList.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  // function to print out statistics
  public void boardStatistics() {
    // simple calculations and printing to console.
    System.out.println("Number of missiles fired: " + shotsFired);
    float hitRatio;
    // cannot divide by 0, so special condition for this one.
    if (shotsFired == 0) {
      System.out.println("Hit ratio: Undef");
    } else {
      hitRatio = (float) (shotsHit) / (float) (shotsFired) * 100;
      System.out.println("Hit ratio: " + hitRatio + "%");
    }
    // find the number of ships sunk by taking the total number of ships and
    // subtracting by ships sunk
    int shipsSunk = 5 - shipList.size();
    System.out.println("Number of ships sunk: " + shipsSunk);
  }
}
