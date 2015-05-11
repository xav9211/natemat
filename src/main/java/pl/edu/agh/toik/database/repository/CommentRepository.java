package main.java.pl.edu.agh.toik.database.repository;

import main.java.pl.edu.agh.toik.database.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
