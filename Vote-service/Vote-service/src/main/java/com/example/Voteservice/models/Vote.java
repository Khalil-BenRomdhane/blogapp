package com.example.Voteservice.models;

import com.example.Voteservice.dto.VoteType;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "Votes")
public class Vote {
    @Id
    private ObjectId id;
    private VoteType type;
    private String idUsr;
    private String idPost;
    private LocalDateTime date=LocalDateTime.now();
    public Vote(VoteType type,String idUsr,String idPost)
    {
        this.type=type;
        this.idUsr=idUsr;
        this.idPost=idPost;

    }
    public Vote()
    {

    }

}
