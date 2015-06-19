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
import main.java.pl.edu.agh.sius.model.*;
import main.java.pl.edu.agh.sius.crawler.NaTematCrawlerService;

public class Controller implements Initializable {

    private ICrawlerService crawler;

    private ObservableList<String> sections;
    private ObservableList<String> months;
    private ObservableList<String> articles;
    private String section;
    private Article article;
    private List<LinkMap> monthsL;
    private List<LinkMap> articlesL;
    private List<List<Integer>> sectionsList;

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
        sectionsList = new ArrayList<List<Integer>>();
    }

    @FXML
    private void downloadAction(ActionEvent event){
        try {
            this.sections = FXCollections.observableArrayList(this.crawler.getAllSectionsList());
            for (String sect : this.sections){
                List<LinkMap> months = this.crawler.getLinksFromSection(sect);
                List<Integer> monthsList = new ArrayList<Integer>();
                Integer articlesListSize;
                for (LinkMap month : months){
                    articlesListSize = (Integer)this.crawler.getNames(this.crawler.getLinksFromMonth(month.getLink())).size();
                    monthsList.add(articlesListSize);
                }
                this.sectionsList.add(monthsList);
            }
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
                this.dataArea.setText("");
                this.textArea.setText("");
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
                this.dataArea.setText("");
                this.textArea.setText("");
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
            this.dataArea.setText("");
            this.textArea.setText("");
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
        if (this.sectionCombo.getSelectionModel().getSelectedItem() != null) {
            String currSect = this.sectionCombo.getSelectionModel().getSelectedItem().toString();
            List<Integer> linksInMonth = this.sectionsList.get(this.sections.indexOf(currSect));
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            for (int i = 0; i < linksInMonth.size(); i++){
                series.getData().add(new XYChart.Data<>(this.crawler.getNames(monthsL).get(i), linksInMonth.get(i)));
            }
            this.histogram.getData().add(series);
        }else{
            this.histogram.getData().clear();
            List<Integer> linksInSection = new ArrayList<Integer>();
            for(int i = 0; i < this.sections.size(); i++) {
                int sumMonth = 0;
                for (Integer monthSize : this.sectionsList.get(i)){
                    sumMonth += monthSize;
                }
                linksInSection.add(sumMonth);
            }
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            for (int i = 0; i < this.sections.size(); i++){
                series.getData().add(new XYChart.Data<>(this.sections.get(i), linksInSection.get(i)));
            }
            this.histogram.getData().add(series);
        }
    }

    @FXML
    private void clearHistogramAction(ActionEvent event){
        this.histogram.getData().clear();
    }
}
