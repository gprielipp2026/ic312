/**
 * George Prielipp 265112
 * VisualRBTree.java
 *
 * Visualization and implementation of a
 * Red-Black tree
 */ 

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;

import java.util.Stack;

public class VisualRBTree extends JPanel implements ActionListener
{ 
  private class Executer implements ActionListener
  {
    private VisualRBTree tree;
    public Executer(VisualRBTree t) { tree = t; }
    public void actionPerformed(ActionEvent e)
    {
      InputOptions opts = ((InputEvent)e).getData();
      this.execute(opts.getCommand(), opts.getValue());   
    }

    private void execute(InputOptions.Commands cmd, Integer val)
    {
      switch(cmd)
      {
        case SEARCH:
          break;
        case INSERT:
          tree.insert(val);
          break;
        case REMOVE:
          break;
        case NULL:
          break;
      }
    }
  }


   private DrawingFrame canvas;
   private Executer executer;

   public VisualRBTree(DrawingFrame df, EventDispatcher<InputEvent> dispatcher) 
   { 
     canvas = df; 
     executer = new Executer(this);
     dispatcher.subscribe(this.executer);
   }

   /**
   * Main structure for holding RBTree
   */
  private class Node 
  {
    private enum Color { BLACK, RED };
    private Color color = Color.RED;
    private Node left;
    private Node right;
    private Node parent;
    private Integer value;
    private int x = 0;
    private int y = 0;

    public Node(Node l, Node r, Integer v)
    {
      parent = null;
      left = l;
      right = r;
      value = v;
    }

    public void paint(Graphics g)
    {
      Graphics2D g2 = (Graphics2D) g;
      java.awt.Color c = (this.isBlack() ? java.awt.Color.BLACK : java.awt.Color.RED);
      g2.setBackground(c);
      g2.draw(new Ellipse2D.Double(this.x, this.y, 20, 20));
    } 

    public Node uncle()
    {
      if (this.isLeftOf(this.parent))
        return this.parent.right;
      else
        return this.parent.left;
    }

    public boolean isBlack() { return color == Color.BLACK; }
    public boolean isRed() { return color == Color.RED; }

    public boolean isLeftOf(Node n)
    {
      return n.left == this;
    }

    public boolean isRightOf(Node n)
    {
      return n.right == this;
    }

    public void draw()
    {
      // need to figure this out still
      System.out.println("draw: " + value);
    }

    public void highlight()
    {
      // need to figure this out still
      System.out.println("highlight: " + value);
    }
  }

  /**
   * To be held by the function and interpreted later
   */
  private class Arguments
  {
    private Node cur = null;
    private Integer value = null;

    public Arguments(Node c, Integer v) { cur = c; value = v; }
  }


  /**
   * callstack is used to slow down the tree and only update/call
   * functions when a TickEvent is generated
   */
  private Stack<Function<Arguments>> callstack = new Stack<>();
  private Node root = null;


  /**
   * Use binary search tree insertion
   * then fix any issues
   */
  public void insert(Integer value)
  {
    // do the function
    root = insert(root, value);
    // then animate it
    callstack.push( new Function<Arguments>("insert", new Arguments(root, value)) );
  }

  /**
   * Actually put the value in the tree
   */
  private Node insert(Node root, Integer value)
  {
    if(root == null)
    {
      return new Node(null, null, value);
    }

    if(value < root.value)
    {
      root.left = insert(root.left, value);
      root.left.parent = root.left;
    }
    else if (value > root.value)
    {
      root.right = insert(root.right, value);
      root.right.parent = root.right;
    }
    else
    {
      // do nothing, value in tree already
    }
    return root;
  }

  /**
   * function that animates insertion
   */ 
  private void insert(Arguments args)
  {
    Node cur = args.cur;
    Integer value = args.value;

    if(value < cur.value)
    {
      cur.highlight();
      callstack.push( new Function<Arguments>("insert", new Arguments(cur.left, value)) );
    }
    else if (value > cur.value)
    {
     cur.highlight();
     callstack.push( new Function<Arguments>("insert", new Arguments(cur.right, value)) );
    }
    else
    {
      cur.draw();
      cur.highlight();
      fixAfterInsertion(cur);
      callstack.push( new Function<Arguments>("rebalance", new Arguments(cur, null)) );
    }
  }

  /**
   * visualize the rebalancing
   */
  private void rebalance(Arguments args)
  {
    Node cur = args.cur;
      
    cur.draw();
    cur.highlight();

    if( cur.parent == null ) return;
    else
      callstack.push( new Function<Arguments>("rebalance", new Arguments(cur.parent, null)) );
    
  }

  /**
   * left rotate at the given node
   */
  private void leftRotate(Node x)
  {
    Node y = x.right;
    x.right = y.left;
    if(y.left != null)
    {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null)
    {
      this.root = y;
    }
    else if(x.isLeftOf(x.parent)){
      x.parent.left = y;
    }
    else
    {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  /**
   * right rotate at the given node
   */
  private void rightRotate(Node x)
  {
    Node y = x.left;
    x.left = y.right;
    if(y.right != null)
    {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null)
    {
      this.root = y;
    }
    else if(x.isRightOf(x.parent)){
      x.parent.right = y;
    }
    else
    {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  /**
   * Fix any violations that happened after inserting
   */
  private void fixAfterInsertion(Node cur)
  {
    if(cur.parent == null) return; // hit the root
                                   
    // case 1: recoloring and propagating upwards
    if(cur.parent.isRed() && cur.uncle().isRed())
    {
      cur.parent.color = Node.Color.BLACK;
      cur.uncle().color = Node.Color.BLACK;
      cur.parent.parent.color = Node.Color.RED;
      fixAfterInsertion(cur.parent.parent);
    }
    // case 2: rotation and recoloring
    else if (cur.uncle().isBlack())
    {
      // left right
      if(cur.isRightOf(cur.parent) && cur.parent.isLeftOf(cur.parent.parent))
      {
        leftRotate(cur.parent);
        rightRotate(cur.parent.parent);
      }
      // right left
      else if (cur.isLeftOf(cur.parent) && cur.parent.isRightOf(cur.parent.parent))
      {
        rightRotate(cur.parent);
        leftRotate(cur.parent.parent);
      } 
      // left left
      else if(cur.isLeftOf(cur.parent) && cur.parent.isLeftOf(cur.parent.parent))
      {
        rightRotate(cur.parent.parent);
      
        Node.Color pColor = cur.parent.color;
        cur.parent.color = cur.parent.parent.color ;
        cur.parent.parent.color = pColor;
      }
      // right right
      else
      {
        leftRotate(cur.parent.parent);

        Node.Color pColor = cur.parent.color;
        cur.parent.color = cur.parent.parent.color ;
        cur.parent.parent.color = pColor;
      }
    }
  }

  /**
   * convert the RBTree to a string
   */
  public String toString()
  {
    String out = "[";
    traverse(root, out);
    // is going to have an extra ", "
    out += "]";
    return out;
  }

  /**
   * helper method to convert RBTree to a string
   */
  private void traverse(Node cur, String out)
  {
    if(cur == null) return;
    traverse(cur.left, out);
    out += String.valueOf(cur.value) + ", "; 
    traverse(cur.right, out);
  }

  /**
   * pop function off callstack and execute it
   * send out any necessary notifications to update the display
   */
  public void actionPerformed(ActionEvent event)
  {
    if (callstack.isEmpty()) return;

    Function<Arguments> func = callstack.pop();

    // lookup the function
    String cmd = func.getName();
    
    // call it with arguments  
    if(cmd.equals("insert"))
    {
      insert(func.getArguments());
    }
    else if(cmd.equals("rebalance"))
    {
      rebalance(func.getArguments());
    }
  }
}
