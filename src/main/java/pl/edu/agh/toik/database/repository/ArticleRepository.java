package main.java.pl.edu.agh.toik.database.repository;

import main.java.pl.edu.agh.toik.database.model.Article;
import main.java.pl.edu.agh.toik.database.model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, String> {

    List<Article> findBySection(Section section);

    @Query(value = "select a from Article a where a.section.sectionName = ?1")
    List<Article> findBySectionName(String sectionName);

    @Query(value = "select * from articles where cast(createddate as text) like ?1%", nativeQuery = true)
    List<Article> findByCreatedDate(String date);

    @Query(value = "select * from articles where sectionid = ?1 and cast(createddate as text) like ?2%", nativeQuery = true)
    List<Article> findBySectionNameAndCreatedDate(String sectionName, String date);

    @Query(value = "select * from articles where cast(createddate as text) between ?1 and ?2", nativeQuery = true)
    List<Article> findBetweenTwoCreatedDates(String date1, String date2);

    @Query(value = "select * from articles where sectionid = ?1 and cast(createddate as text) between ?2 and ?3", nativeQuery = true)
    List<Article> findBySectionNameAndBetweenTwoCreatedDates(String sectionName, String date1, String date2);

}
