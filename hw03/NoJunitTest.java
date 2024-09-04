//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//import static org.junit.Assert.assertThat;
//
//import static org.hamcrest.CoreMatcher.equalTo;
//import static org.hamcrest.CoreMatcher.not;
//
//import org.junit.Test;

public class NoJunitTest{
  //@Test
  public void size0get()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();
    try {
      list.get(0);
      System.out.println("no error thrown");
    } catch(Throwable t)
    {
      System.out.println("error thrown");
    }
    //IndexOutOfBoundsException thrown = assertThrows(
    //IndexOutOfBoundsException.class,
    //() -> list.get(0)
    //);
  } 
  //@Test
  public void size0addInvalidandValid()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    //IndexOutOfBoundsException thrown = assertThrows(
    //IndexOutOfBoundsException.class,
    //() -> list.add(1, 1)
    //);
    try {
      list.add(1,1);
      System.out.println("no error thrown");
    } catch(Throwable t)
    {
      System.out.println("error thrown");
    }
 
    list.add(0, 10); 
    if(list.get(0) != 10) System.out.println("add and get failed");
    if(list.size() != 1) System.out.println("failed size"); 
    //assertThat(list.get(0), equalTo(10));
    //assertThat(list.size(), equalTo(1));
  } 
  //@Test
  public void size0remove()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();

    //IndexOutOfBoundsException thrown = assertThrows(
    //IndexOutOfBoundsException.class,
    //() -> list.remove(0)
    //);
    try {
      list.remove(0);
      System.out.println("no error thrown");
    } catch(Throwable t)
    {
      System.out.println("error thrown");
    }
 
    list.add(0, 10);
    list.remove(0);
    //assertThat(list.size(), equalTo(0));
    if(list.size() != 0) System.out.println("size not zero");
  }

  //@Test
  public void normalUsage()
  {
    LinkedList<Integer> list = new LinkedList<Integer>();
    System.out.println();
    System.out.println("Testing normal usage:");
    list.add(0, 0);
    list.add(0, 1);
    list.add(1, 2);
    list.add(2, 3);
    list.add(1, 4);
    list.add(0, 5);
    list.add(5, 6);

    //assertThat(list.size(), equalTo(7));
    if(list.size() != 7) System.out.println("failed adding");

    int count = 0;
    for(int i = 0; i < list.size(); i++)
    {
      try {
        list.get(i);
      } catch (Throwable t)
      {
        System.out.println("Get(" + i + ") failed");
      }
      if (i % 2 == 1) {
        list.set(i, -2);
        count ++;
      }
    }
    System.out.println("After setting odd indices");
    list.print();
    //assertThat(list.penultimate(), not(equalTo(-2)));
    System.out.println("pen = " + list.penultimate());
    if(list.penultimate() != -2) System.out.println("Failed set or penultimate");

    list.removeAll(-2);
    System.out.println("After removeall");
    list.print();
    //assertThat(list.size(), equalTo(7-count));
    if(list.size() != (7-count)) System.out.println("removeall failed");


    int cursize = list.size();
    System.out.println("Removing " + (cursize/2) + " value=" + list.get(cursize/2));
    list.remove(cursize/2);
    list.print();
    //assertThat(list.size(), equalTo(cursize-1));
    if(list.size() != cursize - 1) System.out.println("Failed remove");
  }

  public static void main(String[] args)
  {
    NoJunitTest tst = new NoJunitTest();
    tst.size0get();
    tst.size0addInvalidandValid();
    tst.size0remove();
    tst.normalUsage();
  }

}
