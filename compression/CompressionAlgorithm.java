import java.util.LinkedList;
import java.util.Queue;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.channels.ByteChannel;

public class CompressionAlgorithm 
{
  protected OutputStream os;
  protected InputStream is;
  private Queue<Integer> bytes = new LinkedList<>();

  public CompressionAlgorithm(InputStream is, OutputStream os)
  {
    this.is = is;
    this.os = os;
  }

  public int nextByte() throws IOException
  {
    if(bytes.isEmpty()) return is.read();
    else return bytes.remove();
  }

  public void write(int val) throws IOException { os.write(val); os.flush(); }
  public void write(String val) throws IOException { os.write(val.getBytes()); os.flush(); }

  // is.available() isn't guaranteed to be > 0 if there are still bytes to read
  public boolean maybeHasNext() throws IOException { return is.available() > 0; }

  public boolean hasNext() 
  {
    try {
      bytes.add(nextByte());     
      return true;
    } catch(Throwable e ) {
      return false;
    }
  }

  public void decompress() throws CompressionException, IOException
  {
    while(maybeHasNext())
    {
      write(nextByte());
    } 
  }   

  public void compress() throws CompressionException, IOException 
  {
    while(maybeHasNext())
    {
      write(nextByte());
    } 
  }
}
