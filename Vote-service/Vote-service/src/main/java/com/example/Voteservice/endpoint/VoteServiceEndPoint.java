package com.example.Voteservice.endpoint;

import com.example.Voteservice.models.Vote;
import com.example.Voteservice.services.VoteService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vots")
public class VoteServiceEndPoint {
    @Autowired
    VoteService voteService;
    @PostMapping("/add")
    String handleAddVote(@RequestBody Vote vote)
    {
        if(voteService.AddVote(vote)==null)
        {
            return "Can't add Vote !";
        }
        return vote.getType()+
                " vote on poste with id: "+
                vote.getIdPost()+
                "and with usrId: "+
                vote.getIdUsr()+
                " added successfully !";
    }
    @DeleteMapping("/delete")
   public void handleDeletePost(Vote vote)
    {
      voteService.deleteVote(vote);
    }
    @GetMapping("/getAllByIdUsr")
    public Optional<List<Vote>> handleGetAllByIdUsr(String id)
    {
        return voteService.findAllByIdUsr(id);
    }
    @GetMapping("/GetAllByIdPost")
    public Optional<List<Vote>> handleGetAllByIdPost(String id)
    {
        return voteService.findAllByIdPost(id);
    }
     @GetMapping("/GetById")
    public Optional<Vote> handleGetById(ObjectId id)
     {
         return voteService.findVoteById(id);
     }


}
