import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test; 

import java.util.*;

public class BoundedStackTest
{
  @Test
  public void normalUse()
  {
    Random rand = new Random();
    BoundedStack<Integer> stack = new MyBoundedStack<Integer>(10);
    Stack<Integer> tstack = new Stack<Integer>();

    for(int i = 10 + rand.nextInt(20); i > 0; i--) 
    {
      int v = rand.nextInt(200);
      //System.out.println("Adding: " + v);

      stack.push(v);
      tstack.push(v);
    }

    while(!stack.isEmpty())
    {
      assertEquals("failure - stacks not the same", stack.pop(), tstack.pop());
    }
  } 

  @Test
  public void testFill()
  {
    BoundedStack<Integer> stack = new MyBoundedStack<Integer>(10);
    fill(stack, 10);
    Stack<Integer> tstack = new Stack<Integer>();
    fill(tstack, 10);

    testSame(stack, tstack);
  }

  @Test
  public void emptyStack()
  {
    try { new MyBoundedStack<Integer>(5).clear(); }// should be okay
    catch(Throwable t) { t.printStackTrace(); }
    try { new MyBoundedStack<Integer>(5).isEmpty(); } // should be okay
    catch(Throwable t) { t.printStackTrace(); }
    try { new MyBoundedStack<Integer>(5).pop(); System.out.println("failure - empty stack pop"); } // this should error
    catch(Throwable t) { }
    try { new MyBoundedStack<Integer>(5).push(1); }// should be ok
    catch(Throwable t) { t.printStackTrace(); }
    try { new MyBoundedStack<Integer>(5).setCapacity(1); }// should be ok
    catch(Throwable t) { t.printStackTrace(); }
  }

  private static void fill(BoundedStack<Integer> stack, int N)
  {
    Random rand = new Random(2003);
    while(N-- > 0)
    {
      int v = rand.nextInt(100);
      //System.out.println("MS: adding - " + v);
      stack.push(v);
    }  
  }

  private static void fill(Stack<Integer> stack, int N)
  {
    Random rand = new Random(2003);
    while(N-- > 0)
    {
      int v = rand.nextInt(100);
      //System.out.println("JS: adding - " + v);
      stack.push(v);
    } 
  }

  private static void testSame(BoundedStack<Integer> stack, Stack<Integer> tstack )
  {
    while(!stack.isEmpty())
      assertEquals("failure - stacks not the same", tstack.pop(), stack.pop());
  }

  @Test
  public void setCapacity()
  {
    BoundedStack<Integer> stack = new MyBoundedStack<Integer>(5);
    Stack<Integer> tstack = new Stack<Integer>();
    fill(stack, 5);
    fill(tstack, 5);
    testSame(stack, tstack);

    //System.out.println();

    fill(stack, 5);
    fill(tstack, 5);
    // new > old size 
    stack.setCapacity(10);
    testSame(stack, tstack);
    
    //System.out.println();

    // new < old size
    fill(stack, 10);
    fill(tstack, 10);
    stack.setCapacity(3);
    testSame(stack, tstack); 
    
    //System.out.println();

    // try size = 0
    stack.setCapacity(0);
    testSame(stack, tstack);
  }
}
