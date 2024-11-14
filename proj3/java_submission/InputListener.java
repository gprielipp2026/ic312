/**
 * George Prielipp 265112
 * InputListener.java
 *
 * Attached to all input fields in the App,
 * Listens for EnterEvent's and TypingEvent's
 * Interacts with RBTree
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class InputListener implements ActionListener
{
  private JTextField insert;
  private JTextField remove;
  private JTextField search;
  private InputOptions options;
  private EventDispatcher<InputEvent> dispatcher;

  public InputListener(JTextField i, JTextField r, JTextField s, InputOptions o)
  {
    insert = i;
    remove = r;
    search = s;
    options = o;
  }

  public void setDispatcher(EventDispatcher<InputEvent> dispatcher)
  {
    this.dispatcher = dispatcher;
  }

  /**
   * updates options and trigger event
   */
  public void actionPerformed(ActionEvent e)
  {
    // look at each of the text fields
    Integer value = null;
    boolean found = false;
    InputOptions.Commands cmd = InputOptions.Commands.NULL;
    try {
      value = Integer.parseInt(insert.getText());
      cmd = InputOptions.Commands.INSERT;
      found = true;
      insert.setText("insert");
    } 
    catch (Exception e1) {} 
    try {
      value = Integer.parseInt(search.getText());
      cmd = InputOptions.Commands.SEARCH;
      if (found) { /* do some bad error */ }
      found = true;
      search.setText("search");
    }
    catch (Exception e2) {} 
    try {
      value = Integer.parseInt(remove.getText());
      cmd = InputOptions.Commands.REMOVE;
      if (found) { /* do some bad error */ }
      found = true;
      remove.setText("remove");
    }
    catch (Exception e3) {} 
    
    if (found) 
    {
      options.update(cmd, value);
      // trigger some event
      System.out.println(options);
      //dispatcher.dispatch(new InputEvent.from(options));
    }
  }
}
