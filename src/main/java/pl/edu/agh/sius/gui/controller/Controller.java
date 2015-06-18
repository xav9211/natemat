package main.java.pl.edu.agh.sius.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import main.java.pl.edu.agh.sius.crawler.ICrawlerService;
import main.java.pl.edu.agh.sius.crawler.LinkMap;
import main.java.pl.edu.agh.sius.crawler.NaTematCrawlerService;
import main.java.pl.edu.agh.sius.model.Article;

public class Controller implements Initializable {

    private ICrawlerService crawler;

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

    @FXML
    private BarChart<String, Integer> histogram;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.crawler = new NaTematCrawlerService();
    }

    @FXML
    private void downloadAction(ActionEvent event){
        try {
            this.sections = FXCollections.observableArrayList(this.crawler.getAllSectionsList());
        }catch (IOException e){
            e.printStackTrace();
        }

        this.sectionCombo.setValue(null);
        this.monthCombo.getSelectionModel().clearSelection();
        this.monthCombo.setValue(null);
        this.articleCombo.getSelectionModel().clearSelection();
        this.articleCombo.setValue(null);
        this.dataArea.setText("");
        this.textArea.setText("");
        this.sectionCombo.setItems(this.sections);
        this.histogram.getData().clear();
    }

    @FXML
    private void getFromSection(ActionEvent event){
        try {
            if (this.sectionCombo.getSelectionModel().getSelectedItem() != null) {
                this.section = this.sectionCombo.getSelectionModel().getSelectedItem().toString();
                this.monthsL = this.crawler.getLinksFromSection(this.section);
                this.months = FXCollections.observableArrayList(this.crawler.getNames(this.monthsL));
            }else{
                this.sectionCombo.getSelectionModel().clearSelection();
                this.sectionCombo.setValue(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.monthCombo.setItems(this.months);
    }

    @FXML
    private void getFromMonth(ActionEvent event){
        try {
            if (this.monthCombo.getSelectionModel().getSelectedItem() != null) {
                this.articlesL = this.crawler.getLinksFromMonth(this.crawler.getLinkFromName(this.monthCombo.getSelectionModel().getSelectedItem().toString(), this.monthsL));
                this.articles = FXCollections.observableArrayList(this.crawler.getNames(this.articlesL));
            }else{
                this.monthCombo.getSelectionModel().clearSelection();
                this.monthCombo.setValue(null);
            }
        } catch (IOException e) {
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
        data += "Date: " + this.article.getCreatedDate().getYear() + ":"
                + this.article.getCreatedDate().getMonthOfYear() + ":"
                + this.article.getCreatedDate().getDayOfMonth() + "\n";
        data += "Number of Facebook shares: " + this.article.getFacebookShares() + "\n";
        this.dataArea.setText(data);

        String text = this.article.getText();
        StringBuilder sb = new StringBuilder(text);
        int i = 0;
        while ((i = sb.indexOf(" ", i + 120)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        this.textArea.setText(sb.toString());
    }

    @FXML
    private void chartAction(ActionEvent event){
        try {
            if (this.sectionCombo.getSelectionModel().getSelectedItem() != null) {
                List<Integer> linksInMonth = new ArrayList<Integer>();
                for(LinkMap month : this.monthsL){
                    linksInMonth.add(this.crawler.getLinksFromMonth(month.getLink()).size());
                }
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                for (int i = 0; i < linksInMonth.size(); i++){
                    series.getData().add(new XYChart.Data<>(this.crawler.getNames(monthsL).get(i), linksInMonth.get(i)));
                }
                this.histogram.getData().add(series);
            }else{
                this.histogram.getData().clear();
                List<Integer> linksInSection = new ArrayList<Integer>();
                for(String sect : this.sections) {
                    linksInSection.add(this.crawler.getLinksFromSection(sect).size());
                }
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                for (int i = 0; i < linksInSection.size(); i++){
                    series.getData().add(new XYChart.Data<>(this.sections.get(i), linksInSection.get(i)));
                }
                this.histogram.getData().add(series);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearHistogramAction(ActionEvent event){
        this.histogram.getData().clear();
    }
}
