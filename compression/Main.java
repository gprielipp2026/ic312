import java.io.*;

public class Main
{
  private static CompressionAlgorithm lookup(String alg, InputStream is)
  {
    if(alg.equals("RLE")) return new RLE(is, System.out);

    return new CompressionAlgorithm(System.in, System.out);
  }

  private static void printAlgorithms()
  {
    System.out.println("Algorithms:");
    System.out.println("\t- RLE");
  }

  public static void main(String[] args) throws IOException, CompressionException
  {
    InputStream is;
    OutputStream os;
    CompressionAlgorithm ca = null;
    boolean infLoop = true;
    boolean isDecomp = false;
    if(args.length == 0)
    {
      ca = new CompressionAlgorithm(System.in, System.out);
    }
    else if(args.length == 1)
    {
      ca = lookup(args[0], System.in);
    }
    else if(args.length == 2)
    {
      ca = lookup(args[0], new ByteArrayInputStream(args[1].getBytes()));
      infLoop = false;
    }
    else if(args.length == 3)
    {
      ca = lookup(args[0], new ByteArrayInputStream(args[2].getBytes()));
      infLoop = false;
      if(args[1].equals("-d")) isDecomp = true; 
    }
    else
    {
      System.out.println("usage: java Main");
      System.out.println("usage: java Main <algorithm>");
      System.out.println("usage: java Main <algorithm> <input string>");
      System.out.println("usage: java Main <algorithm> -d <input string>");
      printAlgorithms();
      System.exit(0);
    }
    while(infLoop)
    {
      ca.compress();
    }

    if(isDecomp) ca.decompress();
    else ca.compress();
  }

}
