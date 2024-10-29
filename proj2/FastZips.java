/** Program to identify which zip codes had the highest per-capity
 * number of opioid pills sold.
 * Input will be two filenames (for ARCOS data and zip code data)
 * and an integer k.
 * The k zipcodes with the highest pills/population ratios should be
 * printed out.
 */
public class FastZips {
  /**
   * I might be able to make the reading of data from the TSV faster by having making a new "TSV" Reader
   * that puts stuff directly into a "CityInfo" class. Then accesses of what was previously the map
   * would be O(1) instead of O(n)
   */
  private static class CityInfo implements Comparable<CityInfo>
  {
    private int zip;
    private String city;
    private String state_id;
    private int population;

    public CityInfo(int zip, String city, String state_id, int population)
    {
      this.zip = zip;
      this.city = city;
      this.state_id = state_id;
      this.population = population;
    }

    public int getPopulation() { return population; }
    public void addPopulation(int amt) { population += amt; }
    public String toString()
    {
      return city + ", " + state_id + " " + zip;
    }

    public int compareTo(CityInfo other)
    {
      return (zip < other.zip ? -1:(zip > other.zip ? 1:0));
    }
  } // end of CityInfo

  private static class PerCapita implements Comparable<PerCapita>
  {
    private Double percapita;
    private CityInfo city;

    public PerCapita(Double pc, CityInfo ci)
    {
      percapita = pc;
      city = ci;
    }

    public void display()
    {
      double ratio = percapita;
      System.out.format("%8.2f " + city.toString() + "\n", ratio);//, city, state, zipCode);
    }

    public int compareTo(PerCapita other)
    {
      return percapita.compareTo(other.percapita);
    }
  } // end of PerCapita

  public static void main(String[] args) {
    // Read pills and zip codes filenames, as well as k value,
    // either from command line arguments or from the console.

    String pillsFile, zipsFile, kstring;

    if (args.length >= 1)
      pillsFile = args[0];
    else
      pillsFile = System.console().readLine("pills file: ");

    if (args.length >= 2)
      zipsFile = args[1];
    else
      zipsFile = System.console().readLine("zips file: ");

    if (args.length >= 3)
      kstring = args[2];
    else
      kstring = System.console().readLine("k: ");
    int k = Integer.valueOf(kstring);

    // TODO you write the rest!

    // Use code like this to print the output.
    // Remember that only the top k pills/population ratios should be printed.
    // If there are fewer than k zip codes in the data set, then print all of them.
    // The output lines should be sorted by the pills/population ratio, largest first.
    // System.out.format("%8.2f %s, %s %d\n", ratio, city, state, zipCode);

    /**
     * *zips.tsv fields
     * zip	
     * city	
     * state_id	
     * population
     */ 
    // I want to pull zip and and store city, state_id, and population

    // lets parse out the zipsLines first
    TsvReader zipsLines  = new TsvReader(zipsFile);
    Map<Integer, CityInfo> zipToCity = new RBTreeMap<>();

    for(Map<String, String> line : zipsLines)
    {
      Integer zipcode = Integer.parseInt(line.get("zip"));
      Integer population = Integer.parseInt(line.get("population"));
      CityInfo city = zipToCity.get(zipcode); 
      if(city != null) {
        city.addPopulation(population);
        zipToCity.put(zipcode, city);
      }
      else
      {
        zipToCity.put(zipcode, new FastZips.CityInfo(zipcode,
              line.get("city"),
              line.get("state_id"),
              population));
      }
    }

    // now zipToCity contains zipcode: CityInfo[...]

    /**
     * arcos-*.tsv fields
     * BUYER_NAME	
     * BUYER_ADDL_CO_INFO	
     * BUYER_CITY	
     * BUYER_COUNTY	
     * BUYER_STATE	
     * BUYER_ZIP	
     * DRUG_NAME	
     * DOSAGE_UNIT	
     * TRANSACTION_DATE
     */
    // I want to pull BUYER_ZIP and DOSAGE_UNIT
    // time to process amount of pills total per zipcode
    TsvReader pillsLines = new TsvReader(pillsFile);
    Map<Integer, Integer> zipToPillCount = new RBTreeMap<>();

    for(Map<String,String> line : pillsLines)
    {
      Integer zipcode = Integer.parseInt(line.get("BUYER_ZIP"));
      Double dosageUnit = Double.parseDouble(line.get("DOSAGE_UNIT"));
      Integer count = zipToPillCount.get(zipcode);
      Integer updatedCount = (count == null ? 0:count) + (int)Math.ceil(dosageUnit);

      zipToPillCount.put(zipcode, updatedCount);
    }

    // now time to correlate the values
    TopK<PerCapita> topK = new TopK<>(k);
    for(Integer zipcode : zipToPillCount.keys())
    {
      CityInfo city = zipToCity.get(zipcode);
      int population = city.getPopulation();
      int pillCount  = zipToPillCount.get(zipcode);
      double perCapita = ((double)pillCount)/((double)population);
      topK.add(new FastZips.PerCapita(perCapita, city));
    }

    // display the top k
    for(PerCapita value : topK.getTop())
    {
      value.display();
    } 
  }
}

