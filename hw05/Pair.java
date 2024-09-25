/**
 * I pulled this directly from the course notes
 */

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K,V>> {
  public K key;
  public V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;

  }

  public K getKey() { return key;  }
  public V getValue() { return value;  }

  public void setKey(K key) { this.key = key;  }
  public void setValue(V value) { this.value = value;  }

  public int compareTo(Pair<K,V> other) {
    return key.compareTo(other.key);

  }

}
