import java.util.NoSuchElementException;

/** An editable sequence of characters with a current "cursor" position.
 *
 * The character sequence can be any length.
 *
 * The cursor can be at any character in the sequence, OR at the end.
 * When the cursor is at the end, there is no character to get() or delete(),
 * but insert() will add a new character at the end of the sequence.
 *
 * There should be a no-argument constructor which creates an
 * initially-empty sequence of text, with the cursor at the end.
 *
 * All operations should be O(1) worst-case or O(1) amortized time.
 */
public interface Text {
  /** Returns the character at the current cursor position. */
  char get() throws NoSuchElementException;

  /** Inserts a new character before the current cursor position. */
  void insert(char c);

  /** Deletes the character at the current cursor position.
   * The cursor should move to the right of what was just deleted.
   * @throws NoSuchElementException if the cursor is at the end.
   */
  void delete() throws NoSuchElementException;

  /** Returns whether there is another character to the left of the cursor. */
  boolean canMoveLeft();

  /** Moves the cursor one character to the left.
   * @throws NoSuchElementException if the cursor is already at the beginning.
   */
  void moveLeft() throws NoSuchElementException;

  /** Returns whether the cursor is NOT at the end. */
  boolean canMoveRight();

  /** Moves the cursor one character to the right.
   * @throws NoSuchElementException if the cursor is already at the end.
   */
  void moveRight() throws NoSuchElementException;

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
  void print();
}
