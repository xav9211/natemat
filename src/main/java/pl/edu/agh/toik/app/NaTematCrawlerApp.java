package main.java.pl.edu.agh.toik.app;

import main.java.pl.edu.agh.toik.crawler.NaTematCrawler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NaTematCrawlerApp {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(NaTematCrawlerConfig.class);
        NaTematCrawler naTematCrawler = context.getBean(NaTematCrawler.class);
        naTematCrawler.startCrawler("http://natemat.pl/");
    }
}
