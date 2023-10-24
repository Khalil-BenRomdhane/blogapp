package com.example.Commentservice.CloudServices;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueServiceClient;
import com.azure.storage.queue.QueueServiceClientBuilder;
import com.azure.storage.queue.models.QueueMessageItem;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.example.Commentservice.endpoints.CommentController.response;


@Service
public class AzureQueueService {

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

    public void sendMessageToPostQueue(String message) {
        postQueueClient.sendMessage(message);

    }

    public void  ListenToCommentQueue()

    {
            while(true)
        {
           Iterable<QueueMessageItem> Messages=commentQueueClient.receiveMessages(1);
            for (QueueMessageItem message : Messages) {
                // Process the message
                String messageText = message.getMessageText();

                // Your processing logic here
                if(messageText!=null)
                {
                    response=messageText;

                }

                // Delete the message from the queue after processing
                commentQueueClient.deleteMessage(message.getMessageId(), message.getPopReceipt());
            }

        }


    }
    @PostConstruct
    public void startListeningToQueue() {
        Thread listenerThread = new Thread(this::ListenToCommentQueue);
        listenerThread.start();
    }



}