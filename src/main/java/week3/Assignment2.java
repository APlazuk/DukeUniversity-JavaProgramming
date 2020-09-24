package week3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;

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

    public static CSVRecord getLowestHumOfTwo(CSVRecord lowestHumidity, CSVRecord currentRow) {
        if (lowestHumidity == null) {
            lowestHumidity = currentRow;
        }

        String humidity = currentRow.get("Humidity");
        String lowHum = lowestHumidity.get("Humidity");
        double currHum = 0;
        double lowestHum = 0;

        if (!humidity.equalsIgnoreCase("N/A")) {
            currHum = Double.parseDouble(humidity);
        }

        if (!lowHum.equalsIgnoreCase("N/A")) {
            lowestHum = Double.parseDouble(lowHum);
        }

        if (currHum < lowestHum && !humidity.equalsIgnoreCase("N/A")) {
            lowestHumidity = currentRow;
        }
        return lowestHumidity;
    }

    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidity = null;

        for (CSVRecord currentRow : parser) {
            lowestHumidity = getLowestHumOfTwo(lowestHumidity, currentRow);

        }
        return lowestHumidity;
    }

    public static CSVRecord lowestHumidityInManyFiles() {

        CSVRecord lowestHumidity = null;

        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);

            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidity = getLowestHumOfTwo(lowestHumidity, record);

        }

        return lowestHumidity;
    }

    public static double medianTemperatureInFile(CSVParser parser) throws IOException {
        double medianTemp;

        CSVRecord hottestHourInFile = CSVMax.hottestHourInFile(parser);

        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        CSVRecord coldestHourInFile = coldestHourInFile(parser);

        double hottestTemp = Double.parseDouble(hottestHourInFile.get("TemperatureF"));
        double coldestTemp = Double.parseDouble(coldestHourInFile.get("TemperatureF"));

        medianTemp = (hottestTemp + coldestTemp) / 2;
        return medianTemp;
    }

    public static double averageTemperatureInFile(CSVParser parser) {
        double avgTemp = 0;
        double count = 0;

        for (CSVRecord record : parser) {

            double sumTemp = Double.parseDouble(record.get("TemperatureF"));

            avgTemp = avgTemp + sumTemp;
            count++;

        }
        return avgTemp / count;
    }


    public static double averageTemperatureInFileHum(CSVParser parser, int value) {
        double avgTemp = 0;


        for (CSVRecord record : parser) {

            double humidity = 0;
            if (!record.get("Humidity").equalsIgnoreCase("N/A")) {
                humidity = Double.parseDouble(record.get("Humidity"));
            }
            if ((humidity >= value) && !record.get("Humidity").equalsIgnoreCase("N/A")){
                avgTemp = averageTemperatureInFile(parser);
            }
        }
        return avgTemp;
    }


    public static void testColdestHourInFile() {
        FileResource fr = new FileResource();
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
            System.out.println("All the Temperatures on the coldest day were: " + record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }

    public static void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + csv.get("Humidity") +
                " at " + csv.get("DateUTC"));

    }

    public static void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityInManyFiles = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityInManyFiles.get("Humidity") +
                " at " + lowestHumidityInManyFiles.get("DateUTC"));
    }

    public static void testMedianTemperatureInFile() throws IOException {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double medianTemperatureInFile = medianTemperatureInFile(parser);

        System.out.println("Average temperature in file is " + medianTemperatureInFile);

    }

    public static void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureInFile = averageTemperatureInFile(parser);

        System.out.println("Average temperature in file is " + averageTemperatureInFile);

    }

    public static void testAverageTemperatureWithHighHumidityInFile() throws IOException {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureInFileHum = averageTemperatureInFileHum(parser, 80);
        System.out.println("Average temperature in file is " + averageTemperatureInFileHum);
    }

    public static void main(String[] args) throws IOException {
//        testColdestHourInFile();
//        testFileWithColdestTemperature();
//        testLowestHumidityInFile();
//        testLowestHumidityInManyFiles();
//        testMedianTemperatureInFile();
//        testAverageTemperatureInFile();
        testAverageTemperatureWithHighHumidityInFile();
    }
}
