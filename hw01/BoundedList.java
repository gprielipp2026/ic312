import java.util.*;

/** A List implementation with fixed, bounded capacity.
 *
 * When an instance of this class is constructed, the chosen capacity
 * represents the maximum size the list can ever grow to.
 */
public class BoundedList<T> implements Iterable<T>, List<T> {
  private T[] elements;
  private int currentSize = 0; // keeps track of how many elements are in list

  /**
   * helps determine if index is valid to access elements effectively
   */
  private boolean isWithinBounds(int index)
  {
    return index >= 0 && index < currentSize;
  }

  /**
   * Shifts elements in list right from a given index
   */
  private void shiftElementsRightFrom(int index)
  {
    // should not be able to enter this if currentSize >= maxSize 
    for(int i = currentSize; i > index; i--)
    {
      // obviously can't copy anything from the left of index 0
      if(i == 0) continue;

      elements[i] = elements[i - 1];
    }
    elements[index] = null;    
  }

  /**
   * Shifts elements in list left from a given index
   */
  private void shiftElementsLeftFrom(int index)
  {
    // just overwrite everything
    for(int i = index; i < currentSize; i++)
    {
      if(i+1 >= elements.length)
      {
        elements[i] = null;
        break;
      }
      elements[i] = elements[i+1];
    }
  }

  /** Create a new BoundedList with the given maximum capacity.
  */
  public BoundedList(int capacity) {
    @SuppressWarnings("unchecked")
    T[] elements = (T[]) new Object[capacity];
    this.elements = elements;
  }

  /**
   * Retrieves an elements from a given index
   */
  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if(index < 0) throw new IndexOutOfBoundsException("negative index");
    else if(!isWithinBounds(index)) 
      throw new IndexOutOfBoundsException("outside the bounds of the fixedList at size " + String.valueOf(elements.length));
    T item = elements[index];
    return item;
  }

  /**
   * Sets a number at an index
   */
  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if(index < 0) throw new IndexOutOfBoundsException("negative index");
    else if(!isWithinBounds(index)) 
      throw new IndexOutOfBoundsException("outside the bounds of the fixedList at size " + String.valueOf(elements.length));
    elements[index] = data;
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException, IllegalStateException {
    if(index < 0) throw new IndexOutOfBoundsException("negative index");
    else if(!(index == currentSize) && !isWithinBounds(index)) 
      throw new IndexOutOfBoundsException("outside the bounds of the fixedList at size " + String.valueOf(elements.length));
    else if(index > currentSize && currentSize != 0) 
      throw new IndexOutOfBoundsException("adding more than +" + String.valueOf(index - currentSize) + " the current size");
    else if(currentSize == elements.length) 
      throw new IllegalStateException("list is full");

    // see if the spot is full
    if(elements[index] != null)
    {
      // shift elements so spot is empty
      shiftElementsRightFrom(index);
    }

    // add new element to spot
    elements[index] = data;

    // increment currentSize
    currentSize++;
  }

  /**
   * Ensure list is pushed as far left as possible
   * ex: [1,2,3,4,5,6]  =>  remove(2) =>  [1,2,4,5,6,_]
   * 
   * check bounds before operating
   */
  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    // validate index
    if(index < 0) throw new IndexOutOfBoundsException("negative index");
    else if(!isWithinBounds(index)) 
      throw new IndexOutOfBoundsException("outside the bounds of the fixedList at size " + String.valueOf(elements.length));
    else if(index > currentSize) 
      throw new IndexOutOfBoundsException("removing more than +" + String.valueOf(index - currentSize) + " the current size");
    else if(currentSize == 0) 
      throw new IndexOutOfBoundsException("list is empty");

    // get element at index
    //T element = elements[index];

    // shift it all left
    shiftElementsLeftFrom(index);

    // decrement currentSize
    currentSize--;

    // return element
    //return element;
  }

  @Override
  public int size() {
    return currentSize;
  }

  @Override
  // this produces a string like "[ 1 2 3 4 ]"
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size(); ++i) {
      sb.append(get(i).toString());
      sb.append(' ');
    }
    sb.append(']');
    return sb.toString();
  }

  // code to make it iterable
  @Override
  public Iterator<T> iterator()
  {
    return new Iter();
  }

  private class Iter implements Iterator<T>
  {
    int curInd = 0; 
    public boolean hasNext() { return curInd < currentSize; }
    public T next() { return get(curInd++); }
  }

  /**
   * Main method to write all of the test cases
   */
  public static void main(String[] args)
  {
    // need to test all methods @
    // empty
    // one element
    // partially full
    // completely full
    // bad indices

    List<Integer> list = new BoundedList<Integer>(30);
    list.add(0, 0);
    list.add(1, 1);
    list.add(2, 2);
    list.add(3, 3);
    System.out.print(list.size() + " " );
    System.out.println(list); 
    list.remove(list.size()-2);
    System.out.print(list.size() + " " );
    System.out.println(list);
    list.remove(1);
    System.out.print(list.size() + " " );
    System.out.println(list);
    for(int i = 0; i < 30; i++)
    {
      System.out.println(i + " " + list.get(i));
    }
  }
}
