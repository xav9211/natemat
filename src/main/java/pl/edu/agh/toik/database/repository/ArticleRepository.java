package main.java.pl.edu.agh.toik.database.repository;

import main.java.pl.edu.agh.toik.database.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
}
