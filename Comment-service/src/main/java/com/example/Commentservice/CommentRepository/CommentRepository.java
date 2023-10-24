package com.example.Commentservice.CommentRepository;

import com.example.Commentservice.models.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
 Optional<List<Comment>> findAllByIdPost(String idPost);
 Optional<List<Comment>> findAllByIdUsr(String idUsr);

}
