/**
 * George Prielipp (265112)
 * RBTreeMap.java
 *
 * Implements the Map Interface using a Red-Black Tree 
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class RBTreeMap<K extends Comparable<K>, V> implements Map<K,V> 
{
  /**
   * holds the data in the tree
   */
  private class Node implements Comparable<K> 
  {
    private K key;
    private V value;
    private Node parent;
    private Node left = null;
    private Node right = null;
    private boolean isRed = true;
    
    public Node(Node parent, K key, V value)
    {
      this.parent = parent;
      this.key = key;
      this.value = value;
    }

    public int compareTo(K other)
    {
      return this.key.compareTo(other);
    }
  }

  private Node root = null;
  private int numKeys = 0;

  /**
   * retrieve the value associated with a given key
   * or null if it does not exist
   */
  public V get(K key)
  {
    Node cur = root;
    while(cur != null)
    {
      int comparison = cur.compareTo(key);
      if(comparison < 0)
        cur = cur.right;
      else if (comparison > 0)
        cur = cur.left;
      else
        return cur.value;
    } 
    return null;
  }

  /**
   * returns true if the given key is in the tree
   */
  public boolean containsKey(K key)
  {
    Node cur = root;
    while(cur != null)
    {
      int comparison = cur.compareTo(key);
      if(comparison < 0)
        cur = cur.right;
      else if (comparison > 0)
        cur = cur.left;
      else
        return true;
    } 
    return false;
  }

  /**
   * Places a key in the tree
   * if the key exists already, the value gets overwritten
   */
  public void put(K key, V value)
  {
    Node node = root;
    Node parent = null;

    // recurse down the tree
    int comparison = 0;
    while(node != null)
    {
      parent = node;
      comparison = node.compareTo(key);
      if(comparison < 0)
      {
        node = node.right;
      }
      else if (comparison > 0)
      {
        node = node.left;
      }
      else
      {
        node.value = value;
        return;
      }
    }  

    // insert the new node
    Node newNode = new Node(parent, key, value);
    if(parent == null) root = newNode;
    else if(comparison < 0) parent.right = newNode;
    else parent.left = newNode;
    numKeys++;

    fixViolations(newNode);
  }

  public void levelOrderPrint() 
  { 
    Deque<Node> nodes = new LinkedList<Node>();
    nodes.add(root);    
   
    while(!nodes.isEmpty())
    {
      Node cur = nodes.removeFirst();
      if(cur == null) continue;

      System.out.println(cur.key + ":\t" + cur.value);
      nodes.addFirst(cur.left);
      nodes.addFirst(cur.right);
    } 
  } 

  /**
   * Follows Red-Black tree rules
   * for fixing the tree
   */
  private void fixViolations(Node root)
  {
    Node parent = root.parent;

    if(parent == null || !parent.isRed)
    {
      // root node, (nothing to do)
      // OR root is red
      // force root to be black
      root.isRed = false;
      return;
    }  

    // parent is red
    Node grandparent = parent.parent;
    
    // retrieve the uncle
    Node uncle;
    if(parent == grandparent.left)
      uncle = grandparent.right;
    else
      uncle = grandparent.left; 
    
    // if uncle is red
    if(uncle != null && uncle.isRed)
    {
      parent.isRed = false;
      uncle.isRed = false;
      grandparent.isRed = true;
    
      fixViolations(grandparent);
    }
    else if (parent == grandparent.left)
    {
      if(root == parent.right)
      {
        leftRotate(parent);
        parent = root;
      }
      rightRotate(grandparent);
      parent.isRed = false;
      grandparent.isRed = true;
    }
    else
    {
      if(root == parent.left)
      {
        rightRotate(parent);
        parent = root;
      }

      leftRotate(grandparent);
      parent.isRed = false;
      grandparent.isRed = true;
    }
  }

  /**
   * Left rotate the tree about a node
   */
  private void leftRotate(Node cur)
  {
    Node parent = cur.parent;
    Node middle = cur.right;

    cur.right = middle.left;
    if(middle.left != null)
      middle.left.parent = cur;

    middle.left = cur;
    cur.parent = middle;

    // update parent, cur, leftChild    
    if(parent == null) this.root = middle;
    else if(parent.left == cur) parent.left = middle;
    else if(parent.right == cur) parent.right = middle;

    if(middle != null) middle.parent = parent;    
  }

  /**
   * Right rotate the tree about a node
   */
  private void rightRotate(Node cur)
  {
    Node parent = cur.parent;
    Node middle = cur.left;

    cur.left = middle.right;
    if(middle.right != null)
      middle.right.parent = cur;

    middle.right = cur;
    cur.parent = middle;

    // update parent, cur, leftChild    
    if(parent == null) this.root = middle;
    else if(parent.left == cur) parent.left = middle;
    else if(parent.right == cur) parent.right = middle;

    if(middle != null) middle.parent = parent;    
  }

  /**
   * returns how many keys are in the tree
   */
  public int size() { return numKeys; }

  /**
   * Returns a List<K> of the keys
   */
  public List<K> keys()
  {
    List<K> keys = new ArrayList<K>(numKeys);
    traverse(root, keys);
    return keys;
  }

  /**
   * performs an in order traversal of the tree
   */
  private void traverse(Node root, List<K> keys)
  {
    if(root == null) return;
    traverse(root.left, keys);
    keys.add(root.key);
    traverse(root.right, keys);
  }

  /**
   * I might look at implementing remove
   * but for now I'm not :)
   */
}
