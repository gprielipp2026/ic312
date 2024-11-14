/**
 * George Prielipp 265112
 * App.java
 *
 * Main window the user interacts with
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class App extends JPanel
{
  private UserInputs usrInputs;

  public App()
  {
    super();

    setPreferredSize(new Dimension(600, 400));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    usrInputs = new UserInputs(600, 50, null);

    add(usrInputs);
  }

  public static void main(String[] args)
  {
    JFrame win = new JFrame();
    win.add(new App());
    
    win.addWindowListener(new ShutdownListener());

    win.pack();
    win.setVisible(true);
  }
}
