import java.util.List;

/** Map ADT interface, supporting insertion and lookup of key/value pairs.
 *
 * Similar to java's java.util.Map, but simplified to have just a few methods.
 */
public interface Map<K,V> {
  /** Retrieves the value associated with the given key.
   * @return The value set for the given key, or <code>null</code>.
   */
  V get(K key);

  /** Checks whether the given key has been put into the map (and not removed). */
  boolean containsKey(K key);

  /** Associates the given key with the given value.
   *
   * If the key was previously put into the map, then the previous value
   * is overridden by the given one.
   */
  void put(K key, V value);

  /** Returns the number of disctinct keys added to the map so far. */
  int size();

  /** Produces a list of all (unique) keys that are currently in the map.
   *
   * The keys may be returned in any order.
   */
  List<K> keys();

  /** Removes any value associated with the given key (if present).
   *
   * For Project 2, this is an OPTIONAL operation that you shouldn't really need.
   */
  default void remove(K key) {
    throw new UnsupportedOperationException("remove not supported for this Map");
  }
}
