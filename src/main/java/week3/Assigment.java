package week3;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Assigment {

    public static void main(String[] args) {
        tester();
    }

    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println(countryInfo(parser, "Nauru"));

        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");

        parser = fr.getCSVParser();
        System.out.println(numberOfExporter(parser, "cocoa"));

        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String foundCntr = record.get("Country");

            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            if (foundCntr.contains(country)) {
                return country + " : " + exports + " : " + value;
            }
        }
        return "Not Found";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {

            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public static Integer numberOfExporter(CSVParser parser, String exportItem) {
        int countCountries = 0;
        for (CSVRecord record : parser) {

            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                countCountries++;
            }
        }
        return countCountries;
    }

    public static void bigExporters(CSVParser parser, String amount) {
        amount = amount.substring(1);
        amount = amount.replace(",", "");

        long newAmount = Long.parseLong(amount);
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            value = value.substring(1);
            value = value.replace(",", "");
            long newValue = Long.parseLong(value);

            if (newValue > newAmount) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
}
