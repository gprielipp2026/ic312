/**
 * George Prielipp 265112
 * SliderEvent.java
 *
 * Sent out when the slider bar changes
 * Can also be sent out by the FPS field changing and enter being submitted
 */

import javax.swing.event.ChangeEvent;

public class SliderEvent extends ChangeEvent 
{
    public SliderEvent(Object source)
    {
        super(source);
    }
}
