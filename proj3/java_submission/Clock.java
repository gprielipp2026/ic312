/**
 * George Prielipp 265112
 * Clock.java
 *
 * Sends out tick events based on a given frame rate
 */

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Clock implements Runnable 
{
  private int fps = 12;
  private long sleepTime = 1000/12;
  private List<ActionListener> listeners = new ArrayList<>();
  private boolean notDone = true;

  public Clock()
  {
    Thread t = new Thread(this);
    t.start(); 
  }

  public void updateFPS(int fps)
  {
    this.fps = fps;
    if (this.fps > 0)
      this.sleepTime = 1000/this.fps;
    else if (this.fps <= 0)
      this.sleepTime = 0;
  }

  public void setDone() { notDone = false; }

  public void subscribe(ActionListener a)
  {
    listeners.add(a);
  }

  public void run()
  {
    while(notDone)
    {
      try {
        Thread.sleep(sleepTime);
        for(ActionListener l : listeners)
        {
          l.actionPerformed(new TickEvent(this, 0, "tick event"));    
        }      

      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
  }
}
