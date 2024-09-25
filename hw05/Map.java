import java.util.Deque;

public interface Map<K,V> {
  /** Retrieves the value associated with the given key.
   * If the key is not in the map, null is returned.
   */
  V get(K key);

  /** Returns true if the key has been put into this map (and not removed).
   */
  boolean containsKey(K key);

  /** Associates the given value to the given key.
   * If the key was not in the map, it is added and the size increases.
   * Otherwise the size stays the same, but the new value should update
   * the value previously associated to that key.
   */
  void put(K key, V value);

  /** Removes the key and any associated value from the map, if present.
   * If the key is not in the map, nothing happens (no error should be
   * raised).
   *
   * This method is optional; the default just throws an exception.
   */
  void remove(K key);

  /** Returns the number of distinct keys that have been put in the map
   * (and not yet removed).
   */
  int size();

  /** Returns a new Deque with all of the keys of this map.
   * When feasible based on the underlying data structure (such as a binary search
   * tree), the keys should be added to the tail end of the Deque in sorted
   * order.
   */
  Deque<K> traverse();
}
