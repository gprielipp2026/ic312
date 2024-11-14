/**
 * George Prielipp 265112
 * DrawingFrame.java
 *
 * Provides a scrollable canvas for the 
 * VisualRBTree to display its data
 */

import javax.swing.JScrollPane;
import java.awt.Dimension;

public class DrawingFrame extends JScrollPane
{
  public DrawingFrame(int pwidth, int pheight)
  {
    super();

    setPreferredSize(new Dimension(pwidth, pheight));
  }
}
