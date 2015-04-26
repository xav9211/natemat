package main.java.pl.edu.agh.toik.examples.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class ListLinks {

    private static JTextArea mainTextField;
    private static Elements links;
    //10 seconds
    private final static int TIMEOUT = 10 * 1000;
    private static String url = "http://natemat.pl/";

    public static void main(String[] args) throws IOException {

        print("Fetching %s...", url);


        Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();

        System.out.println("natemat.pl text: " + doc.text());

        links = doc.select("a[href^=" + url + "], a[href^=/]");

        print("\nLinks: (%d)", findUniqueLinks(links).size());

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

//        for (Element link : links) {
//            String subUrl = link.attr("href");
//            Document tmpDoc = Jsoup.connect(subUrl).timeout(TIMEOUT).get();
//            System.out.println("URL: " + subUrl);
//            System.out.println("Text length: " + tmpDoc.text().length());
//            System.out.println("Html length: " + tmpDoc.html().length());
//            System.out.println("Number of links: " + findUniqueLinks(tmpDoc.select("a[href^=" + url + "], a[href^=/]")).size());
//        }
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

    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Na-temat");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);

        mainTextField = new JTextArea();
        JScrollPane scrl = new JScrollPane(mainTextField);
        frame.getContentPane().add(scrl);

        //Display the window.
        frame.setVisible(true);

        for (Element link : links) {
            String subUrl = link.attr("href");
            Document tmpDoc = Jsoup.connect(subUrl).timeout(TIMEOUT).get();
            String newLine;
            if(links.indexOf(link) == 0){
                newLine = "";
            }else{
                newLine = "\n";
            }
            mainTextField.setText(mainTextField.getText() + newLine + "URL: " + subUrl);
            mainTextField.setText(mainTextField.getText() + "\nText length: " + tmpDoc.text().length());
            mainTextField.setText(mainTextField.getText() + "\nHtml length: " + tmpDoc.html().length());
            mainTextField.setText(mainTextField.getText() + "\nNumber of links: " + findUniqueLinks(tmpDoc.select("a[href^=" + url + "], a[href^=/]")).size());
        }
    }
}
