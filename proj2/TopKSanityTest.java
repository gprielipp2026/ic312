import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.*;

public class TopKSanityTest {
  @Test
  public void add4get3() {
    TopK<String> tt = new TopK<>(3);
    tt.add("what");
    //tt.print();
    tt.add("is");
    //tt.print();
    tt.add("the");
    //tt.print();
    tt.add("what");
    //tt.print();
    List<String> top = tt.getTop();
    //for(String x : top)
    //System.out.print(x + " ");
    //System.out.println();
    assertEquals(3, top.size());
    assertEquals("what", top.get(0));
    assertEquals("what", top.get(1));
    assertEquals("the", top.get(2));
  }

  @Test
  public void randomAddMoreThanK()
  {
    long seed = System.currentTimeMillis();
    System.out.println("seed=" + seed);
    
    Random rand = new Random(seed);
    int k = rand.nextInt(20)+20;
    System.out.println("k=" + k);
    TopK<Integer> tt = new TopK<>(k);
    List<Integer> test = new ArrayList<>();

    for(int i = 0; i < k*2; i++)
    {
      int x = rand.nextInt(1000);
      tt.add(x);
      test.add(x);

      //tt.print();
    }

    Collections.sort(test, Collections.reverseOrder());

    List<Integer> actual = tt.getTop();
    for(int i = 0; i < k; i++)
    {
      //System.out.println(i + ":\t" + test.get(i) + "\t" + actual.get(i));
      assertEquals(test.get(i), actual.get(i));
    }

  }

  @Test
  public void add3get5() {
    TopK<Integer> tt = new TopK<>(5);
    tt.add(5);
    //tt.print();
    tt.add(10);
    //tt.print();
    tt.add(2);
    //tt.print();
    List<Integer> top = tt.getTop();
    //for(Integer x : top)
    //System.out.print(x + " ");
    //System.out.println();

    assertEquals(3, top.size());
    assertEquals(10, (int)top.get(0));
    assertEquals(5, (int)top.get(1));
    assertEquals(2, (int)top.get(2));
  }
}
