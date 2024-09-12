/** * George Prielipp (265112)
 * MyText.java
 *
 * implements the Text interface
 * Provides an editable sequence of characters with a current "cursor" position
 * implemented with a doubly linked list
 */

import java.util.NoSuchElementException;

public class MyText implements Text
{
  /**
   * Node for a doubly linked list
   * I'm thinking about it linearly left to right
   * instead of up and down
   */
  private class Node
  { 
    char letter;
    Node left;
    Node right;

    public Node(char l, Node nl, Node nr)
    {
      letter = l;
      left = nl;
      right = nr;
    }

    private int indexOf(Node n)
    {
      if(n == null) return 0;
      return 1 + indexOf(n.right);
    }
  }

  // this is the current cursor position
  private Node current = new Node('"', null, null);

  /** Returns the character at the current cursor position */
  public char get() throws NoSuchElementException
  {
    if( current == null ) throw new NoSuchElementException();
    return current.letter;
  }

  /** Inserts a new character before the current cursor position*/
  public void insert(char c)
  {
    Node newNode = new Node(c, current.left, current);
    if( newNode.left != null )
      newNode.left.right = newNode;
    current.left = newNode;
  }

  /** Deletes the character at the current cursor position.
   * The cursor should move to the right of what was just deleted.
   * @throws NoSuchElementException if the cursor is at the end.
   */
  public void delete() throws NoSuchElementException
  { 
    if( current == null ) throw new NoSuchElementException();
    Node left = current.left;
    Node right = current.right;

    // remove current
    if(left != null)
      left.right = right;

    if(right != null)
      right.left = left;

    // set it to the right 
    // there should always be a right (i think)
    current = right; 
  }

  /** Returns whether there is another character to the left of the cursor. */
  public boolean canMoveLeft()
  {
    return current.left != null;
  }

  /** Moves the cursor one character to the left.
   * @throws NoSuchElementException if the cursor is already at the beginning.
   */
  public void moveLeft() throws NoSuchElementException
  {
    if( !canMoveLeft() ) throw new NoSuchElementException();
    current = current.left;
  }

  /** Returns whether the cursor is NOT at the end. */
  public boolean canMoveRight()
  {
    return current.right != null;
  }

  /** Moves the cursor one character to the right.
   * @throws NoSuchElementException if the cursor is already at the end.
   */
  public void moveRight() throws NoSuchElementException
  {
    if( !canMoveRight() ) throw new NoSuchElementException();
    current = current.right;
  }

  /** Displays the current sequence of characters one one line, with the cursor underneath.
   *
   * Two lines should always be printed to System.out.
   *
   * For example, if the current characters are a, b, c, d, and the cursor is at the end,
   * we should see:
   *     abcd
   *         ^
   *
   * With the same characters, but the cursor under the 'b', we would see:
   *     abcd
   *      ^
   */
  public void print()
  {
    // get the first line
    int cursorIndex = printGoingLeft(current)-1;
    printGoingRight(current.right);
    System.out.println();

    // print out the cursor
    for(int i = 0; i < cursorIndex; i++)
      System.out.print(" ");
    System.out.println("^");
  }

  /**
   * Recurses left to the front of the list
   * Maintains a count of the nodes it passes
   */
  private int printGoingLeft(Node n)
  {
    if( n == null ) return 0;
    else
    {
      int count = 1 + printGoingLeft(n.left);
      if(n.letter != '&' && n.right != null)
        System.out.print(n.letter);
      return count;
    }
  }

  /**
   * Prints everything going to the right in the list 
   */
  private void printGoingRight(Node n)
  {
    if( n != null ) 
    {
      if(n.letter != '"' && n.right != null)
        System.out.print(n.letter);
      printGoingRight(n.right);
    }
  }
}
