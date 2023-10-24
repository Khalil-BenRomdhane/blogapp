package com.example.Post.Service.endpoint;

import com.azure.core.util.BinaryData;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.azure.storage.queue.models.QueueMessageItem;
import com.example.Post.Service.CloudServices.AzureQueueService;
import com.example.Post.Service.dto.DtoPost;
import com.example.Post.Service.models.Post;
import com.example.Post.Service.services.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostServiceEndpoint {

    @Autowired
    PostService postService;
    @Autowired
    AzureQueueService azureQueueService;

    @PostMapping("/add")
    String handleAddPost(@RequestBody Post post) {

        if (postService.addPost(post)==null)
        {
            return "Can't add the post !";
        }
        return "Post added successfully !";


    }
@DeleteMapping("/delete/{id}")
        String handleDeletePost(@PathVariable("id") ObjectId id)
        { Optional<Post> post=postService.findPostById(id);
            System.out.println(post);
                if(!post.isEmpty())
                {
                    postService.deletePost(id);
                    return "Post with id: "+id+" deleted succefully";
                }

            return "Post does not exist anymore !";

        }
        @GetMapping("/getAllByHashtag")
        Optional<List<Post>> handleGetPostsByHashtag(@RequestParam("hashtag") String hashtag)
        {
            System.out.println(hashtag);
            return postService.getAllPostsByHashtag(hashtag);

        }
        @GetMapping("/getAll")
       List<Post> handleGetAllPosts()
        {
            return postService.getAllPosts();
        }
        @GetMapping("/getAllByUsrId/{id}")
     Optional<List<Post>> handleGetAllPostsByUsrId(@PathVariable("id") String id)
        {
            return postService.getAllByIdUsr(id);
        }
@GetMapping("/send")
void sendMsg()
{
    azureQueueService.sendMessageToCommentQueue("hello world");
}


}