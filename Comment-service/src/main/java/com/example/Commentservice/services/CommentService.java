package com.example.Commentservice.services;

import com.example.Commentservice.CommentRepository.CommentRepository;
import com.example.Commentservice.models.Comment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    public CommentRepository commentRepository;
    Optional<Comment> getCommentById(ObjectId id)
    {
        return commentRepository.findById(id);
    }
    public Optional<List<Comment>>  getAllCommentsByIdUsr(String idUsr)
    {
        return commentRepository.findAllByIdUsr(idUsr);
    }
    public Comment AddComment(Comment comment)
    {
        return commentRepository.save(comment);
    }
    public Optional<List<Comment>> getAllCommentsByIdPost(String idPost)
    {
        return commentRepository.findAllByIdPost(idPost);
    }
    public void  deleteCommentById(ObjectId id)
    {
         commentRepository.deleteById(id);
    }
   public List<Comment> getAllComments()
   {
       return commentRepository.findAll();
   }
}

