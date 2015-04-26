package main.java.pl.edu.agh.toik.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaTematCrawlerController {

    private NaTematCrawler naTematCrawler;
    private ICrawlerSettings crawlerSettings;

    @Autowired
    public NaTematCrawlerController(NaTematCrawler naTematCrawler, ICrawlerSettings crawlerSettings) {
        this.naTematCrawler = naTematCrawler;
        this.crawlerSettings = crawlerSettings;
    }

    public void startCrawler() throws Exception {
        String crawlStorageFolder = crawlerSettings.getStorageFolder();
        int numberOfCrawlers = crawlerSettings.getNumberOfCrawlers();

        CrawlConfig config = new CrawlConfig();

        config.setMaxDepthOfCrawling(crawlerSettings.getMaxDepthOfCrawling());
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setResumableCrawling(crawlerSettings.resumableCrawling());

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */

        //controller.addSeed("http://natemat.pl/139509,koniec-swiata-poczatek");
        controller.addSeed("http://natemat.pl/");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(naTematCrawler.getClass(), numberOfCrawlers);
    }
}
