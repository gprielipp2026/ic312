import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatcher.equalTo;
import static org.hamcrest.CoreMatcher.not;

import org.junit.Test;

public class LinkedListTest {
  @Test
  public void size0get()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    IndexOutOfBoundsException thrown = assertThrows(
        IndexOutOfBoundsException.class,
        () -> list.get(0)
        );
  } 
  @Test
  public void size0addInvalidandValid()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    IndexOutOfBoundsException thrown = assertThrows(
        IndexOutOfBoundsException.class,
        () -> list.add(1, 1)
        );
 
    list.add(0, 10); 
    assertThat(list.get(0), equalTo(10));
    assertThat(list.size(), equalTo(1));
  } 
  @Test
  public void size0remove()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    IndexOutOfBoundsException thrown = assertThrows(
        IndexOutOfBoundsException.class,
        () -> list.remove(0)
        );

    list.add(0, 10);
    list.remove(0);
    assertThat(list.size(), equalTo(0));
  }

  @Test
  public void normalUsage()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.add(0, 0);
    list.add(0, 1);
    list.add(1, 2);
    list.add(2, 3);
    list.add(1, 4);
    list.add(0, 5);
    list.add(5, 6);

    assertThat(list.size(), equalTo(7));

    int count = 0;
    for(int i = 0; i < 7; i++)
    {
      if (i % 2 == 1) {
        list.set(i, -2);
        count ++;
      }
    }

    assertThat(list.penultimate(), not(equalTo(-2)));
    
    list.removeAll(-2);
    assertThat(list.size(), equalTo(7-count));

    int cursize = list.size();
    list.remove(cursize/2);
    assertThat(list.size(), equalTo(cursize-1));
  }

}
