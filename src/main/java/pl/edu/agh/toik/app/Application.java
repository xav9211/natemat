package main.java.pl.edu.agh.toik.app;

import main.java.pl.edu.agh.toik.crawler.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("main.java.pl.edu.agh.toik.crawler")
public class Application {

    @Bean
    ICrawlerSettings crawlerSettings() {
        return new NaTematCrawlerSettings();
    }

    @Bean
    NaTematCrawler naTematCrawler() {
        return new NaTematCrawler();
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        NaTematCrawlerController naTematCrawlerController = context.getBean(NaTematCrawlerController.class);
        naTematCrawlerController.startCrawler();
    }
}
