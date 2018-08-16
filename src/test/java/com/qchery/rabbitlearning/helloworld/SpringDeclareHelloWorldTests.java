package com.qchery.rabbitlearning.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.TEST_DIRECT_EXCHANGE;
import static com.qchery.rabbitlearning.helloworld.HelloWorldConstants.TEST_HELLO_WORLD_BEAN_ROUTING_KEY;

/**
 * @author Chery
 * @date 2018/8/14 21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDeclareHelloWorldTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendHelloWorld() {
        amqpTemplate.convertAndSend(TEST_DIRECT_EXCHANGE, TEST_HELLO_WORLD_BEAN_ROUTING_KEY, "hello world with routingKey");
    }

}
