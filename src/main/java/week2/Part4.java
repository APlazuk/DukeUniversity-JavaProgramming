package week2;

import edu.duke.URLResource;

public class Part4 {

    private static String findingWebLinks() {

        URLResource urlResource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String webPage = "youtube.com";

        for (String word : urlResource.words()) {

            if (word.toLowerCase().contains(webPage)) {

                int firstQuote = word.indexOf("\"");
                int lastQuote = word.lastIndexOf("\"");

                return word.substring(firstQuote + 1, lastQuote);
            }
        }
        return "no youtube websites";
    }

    public static void main(String[] args) {
        System.out.println(findingWebLinks());
    }
}
