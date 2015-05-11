package main.java.pl.edu.agh.toik.database.service;

import main.java.pl.edu.agh.toik.database.model.Comment;
import main.java.pl.edu.agh.toik.database.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void saveComments(Iterable<Comment> comments) {
        commentRepository.save(comments);
    }

}
