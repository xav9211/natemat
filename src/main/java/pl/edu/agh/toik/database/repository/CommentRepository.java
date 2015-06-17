package main.java.pl.edu.agh.toik.database.repository;

import main.java.pl.edu.agh.toik.database.model.Article;
import main.java.pl.edu.agh.toik.database.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query(value = "select * from comments where cast(createddate as text) like ?1%", nativeQuery = true)
    List<Comment> findByCreatedDate(String date);

    @Query(value = "select * from comments where cast(createddate as text) between ?1 and ?2", nativeQuery = true)
    List<Comment> findBetweenTwoCreatedDates(String date1, String date2);

    List<Comment> findByArticle(Article article);

    @Query(value = "select c from Comment c where c.article.id = ?1")
    List<Comment> findByArticleUrl(String url);

    @Query(value = "select * from comments where likecounter >= ?1 and likecounter <= ?2", nativeQuery = true)
    List<Comment> findInLikeCounterPeriod(Integer from, Integer to);

    List<Comment> findSubCommentsByComment(Comment comment);

}
