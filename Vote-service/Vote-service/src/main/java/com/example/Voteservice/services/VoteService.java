package com.example.Voteservice.services;

import com.example.Voteservice.models.Vote;
import com.example.Voteservice.repositories.VoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {


    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository=voteRepository;
    }
    
    public Vote AddVote(Vote  vote)
    {
        return voteRepository.save(vote);

    }
    public Optional<Vote> findVoteById(ObjectId id)
    {
        return voteRepository.findById(id);
    }
    public void deleteVote(Vote vote)
    {
        voteRepository.delete(vote);
    }
    public Optional<List<Vote>> findAllByIdUsr(String idUsr)
    {
        return voteRepository.findAllByIdUsr(idUsr);
    }
    public Optional<List<Vote>> findAllByIdPost(String id)
    {
        return voteRepository.findAllByIdPost(id);
    }


}
