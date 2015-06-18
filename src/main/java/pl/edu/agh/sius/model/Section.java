package main.java.pl.edu.agh.sius.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sections")
public class Section {

    @Id
    private String sectionName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Article> articles;

    public Section() {
    }

    public Section(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
