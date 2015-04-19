package pl.edu.agh.examples.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ListLinks {
    public static void main(String[] args) throws IOException {

        String url = "http://natemat.pl/";

        print("Fetching %s...", url);

        //10 seconds
        final int TIMEOUT = 10 * 1000;

        Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

        System.out.println("natemat.pl text: " + doc.text());

        Elements links = doc.select("a[href^=" + url + "], a[href^=/]");

        print("\nLinks: (%d)", findUniqueLinks(links).size());

        for (Element link : links) {
            String subUrl = link.attr("href");
            Document tmpDoc = Jsoup.connect(subUrl).timeout(TIMEOUT).get();
            System.out.println("URL: " + subUrl);
            System.out.println("Text length: " + tmpDoc.text().length());
            System.out.println("Html length: " + tmpDoc.html().length());
            System.out.println("Number of links: " + findUniqueLinks(tmpDoc.select("a[href^=" + url + "], a[href^=/]")).size());
        }
    }

    private static Set<String> findUniqueLinks(Collection<Element> links) {
        Set<String> uniqueLinks = new HashSet<String>();
        for (Element link : links)
            uniqueLinks.add(link.attr("href"));
        return uniqueLinks;
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
