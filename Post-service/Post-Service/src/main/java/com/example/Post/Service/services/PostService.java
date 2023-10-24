package com.example.Post.Service.services;

import com.example.Post.Service.models.Post;
import com.example.Post.Service.repositories.PostsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PostService {
    @Autowired
    PostsRepository postsRepository;
    public Optional<Post> findPostById(ObjectId id)
    {
        return postsRepository.findById(id);
    }
    public Post addPost(Post post)
    {
        return
                postsRepository.save(post);
    }

   public  void  deletePost( ObjectId id)
    {
         postsRepository.deletePostById(id);
    }
    public Optional<List<Post>> getAllPostsByHashtag(String hashtag)
    {
        return postsRepository.getAllByHashtag(hashtag);

    }
    public List<Post> getAllPosts()
    {
        return postsRepository.findAll();
    }
    public Optional<List<Post>> getAllByIdUsr(String usrId)
    {
        return postsRepository.getAllByIdUsr(usrId);
    }

}
