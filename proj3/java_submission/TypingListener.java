/**
 * George Prielpp 265112
 * TypingListener.java
 *
 * Listens for TypingEvents
 * and triggers the approriate response in the app
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TypingListener implements ActionListener
{

  public void actionPerformed(ActionEvent e)
  {
    System.out.println("TypingListener: " + e);
  }

}
