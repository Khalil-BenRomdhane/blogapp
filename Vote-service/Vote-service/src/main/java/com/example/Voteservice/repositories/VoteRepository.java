package com.example.Voteservice.repositories;

import com.example.Voteservice.models.Vote;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface VoteRepository extends MongoRepository<Vote, ObjectId> {
    Optional<List<Vote>> findAllByIdPost(String idPost);
    Optional<List<Vote>> findAllByIdUsr(String idUsr);


}
