/**
 * George Prielipp 265112
 * InputOptions.java
 *
 * contains a command and value to execute
 */

public class InputOptions
{
  public static enum Commands { SEARCH, INSERT, REMOVE, NULL };
  private Commands command;
  // I am now deciding tree can only hold ints
  private Integer value;

  public void update(Commands c, Integer v) { command = c; value = v; }
  public Commands getCommand() { return command; }
  public Integer getValue() { return value; }

  public String toString()
  {
    String out = "";

    switch(command)
    {
      case SEARCH:
        out += "SEARCH";
        break;
      case INSERT:
        out += "INSERT";
        break;
      case REMOVE:
        out += "REMOVE";
        break;
      case NULL:
        out += "NULL";
        break;
    }

    out += " " + value;

    return out;
  }
}
