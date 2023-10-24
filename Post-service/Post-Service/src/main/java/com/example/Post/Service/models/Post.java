package com.example.Post.Service.models;




import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "Posts")

public class Post {


    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String contenu;
    private String hashtag;
    private String idUsr;
    private LocalDateTime date=LocalDateTime.now();
    public Post(String contenu,String hashtag,String idUsr)
    {   this.contenu=contenu;
        this.hashtag=hashtag;
        this.idUsr=idUsr;
    }


    public Post() {

    }
}
