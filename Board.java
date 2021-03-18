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
      placeRandomShips();
    }
    placeUserShips();
  }

  public void placeRandomShips() {
    placeAircraftCarrier(0, 0, true);
  }

  public void placeUserShips() {

  }

  /*
   * If orientation is true, then the orientation of the ship is horizontal. If
   * false, the orientation of the ship is vertical
   */

  public void placeAircraftCarrier(int row, int col, boolean orientation) {
    board[row][col] = Node.A;
    for (int i = 1; i <= 4; i++) {
      if (orientation) {
        board[row][col + i] = Node.A;
      } else {
        board[row + i][col] = Node.A;
      }
    }
  }

  public void placeBattleship(boolean orientation) {

  }

  public void placeCruiser(boolean orientation) {

  }

  public void placeDestroyer(boolean orientation) {

  }

  public void boardStatistics() {

  }
}
