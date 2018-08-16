package com.qchery.rabbitlearning.routing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.ROUTING_LOGS_CONSOLE_QUEUE;
import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.ROUTING_LOGS_FILE_QUEUE;

/**
 * @author Chery
 * @date 2018/8/16 21:43
 */
@Component
public class DirectRoutingListeners {

    @RabbitListener(queues = ROUTING_LOGS_CONSOLE_QUEUE)
    public void writeToConsole(String content) {
        System.out.println("print to console : " + content);
    }

    @RabbitListener(queues = ROUTING_LOGS_FILE_QUEUE)
    public void writeToFile(String content) {
        System.out.println("write to file : " + content);
    }

}
