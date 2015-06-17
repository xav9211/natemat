package main.java.pl.edu.agh.toik.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import main.java.pl.edu.agh.toik.crawler.NaTematCrawlerService;
import main.java.pl.edu.agh.toik.crawler.LinkMap;
import main.java.pl.edu.agh.toik.database.model.Article;

/**
 * Created by xav92 on 14.06.15.
 */
public class Controller implements Initializable{

    private NaTematCrawlerService crawler;
    private ObservableList<String> sections;
    private ObservableList<String> months;
    private ObservableList<String> articles;
    private String section;
    private Article article;
    private List<LinkMap> monthsL;
    private List<LinkMap> articlesL;

    @FXML
    private ComboBox<String> sectionCombo;

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private  ComboBox<String> articleCombo;

    @FXML
    private TextArea dataArea;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        crawler = new NaTematCrawlerService();
    }

    @FXML
    private void downloadAction(ActionEvent event){
        try {
            this.sections = FXCollections.observableArrayList(this.crawler.getAllSectionsList());
        }catch (IOException e){
            e.printStackTrace();
        }

        this.sectionCombo.setItems(this.sections);
    }

    @FXML
    private void getFromSection(ActionEvent event){
        try{
            this.section = this.sectionCombo.getSelectionModel().getSelectedItem().toString();
            this.monthsL = this.crawler.getLinksFromSection(this.section);
            this.months = FXCollections.observableArrayList(this.crawler.getNames(this.monthsL));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.monthCombo.setItems(this.months);
    }

    @FXML
    private void getFromMonth(ActionEvent event){
        try{
            this.articlesL = this.crawler.getLinksFromMonth(this.crawler.getLinkFromName(this.monthCombo.getSelectionModel().getSelectedItem().toString(), this.monthsL));
            this.articles = FXCollections.observableArrayList(this.crawler.getNames(this.articlesL));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.articleCombo.setItems(this.articles);
    }

    @FXML
    private  void getFromArticle(ActionEvent event){
        try{
            this.article = this.crawler.getArticleFromUrl(this.crawler.getLinkFromName(this.articleCombo.getSelectionModel().getSelectedItem().toString(), this.articlesL));
        }catch (IOException e){
            e.printStackTrace();
        }
        String data = "";
        data += "Tytuł: ";
        data += this.article.getTitle() + "\n";
        data += "Autor: " + this.article.getAuthor() + "\n";
        data += "Url: " + this.article.getUrlId() + "\n";
        data += "Date: " + this.article.getCreatedDate() + "\n";
        data += "Number of Facebook shares: " + this.article.getFacebookShares() + "\n";
        this.dataArea.setText(data);

        String text = this.article.getText();
        StringBuilder sb = new StringBuilder(text);
        int i = 0;
        while ((i = sb.indexOf(" ", i + 60)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        this.textArea.setText(sb.toString());
    }
}
