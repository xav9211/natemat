package main.java.pl.edu.agh.toik.crawler;

import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Collection;
import java.util.List;

public interface ICrawler {
    List<WebURL> filterURLs(Collection<WebURL> urls, String filterPrefix);
}
