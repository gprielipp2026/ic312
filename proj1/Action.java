/**
 * George Prielipp (265112)
 * Action.java
 *
 * holds a command and beneficial state values
 * can compute the opposite action
 */

public class Action
{
  private char deleted;
  private String command;
  public Action(String command, char iX)
  {
    this.command = command;
    this.deleted = iX;// needed for undoing a 'd'
  }

  public String getCommand() { return command; }

  // Returns the opposite command
  public String computeOpposite()
  {
    String newCmd = "";

    switch(command.charAt(0))
    {
      case 'i':
        newCmd += "< d";
        break;
      case 'd':
        newCmd += "i";
        newCmd += deleted;
        break;
      case '<':
        newCmd = ">";
        break;
      case '>':
        newCmd = "<";
        break; 
    }

    return newCmd;
  }
}
