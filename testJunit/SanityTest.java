import org.junit.Test;
import static org.junit.Assert.*;

public class SanityTest {
  @Test
  public void extendsList() {
    assertTrue(List.class.isAssignableFrom(LinkedList.class));

  }

  @Test
  public void size1add() {
    List<String> lst = new LinkedList<>();
    lst.add(0, "ren");
    assertEquals(1, lst.size());
    assertEquals("ren", lst.get(0));

  }

  @Test
  public void size1set() {
    List<String> lst = new LinkedList<>();
    lst.add(0, "ren");
    lst.set(0, "stimpy");
    assertEquals("stimpy", lst.get(0));

  }

  @Test
  public void size1remove() {
    List<String> lst = new LinkedList<>();
    lst.add(0, "stimpy");
    lst.remove(0);
    assertEquals(0, lst.size());

  }

  @Test
  public void size3removeAll() {
    LinkedList<Integer> lst = new LinkedList<>();
    lst.add(0, 10);
    lst.add(1, 20);
    lst.add(2, 10);
    lst.removeAll(10);
    assertEquals(1, lst.size());
    assertEquals(20, (int)lst.get(0));

  }

  @Test
  public void size4penultimate() {
    LinkedList<String> lst = new LinkedList<>();
    lst.add(0, "zero");
    lst.add(1, "one");
    lst.add(2, "two");
    lst.add(3, "three");
    assertEquals("two", lst.penultimate());

  }

}

