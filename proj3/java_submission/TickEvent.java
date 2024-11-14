/**
 * George Prielipp 265112
 * TickEvent.java
 *
 * Used to update the VisualRBTree
 * Sent out periodically based on the FPS value
 */

import java.awt.event.ActionEvent;

public class TickEvent extends ActionEvent
{
    public TickEvent(Object source, int id, String command)
    {
        super(source, id, command);
    }
}
