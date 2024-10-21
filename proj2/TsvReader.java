import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
  * TsvReader is designed to ease the reading of the .tsv files from the
  * ARCOS Washington Post dataset.  Rather than forcing you to go line by line,
  * it converts each line into a Map, where the key is the column heading, and
  * the value is the contents of that column on that line.
  *
  * TsvReader is Iterable so that it will provide you these Maps one by one.
  */
public class TsvReader implements Iterable<Map<String, String>> {
  private String filename;

  /** Builds the reader to read from the given file
    * @param filename : the file to read from - needs to be an ARCOS .tsv file,
    * or you'll get errors.
    */
  public TsvReader(String filename) {
    this.filename = filename;
  }

  /** Returns an Iterator, which will give you one Map at a time, where the key
    * of the Map is a column heading, and the value is the contents of the cell
    * at that column, in that line.
    *
    * @throws IllegalArgumentException if the filename the object was build with
    * does not exist.  God only knows what it throws if it's not an ARCOS file.
    */
  @Override
  public Iterator<Map<String,String>> iterator() {
    try { return new Iter(); }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found: " + filename);
    }
  }

  private class Iter implements Iterator<Map<String, String>> {
    private BufferedReader file;
    private List<String> headings;
    private String curLine = null;

    public Iter() throws FileNotFoundException {
      file = new BufferedReader(new java.io.FileReader(filename));
      headings = new java.util.ArrayList<>();
      for (String heading : peekLine().split("\t"))
        headings.add(heading);
      curLine = null;
    }

    private String peekLine() {
      if (curLine == null) {
        try { curLine = file.readLine(); }
        catch (java.io.IOException e) { }
        if (curLine != null) curLine = curLine.trim();
      }
      return curLine;
    }

    public boolean hasNext() {
      return peekLine() != null;
    }

    public Map<String,String> next() {
      Map<String,String> map = new TreeMap<>(); // TODO replace with a new instance of your map!
      String[] parts = peekLine().split("\t");
      for (int i = 0; i < headings.size() && i < parts.length; i++)
        map.put(headings.get(i), parts[i]);
      curLine = null;
      return map;
    }
  }
}
