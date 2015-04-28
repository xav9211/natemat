package main.java.pl.edu.agh.toik.crawler;

import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class NaTematCrawlerService implements ICrawlerService {

    @Override
    public Set<String> findUniqueLinks(Collection<Element> links) {
        Set<String> uniqueLinks = new HashSet<String>();
        for (Element link : links)
            uniqueLinks.add(link.attr("href"));
        return uniqueLinks;
    }
}
