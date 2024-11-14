/**
 * George Prielipp 265112
 * SliderListener.java
 *
 * Listens for SliderEvents
 */

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSlider;

public class SliderListener implements ChangeListener, ActionListener
{
  private JTextField textField;
  private JSlider slider; 

  public SliderListener(JTextField field, JSlider slider)
  {
    this.slider = slider;
    textField = field;
  }
  
  public void stateChanged(ChangeEvent e)
  {
    Integer val = this.slider.getValue();
    textField.setText(val.toString());    
  }

  public void actionPerformed(ActionEvent e)
  {
    try {
      Integer val = Integer.parseInt(textField.getText());
      if( val >= slider.getMinimum() && val <= slider.getMaximum())
      {
        slider.setValue(val);
      }
      else
      {
        /* throw some error here */
        System.out.println("entered value is too big");
      }
    } catch (Exception ex) { /* show some error*/ }
  }
}
