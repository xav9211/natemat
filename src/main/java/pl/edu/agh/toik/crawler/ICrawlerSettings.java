package main.java.pl.edu.agh.toik.crawler;

public interface ICrawlerSettings {
    String getStorageFolder();
    Integer getMaxDepthOfCrawling();
    Boolean resumableCrawling();
    Integer getNumberOfCrawlers();
}
