package main.java.pl.edu.agh.toik.crawler;

import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.Set;

public interface ICrawlerService {
    Set<String> findUniqueLinks(Collection<Element> links);
}
