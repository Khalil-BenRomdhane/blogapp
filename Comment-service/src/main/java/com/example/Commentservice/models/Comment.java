package com.example.Commentservice.models;

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
@Document(collection = "Comments")
public class Comment {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String contenu;
    private String idUsr;
    private String idPost;
    private LocalDateTime date=LocalDateTime.now();
    public Comment(String contenu,String idUsr,String idPost)
    {   this.contenu=contenu;
        this.idPost=idPost;
        this.idUsr=idUsr;}
    public Comment() {

    }

}
