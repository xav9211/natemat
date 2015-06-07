package main.java.pl.edu.agh.toik.crawler;

import main.java.pl.edu.agh.toik.database.model.*;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ICrawlerService {
    Set<Element> findUniqueLinks(Collection<Element> links);
    List<Comment> getCommentsForUrl(String url) throws IOException;
    Set<Comment> getSubCommentsForCommentId(String commentId) throws IOException;
    int getNumberOfCommentsForUrl(String url) throws IOException;
    Article getArticleFromUrl(String url) throws IOException;
    Set<String> getAllBlogsLinks() throws IOException;
    Set<String> getAllArticlesLinks() throws IOException;

}
