package com.qchery.rabbitlearning.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.qchery.rabbitlearning.topic.TopicRabbitConfiguration.TOPIC_ONE_QUEUE;
import static com.qchery.rabbitlearning.topic.TopicRabbitConfiguration.TOPIC_TWO_QUEUE;

/**
 * @author Chery
 * @date 2018/9/2 10:35
 */
@Component
public class TopicListeners {

    @RabbitListener(queues = TOPIC_ONE_QUEUE)
    public void printQueueOne(String message) {
        System.out.println("queue1 : " + message);
    }

    @RabbitListener(queues = TOPIC_TWO_QUEUE)
    public void printQueueTwo(String message) {
        System.out.println("queue2 : " + message);
    }

}
