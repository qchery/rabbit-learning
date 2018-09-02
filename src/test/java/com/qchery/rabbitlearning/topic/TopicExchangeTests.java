package com.qchery.rabbitlearning.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.qchery.rabbitlearning.topic.TopicRabbitConfiguration.TOPIC_EXCHANGE;

/**
 * @author Chery
 * @date 2018/9/2 10:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicExchangeTests {

    private static final String QUICK_ORANGE_RABBIT = "quick.orange.rabbit";
    private static final String LAZY_ORANGE_ELEPHANT = "lazy.orange.elephant";
    private static final String QUICK_ORANGE_FOX = "quick.orange.fox";
    private static final String LAZY_BROWN_FOX = "lazy.brown.fox";
    private static final String LAZY_PINK_RABBIT = "lazy.pink.rabbit";
    private static final String QUICK_BROWN_FOX = "quick.brown.fox";
    private static final String QUICK_ORANGE_MALE_RABBIT = "quick.orange.male.rabbit";
    private static final String LAZY_ORANGE_MALE_RABBIT = "lazy.orange.male.rabbit";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessages() throws InterruptedException {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, QUICK_ORANGE_RABBIT, QUICK_ORANGE_RABBIT);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, LAZY_ORANGE_ELEPHANT, LAZY_ORANGE_ELEPHANT);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, QUICK_ORANGE_FOX, QUICK_ORANGE_FOX);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, LAZY_BROWN_FOX, LAZY_BROWN_FOX);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, LAZY_PINK_RABBIT, LAZY_PINK_RABBIT);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, QUICK_BROWN_FOX, QUICK_BROWN_FOX);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, QUICK_ORANGE_MALE_RABBIT, QUICK_ORANGE_MALE_RABBIT);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, LAZY_ORANGE_MALE_RABBIT, LAZY_ORANGE_MALE_RABBIT);
        Thread.sleep(2000);
    }

}
