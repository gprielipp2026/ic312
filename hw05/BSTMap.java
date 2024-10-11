/**
 * George Prielipp (265112)
 * BSTMap.java
 *
 * implements a generic Binary Search Tree (BST)
 */

import java.util.Deque;
import java.util.LinkedList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K,V> {
  // private classes and fields here
  private class Node {
    private Pair<K, V> data;
    private Node left;
    private Node right;
 
    public Node(Pair<K, V> d, Node l, Node r)
    {
      data = d;
      left = l;
      right = r;
    }

    public int compareTo(K key)
    {
      return data.getKey().compareTo(key);
    } 
  }

  private Node root = null;
  private int numPuts = 0;

  @Override
  public V get(K key) {
    // requirement: O(height)
    return get(root, key);
  }

  /**
   * Less than to left or root
   * Greater than to right of root
   * compareTo --> negative = less than
   *           --> zero     = same
   *           --> positive = greater than
   */
  private V get(Node cur, K key)
  {
    if(cur == null) return null;
    else if (cur.compareTo(key) < 0) {
      // cur < key 
      return get(cur.right, key);
    }
    else if (cur.compareTo(key) > 0) {
      // cur > key
      return get(cur.left, key);
    }
    else if (cur.compareTo(key) == 0)
    {
      // cur == key
      return cur.data.getValue();
    }
    return null;
  }

  @Override
  public boolean containsKey(K key) {
    // requirement: O(height)
    return get(key) != null; // unless storing null then I need to rework this
  }

  private boolean containsKey(Node cur, K key)
  {
    if(cur == null) return false;
    else if (cur.compareTo(key) < 0) {
      // cur < key 
      return containsKey(cur.right, key);
    }
    else if (cur.compareTo(key) > 0) {
      // cur > key
      return containsKey(cur.left, key);
    }
    else if (cur.compareTo(key) == 0)
    {
      // cur == key
      return true;
    }
    return false;

  }

  @Override
  public void put(K key, V value) {
    // requirement: O(height)
    root = insert(root, new Pair<K, V>(key, value));
  }

  /**
   * Item will always get put in as a leaf node
   */
  private Node insert(Node cur, Pair<K, V> item)
  {
    if(cur == null) 
    {
      numPuts++;
      return new Node(item, null, null);
    }
    else if(cur.compareTo(item.getKey()) < 0)
    {
      cur.right = insert(cur.right, item);
    }
    else if(cur.compareTo(item.getKey()) > 0)
    {
      cur.left = insert(cur.left, item);
    }
    else if(cur.compareTo(item.getKey()) == 0)
    {
      // drop
      return cur;
    }
    return cur;
  }

  @Override
  public int size() {
    // requirement: O(1)
    return numPuts;
  }

  @Override
  public Deque<K> traverse() {
    // requirement: O(n)
    Deque<K> deque = new LinkedList<K>();
    traverse(root, deque);
    return deque;
  }

  /**
   * in-order traversal of the tree
   */
  private void traverse(Node cur, Deque<K> deque)
  {
    if(cur != null)
    {
      traverse(cur.left, deque);
      deque.add(cur.data.getKey());
      traverse(cur.right, deque);
    }
  }

  @Override
  public void remove(K key) {
    // requirement: O(height)
    root = remove(root, key);
  }

  /**
   * Removes key value pair from the tree
   */
  private Node remove(Node cur, K key)
  {
    // cannot remove from an empty tree
    if(cur == null) return null;
    else if(cur.compareTo(key) < 0)
    {
      // key > cur
      cur.right = remove(cur.right, key);
    }
    else if(cur.compareTo(key) > 0)
    {
      // key < cur
      cur.left = remove(cur.left, key);
    }
    else // cur.data.getKey() == key
    {
      // no children/1 child
      if(cur.left == null)
      {
        numPuts--;
        return cur.right;
      }
      // 1 child
      else if(cur.right == null)
      {
        numPuts--;
        return cur.left;
      }
      // 2 children 
      Node successor = getSuccessor(cur.right);
      cur.data = successor.data;
      cur.right = remove(cur.right, successor.data.getKey()); 
    }
    return cur;
  }

  /**
   * gets the successor (go left as much as possible)
   */
  private Node getSuccessor(Node cur)
  {
    if(cur == null) return null;
    if(cur.left == null) return cur;
    else return getSuccessor(cur.left);
  }
}
