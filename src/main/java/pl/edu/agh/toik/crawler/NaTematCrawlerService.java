package main.java.pl.edu.agh.toik.crawler;

import main.java.pl.edu.agh.toik.database.model.Comment;
import main.java.pl.edu.agh.toik.util.JsonReader;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class NaTematCrawlerService implements ICrawlerService {

    @Override
    public Set<Element> findUniqueLinks(Collection<Element> links) {

        Set<String> uniqueNameLinks = new HashSet<String>();
        Set<Element> uniqueLinks = new LinkedHashSet<Element>();

        for (Element link : links) {
            uniqueNameLinks.add(link.attr("href"));
            if (uniqueNameLinks.size() > uniqueLinks.size())
                uniqueLinks.add(link);
        }

        return uniqueLinks;
    }

    @Override
    public List<Comment> getCommentsForUrl(String url) throws IOException {

        List<Comment> comments = new ArrayList<Comment>();

        JSONObject json1 = JsonReader.readJsonFromUrl("http://graph.facebook.com/comments?id=" + url);
        JSONArray data = json1.getJSONArray("data");

        for (int i = 0; i < data.length(); ++i) {
            String commentId = data.getJSONObject(i).getString("id");
            String commentAuthor = data.getJSONObject(i).getJSONObject("from").getString("name");
            DateTime commentCreatedDate = DateTime.parse(data.getJSONObject(i).getString("created_time"));
            Integer commentLikeCounter = data.getJSONObject(i).getInt("like_count");
            String commentContent = data.getJSONObject(i).getString("message");

            Comment comment = new Comment(commentId, commentAuthor, commentCreatedDate, commentLikeCounter, commentContent);
            comments.add(comment);
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
