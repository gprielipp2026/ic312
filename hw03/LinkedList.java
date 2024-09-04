// YOUR NAME HERE
// ANY SOURCES OR COLLABORATION YOU USED HERE

import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
  // TODO your private inner classes and fields...
  private int length = 0;
  private class Node {
    private T data;
    private Node next; 

    public Node(T data, Node next) { this.data = data; this.next = next; }
  }
  private Node head = null;

  @Override
  public T get(int index) throws IndexOutOfBoundsException {
    if(index < 0 || index > length - 1) throw new IndexOutOfBoundsException();
    return get_r(head, index);
  }

  private T get_r(Node cur, int index)
  {
    if( index == 0 ) return cur.data;
    return get_r(cur.next, index-1); 
  }

  @Override
  public void set(int index, T data) throws IndexOutOfBoundsException {
    if(index < 0 || index > length - 1) throw new IndexOutOfBoundsException();
    // have to have elements before you can set them
    if( head == null )
      head = new Node(data, null);
    else
      set_r(head, index, data);
  }

  private void set_r(Node cur, int index, T data) {
    if( index == 0 ) cur.data = data;
    else set_r(cur.next, index - 1, data);
  }

  @Override
  public void add(int index, T data) throws IndexOutOfBoundsException {
    // need to be able to add elements to start
    //System.out.println("add(" + index + ", " + data + ")");
    if(head != null && (index < 0 || index > length - 1)) throw new IndexOutOfBoundsException();
    if( head == null && index == 0)
      head = new Node(data, null);
    else if (head == null && index != 0)
      throw new IndexOutOfBoundsException();
    else  
      head = add_r(head, index, data);
    //print(head);
    length++;
  }

  // for testing
  public void print()
  {
    print(head);
  }
  private void print(Node cur)
  {
    if(cur == null) 
    {
      System.out.println();
      return;
    }
    System.out.print(cur.data + " ");
    print(cur.next);
  }

  private Node add_r(Node cur, int index, T data)
  {
    if(cur == null)
      return new Node(data, null);
    else if( index == 0 )
      return new Node(data, cur);
    else
    {
      cur.next = add_r(cur.next, index-1, data);
      return cur;
    }
  }

  @Override
  public void remove(int index) throws IndexOutOfBoundsException {
    if(index < 0 || index > length - 1) throw new IndexOutOfBoundsException();
    else
      head = remove_r(head, index);
    length--;
  }

  private Node remove_r(Node cur, int index)
  {
    if(index == 0)
      return cur.next;
    else
    {
      cur.next = remove_r(cur.next, index-1); 
      return cur;
    }
  }

  @Override
  public int size() {
    return length;
  }

  private int update_length(Node cur)
  {
    if(cur == null) return 0;
    else return 1 + update_length(cur.next);
  }

  /** Removes ALL elements matching the given one using .equals().
   *
   * @param element The element that should be removed
   */
  public void removeAll(T element) {
    head = removeAll(head, element);
    length = update_length(head);
  }

  private Node removeAll(Node cur, T element)
  {
    if(cur == null)
      return null;
    else if(cur.data == element)
    {
      return removeAll(cur.next, element);
    }
    else
    {
      cur.next = removeAll(cur.next, element);
      return cur;
    }
  }

  /** Gets the 2nd-to-last element.
   *
   * @return The data in the second-to-last node in the list (if any)
   * @throws NoSuchElementException if the list size is less than 2
   */
  public T penultimate() throws NoSuchElementException {
    if(length < 2) throw new NoSuchElementException();
    Node pen = penultimate(head);
    return pen.data;
  }

  private Node penultimate(Node cur)
  {
    if(cur.next.next == null)
      return cur;
    else
      return penultimate(cur.next);
  }
}
