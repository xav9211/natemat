package main.java.pl.edu.agh.toik.crawler;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ICrawlerService {
    Set<String> findUniqueLinks(Collection<Element> links);
    List<String> getCommentsForUrl(String url) throws IOException;
    int getNumberOfCommentsForUrl(String url) throws IOException;
}
