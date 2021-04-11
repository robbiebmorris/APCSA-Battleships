//standard enumerate class to simplify the board class

public enum Node {
  A('A'), B('B'), C('C'), D('D'), EMPTY('-'), BLANK('O'), HIT('X');

  private final char node;

  Node(char newNode) {
    this.node = newNode;
  }

  // getter method
  public char getNode() {
    return this.node;
  }
}