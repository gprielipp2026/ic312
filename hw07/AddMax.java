import java.util.NoSuchElementException;

/** A collection of doubles which supports adding and removing the largest.
 */
public interface AddMax {
  /** Inserts a new item into the collection.
   *
   * You can assume that x may already have been added.
   * That is, you may assume there are duplicates.
   * The item shoud not be added if x already exists in the collection.
   *
   * Required runtime: O(log n)
   */
  void add(double x);

  /** Removes and returns the largest item added so far.
   *
   * @throws NoSuchElementException if there is nothing to remove.
   *
   * Required runtime: O(log n)
   */
  double removeMax() throws NoSuchElementException;
}
