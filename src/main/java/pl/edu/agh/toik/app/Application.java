package main.java.pl.edu.agh.toik.app;

import main.java.pl.edu.agh.toik.crawler.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("main.java.pl.edu.agh.toik.crawler")
public class Application {

    @Bean
    ICrawlerService crawlerService() {
        return new NaTematCrawlerService();
    }

    @Bean
    ICrawlerSettings crawlerSettings() {
        return new NaTematCrawlerSettings();
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        NaTematCrawler naTematCrawler = context.getBean(NaTematCrawler.class);
        naTematCrawler.startCrawler("http://natemat.pl/");
    }
}
