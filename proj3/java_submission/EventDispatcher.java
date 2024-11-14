/**
 * George Prielipp 265112
 * EventDispatcher.java
 *
 * Used for sending out events
 * Follows the Observer model
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventDispatcher<E extends ActionEvent> implements Runnable, Consumer<E>
{
  private Queue<E> events = new ConcurrentLinkedQueue<>();
  private List<ActionListener> listeners = new ArrayList<>();
  private Thread worker = null;

  /**
   * Add a listener
   */
  public void subscribe(ActionListener al) { listeners.add(al); }

  /**
   * Start the thread if its null
   * add Event to the queue
   */
  public void dispatch(E event) 
  {
    events.add(event);

    if(worker == null)
    {
      worker = new Thread(this);
      worker.start();
    }
  }

  /**
   * Implementation for Runnable
   * send all events out to the listeners
   */
  public void run()
  {
    events.forEach(this);
  }

  /**
   * consume an event in the queue
   * send it to all listeners
   */
  public void accept(E event)
  {
    for(ActionListener listener : listeners)
      listener.actionPerformed(event);
  }
}
