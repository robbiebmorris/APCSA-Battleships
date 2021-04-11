//parent class for all ship types

public class Ships {
  // initialize important values to store in ships
  int length;
  Node symbol;
  int row;
  int col;
  boolean orientation;

  // simple getter methods

  public Node getSymbol() {
    return this.symbol;
  }

  public int getLength() {
    return this.length;
  }

  // store coordinates of a ship in the the object
  public void setCoordinates(int newRow, int newCol, boolean newOrientation) {
    this.row = newRow;
    this.col = newCol;
    this.orientation = newOrientation;
  }

  // more simple getter methods
  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public boolean getOrientation() {
    return this.orientation;
  }

}
