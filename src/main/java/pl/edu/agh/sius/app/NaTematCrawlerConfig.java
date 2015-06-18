package main.java.pl.edu.agh.sius.app;

import main.java.pl.edu.agh.sius.crawler.ICrawlerService;
import main.java.pl.edu.agh.sius.crawler.ICrawlerSettings;
import main.java.pl.edu.agh.sius.crawler.NaTematCrawlerService;
import main.java.pl.edu.agh.sius.crawler.NaTematCrawlerSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("main.java.pl.edu.agh.sius.crawler")
public class NaTematCrawlerConfig {

    @Bean
    ICrawlerService crawlerService() {
        return new NaTematCrawlerService();
    }

    @Bean
    ICrawlerSettings crawlerSettings() {
        return new NaTematCrawlerSettings();
    }

}
