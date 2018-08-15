package com.qchery.rabbitlearning.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_CONSOLE_QUEUE;
import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_FILE_QUEUE;

/**
 * @author Chery
 * @date 2018/8/15 22:24
 */
@Component
public class FanoutListeners {

    @RabbitListener(queues = FANOUT_LOGS_CONSOLE_QUEUE)
    public void writeToConsole(String content) {
        System.out.println("print to console : " + content);
    }

    @RabbitListener(queues = FANOUT_LOGS_FILE_QUEUE)
    public void writeToFile(String content) {
        System.out.println("write to file : " + content);
    }

}
