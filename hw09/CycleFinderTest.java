import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.NoSuchElementException;

public class CycleFinderTest {
  // helper method in case I want to test a different class name
  private Graph create() {
    return new MyGraph();
  }
  private Graph create(String fn)
  {
    return DotReader.readFrom(fn);
  }

  @Rule
  public Timeout globalTimeout = Timeout.seconds(2);
  
  @Test
  public void r100_dot()
  {
    Graph graph = create("r100.dot");

    for(int i = 1; i <= 50; i++)
    {
      assertFalse(CycleFinder.hasCycle(graph, "a" + i));
    }
  
    for(int i = 1; i <= 50; i++)
    {
      assertTrue(CycleFinder.hasCycle(graph, "c" + i));
    }
}

}
