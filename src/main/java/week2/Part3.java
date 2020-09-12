package week2;

public class Part3 {

    private static boolean twoOccurrences(String stringA, String stringB) {

        int startIndex = stringB.indexOf(stringA);
        if (startIndex == -1) {
            return false;
        }
        int endIndex = stringB.indexOf(stringA, startIndex + stringA.length());

        return endIndex != -1;
    }

    private static void testTwoOccurrences() {

        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));

    }

    private static String lastPart(String stringA, String stringB) {

        int startIndex = stringB.indexOf(stringA);

        if (startIndex == -1) {
            return stringB;
        }

        return stringB.substring(startIndex + stringA.length());
    }

    private static void testLastPart() {

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));

    }

    public static void main(String[] args) {
        testTwoOccurrences();
        testLastPart();
    }
}
