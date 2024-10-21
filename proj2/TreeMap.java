/**
 * George Prielipp (265112)
 * TreeMap.java
 *
 * Implements the Map Interface using a AVL tree
 */

import java.util.List;
import java.util.ArrayList;

public class TreeMap<K extends Comparable<K>,V> implements Map<K,V>
{
  /**
   * private fields and classes 
   */
  private class Node implements Comparable<K>
  {
    private K key;
    private V value;
    private Node left = null;
    private Node right = null;

    private int height = 0;
    private int bf = 0;

    public void update()
    {
      int lh = (left == null ? -1:left.height);
      int rh = (right == null ? -1:right.height);
      
      height = Math.max(lh, rh) + 1;
      bf = rh - lh;
    }

    public Node(K k, V v) { key = k; value = v; }

    public int compareTo(K otherKey)
    {
      return key.compareTo(otherKey);
    }
  }

  private int numKeys = 0;
  private Node root = null;

  /** Retrieves the value associated with the given key.
   * @return The value set for the given key, or <code>null</code>.
   */
  public V get(K key)
  {
    return get(root, key);
  }

  /**
   * Traverse the AVL to find the key and 
   * return the value stored at that position
   */
  private V get(Node root, K key)
  {
    if(root == null) return null;
    else if( root.compareTo(key) > 0)
    {
      return get(root.left, key);
    }
    else if( root.compareTo(key) < 0 )
    {
      return get(root.right, key);
    }
    else
    {
      return root.value;
    }
  }

  /** Checks whether the given key has been put into the map (and not removed). */
  public boolean containsKey(K key)
  {
    return containsKey(root, key);
  }

  /**
   * Traverse the AVL to find the key
   * if it exists, return true
   * else return false
   * allows for the storage of null keys 
   */
  private boolean containsKey(Node root, K key)
  {
    if(root == null) return false;
    else if( root.compareTo(key) > 0)
    {
      return containsKey(root.left, key);
    }
    else if( root.compareTo(key) < 0 )
    {
      return containsKey(root.right, key);
    }
    else
    {
      return true;
    }
  }

  /** Associates the given key with the given value.
   *
   * If the key was previously put into the map, then the previous value
   * is overridden by the given one.
   */
  public void put(K key, V value)
  {
    root = put(root, key, value);
  }

  /**
   * Follows the tree and inserts
   * a new node at the corresponding area
   */
  private Node put(Node root, K key, V value)
  {
    if(root == null) 
    {
      numKeys++;
      return new Node(key, value);
    }
    else if (root.compareTo(key) > 0)
    {
      root.left = put(root.left, key, value);
    }
    else if(root.compareTo(key) < 0)
    {
      root.right = put(root.right, key, value);
    }
    else
    {
      // overide with new value
      root.value = value;
    }
    // and what needs to happen after recursing
 
    // update tree
    root.update();
    // rebalance 
    root = rebalance(root);

    return root;
  }

  /**
   * Rebalances an AVL tree
   */
  private Node rebalance(Node root)
  {
    if(root.bf == -2)
    {
      if(root.left != null && root.left.bf == 1) root.left = leftRotate(root.left);
      return rightRotate(root);
    }
    else if (root.bf == 2)
    {
      if(root.right != null && root.right.bf == -1) root.right = rightRotate(root.right);
      return leftRotate(root);
    }
    return root;
  }

  /**
   * Follows the notes provided and performs a left rotation on an AVL tree
   */
  private Node leftRotate(Node oldRoot)
  {
    Node newRoot = oldRoot.right;
    Node middle = (newRoot == null ? null:newRoot.left);
    newRoot.left = oldRoot;
    oldRoot.right = middle;

    if(newRoot.left != null) newRoot.left.update();
    if(newRoot.right != null) newRoot.right.update();
    newRoot.update();

    return newRoot;
  }

  /**
   * Follows the notes provided and performs a right rotation on an AVL tree
   */
  private Node rightRotate(Node oldRoot)
  {
    Node newRoot = oldRoot.left;
    Node middle = (newRoot == null ? null:newRoot.right);
    newRoot.right = oldRoot;
    oldRoot.left  = middle;

    if(newRoot.left != null) newRoot.left.update();
    if(newRoot.right != null) newRoot.right.update();
    newRoot.update();

    return newRoot;
  }

  /** Returns the number of disctinct keys added to the map so far. */
  public int size()
  {
    return numKeys;
  }


  /** Produces a list of all (unique) keys that are currently in the map.
   *
   * The keys may be returned in any order.
   */
  public List<K> keys()
  {
    List<K> list = new ArrayList<K>();
    traverse(root, list);
    return list;
  }

  /**
   *  traverses the internal structure in order 
   */
  private void traverse(Node root, List<K> list)
  {
    if(root == null) return;
    traverse(root.left, list);
    list.add(root.key);
    traverse(root.right, list);
  }

  /** Removes any value associated with the given key (if present).
   *
   * For Project 2, this is an OPTIONAL operation that you shouldn't really need.
   */
  public void remove(K key) 
  {
    root = remove(root, key);
  }

  /**
   * Removes a key and value from the tree
   */
  private Node remove(Node root, K key)
  {
    if(root == null) return null; // in case the tree is completely empty
    if(root.compareTo(key) > 0)
    {
      root.left = remove(root.left, key);
    }
    else if (root.compareTo(key) < 0)
    {
      root.right = remove(root.right, key);
    }
    else
    {
      // no left child or 0 children
      if(root.left == null)
      {
        // change the internal data 
        root.key = (root.right == null ? null:root.right.key);
        root.value = (root.right == null ? null:root.right.value);
        
        root.right = null; 

        numKeys--;
      } 
      else if(root.right == null)
      {
        // change the internal data 
        root.key = (root.left == null ? null:root.left.key);
        root.value = (root.left == null ? null:root.left.value);
        
        root.left = null; 

        numKeys--;
      }
      else
      {
        // two children
        Node successor = getMin(root.right);
        root.key = successor.key;
        root.value = successor.value;
       
        // I think this is adding unnecessary work but I'm not sure how to do this better... 
        root.right = remove(root.right, successor.key);
      }
    }

    // make sure tree stays correct
    root.update();
    root = rebalance(root);
    return root;
  }

  /**
   * Gets the minimum node from a subtree
   */
  private Node getMin(Node root)
  {
    if(root == null) return null;
    if(root.left == null) return root;
    return getMin(root.left);
  }
}
