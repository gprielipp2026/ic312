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
import javax.swing.JOptionPane;
import javax.swing.JFrame;

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
    // look at the insert text field
    try {
      if(!insert.getText().equals("insert"))
      {
        value = Integer.parseInt(insert.getText());
        cmd = InputOptions.Commands.INSERT;
        found = true;
        insert.setText("insert");
      } 
    } 
    catch(NumberFormatException e1)
    {
      JOptionPane.showMessageDialog(new JFrame(),
          e1.getMessage() + " is not an integer",
          "Integer parse error",
          JOptionPane.ERROR_MESSAGE);
    }
    // look at the search text field
    try {
      if(!search.getText().equals("search"))
      {
        value = Integer.parseInt(search.getText());
        cmd = InputOptions.Commands.SEARCH;
        if (found) 
        { 
          /* do some bad error */ 
          JOptionPane.showMessageDialog(new JFrame(), 
              "Only one function can be used at a time", 
              "Too many arguments error", 
              JOptionPane.ERROR_MESSAGE); 
          search.setText("search");
          return; 
        }
        found = true;
        search.setText("search");
      } 
    }
    catch (NumberFormatException e2) 
    {
      JOptionPane.showMessageDialog(new JFrame(),
          e2.getMessage() + " is not an integer",
          "Integer parse error",
          JOptionPane.ERROR_MESSAGE);
    } 
    // look at the remove text field
    try {
      if(!remove.getText().equals("remove"))
      {
        value = Integer.parseInt(remove.getText());
        cmd = InputOptions.Commands.REMOVE;
        if (found) 
        { 
          /* do some bad error */ 
          JOptionPane.showMessageDialog(new JFrame(), 
              "Only one function can be used at a time", 
              "Too many arguments error", 
              JOptionPane.ERROR_MESSAGE); 
          remove.setText("remove");
          return; 
        }
        found = true;
        remove.setText("remove");
      } 
    }
    catch (NumberFormatException e3) 
    {
      JOptionPane.showMessageDialog(new JFrame(),
          e3.getMessage() + " is not an integer",
          "Integer parse error",
          JOptionPane.ERROR_MESSAGE);
    } 

    if (found) 
    {
      options.update(cmd, value);
      // trigger some event
      dispatcher.dispatch(InputEvent.from(options));
    }
  }
}
