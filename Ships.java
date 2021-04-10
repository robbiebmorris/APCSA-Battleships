public class Ships {
  int length;
  Node symbol;
  int row;
  int col;
  boolean orientation;

  public Node getSymbol() {
    return this.symbol;
  }

  public int getLength() {
    return this.length;
  }

  public void setCoordinates(int newRow, int newCol, boolean orientation) {
    this.row = newRow;
    this.col = newCol;
  }

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
