/** Program to read in ARCOS data and report how many pills were
 * sold by each pharmacy.
 * The results should be sorted in alphabetical order by pharmacy name.
 * The pharmacy name is equal to the BUYER_NAME field, or, if the
 * BUYER_ADDL_CO_INFO field is not "null", then the pharmacy name
 * should be BUYER_NAME and BUYER_ADDL_CO_INFO, separated by one space.
 */
public class Pharmacies {
  public static void main(String[] args) {
    // read the name of the pills file from command line or the console
    String pillsFile=null;
    if (args.length >= 1) pillsFile = args[0];
    if (pillsFile == null)
      pillsFile = System.console().readLine("pills file: ");

    TsvReader pillsLines = new TsvReader(pillsFile);

    // TODO you write the rest!

    // To print the output, use a line like this.
    // Remember the output lines should be sorted alphabetically by
    // pharmacy name.
    // System.out.format("%8d %s\n", pillCount, pharmacyName);
 
    Map<String, Integer> buyerCount = new TreeMap<>();
    
    // parses the file
    for(Map<String,String> line : pillsLines)
    {
      // get the buyer name
      String addl  = line.get("BUYER_ADDL_CO_INFO");
      String buyer = line.get("BUYER_NAME") + (addl.equals("null") ? "":(" " + addl)); 
    
      // get the current count and the count from the line
      Integer count = buyerCount.get(buyer);
      Double dosageUnit = Double.parseDouble(line.get("DOSAGE_UNIT"));
      // update the current count 
      Integer updatedCount = (count == null ? 0:count) + (int)Math.ceil(dosageUnit);//+ intify(dosageUnit);

      // will overwrite value at buyer if it exists already
      buyerCount.put(buyer, updatedCount);
    }


    // print the formatted information out to the screen
    for(String pharmacyName : buyerCount.keys())
    {
      Integer pillCount = buyerCount.get(pharmacyName);
      System.out.format("%8d %s\n", pillCount, pharmacyName);
    }
  }

  /**
   * I realized it does the same thing as (int)Math.ceil()
   * 
   * Takes a Double
   * if it has any decimal portion:
   *    then Math.ceil()
   */
  private static Integer intify(Double val)
  {
    Integer intValue = val.intValue();
    if(intValue < val ) 
    {
      return intValue + 1;
    }
    return intValue;
  }
}
