package week3;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Assigment {

    public static void main(String[] args) {
        tester();
    }

    public static void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println(countryInfo(parser, "Germany"));
    }

    public static String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser) {
            String foundCntr = record.get("Country");

            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
             if (foundCntr.contains(country)){
                 return country + " : " + exports + " : " + value;
             }
        }
        return "Not Found";
    }
}
