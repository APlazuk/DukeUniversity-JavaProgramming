package week2;

public class StringAssigment2Part2 {

    public static void main(String[] args) {
        System.out.println(howMany("a", "banana"));
    }

    public static int howMany(String stringA, String stringB) {
        int count = 0;

        int startIndex = stringB.indexOf(stringA);

        while (true) {

            if (startIndex != -1) {
                count++;
            } else {
                break;
            }

            startIndex = stringB.indexOf(stringA, startIndex + stringA.length());

        }

        return count;
    }
}
