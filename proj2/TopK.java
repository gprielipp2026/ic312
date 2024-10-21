import java.util.List;
import java.util.ArrayList;

/** Collects elements and allows one-time selection of the k largest ones.
 *
 * Best implemented with a heap!
 * The current implementation (using a sorted list) should be replaced
 * with something more efficient via heap operations.
 */
public class TopK<T extends Comparable<T>> {
  /** The k value that will be used to select the top k. */
  private int k;
  /** A list to hold the inserted elements. */
  private T[] heap;
  private boolean calledGetTop = false;
  private int numElems = 0;

  /** Create a new, empty instance.
   *
   * @param k How many items to return from a later call to getTop().
   */
  public TopK(int k) {
    this.k = k;
    // Stack Overflow helped me get this line to create the new heap
    @SuppressWarnings("unchecked")
    final T[] e = (T[])new Comparable[k + 1]; 
    this.heap = e;
  }

  /** Adds a new element to the collection.
   * Note that this method should not be called after getTop() has been called.
   */
  public void add(T element) {
    if (calledGetTop)
      throw new IllegalStateException("Can't make any other calls after the first call to getTop().");
    // insert at the bottom of the heap 
    heap[numElems] = element;
    if(numElems <= k) numElems++;
    heapify(heap);

    if(numElems > k) 
    {
      removeMin();
    }
  }

  /**
   * Removes the minimum value from a heap
   */
  private T removeMin()
  {
    T min = heap[0];
    heap[0] = heap[numElems-1];
    heap[numElems-1] = null;
    numElems--;
    bubbledown(heap, 0); 
    return min;
  }

  /**
   * turns an unsorted array into a heap
   */
  private void heapify(T arr[])
  {
    for(int i = numElems-1; i >= 0; i--)
    {
      bubbledown(arr, i);
    }
  }

  public void print()
  {
    for(int i = 0; i < numElems; i++)
      System.out.print(heap[i] + " ");
    System.out.println();
  }

  /**
   * from a given index, 
   * leftChild = 2*index + 1
   * rightChild = 2*index + 2
   * if arr[i] < max(left, right) then swap them 
   * recurse with index = max(left, right).index
   */
  private void bubbledown(T arr[], int i)
  {
    T item = arr[i];
    // I cannot figure out why this is happening
    // so temp fix
    if(item == null) 
    {
      return;
    }
    
    int index;
    while(i < heap.length/2)
    {
      int li = 2 * i + 1;
      int ri = 2 * i + 2;
      if((li > heap.length || arr[li] == null) && ( ri > heap.length || arr[ri] == null)) break; // both children are null
      if(ri < heap.length && arr[ri] != null && arr[li].compareTo(arr[ri]) > 0)
        index = ri; // then ri is smallest child
      else
        index = li; // otherwise li is smallest child
      // original value is less than both children (done)
      if(item.compareTo(arr[index]) <= 0) break; // reached bottom of bubbledown
      // swap
      arr[i] = arr[index];
      i = index;
    }    
    arr[i] = item;
  }

  /**
   * finds the min of the two inputs
   */
  private T min (T a, T b)
  {
    if(a == null) return b;
    if(b == null) return a;
    return (a.compareTo(b) > 0 ? b:a);
  }


  /**
   * finds the max of the two inputs
   */
  private T max(T a, T b)
  {
    if(a == null) return b;
    if(b == null) return a;
    return (a.compareTo(b) > 0 ? a:b);
  }

  /** Retrieves the k largest elements that have been added, from largest to smallest.
   * Note that this method can only be called once.
   * If fewer than k items have been added, then all of them should be returned, sorted
   * from largest to smallest.
   */
  public List<T> getTop() {
    if (calledGetTop)
      throw new IllegalStateException("Can't make any other calls after the first call to getTop().");
    calledGetTop = true;
    // TODO you must change everything below to use a heap instead!
    // The current method of repeatedly removing the max is too inefficient.
    List<T> largest = new ArrayList<>();
    //while (!elements.isEmpty() && largest.size() < k) {
    //int maxInd = 0;
    //for (int i = 0; i < elements.size(); ++i) {
    //if (elements.get(i).compareTo(elements.get(maxInd)) > 0)
    //maxInd = i;
    //}
    //largest.add(elements.remove(maxInd));
    //}
    //elements = null; // can't call any other methods after calling getTop()


    // now O(k) - is this the best I can do?    
    int min = (k < numElems ? k:numElems);
    for(int i = 0; i < min; i++)
    {
      largest.add(removeMin()); // O(1) ammortized
    }
  
    // O(k)
    java.util.Collections.reverse(largest);
   
    return largest;
  }
}
