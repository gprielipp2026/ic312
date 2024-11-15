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
  private InputOptions data;

  public InputEvent(Object source, int id, String command)
  {
    super(source, id, command);
  }

  public InputOptions getData() { return data; }

  public String toString()
  {
    return "InputEvent: " + data.toString();
  }

  public static InputEvent from(InputOptions opts)
  {
    InputEvent e = new InputEvent(opts, 0, "input options");
    e.data = opts;
    return e;
  }

}
