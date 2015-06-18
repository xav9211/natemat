package main.java.pl.edu.agh.sius.crawler;

import main.java.pl.edu.agh.sius.model.*;
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
    int getNumberOfFacebookSharesForArticle(String articleUrl) throws IOException;
    List<String> getAllSectionsList() throws IOException;
    List<LinkMap> getLinksFromSection(String section) throws IOException;
    List<LinkMap> getLinksFromMonth(String url) throws IOException;
    String getLinkFromName(String name, List<LinkMap> list);
    List<String> getNames(List<LinkMap> list);
}
