package main.java.pl.edu.agh.sius.crawler;

public class NaTematCrawlerSettings implements ICrawlerSettings {

    private String storageFolder = ".";
    private Integer maxDepthOfCrawling = 1;
    private Boolean resumableCrawling = true;
    private Integer numberOfCrawlers = 7;

    @Override
    public String getStorageFolder() {
        return storageFolder;
    }

    @Override
    public Integer getMaxDepthOfCrawling() {
        return maxDepthOfCrawling;
    }

    @Override
    public Boolean resumableCrawling() {
        return resumableCrawling;
    }

    @Override
    public Integer getNumberOfCrawlers() {
        return numberOfCrawlers;
    }
}
