import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class MyTest
{
  private AddMax create() { return new DoubleTree(); }

  //@Test
  public void addElements()
  {
    AddMax am = create();
    for(int i = 0; i < 5; i++)
    {
      am.add(1.0*i);
      am.print();
    }
  }

  @Test
  public void addRandomElementsAndRemove()
  {
    AddMax tree = create();
    Random rand = new Random(0);
    LinkedList<Double> test = new LinkedList<Double>();

    for(int i = 10; i > 0; i--)
    {
      Double num = 1.0*rand.nextInt(100);
      tree.add(num); 
      test.add(num);
      tree.print();
    }

    // ascending order
    test.sort(null);

    boolean control = true;
    while(control)
    {
      try{
        assertEquals(new Double(test.removeLast()), new Double(tree.removeMax()));
      } catch (NoSuchElementException e)
      {
        control = false;
      }
    }
  }
}
