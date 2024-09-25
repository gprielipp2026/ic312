import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test; 

import java.util.*;

public class TestMap
{

  private static <K extends Comparable<K>, V> void printMap(BSTMap<K, V> map)
  {
    Deque<K> keys = map.traverse();
    System.out.println();
    System.out.println("Map has " + map.size() + " keys");
    System.out.println("Deque has " + keys.size() + " elements");
    for(K key : keys) 
    {
      System.out.println(key);
    }
    assertEquals("map traverse failed", map.size(), keys.size());
  }

  @Test
  public void size4put()
  {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();
    
    map.put("1000", 1000);
    map.put("0100", 100);
    map.put("2003", 2003);
    map.put("0050", 50);

    TestMap.printMap(map);
  }

  @Test
  public void leftSide_size3traverse()
  {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();
    map.put("300", 300);
    map.put("200", 200);
    map.put("100", 100);

    TestMap.printMap(map);
  }
 
  @Test
  public void rightSide_size3traverse()
  {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();
    
    map.put("100", 100);
    map.put("200", 200);
    map.put("300", 300);

    TestMap.printMap(map);
  }
 
  @Test 
  public void equalSize_size3traverse()
  {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();

    map.put("200", 200);
    map.put("300", 300);
    map.put("100", 100);

    TestMap.printMap(map);
  }

  @Test
  public void remove2()
  {
    BSTMap<String, Integer> map = new BSTMap<String, Integer>();
    
    map.put("1000", 1000);
    map.put("0050", 50);
    map.put("0100", 100);
    map.put("2003", 2003);
    map.put("1003", 2003);
    map.put("3003", 2003);
    map.put("2006", 2003);

    TestMap.printMap(map);

    map.remove("1000");
    map.remove("0050");
  
    TestMap.printMap(map);
  }
}
