package pl.edu.agh.examples.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ListLinks {
    public static void main(String[] args) throws IOException {
        String url = "http://natemat.pl/";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();

        System.out.println("natemat.pl text: " + doc.text());

        Elements links = doc.select("a[href^=http://natemat.pl/]");

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            String subUrl = link.attr("href");
            Document tmpDoc = Jsoup.connect(subUrl).get();
            System.out.println("URL: " + subUrl);
            System.out.println("Text length: " + tmpDoc.text().length());
            System.out.println("Html length: " + tmpDoc.html().length());
            System.out.println("Number of outgoing links: " + tmpDoc.select("a[href]").size());
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
