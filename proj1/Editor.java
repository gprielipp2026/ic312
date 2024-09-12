import java.util.Scanner;
import java.io.StringReader;

public class Editor {
  private Text txt = new MyText();
  private BoundedStack<Action> undoStack = new MyBoundedStack<Action>(100);
  private BoundedStack<Action> redoStack = new MyBoundedStack<Action>(100);

  /** Displays information on available commands.
   * THis will NOT be part of any autotesting.
   * You can (and should!) update as new commands are enabled.
   */
  public static void showHelp() {
    // Note to students: we won't test this function with autotesting.
    // You should keep it up to date if you add new commands, but
    // the exact wording etc. is up to you!
    System.out.println("HELP");
    System.out.println("  iX   insert letter X before the cursor");
    System.out.println("  d    delete letter at current position, then move cursor right");
    System.out.println("  <    move cursor left");
    System.out.println("  >    move cursor right");
    System.out.println("  p    print the entire txt on one line, with the cursor on the next line");
    System.out.println("  h    show this help message");
    System.out.println("  q    quit");
  }

  /** A simple helper function to display if anything goes wrong.
   * Do NOT modify this to make more descriptive error messages,
   * as that will break the autotests.
   */
  public static void showError() {
    System.out.println("ERROR");
  }

  /**
   * Using the undoStack, compute the opposite action
   * then process it
   */
  private boolean undo()
  {
    if(undoStack.isEmpty()) return false;
    Action a = undoStack.pop();
    redoStack.push(a);
    process(a.computeOpposite());
    return true;
  }

  /**
   * using the redoStack,
   * it will "undo" an undo
   */
  private boolean redo()
  {
    if(redoStack.isEmpty()) return false;
    Action a = redoStack.pop();
    undoStack.push(a);
    //System.out.println("redo => processLine(" + a.getCommand() + ")");
    processLine(a.getCommand(), false);
    return true;
  }

  /**
   * Processes an action
   */
  private void process(String commands)
  {
    if(commands.charAt(0) == 'i')
    {
      // only because it wasn't handling spaces
      processLine(commands, false);
      return;
    }
    Scanner inStream = new Scanner(new StringReader(commands));
    while(inStream.hasNext())
    {
      String command = inStream.next();
      //System.out.println("processLine(" + command + ")");
      processLine(command, false);
    }
  }

  /** Runs the command specified by the given input line.
   * @return true if the command was not "quit".
   */
  public boolean processLine(String line, boolean pushUndo) {
    if (line.length() == 0) line = "h";
    
    switch (line.charAt(0)) {
      case 'i':
        if (line.length() == 2)
        {
          txt.insert(line.charAt(1));
          if (pushUndo)
          {
            undoStack.push(new Action(line,'"'));
            redoStack.clear();
          } 
        }

        else showError();
        break;
      case 'd':
        if (txt.canMoveRight())
        {
          char deleted = txt.get();
          txt.delete();
          if (pushUndo)
          {
            undoStack.push(new Action(line, deleted));
            redoStack.clear();
          }

        }  
        else showError();
        break;
      case '<':
        if (txt.canMoveLeft())
          txt.moveLeft();
        else showError();
        if (pushUndo)
        {
          undoStack.push(new Action(line,'"'));
          redoStack.clear();
        }
        break;
      case '>':
        if (txt.canMoveRight())
          txt.moveRight();
        else showError();
        if (pushUndo)
        {
          undoStack.push(new Action(line,'"'));
          redoStack.clear();
        }
        break;
      case 'p':
        txt.print();
        redoStack.clear();
        break;
      case 'q':
        return false;
      case 'u':
        if(!undo()) showError(); 
        break;
      case 'c':
        int capacity = Integer.parseInt(line.substring(1));
        undoStack.setCapacity(capacity);
        redoStack.setCapacity(capacity);
        redoStack.clear();
        break; 
      case 'r':
        if(!redo()) showError();
        break;
      default:
        showHelp();
    }
    // remove after done debugging text
    //txt.print();

    return true;
  }

  public static void main(String[] args) {
    Editor editor = new Editor();
    Scanner in = new Scanner(System.in);
    java.io.Console cons = System.console();
    do {
      if (cons != null) {
        // Only print the "command: " prompt if the output is to a
        // live terminal window (so it won't show up in autotesting).
        cons.printf("command: ");
        cons.flush();
      }
    } while (editor.processLine(in.nextLine(), true));
  }
}
