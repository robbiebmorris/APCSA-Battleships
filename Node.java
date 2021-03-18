public enum Node {
  A('A'), B('B'), C('C'), D('D'), EMPTY('-'), BLANK('O'), HIT('X');

  private final char node;

  Node(char newNode) {
    this.node = newNode;
  }

  public char getNode() {
    return this.node;
  }
}