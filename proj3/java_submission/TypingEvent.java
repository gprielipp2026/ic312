/**
 * George Prielipp 265112
 * TypingEvent.java
 *
 * Event that gets sent when a textfield 
 * changes
 */

import java.awt.event.ActionEvent;

public class TypingEvent extends ActionEvent
{
    public TypingEvent(Object source, int id, String command)
    {
        super(source, id, command);
    }
}
