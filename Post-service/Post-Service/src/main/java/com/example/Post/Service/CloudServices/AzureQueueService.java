package com.example.Post.Service.CloudServices;


import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.QueueServiceClientBuilder;
import com.azure.storage.queue.models.QueueMessageItem;
import com.example.Post.Service.services.PostService;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Queue;

@Service
    public class AzureQueueService {
       @Autowired
            PostService postService;
        private final QueueClient postQueueClient;
        private final QueueClient commentQueueClient;

        public AzureQueueService(@Value("DefaultEndpointsProtocol=https;AccountName=blogsextraa;AccountKey=VIwK8APlVP/oAsk93nITdjqRvgyidhtO2eflyVkDbSnQBUjOEeVmscnsjUvKNmSuiDrwTOhe5qM9+ASt1smtiA==;EndpointSuffix=core.windows.net") String connectionString) {
            QueueServiceClient serviceClient = new QueueServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();

            this.postQueueClient = serviceClient.getQueueClient("queue1");
            QueueServiceClient serviceClient2 = new QueueServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();
            this.commentQueueClient = serviceClient2.getQueueClient("queue2");
        }

        public void sendMessageToCommentQueue(String message) {
            commentQueueClient.sendMessage(message);

        }
        public void  ListenToPostQueue()

        {
            while (true)
            {  Iterable<QueueMessageItem> Messages=postQueueClient.receiveMessages(1);
                for (QueueMessageItem message : Messages) {
                    // Process the message
                    String messageText = message.getMessageText();

                    // Your processing logic here
                    if(messageText!=null)
                    {
                        if(messageText.contains("comment service"))
                        {
                            String[] split=messageText.split(" ");
                            System.out.println(split[0]);
                            if(postService.findPostById(new ObjectId(split[0])).isPresent())
                            {
                                sendMessageToCommentQueue("OK");
                                System.out.println("OK");
                            }else
                            {
                                sendMessageToCommentQueue("NOT OK");
                                System.out.println("NOT OK");
                            }

                        }
                    }
                    // Delete the message from the queue after processing
                    postQueueClient.deleteMessage(message.getMessageId(), message.getPopReceipt());

                    }





            }


        }

    @PostConstruct
    public void startListeningToQueue() {
        Thread listenerThread = new Thread(this::ListenToPostQueue);
        listenerThread.start();
    }


}

