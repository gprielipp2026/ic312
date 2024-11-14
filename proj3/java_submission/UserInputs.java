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
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class UserInputs extends JPanel
{
  private JTextField insert;
  private JTextField remove;
  private JTextField search;
  private JTextField fps;
  private int fpsval = 12;
  private JSlider slider;
  private JButton enter;
  //private Clock clock; // generates TickEvents based on fpsval
  private InputOptions options; // will be a command and a value; ex: SEARCH 10
  private EventDispatcher<InputEvent> dispatcher;

  public UserInputs(int pwidth, int pheight, EventDispatcher<InputEvent> dispatcher)
  {
    super();

    this.dispatcher = dispatcher; 
    
    this.insert = new JTextField("insert");
    this.remove = new JTextField("remove");
    this.search = new JTextField("search");
    JLabel fpsLabel = new JLabel("FPS:");    
    this.fps    = new JTextField(String.valueOf(this.fpsval));
    this.options = new InputOptions();
    // for when enter is pressed on keyboard or the button, update options
    InputListener inputListener = new InputListener(insert, remove, search, options);
    inputListener.setDispatcher(this.dispatcher);
    // document listener, removes input from all other text fields when one changes
    //TypingListener typingListener = new TypingListener(); 
    
    // add listeners   
    //this.insert.getDocument.addDocumentListener(typingListener); 
    this.insert.addActionListener(inputListener); 
    this.remove.addActionListener(inputListener); 
    this.search.addActionListener(inputListener); 

    this.slider = new JSlider(0, 30, this.fpsval);
    SliderListener sliderListener = new SliderListener(this.fps, this.slider);
    this.fps.addActionListener(sliderListener); 
    
    this.slider.setMajorTickSpacing(5);
    this.slider.setMinorTickSpacing(1);
    this.slider.setPaintLabels(true);
    this.slider.setPaintTicks(true);
    this.slider.setSnapToTicks(true);    
    this.slider.setPaintTrack(true);
    this.slider.addChangeListener(sliderListener);
    

    this.enter  = new JButton("enter");
    this.enter.addActionListener(inputListener);

    setSize(new Dimension(pwidth, pheight));
    
    //                       rows, col, hgap, wgap 
    setLayout(new GridLayout(   2,    3,  10,   10));
    add(insert);
    add(remove);
    
    JPanel fpsPanel = new JPanel();
    fpsPanel.setLayout(new BoxLayout(fpsPanel, BoxLayout.X_AXIS));
    fpsPanel.add(fpsLabel);
    fpsPanel.add(this.fps);
    
    add(fpsPanel);
    
    add(search);
    add(enter );
    add(slider);
  }
}
