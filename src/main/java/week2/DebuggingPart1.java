package week2;

public class DebuggingPart1 {

    public static void findAbc(String input) {
        int index = input.indexOf("abc");
        while (index != -1 && index < input.length() - 3) {

            System.out.println(index);

            String found = input.substring(index + 1, index + 4);
            System.out.println(found + " " + (index + 1) + " " + (index + 4));

            index = input.indexOf("abc", index + 3);
            System.out.println("index after updating " + index);

        }
    }

    public static void test() {
//        findAbc("abcd");
//        findAbc("abcdabc");
//        findAbc("woiehabchi");
        findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
//        findAbc("abcabcabcabca");
//        findAbc("eusabce");


    }


    public static void main(String[] args) {
        test();
    }
}
