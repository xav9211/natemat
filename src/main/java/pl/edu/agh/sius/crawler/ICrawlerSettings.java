package main.java.pl.edu.agh.sius.crawler;

public interface ICrawlerSettings {
    String getStorageFolder();
    Integer getMaxDepthOfCrawling();
    Boolean resumableCrawling();
    Integer getNumberOfCrawlers();
}
