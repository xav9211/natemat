package main.java.pl.edu.agh.toik.database.service;

import main.java.pl.edu.agh.toik.database.model.*;
import main.java.pl.edu.agh.toik.database.repository.ArticleRepository;
import main.java.pl.edu.agh.toik.database.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void saveArticles(Iterable<Article> articles) {
        articleRepository.save(articles);
    }

    @Transactional
    public void saveCommentsForArticle(Article article, List<Comment> comments) {
        for (Comment comment : comments) {
            comment.setArticle(article);
            commentRepository.save(comment);
        }
    }

    @Transactional
    public void saveArticlesForSection(Section section, Iterable<Article> articles) {
        for (Article article : articles) {
            article.setSection(section);
            articleRepository.save(article);
        }
    }

}
