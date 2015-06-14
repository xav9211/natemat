package main.java.pl.edu.agh.toik.app;

import main.java.pl.edu.agh.toik.crawler.NaTematCrawler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class NaTematCrawlerApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("app.fxml"));
        primaryStage.setTitle("NaTemat crawler");
        primaryStage.setScene(new Scene(root, 720, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(NaTematCrawlerConfig.class);
        launch(args);
        NaTematCrawler naTematCrawler = context.getBean(NaTematCrawler.class);
        naTematCrawler.crawl("http://natemat.pl/");
    }
}
