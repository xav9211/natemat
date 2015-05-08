package main.java.pl.edu.agh.toik.database.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private String id;
    private String author;
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;
    private Integer likeCounter;
    @Column(columnDefinition = "TEXT")
    private String content;

    public Comment() {
    }

    public Comment(String id, String author, DateTime createdDate, Integer likeCounter, String content) {
        this.id = id;
        this.author = author;
        this.createdDate = createdDate;
        this.likeCounter = likeCounter;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(Integer likeCounter) {
        this.likeCounter = likeCounter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
