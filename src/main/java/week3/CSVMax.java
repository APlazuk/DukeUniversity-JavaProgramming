package week3; /**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
	public static CSVRecord hottestHourInFile(CSVParser parser) throws IOException {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord record : parser.getRecords()) {
			//If largestSoFar is nothing
			if (largestSoFar == null){
				largestSoFar = record;
			}else {
				double currentTemp = Double.parseDouble(record.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

				if (currentTemp > largestTemp){
					//If so update largestSoFar to currentRow
					largestSoFar = record;
				}
			}
		}
		//The largestSoFar is the answer
		return largestSoFar;
	}
	public static CSVRecord hottestInManyDays() throws IOException {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			// use method to get largest in file.}
			CSVRecord record = hottestHourInFile(fr.getCSVParser());
			if (largestSoFar == null){
				largestSoFar = record;
			}
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			double currentTemp = Double.parseDouble(record.get("TemperatureF"));
			if (currentTemp > largestTemp){
				largestSoFar = record;
			}
		}
		return largestSoFar;
	}


	public static void testHottestInDay () throws IOException {
		FileResource fr = new FileResource("src/main/resources/data/2015/weather-2015-01-01.csv");
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				   " at " + largest.get("TimeEST"));
	}

	public static void testHottestInManyDays() throws IOException {
		CSVRecord largest = hottestInManyDays();
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				" at " + largest.get("DateUTC"));
	}

	public static void main(String[] args) throws IOException {
//		testHottestInDay();
	testHottestInManyDays();
	}
}
