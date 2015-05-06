package main.java.pl.edu.agh.toik.crawler;

import main.java.pl.edu.agh.toik.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class NaTematCrawlerService implements ICrawlerService {

    @Override
    public Set<String> findUniqueLinks(Collection<Element> links) {
        Set<String> uniqueLinks = new HashSet<String>();
        for (Element link : links)
            uniqueLinks.add(link.attr("href"));
        return uniqueLinks;
    }

    @Override
    public List<String> getCommentsForUrl(String url) throws IOException {

        List<String> comments = new ArrayList<String>();

        JSONObject json1 = JsonReader.readJsonFromUrl("http://graph.facebook.com/comments?id=" + url);
        JSONArray data = json1.getJSONArray("data");

        for (int i = 0; i < data.length(); ++i) {
            comments.add((String) data.getJSONObject(i).get("message"));
        }

        return comments;
    }

    @Override
    public int getNumberOfCommentsForUrl(String url) throws IOException {
        JSONObject json1 = JsonReader.readJsonFromUrl("http://graph.facebook.com/comments?id=" + url);
        JSONArray data = json1.getJSONArray("data");
        return data.length();
    }

}
