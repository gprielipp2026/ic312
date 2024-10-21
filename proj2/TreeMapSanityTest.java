import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class TreeMapSanityTest {
  @Test
  public void empty() {
    Map<String,Double> mm = new TreeMap<>();
    assertEquals(0, mm.size());
    assertNull(mm.get("anything"));
    assertFalse(mm.containsKey("anything"));
    assertTrue(mm.keys().isEmpty());
  }

  @Test
  public void sizeOne() {
    Map<Integer,String> mm = new TreeMap<>();
    mm.put(20, "value");
    assertEquals(1, mm.size());
    assertTrue(mm.containsKey(20));
    assertFalse(mm.containsKey(21));
    assertNull(mm.get(0));
    assertEquals("value", mm.get(20));
    List<Integer> keys = mm.keys();
    assertEquals(1, keys.size());
    assertEquals(20, (int)keys.get(0));
  }

  @Test
  public void sizeTen() {
    Map<Integer,Integer> mm = new TreeMap<>();
    mm.put(73, 36);
    mm.put(5, 45);
    mm.put(16, 89);
    mm.put(54, 49);
    mm.put(52, 53);
    mm.put(15, 18);
    mm.put(79, 95);
    mm.put(32, 51);
    mm.put(1, 13);
    mm.put(35, 0);
    assertEquals(10, mm.size());
    assertEquals(0, (int)mm.get(35));
    assertEquals(36, (int)mm.get(73));
    assertEquals(53, (int)mm.get(52));
    List<Integer> keys = mm.keys();
    //for(Integer key : keys)
    //System.out.print(key + " ");
    //System.out.println();
    //java.util.Collections.sort(keys);
    assertEquals(java.util.Arrays.asList(1, 5, 15, 16, 32, 35, 52, 54, 73, 79), keys);
  }
}
