package week3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Assignment2 {

    public static CSVRecord getColdestOfTwo(CSVRecord coldestSoFar, CSVRecord record) {
        if (coldestSoFar == null) {
            coldestSoFar = record;
        }

        double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
        double currentTemp = Double.parseDouble(record.get("TemperatureF"));

        if (currentTemp < coldestTemp) {
            coldestSoFar = record;
        }
        return coldestSoFar;
    }

    public static CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;


        for (CSVRecord record : parser) {
            coldestSoFar = getColdestOfTwo(coldestSoFar, record);
        }
        return coldestSoFar;
    }


    public static File fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File fileWithLowestTemp = null;


        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);

            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(coldestSoFar, record);

            fileWithLowestTemp = file;
        }

        return fileWithLowestTemp;
    }


    public static void testColdestHourInFile() {
        FileResource fr = new FileResource("src/main/resources/data/2015/weather-2015-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());

        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                " at " + coldest.get("TimeEST"));
    }

    public static void testFileWithColdestTemperature() {
        File file = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + file.getName());

        FileResource fr = new FileResource(file);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));

        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println("All the Temperatures on the coldest day were: " + record.get("TimeEST") + " " + record.get("TemperatureF"));
        }
    }

    public static void main(String[] args) {
//        testColdestHourInFile();
        testFileWithColdestTemperature();
    }
}
