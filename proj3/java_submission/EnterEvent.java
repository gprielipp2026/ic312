/**
 * George Prielipp 265112
 * EnterEvent.java
 * 
 * Anytime the "enter" key is pressed 
 * or the enter button is pressed
 * this event will be created and sent
 */

import java.awt.event.ActionEvent;

public class EnterEvent extends ActionEvent
{
    public EnterEvent (Object source, int id, String command)
    {
        super(source, id, command);
    }
}
