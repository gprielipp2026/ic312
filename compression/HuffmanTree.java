import java.util.List;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class HuffmanTree
{
  private static final int LEFT  = 0;
  private static final int RIGHT = 1; 
  private interface Node
  {
    int getFrequency();
    boolean isInternal();
    String getData();
    void setChild(int dir, Node n);
    Node getChild(int dir);
  }
  private static class InternalNode implements Node
  {
    private int frequency;
    private Node children[];
    public InternalNode(Node a, Node b)
    {
      System.out.println("New Internal Node");
      if(a == null || b == null)
        frequency = 0;
      else
        frequency = a.getFrequency() + b.getFrequency();
      children = new Node[2];
      children[LEFT] = a;
      children[RIGHT] = b;
    }
    public void setChild(int dir, Node n)
    {
      children[dir] = n;
    }
    public int  getFrequency() { return frequency; }
    public Node getChild(int dir) { return children[dir]; }
    public String getData() { return null; }
    public boolean isInternal() { return true; }
  }
  private static class LeafNode implements Node
  {
    private int frequency;
    private String data; 
    public LeafNode(int f, String d)
    {
      System.out.println("New Leaf Node <" + d + ">");
      frequency = f;
      data = d;
    }
    public int  getFrequency() { return frequency; }
    public Node getChild(int dir) { return null; }
    public void setChild(int dir, Node n) {}
    public String getData() { return data; }
    public boolean isInternal() { return false; }
  }

  private Node root;
  public HuffmanTree(int freq, String val)
  {
    root = new LeafNode(freq, val);
  }
  public HuffmanTree(Node n) 
  {
    root = n;
  }
  public HuffmanTree()
  {
    root = null;
  }

  public HuffmanTree combine(HuffmanTree a, HuffmanTree b)
  {
    return new HuffmanTree(new InternalNode(a.root, b.root));
  }

  public int getWeight() 
  {
    return root.getFrequency();
  }

  public Map<BitSet, String> createTable()
  {
    Map<BitSet, String> map = new HashMap<>();
    traverse(root, map, "");
    return map;
  }

  private BitSet fromString(String str)
  {
    BitSet set = new BitSet();
    int ind;
    //System.out.print(str + " --> ");
    int strLen = str.length();
    for(ind = 0; ind < strLen; ind++)
    {
      // the string coming in is reversed compared to the actual bits I want to set
      set.set(strLen-ind-1, str.charAt(ind) == '1');
    }
    //System.out.println(set.toString());
    //set.set(ind, true);
    return set;
  }

  public String toString()
  {
    StringBuilder out = new StringBuilder();
    toStringHelper(root, out);
    return out.toString();    
  }

  private void toStringHelper(Node cur, StringBuilder out)
  {
    if(cur.isInternal())
    {
      out.append('0');
      toStringHelper(cur.getChild(LEFT), out);
      toStringHelper(cur.getChild(RIGHT), out);
    }
    else
    {
      out.append('1');
      out.append(cur.getData().toString());
    }
  }

  private class StringIterator implements Iterator<Character>
  {
    private int ind = 0;
    private String val;
    public StringIterator(String v) { val = v; }
    public boolean hasNext() { return ind < val.length(); }
    public Character next() { return val.charAt(ind++); }
    public String toString() { return val.substring(ind); }
  }

  @SuppressWarnings("unchecked")
  private void traverse(Node cur, Map<BitSet, String> map, String postfix)
  {
    if(cur == null) return;
    if(!cur.isInternal())
    {
      System.out.println("leaf node: " + postfix + " --> " + cur.getData());
      map.put(fromString(postfix), cur.getData());      
    }    
    traverse(cur.getChild(LEFT), map, "0" + postfix);
    traverse(cur.getChild(RIGHT), map, "1" + postfix);
  }

  public HuffmanTree treeFromString(String treeRep)
  {
    Node root = populate(null, new StringIterator(treeRep));

    return new HuffmanTree(root);
  }

  private Node populate(Node root, Iterator<Character> rep)
  {
    // no more bits to encode
    if(!rep.hasNext()) return null;

    char c = rep.next();
    System.out.println("read: " + c + "\tremaining: " + rep);
    if(c == '0')
    {
      if(root == null) root = new InternalNode(null, null);
      System.out.println("Setting left child to: ");
      root.setChild(LEFT, populate(root.getChild(LEFT), rep));
      System.out.println("Setting right child to: ");
      root.setChild(RIGHT, populate(root.getChild(RIGHT), rep));
      return root;
    }
    else if(c == '1')
    {
      char val = rep.next();
      System.out.println("read: " + val + "\tremaining: " + rep);
      return new LeafNode(0, String.valueOf(val));
    }

    return null;
  }

  private String BitSet_toString(BitSet set)
  {
    String out = "";

    if(set.length() == 0) out = "0";

    // the bitset is storing bits s.t. set.get(0) == LSB 
    for(int i = 0; i < set.length(); i++)
    {
      if(set.get(i)) out = '1' + out;
      else out = '0' + out;
    }

    return out;
  }

  public void printTable()
  {
    Map<BitSet, String> table = createTable();

    System.out.println("table: ");
    for(BitSet key : table.keySet())
    {
      System.out.println(BitSet_toString(key) + ":\t" + table.get(key));
    }

  }

  // to test using some encdoded strings
  public static void main(String[] args)
  {
    for(String arg : args)
    {
      HuffmanTree tree = (new HuffmanTree()).treeFromString(arg);

      System.out.println("tree = " + tree.toString());

      tree.printTable();

      System.out.println();
    }


    // sample should end up:
    /**
     * 01A001B1C1N
     *
     *        o
     *       / \
     *      A  /\
     *        /\ N
     *       B  C   
     *
     * 0:    A
     * 100:  B
     * 101:  C
     * 11:   N
     */

    HuffmanTree leafs[] = {
      new HuffmanTree(new HuffmanTree.LeafNode(1, "C")),
      new HuffmanTree(new HuffmanTree.LeafNode(2, "N")),
      new HuffmanTree(new HuffmanTree.LeafNode(4, "B")),
      new HuffmanTree(new HuffmanTree.LeafNode(10, "A")),
    }; 

    List<HuffmanTree> trees = new ArrayList<>();
    for(HuffmanTree tree : leafs)
    {
      trees.add(tree);
      System.out.println("Leaf: ");
      System.out.println(tree);
      tree.printTable();
      System.out.println();
    }

    HuffmanTree sample;

    while(trees.size() > 1)
    {
      HuffmanTree min1 = null, min2 = null;

      // find first min
      for(HuffmanTree tree : trees)
      {
        if(min1 == null || tree.getWeight() < min1.getWeight())
          min1 = tree;
      }
      trees.remove(min1); 

      // find second min    
      for(HuffmanTree tree : trees)
      {
        if(min2 == null || tree.getWeight() < min2.getWeight())
          min2 = tree;
      }
      trees.remove(min2); 

      // combine them
      // and put it back into the trees
      HuffmanTree tree = min1.combine(min1, min2);
      System.out.println("Combined: ");
      System.out.println(tree);
      tree.printTable();
      System.out.println();
      trees.add( tree );
    } 
    sample = trees.get(0);

    System.out.println("tree: " + sample.toString());
    sample.printTable();
  }
}
