package main.java.pl.edu.agh.toik.app;

import main.java.pl.edu.agh.toik.crawler.ICrawlerService;
import main.java.pl.edu.agh.toik.crawler.ICrawlerSettings;
import main.java.pl.edu.agh.toik.crawler.NaTematCrawlerService;
import main.java.pl.edu.agh.toik.crawler.NaTematCrawlerSettings;
import main.java.pl.edu.agh.toik.database.PersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({PersistenceConfig.class})
@Configuration
@ComponentScan("main.java.pl.edu.agh.toik.crawler")
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
