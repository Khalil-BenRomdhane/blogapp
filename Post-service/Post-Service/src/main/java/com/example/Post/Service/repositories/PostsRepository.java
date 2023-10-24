package com.example.Post.Service.repositories;

import com.example.Post.Service.models.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends MongoRepository<Post, ObjectId> {
    Long deletePostById(ObjectId  id);
    Optional<List<Post>> getAllByHashtag(String hashtag);
    Optional<List<Post>> getAllByIdUsr(String idUsr);

}
