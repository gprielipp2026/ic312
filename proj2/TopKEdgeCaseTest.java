import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.*;

public class TopKEdgeCaseTest {
  private static Random rand;
  private static long seed;
  private static Random getRand() 
  {
    if(rand == null)
    {
      seed = System.currentTimeMillis() % 65535;
      rand = new Random(seed);
    }

    System.out.println("seed = " + seed);
    return rand;
  }  

  @Test
  public void fewerAddsThanK()
  {
    Random rand = getRand();
    int k = rand.nextInt(1000);
    List<Integer> list = new ArrayList<>();
    TopK<Integer> topk = new TopK<>(k);

    for(int i = 0; i < k/(rand.nextInt(10)+2); i++)
    {
      Integer val = rand.nextInt(1000);
      list.add(val);
      topk.add(val); 
    }
    Collections.sort(list, Collections.reverseOrder());

    List<Integer> actual = topk.getTop();
    for(int i = 0; i < actual.size(); i++)
    {
      assertEquals(list.get(i), actual.get(i));
    }
  }

  @Test
  public void moreThanKAdds()
  {
    Random rand = getRand();
    int k = rand.nextInt(1000);
    List<Integer> list = new ArrayList<>();
    TopK<Integer> topk = new TopK<>(k);

    for(int i = 0; i < k*(rand.nextInt(10)+2); i++)
    {
      Integer val = rand.nextInt(1000);
      list.add(val);
      topk.add(val); 
    }
    Collections.sort(list, Collections.reverseOrder());

    List<Integer> actual = topk.getTop();
    for(int i = 0; i < actual.size(); i++)
    {
      assertEquals(list.get(i), actual.get(i));
    }
  }

  @Test
  public void size0toSize100()
  {
    Random rand = getRand();
    List<Integer> list = new ArrayList<>();
    TopK<Integer> topk;
    for(int k = 0; k <= 100; k++)
    {
      //System.out.println("testing k = " + "'" + k + "'");
      topk = new TopK<Integer>(k);
      for(int i = 0; i < k+rand.nextInt(10); i++)
      {
        Integer val = rand.nextInt(1000);
        topk.add(val);
        list.add(val);
      } 
      Collections.sort(list, Collections.reverseOrder());
      List<Integer> actual = topk.getTop();
      for(int i = 0; i < k; i++)
      {
        assertEquals(list.get(i), actual.get(i));
      }
      list.clear();
    }
  }

  @Test
  public void size1()
  {
    TopK<Integer> topk = new TopK<>(1);
    Integer t = 20;
    topk.add(10);
    topk.add(20);
    //System.out.println("little value first");
    assertEquals(t, topk.getTop().get(0));

    topk = new TopK<Integer>(1);
    topk.add(20);
    topk.add(10);
    //System.out.println("big value first");
    assertEquals(t, topk.getTop().get(0));
  }

  //@Test
  public void specificTesting()
  {
    //System.out.println("specific testing:");
    int badk = 1;
    Random rand = new Random(60391);
    List<Integer> list = new ArrayList<>();
    TopK<Integer> topk;
    for(int k = 0; k <= 100; k++)
    {
      //System.out.println("\ntesting k = " + "'" + k + "'");
      topk = new TopK<Integer>(k);
      for(int i = 0; i < k+rand.nextInt(10); i++)
      {
        Integer val = rand.nextInt(1000);
        if(k == badk)
          //System.out.println("adding '" + val + "'");

          topk.add(val);
        list.add(val);
      } 
      Collections.sort(list, Collections.reverseOrder());
      List<Integer> actual = topk.getTop();
      for(int i = 0; i < k; i++)
      {
        if(k != badk)
          assertEquals(list.get(i), actual.get(i));
        else {}
        //System.out.print(list.get(i) + "\t===\t" + actual.get(i));
      }
      if(k == badk) {}
      //System.out.println();
      list.clear();
    }
  }

}

