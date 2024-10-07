import java.util.NoSuchElementException;

import java.util.LinkedList;
import java.util.Queue;

/* George Prielipp (265112) 
 * I used class notes 
 */
public class DoubleTree implements AddMax {
  /**
   * class for storing values
   */
  private class Node {
    private double data;
    private Node left;
    private Node right;
    private int height = 0;
    private int bf = 0; // balance factor = right.height - left.height

    public Node(double d, Node l, Node r) {
      data = d;
      left = l;
      right = r;
    }

    public void update()
    {
      height = Math.max((right == null ? -1:right.height), (left == null ? -1:left.height)) + 1;
      bf = (right == null ? -1:right.height) - (left == null ? -1:left.height);
    }

    public boolean isBalanced()
    {
      return Math.abs(bf) <= 1; // 1 or 0
    }

    public String toString()
    {
      return "(data=" + data + ", h=" + height + ", bf=" + bf + ")";
    }
  } 

  /**
   * class fields
   */
  private Node root;

  /**
   * part of the interface
   * cannot exceed O(log n)
   */
  public void add(double x)
  {
    root = insert(root, x); 
  }

  /**
   * method to visualize tree using level order traversal
   */
  public void print()
  {
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);

    int maxNodes = 1;
    int curNodes = 0;
    int level = 1;
    System.out.println();
    System.out.print("1: ");
    while(!queue.isEmpty())
    {
      Node cur = queue.remove();
      if(cur == null) continue;
      System.out.print(cur + " ");
      curNodes++;
    
      if(cur.left != null)
        queue.add(cur.left);
      if(cur.right != null)
        queue.add(cur.right);
    
      if(curNodes == maxNodes && !queue.isEmpty()) {
        System.out.println();
        System.out.print(++level + ": ");
        maxNodes *= 2;
        curNodes = 0;
      }
    }
    System.out.println();
  }

  public double removeMax() throws NoSuchElementException
  {
    if (root == null) throw new NoSuchElementException();
    Node max = getMax(root);
    double val = max.data;
    root = remove(root, max.data);  
    return val;
  }

  // returns the right most node in the tree
  private Node getMax(Node cur)
  {
    if(cur.right == null) return cur;
    return getMax(cur.right);
  }

  /**
   * removes node from the tree 
   */
  private Node remove(Node cur, double key)
  {
    if (cur == null) return null;
    if(key < cur.data)
    {
      cur.left = remove(cur.left, key);
    }
    else if (key > cur.data)
    {
      cur.right = remove(cur.right, key);
    }
    else
    {
      // no children/1 child
      if(cur.left == null)
      {
        return cur.right;
      }
      // 1 child
      else if(cur.right == null)
      {
        return cur.left;
      }
      // 2 children 
      Node successor = getSuccessor(cur.right);
      cur.data = successor.data;
      cur.right = remove(cur.right, successor.data); 
    }
    return cur;
  }

  /** 
   * returns the successor
   */
  private Node getSuccessor(Node cur)
  {
    if(cur.left == null) return cur;
    return getSuccessor(cur.left);
  }

  /**
   * Put values into the AVL tree
   */
  private Node insert(Node cur, double x)
  {
    // recurse down to the leaf nodes
    if(cur == null)
    {
      return new Node(x, null, null);
    }
    // comparisons would change if this became generic
    else if(cur.data < x)
    {
      cur.right = insert(cur.right, x);
    }
    else if(cur.data > x)
    {
      cur.left = insert(cur.left, x);
    }
    else
    {
      // not sure what to do here 
      // x == cur.data
    }

    // update the tree values
    // and balance as necessary

    cur.update();
    cur = rebalance(cur);
    cur.update();

    return cur;
  }

  /**
   * Use AVL rules to rebalance the tree in 
   * O(1)
   */ 
  private Node rebalance(Node cur)
  {
    if(cur.bf == -2) // left leaning
    {
      if(cur.left != null && cur.left.bf == 1) // double rotation
      {
        cur.left = leftRotate(cur.left);
      }
      return rightRotate(cur);
    }
    else if(cur.bf == 2) // right leaning
    {
      if(cur.right != null && cur.right.bf == -1) // double rotation
      {
        cur.right = rightRotate(cur.right);
      }
      return leftRotate(cur);
    } 
    return cur;
  }

  private Node leftRotate(Node oldRoot)
  {
    Node newRoot = oldRoot.right;
    Node middle = (newRoot == null ? null:newRoot.left);
    newRoot.left = oldRoot;
    oldRoot.right = middle;

    if(newRoot.left != null) newRoot.left.update();
    if(newRoot.right!= null) newRoot.right.update();
    newRoot.update();

    return newRoot;
  }

  private Node rightRotate(Node oldRoot)
  {
    Node newRoot = oldRoot.left;
    Node middle = (newRoot == null ? null:newRoot.right);
    newRoot.right = oldRoot;
    oldRoot.left = middle;

    if(newRoot.left != null) newRoot.left.update();
    if(newRoot.right!= null) newRoot.right.update();
    newRoot.update();

    return newRoot;

  }
}
