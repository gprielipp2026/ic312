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
  private EventDispatcher<InputEvent> dispatcher;
  private Clock clock;
  private UserInputs usrInputs;
  private DrawingFrame canvas;
  private VisualRBTree tree;
  public App()
  {
    super();

    setSize(new Dimension(600, 400));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    clock = new Clock();
    dispatcher = new EventDispatcher<InputEvent>();
    usrInputs = new UserInputs(600, 50, dispatcher, clock);
    canvas    = new DrawingFrame(600, 350);
    tree = new VisualRBTree(canvas, dispatcher);
    clock.subscribe(tree);

    add(usrInputs);
    add(canvas);
  }

  public static void main(String[] args)
  {
    JFrame win = new JFrame("Red-Black Tree Visualization");
    win.add(new App());
   
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    win.addWindowListener(new ShutdownListener());

    win.pack();
    win.setVisible(true);
  }
}
