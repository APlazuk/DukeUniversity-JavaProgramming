package week4;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Assigment {

    public static void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;

        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;

            if (record.get(1).equalsIgnoreCase("f")) {
                totalGirls ++;
            } else {
                totalBoys ++;
            }
        }
        System.out.println("Total births = " + totalBirths + "\n" +
                "Total girls = " + totalGirls + "\n" +
                "Total boys = " + totalBoys);
    }

    public static int getRank(int year, String name, String gender) {
        String fileName = "src/main/resources/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        int countGenderPos = 0;
        int rank = 0;

        FileResource fr = new FileResource(fileName);
        CSVParser csvParser = fr.getCSVParser(false);

        for (CSVRecord record : csvParser) {

            if (record.get(1).equalsIgnoreCase(gender)) {
                countGenderPos++;

                if (record.get(0).equalsIgnoreCase(name)) {
                    rank = countGenderPos;
                    break;
                }
            }
            if (!record.get(1).equalsIgnoreCase(gender)) {
                rank = -1;
            }
        }
        return rank;
    }

    public static String getName(int year, int rank, String gender) {
        String fileName = "src/main/resources/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        int countGenderPos = 0;
        String babyName = null;

        FileResource fr = new FileResource(fileName);
        CSVParser csvParser = fr.getCSVParser(false);

        for (CSVRecord record : csvParser) {
            if (record.get(1).equalsIgnoreCase(gender)) {
                countGenderPos++;

                if (countGenderPos == rank) {
                    babyName = record.get(0);
                    break;
                } else {
                    babyName = "No name";
                }
            }
        }

        return babyName;
    }

    //year representing the year that name was born
    public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);

    }

    public static int yearOfHighestRank(String name, String gender) {
        int minRank = Integer.MAX_VALUE;
        int minRankYear = 0;

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {

            String fileName = file.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            int currRank = getRank(year, name, gender);


            if (currRank < minRank && currRank != -1) {
                minRank = currRank;
                minRankYear = year;
            }
        }
        return minRankYear;
    }

    public static double getAverageRank(String name, String gender) {

        double avgRank = 0.0;
        int count = 0;

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {

            String fileName = file.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            int currRank = getRank(year, name, gender);

            avgRank += currRank;
            count++;

        }
        return avgRank / count;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        int countGenderPos = 0;
        String fileName = "src/main/resources/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        int currRank = getRank(year, name, gender);


        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {

            if (record.get(1).equalsIgnoreCase(gender)) {
                countGenderPos++;

                if (countGenderPos < currRank) {
                    totalBirths += Integer.parseInt(record.get(2));
                }else {
                    break;
                }
            }
        }
        return totalBirths;
    }

    public static void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public static void testGetRank() {
        String name = "Frank";
        int year = 1971;
        String gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println("Rank for that name: " + rank);

    }

    public static void testGetName() {
        int rank = 450;
        int year = 1982;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("Name with this rank: " + name);
    }

    public static void testWhatIsNameInYear() {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";

        whatIsNameInYear(name, year, newYear, gender);
    }

    public static void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";
        System.out.println("Year of the Highest Rank: " + yearOfHighestRank(name, gender));

    }

    public static void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        System.out.println("Avarage rank " + getAverageRank(name, gender));
    }

    public static void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println(getTotalBirthsRankedHigher(year, name, gender));
    }

    public static void main(String[] args) {
//        testTotalBirths();
//        testGetRank();
//        testGetName();
//        testWhatIsNameInYear();
//        testYearOfHighestRank();
//        testGetAverageRank();
        testGetTotalBirthsRankedHigher();
    }
}


