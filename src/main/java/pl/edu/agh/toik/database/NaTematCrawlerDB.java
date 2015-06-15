package main.java.pl.edu.agh.toik.database;

import main.java.pl.edu.agh.toik.database.service.ArticleService;
import main.java.pl.edu.agh.toik.database.service.CommentService;
import main.java.pl.edu.agh.toik.database.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NaTematCrawlerDB {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private SectionService sectionService;

    public ArticleService getArticleService() {
        return articleService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public SectionService getSectionService() {
        return sectionService;
    }
}
