import java.util.LinkedList; // as a Queue
import java.util.ArrayList;  // as the Path
import java.util.HashSet;    // as a Set
import java.util.List;
import java.util.NoSuchElementException;

public class CycleFinder {
  public static boolean hasCycle(Graph graph, String start) {
    // add, contains
    HashSet<String> visited = new HashSet<>();
    // addLast, removeFirst, size
    LinkedList<List<String>> queue = new LinkedList<>();

    try{
      queue.addLast(graph.neighbors(start));
    } catch (NoSuchElementException e)
    {
      System.out.println(e.getMessage());
      return false;
    }
    visited.add(start);

    // bfs
    while(queue.size() != 0)
    {
      if(queue.get(0).size() != 0)
      {
        String node = queue.get(0).remove(0);
        if(!visited.contains(node))
        {
          visited.add(node);
          ArrayList<String> neighbors = new ArrayList<>();
          for(String n : graph.neighbors(node))
          {
            if (n.equals(start)) return true;
            neighbors.add(n); 
          } 
          queue.addLast(neighbors);
        }
      }
      else
        queue.removeFirst();
    }

    return false;
  }

  public static void main(String[] args) {
    // XXX you shouldn't need to change anything here
    String dotf = Stdin.input("graph file: ");
    Graph graph = DotReader.readFrom(dotf);

    while (true) {
      String start = Stdin.input("starting node or 'quit': ");
      if (start == null || start.equals("quit")) break;
      if (hasCycle(graph, start))
        System.out.format("%s is in a cycle.\n", start);
      else
        System.out.format("%s is NOT in a cycle.\n", start);
    }
  }
}
