/**
 * George Prielipp 265112
 * MyGraph.java
 *
 * Implements Graph using HashMap and Adjacency Matrix
 */
import java.util.HashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.NoSuchElementException;

public class MyGraph implements Graph
{
  private int numVertices = 0;
  private HashMap<String, Integer> vertices = new HashMap<>();
  private HashMap<Integer, String> indToVert = new HashMap<>();
  /**
   * edges is an nxn matrix storing if a connection is present
   * row goes to col
   */
  private ArrayList<BitSet> edges = new ArrayList<BitSet>();
 
  /** Returns a list of all vertices in this graph. */
  public List<String> vertices() { return new ArrayList<String>(vertices.keySet()); }

  /** Returns a list of all outgoing edges from the give source node.
   * @throws NoSuchElementException if source does not exist.
   */
  public List<String> neighbors(String source) throws NoSuchElementException
  {
    if(!vertices.containsKey(source)) throw new NoSuchElementException(source + " is not a valid vertex");
    int sourceInd = vertices.get(source);

    List<String> neighbors = new ArrayList<>();
    
    for(int i = 0; i < numVertices; i++)
    {
      if(edges.get(sourceInd).get(i))
      {
        neighbors.add(indToVert.get(i));
      }
    }

    return neighbors;
  }

  /** Returns true if an edge from source to dest exists.
   * @throws NoSuchElementException if source or dest nodes do not exist.
   */
  public boolean getEdge(String source, String dest) throws NoSuchElementException
  {
    if(!vertices.containsKey(source) || !vertices.containsKey(dest)) 
      throw new NoSuchElementException("invalid vertex given to getEdge()");

    int srcInd = vertices.get(source);
    int dstInd = vertices.get(dest);

    return edges.get(srcInd).get(dstInd);
  }

  /** Adds a new vertex to the graph, if it doesn't already exist.
   * No error if a vertex with that name exists already.
   */
  public void addVertex(String label)
  {
    // no need to do anything new
    if(vertices.containsKey(label)) return;
    
    vertices.put(label, numVertices);
    indToVert.put(numVertices, label);

    numVertices++;

    BitSet newSet = new BitSet(numVertices);
    newSet.clear();
    
    edges.add(newSet);
  }

  /** Adds or removes an edge from the graph.
   *
   * If weight is true, the edge should be added if it doesn't already exist.
   * If weight is false, the edge should be removed if it does exist.
   *
   * @throws NoSuchElementException if source or dest vertex names don't exist.
   */
  public void putEdge(String source, String dest, boolean weight) throws NoSuchElementException
  {
    if(!vertices.containsKey(source) || !vertices.containsKey(dest)) 
      throw new NoSuchElementException("invalid vertex given to putEdge()");
    
    int srcInd = vertices.get(source);
    int dstInd = vertices.get(dest);
    if (weight)
      edges.get(srcInd).set(dstInd);
    else
      edges.get(srcInd).clear(dstInd);
  }
}
