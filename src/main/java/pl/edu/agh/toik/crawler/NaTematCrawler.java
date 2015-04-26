package main.java.pl.edu.agh.toik.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class NaTematCrawler extends WebCrawler implements ICrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://natemat.pl/");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            List<WebURL> filteredURLs = filterURLs(links, "http://natemat.pl/");

            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of links: " + filteredURLs.size());
        }
    }

    @Override
    public List<WebURL> filterURLs(Collection<WebURL> urls, String filterPrefix) {
        List<WebURL> filteredURLs = new ArrayList<WebURL>();
        for (WebURL url : urls)
            if (url.getURL().startsWith(filterPrefix))
                filteredURLs.add(url);
        return filteredURLs;
    }

}
