import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FastReaderTest
{
  private void compare(Map<String, String> expected, Map<String, String> actual)
  {
    List<String> eKeys = expected.keys();
    List<String> aKeys = actual.keys();
    Iterator<String> eIt = eKeys.iterator();
    Iterator<String> aIt = aKeys.iterator();
    while(eIt.hasNext() && aIt.hasNext())
    {
      String eStr = eIt.next();
      String aStr = aIt.next();
      System.out.println(eStr + ":\t" + expected.get(eStr));
      System.out.println(aStr + ":\t" + actual.get(aStr));
      assertEquals(eStr, aStr);
      assertEquals(expected.get(eStr), actual.get(aStr));
    }
    assertEquals(false, eIt.hasNext());
    assertEquals(false, aIt.hasNext());
  }

  @Test
  public void sameSmallFile()
  {
    TsvReader tsv = new TsvReader("arcos-annapolis-small.tsv");
    FastReader fast = new FastReader("arcos-annapolis-small.tsv");
  
    Iterator<Map<String, String>> expected = tsv.iterator();
    Iterator<Map<String, String>> actual = fast.iterator();
  
    while(expected.hasNext() && actual.hasNext())
    {
      compare(expected.next(), actual.next());
    }

    assertEquals(false, expected.hasNext());
    assertEquals(false, actual.hasNext());
  }
}
