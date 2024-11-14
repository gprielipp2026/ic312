/**
 * George Prielipp 265112
 * ShutdownListener.java
 *
 * Gracefully closes the program
 * Listens to the "x" button that closes the window/app
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

public class ShutdownListener extends WindowAdapter implements ActionListener
{
  public void actionPerformed(ActionEvent e)
  {

  }
}
