/**
 * George Prielipp (265112)
 * MyBoundedStack.java
 *
 * Implements the BoundedStack<T> interface
 * Utilizes a circular array
 */

/**
 * try to keep track of a "size" <-- number of elements in the stack
 */

import java.util.NoSuchElementException;

public class MyBoundedStack<T> implements BoundedStack<T> {
  private T[] stack;
  private int capacity;
  private int top;
  private int bottom;
  private int numElements;
  /**
   * constructor requires an initial size
   */
  public MyBoundedStack(int capacity)
  {
    this.capacity = capacity;
    this.stack = makeStack(this.capacity);
    this.top = this.bottom = 0;
    this.numElements = 0;
  }

  /**
   * performs mod math to increase the top
   * and modify the bottom if the top passes the bottom
   */
  private void increment()
  {
    if(numElements < capacity) numElements++;

    top = (top + 1) % capacity;
    // see if top is 1 behind bottom
    if(top % capacity == bottom)
    {
      // the bottom should always remain 1 ahead once capacity is reached
      bottom = (bottom + 1) % capacity;
    }
  }

  /**
   * performs mod math to decrease the top
   */
  private void decrement()
  {
    if(numElements > 0) numElements--;

    if(top - 1 < 0)
    {
      // only because Java does weird mod math
      top = capacity - 1;
    }
    else
    {
      top = (top - 1) % capacity;
    }
  }

  /** Adds a new element to the top of the stack.
   *
   * If the stack is already at its capacity, a single item is
   * removed from the BOTTOM of the stack.
   */
  public void push(T item)
  {
    stack[top] = item;
    increment();
  }

  /** Removes and returns the element at the top of the stack.
   * @throws NoSuchElementException if the stack is empty.
   */
  public T pop() throws NoSuchElementException
  {
    if( isEmpty() ) throw new NoSuchElementException();
    decrement();
    T data = stack[top];
    stack[top] = null;
    return data;
  }

  /** Changes the capacity to the given value.
   *
   * If the current capacity is greater than the given capacity,
   * then elements may need to be removed from the BOTTOM of the
   * stack so that it reaches the desired capacity.
   *
   * This affects all future push/pop operations, but not past
   * ones. That is, increasing the capacity does not make
   * the stack magically "remember" things which have already been
   * removed.
   */
  public void setCapacity(int capacity)
  {
    T[] newStack = makeStack(capacity);
    
    // I was making this to hard math-wise
    int i;
    for(i = capacity-1; i >= 0 && !isEmpty(); i--)
    {
      T data = pop();
      newStack[i] = data;   
    } 
    // need to figure out the top and bottom
    this.top = 0; // because next thing should be capacity + 1 % capacity
    this.bottom = i+1; 
    this.stack = newStack;
    this.capacity = capacity; 
  }

  /**
   * creates a new T[] 
   */
  private T[] makeStack(int size)
  {
    return (T[])(new Object[size]);
  }

  /** Returns whether the stack is currently empty. */
  public boolean isEmpty() { numElements == 0; } 

  /** Removes all elements from the stack.
   *
   * The capacity should remain unchanged.
   */
  public void clear()
  {
    top = bottom;
  }

}
