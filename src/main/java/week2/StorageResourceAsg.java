package week2;

import edu.duke.StorageResource;

public class StorageResourceAsg {

    public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
//    Find stopCodon starting from (startIndex + 3), currIndex
        int currIndex = dnaStr.toUpperCase().indexOf(stopCodon, startIndex + 3);

//     as long as current index is not equal to -1
        while (currIndex != -1) {

//     check to see if the currIndex- startIndex is a multiple of 3
            if ((currIndex - startIndex) % 3 == 0) {
//     if so, currIndex is the answer return it.
                return currIndex;
            }

//     if not, update currIndex, looking for a stopCodon
//     starting from currIndex + 1
            currIndex = dnaStr.indexOf(currIndex + 1);
        }
        return -1;
//     if we didn't find stopCodon, exit the loop and return dnaStr.length()
    }

    public static String findGene(String dna, int where) {
//        find first occurrence of "ATG", tartIndex
        int startIndex = dna.toUpperCase().indexOf("ATG", where);

//        if startIndex is -1, return ""
        if (startIndex == -1) {
            return "";
        }
//        use taaIndex to store findStopCodon(dna, startIndex, "TAA")
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
//        use tagIndex to store findStopCodon(dna, startIndex, "TAG")
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
//        use tgaIndex to store findStopCodon(dna, startIndex, "TGA")
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
//        store in minIndex the smallest of these three
        int minIndex = 0;

        if (taaIndex == -1 ||
                (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
                (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }
        if (minIndex == -1) {
            return "";
        }
//        otherwise answer is text from startIndex to minIndex + 3
        return dna.substring(startIndex, minIndex + 3);
    }

    public static StorageResource getAllGenes(String dna) {
        int startIndex = 0;

        StorageResource geneList = new StorageResource();

        while (true) {

            String currGene = findGene(dna, startIndex);

            if (currGene.isEmpty()) {
                break;
            }

            geneList.add(currGene);

            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }

        return geneList;
    }

    public static Double cgRatio(String dna) {
        double countC = 0;
        double countG = 0;
        double c = dna.indexOf("C");
        double g = dna.indexOf("G");


        while (true) {

            if (c != -1) {
                countC++;
            }

            if (g != -1) {
                countG++;
            } else {
                break;
            }

            c = dna.indexOf("C", (int) (c + 1));
            g = dna.indexOf("G", (int) (g + 1));

        }

        return (countC + countG) / dna.length();
    }

    public static int countCTG(String dna) {
        int count = 0;
        String codon = "CTG";
        int startIndex = dna.indexOf(codon);

        while (true) {

            if (startIndex != -1) {
                count++;
            } else {
                break;
            }

            startIndex = dna.indexOf(codon, startIndex + codon.length());
        }
        return count;
    }


    public static void testFindAllGenes() {
//                    0123456789012345678901234567
//                    ATG      TAG       ATG   TAA
        String dna = "ATGGGGATGTAGTAATTTCATGCCATAA";

        StorageResource allGenes = getAllGenes(dna);
        for (String s : allGenes.data()) {
            System.out.println("All genes test1: " + s);
        }

        String dna1 = "";
        StorageResource allGenes1 = getAllGenes(dna1);

        for (String s : allGenes1.data()) {
            System.out.println(("All genes test2: " + s));
        }

    }

    public static void testCgRatio() {
        String dna = "ATGC";
        System.out.println(cgRatio(dna));

        String dna1 = "ATGGGGATGTAGTAATTTCATGCCATAA";
        System.out.println(cgRatio(dna1));

        String dna3 = "";
        System.out.println(cgRatio(dna3));
    }


    public static void testCountCTG(){
        String dna = "ATGCTGCTG";
        System.out.println(countCTG(dna));

        String dna1 = "ATGCTGGATGTAGTAATTTCATGCCATAA";
        System.out.println(countCTG(dna1));

        String dna2 = "";
        System.out.println(countCTG(dna2));
    }

    public static void main(String[] args) {
        testFindAllGenes();
        testCgRatio();
        testCountCTG();

    }
}
