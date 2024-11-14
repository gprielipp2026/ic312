/**
 * George Prielipp 265112
 * UserInputs.java
 *
 * Main panel for taking user inputs/interactions
 * and triggering the appropriate events, as well as
 * interacting with the RBTree
 */

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Dimension;

public class UserInputs extends JPanel
{
  private JTextField insert;
  private JTextField remove;
  private JTextField search;
  private JTextField fps;
  private JSlider slider;
  private JButton enter;

  private EventDispatcher<InputEvent> dispatcher;

  public UserInputs(int pwidth, int pheight, EventDispatcher<InputEvent> dispatcher)
  {
    super();

    this.dispatcher = dispatcher; 
    this.insert = new JTextField("insert");
    this.remove = new JTextField("remove");
    this.search = new JTextField("search");
    this.fps    = new JTextField("FPS");
    this.slider = new JSlider();
    this.enter  = new JButton("enter");

    setPreferredSize(new Dimension(pwidth, pheight));
    
    //                       rows, col, hgap, wgap 
    setLayout(new GridLayout(   2,    3,  10,   10));
    add(insert);
    add(remove);
    add(fps   );
    add(search);
    add(enter );
    add(slider);
  }
}
