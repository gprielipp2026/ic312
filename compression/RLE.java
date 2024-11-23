import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class RLE extends CompressionAlgorithm
{
  public RLE(InputStream is, OutputStream os)
  {
    super(is, os);
  }

  @Override
  public void compress() throws CompressionException, IOException
  {
    int prevByte = nextByte();
    int run = 1;
    while(hasNext())
    {
      int curByte = nextByte();
      if(curByte == prevByte) run++;
      else
      {
        write("(" + run + "," + prevByte + ")");
        run = 1;
      }
     prevByte = curByte; 
    }
  }

  @Override
  public void decompress() throws CompressionException, IOException
  {
    int numBytesRead = 0;
    int N = -1;
    int x = -1;
    while(hasNext())
    {
      int curByte = nextByte();
      if(curByte < 0) break;
      switch(numBytesRead % 5)
      {
        case 0:
          if (curByte != '(')
            throw new CompressionException("expected '(' but got '" + (char)(curByte) + "'");
          break;
        case 1:
          N = curByte - '0';
          break;
        case 2:
          if (curByte != ',')
            throw new CompressionException("expected ',' but got '" + (char)(curByte) + "'");
          break;
        case 3:
          x = curByte;
          break;
        case 4:
          if (curByte != ')')
            throw new CompressionException("expected ')' but got '" + (char)(curByte) + "'");
          writeN(x,N);
          break;
      }
      numBytesRead++;
    }
  }

  private void writeN(int x, int N) throws CompressionException, IOException
  {
    while(N-- > 0)
      write(x);
  }
}
