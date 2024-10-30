/**
 * George Prielipp 265112
 * TSV Reader that utilizes java.nio
 */

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class FastReader implements Iterable<Map<String, String>>
{
  private String filename;

  public FastReader(String filename)
  {
    this.filename = filename;
  }
  
  /**
   * Returns iterator where the key is the column heading
   * and the value is the content at that column/line intersection
   */
  public Iterator<Map<String, String>> iterator()
  {
    try { return new Iter(); }
    catch(FileNotFoundException e)
    {
      throw new IllegalArgumentException("file not found: " + filename);
    }  
  }

  /**
   * Iterator
   * actually parses the file
   */
  private class Iter implements Iterator<Map<String, String>>
  {
    private MappedByteBuffer buffer; // the file straight up in memory
    private List<String> headings;
    //private Charset charset;

    // opens the file and reads the headings line in
    public Iter() throws FileNotFoundException
    {
      // load file into memory
      RandomAccessFile fd = new RandomAccessFile(new File(filename), "r");
      FileChannel file = fd.getChannel();
      // I do not think loading the whole file into memory is necessarily intelligent
      try {
        buffer = file.map(FileChannel.MapMode.READ_ONLY, 0, file.size());
        buffer.load();
      } catch (IOException e) {}
      if(!buffer.isLoaded())
      {
        // most likely failed
        throw new FileNotFoundException("file could not load - " + filename);
      }


      //charset = Charset.forName("UTF-8");
      headings = readLine('\t'); 
    }

    /**
     * Reads a char by char, delimiting strings by @param
     * returns a List<String>
     */
    private List<String> readLine(char delim)
    {
      List<String> delimLine = new ArrayList<String>();
      
      char curChar = buffer.getChar();
      String curStr = ""; 

      while(curChar != '\n' && !(buffer.remaining() == 0))
      {
        curStr = new String();
        // build string until delimiter
        while(curChar != delim)
        {
          curStr += curChar;
          curChar = buffer.getChar();
        }
        // add built string to list
        delimLine.add(curStr);
        curChar = buffer.getChar();
      } 

      if(curStr.length() > 0)
        delimLine.add(curStr);

      return delimLine;
    } 

    public boolean hasNext() 
    {
      // aka str list is empty
      return buffer.remaining() > 0; 
    }

    public Map<String, String> next() 
    {
      Map<String, String> map = new RBTreeMap<>();
      List<String> line = readLine('\t');
      Iterator<String> keys = headings.iterator();
      Iterator<String> vals = line.iterator();

      while(keys.hasNext() && vals.hasNext())
        map.put(keys.next(), vals.next());
     
      return map; 
    }
  }
}
