import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class RBTreeMapTest 
{
  @Test
  public void empty()
  {
    Map<String, Double> map = new RBTreeMap<>();
    assertEquals(0, map.size());
    assertNull(map.get("anything"));
    assertFalse(map.containsKey("some key"));
    assertTrue(map.keys().isEmpty());
  }

  @Test
  public void sizeOne()
  {
    Map<Integer, String> map = new RBTreeMap<>();
    map.put(20, "value");
    assertEquals(1, map.size());
    assertTrue(map.containsKey(20));
    assertFalse(map.containsKey(21));
    assertNull(map.get(0));
    assertEquals("value", map.get(20));
    List<Integer> keys = map.keys();
    assertEquals(1, keys.size());
    assertEquals(20, (int)keys.get(0));
  }

  @Test
  public void sizeTen() {
    Map<Integer,Integer> map = new TreeMap<>();
    map.put(73, 36);
    map.put(5, 45);
    map.put(16, 89);
    map.put(54, 49);
    map.put(52, 53);
    map.put(15, 18);
    map.put(79, 95);
    map.put(32, 51);
    map.put(1, 13);
    map.put(35, 0);
    assertEquals(10, map.size());
    assertEquals(0, (int)map.get(35));
    assertEquals(36, (int)map.get(73));
    assertEquals(53, (int)map.get(52));
    List<Integer> keys = map.keys();
    for(Integer key : keys)
      System.out.print(key + " ");
    System.out.println();
    java.util.Collections.sort(keys);
    assertEquals(java.util.Arrays.asList(1, 5, 15, 16, 32, 35, 52, 54, 73, 79), keys);
  }

  @Test
  public void randomInserts() {
    Map<Double, Integer> map = new TreeMap<>();
    HashMap<Double, Integer> testMap = new HashMap<>();
    Random rand = new Random(1234);
   

    for(int i = 0; i < 1000000; i++)
    {
      Double key = rand.nextDouble() * 10000;
      Integer value = rand.nextInt(1000000);

      map.put(key, value);
      testMap.put(key, value);
    } 

    for(Double key : map.keys())
    {
      Integer value = testMap.remove(key);
      assertEquals(value, map.get(key));
    }

    assertEquals(0, testMap.size());
  }

}
