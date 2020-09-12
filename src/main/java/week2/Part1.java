package week2;

public class Part1 {

    public static void main(String[] args) {

        testSimpleGene();
    }

    public static String findSimpleGene(String dna) {
        //Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        //Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string.
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1) {
            return "";
        }
        //If the length of the substring between the “ATG” and “TAA” is a multiple of 3,
        // then return the substring that starts with that “ATG” and ends with that “TAA”.

        result = dna.substring(startIndex, endIndex + 3);

        if (result.length() % 3 == 0){
            return result;
        }
        return "";
    }

    public static void testSimpleGene(){

        String dna = "ATGTCF";
        String simpleGene = findSimpleGene(dna);
        System.out.println("DNA strand is " + dna);
        System.out.println("Codon is " + simpleGene);


        String dna2 = "ACFTAAF";
        String simpleGene2 = findSimpleGene(dna2);
        System.out.println("DNA strand is " + dna2);
        System.out.println("Codon is " + simpleGene2);


        String dna3 = "DAATGTCNTAGGTCTAACG";
        String simpleGene3 = findSimpleGene(dna3);
        System.out.println("DNA strand is " + dna3);
        System.out.println("Codon is " + simpleGene3);


        String dna4 = "DEATGTCNTAGCTAAAED";
        String simpleGene4 = findSimpleGene(dna4);
        System.out.println("DNA strand is " + dna4);
        System.out.println("Codon is " + simpleGene4);
    }
}
