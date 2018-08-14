package com.qchery.rabbitlearning.helloworld;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.qchery.rabbitlearning.helloworld.HelloWorldConstants.TEST_HELLO_WORLD_BEAN_QUEUE;
import static com.qchery.rabbitlearning.helloworld.HelloWorldConstants.TEST_HELLO_WORLD_BEAN_ROUTING_KEY;

/**
 * @author Chery
 * @date 2018/8/14 23:06
 */
@Configuration
public class HelloWorldRabbitConfiguration {

    @Bean
    public Binding declareBinding(DirectExchange directExchange) {
        return BindingBuilder.bind(declareQueue()).to(directExchange).with(TEST_HELLO_WORLD_BEAN_ROUTING_KEY);
    }

    @Bean
    public Queue declareQueue() {
        return new Queue(TEST_HELLO_WORLD_BEAN_QUEUE, true, false, false);
    }

}
