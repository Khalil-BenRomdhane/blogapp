package com.example.Commentservice.endpoints;

import com.example.Commentservice.CloudServices.AzureQueueService;
import com.example.Commentservice.models.Comment;
import com.example.Commentservice.services.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    AzureQueueService azureQueueService;
    public static String response;

    @PostMapping("/add")
 Comment handleAddComment(@RequestBody Comment comment) {
        long timeInterval = 5000+2000+3000; // 10 seconds

        azureQueueService.sendMessageToPostQueue(comment.getIdPost()+" comment service");
        long startTime = System.currentTimeMillis();

        while (response == null )
        { Long currentTime=System.currentTimeMillis();
            if(currentTime-startTime>=timeInterval)
            {
                response="NOT OK!";
            }
        }
        System.out.println(response);
        if (response.equalsIgnoreCase("OK")) {

            response = null;
            return commentService.AddComment(comment);

        } else
        {   response=null;
            return null;
        }


    }
    @GetMapping("/getAllByUsrId/{id}")
        Optional<List<Comment>> handleGetCommentByIdUsr(@PathVariable String id)
    {
        return commentService.getAllCommentsByIdUsr(id);
    }
    @GetMapping("/getAllByPostId/{id}")
        Optional<List<Comment>> handleGetCommentsByIdPost(@PathVariable String id)
    {
       return commentService.getAllCommentsByIdPost(id);
    }
    @GetMapping("/getAll")
        List<Comment> handleGetAllComments()
    {
        return commentService.getAllComments();
    }
    @DeleteMapping("/deleteById/{id}")
        void handleDeleteCommentById(@PathVariable ObjectId id)
        {
            commentService.deleteCommentById(id);
            }
}
