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
  private Clock clock; // generates TickEvents based on fpsval
  private InputOptions options; // will be a command and a value; ex: SEARCH 10
  private EventDispatcher<InputEvent> dispatcher;

  public UserInputs(int pwidth, int pheight, EventDispatcher<InputEvent> dispatcher, Clock clock)
  {
    super();

    this.dispatcher = dispatcher; 
    this.clock = clock;
    this.clock.updateFPS(this.fpsval);

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
    SliderListener sliderListener = new SliderListener(this.fps, this.slider, this.clock);
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
    setLayout(new GridLayout(   1,    3,  10,   10));
    //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    JPanel col1 = new JPanel();
    col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));
    col1.add(insert);
    col1.add(search);


    JPanel col2 = new JPanel();
    col2.setLayout(new BoxLayout(col2, BoxLayout.Y_AXIS));
    col2.add(remove);
    col2.add(enter);


    JPanel fpsPanel = new JPanel();
    fpsPanel.setLayout(new BoxLayout(fpsPanel, BoxLayout.X_AXIS));
    fpsPanel.add(fpsLabel);
    fpsPanel.add(this.fps);


    JPanel col3 = new JPanel();
    col3.setLayout(new BoxLayout(col3, BoxLayout.Y_AXIS));
    col3.add(fpsPanel);
    col3.add(slider);    

    
    add(col1);
    add(col2);
    add(col3);
  }
}
