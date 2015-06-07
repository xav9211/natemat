package main.java.pl.edu.agh.toik.database;

import main.java.pl.edu.agh.toik.database.service.ArticleService;
import main.java.pl.edu.agh.toik.database.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaTematCrawlerDB {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    public ArticleService getArticleService() {
        return articleService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

}
