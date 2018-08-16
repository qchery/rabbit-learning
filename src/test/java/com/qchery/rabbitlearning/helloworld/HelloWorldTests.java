package com.qchery.rabbitlearning.helloworld;

import com.qchery.rabbitlearning.routing.DirectRoutingConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.qchery.rabbitlearning.helloworld.HelloWorldConstants.TEST_HELLO_WORLD_QUEUE;
import static com.qchery.rabbitlearning.helloworld.HelloWorldConstants.TEST_HELLO_WORLD_ROUTING_KEY;
import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.TEST_DIRECT_EXCHANGE;

/**
 * @author Chery
 * @date 2018/8/14 21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;
    private Queue queue;

    @Before
    public void doBefore() {
        queue = new Queue(TEST_HELLO_WORLD_QUEUE, true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void sendHelloWorldUseDefaultExchange() {
        // 只声明队列，不声明Exchange，发布消息时会使用默认的Exchange，直接以队列名为识别标记
        // 若不指定RoutingKey，会使用默认的RoutingKey，无法正常加入队列
        amqpTemplate.convertAndSend(TEST_HELLO_WORLD_QUEUE, "hello world");
    }

    @Test
    public void sendHelloWorldUseDeclareExchange() {
        DirectExchange directExchange = new DirectExchange(TEST_DIRECT_EXCHANGE, true, false);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(TEST_HELLO_WORLD_ROUTING_KEY));
        // 此处必须指定Exchange与RoutingKey，否则使用默认的Exchange无法指定需要发布消息到哪个队列
        amqpTemplate.convertAndSend(TEST_DIRECT_EXCHANGE, TEST_HELLO_WORLD_ROUTING_KEY, "hello world with routingKey");
    }

    @Test
    public void getHelloWorldFromQueue() {
        Object convert = amqpTemplate.receiveAndConvert(queue.getName());
        System.out.println(convert);
    }

}
