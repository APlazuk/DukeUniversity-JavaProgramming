package week3; /**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 *
 * @author Duke Software Team
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.IOException;

public class WhichCountriesExport {
    public static void listExporters(CSVParser parser, String exportOfInterest) throws IOException {
        //for each row in the CSV File
        for (CSVRecord record : parser.getRecords()) {
            //Look at the "Exports" column
            String export = record.get("Exports");

            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {

                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public static void whoExportsCoffee() throws IOException {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public static void main(String[] args) throws IOException {
        whoExportsCoffee();
    }
}
