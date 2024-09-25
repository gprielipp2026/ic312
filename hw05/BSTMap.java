import java.util.Deque;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {
  // private classes and fields here

  @Override
  public V get(K key) {
    // requirement: O(height)
    throw new UnsupportedOperationException("you need to implement get(k)");
  }

  @Override
  public boolean containsKey(K key) {
    // requirement: O(height)
    throw new UnsupportedOperationException("you need to implement containsKey(k)");
  }

  @Override
  public void put(K key, V value) {
    // requirement: O(height)
    throw new UnsupportedOperationException("you need to implement put(k,v)");
  }

  @Override
  public int size() {
    // requirement: O(1)
    throw new UnsupportedOperationException("you need to implement size()");
  }

  @Override
  public Deque<K> traverse() {
    // requirement: O(n)
    throw new UnsupportedOperationException("you need to implement traverse()");
  }

  @Override
  public void remove(K key) {
    // requirement: O(height)
    throw new UnsupportedOperationException("implement remove(k) last!");
  }
}
