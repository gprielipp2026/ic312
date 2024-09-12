import java.util.NoSuchElementException;

/** A stack with limited (but changeable) capacity.
 *
 * When new items pushed, the go on the "top" of the stack.
 * Calling pop() also removes from the "top" of the stack,
 * so that push/pop are LIFO.
 *
 * But stacks also have a fixed capacity. WHen calling push(),
 * if the stack is already at its capacity, the oldest item
 * ("bottom" of the stack) is silently removed.
 */
public interface BoundedStack<T> {
  /** Adds a new element to the top of the stack.
   *
   * If the stack is already at its capacity, a single item is
   * removed from the BOTTOM of the stack.
   */
  void push(T item);

  /** Removes and returns the element at the top of the stack.
   * @throws NoSuchElementException if the stack is empty.
   */
  T pop() throws NoSuchElementException;

  /** Changes the capacity to the given value.
   *
   * If the current capacity is greater than the given capacity,
   * then elements may need to be removed from the BOTTOM of the
   * stack so that it reaches the desired capacity.
   *
   * This affects all future push/pop operations, but not past
   * ones. That is, increasing the capacity does not make
   * the stack magically "remember" things which have already been
   * removed.
   */
  void setCapacity(int capacity);

  /** Returns whether the stack is currently empty. */
  boolean isEmpty();

  /** Removes all elements from the stack.
   *
   * The capacity should remain unchanged.
   */
  void clear();
}
