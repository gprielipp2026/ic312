/**
 * George Prielipp 265112
 * InputEvent.java
 *
 * Broad characterization for all events that 
 * deal with consuming input
 */

import java.awt.event.ActionEvent;

public class InputEvent extends ActionEvent
{
  public InputEvent(Object source, int id, String command)
  {
    super(source, id, command);
  }
}
