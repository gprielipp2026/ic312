/**
 * George Prielipp 265112
 * Function.java
 *
 * Has function string name that can be interpreted as a "command" 
 * and the arguments required for the function
 */

import java.util.List;
import java.util.ArrayList;

public class Function<T>
{
  private String command;
//  private List<T> arguments = new ArrayList<>();
  private T arguments;

  public Function(String name) { command = name; }
  public Function(String name, T arg) { command = name; arguments = arg; }

  public String getName() { return command; }

  public T getArguments() { return arguments; }
}
