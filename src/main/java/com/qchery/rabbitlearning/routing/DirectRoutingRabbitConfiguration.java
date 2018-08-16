package com.qchery.rabbitlearning.routing;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.*;

/**
 * @author Chery
 * @date 2018/8/16 21:45
 */
@Configuration
public class DirectRoutingRabbitConfiguration {

    @Bean
    public Queue routingConsoleQueue() {
        return QueueBuilder.durable(ROUTING_LOGS_CONSOLE_QUEUE).build();
    }

    @Bean
    public Queue routingFileQueue() {
        return QueueBuilder.durable(ROUTING_LOGS_FILE_QUEUE).build();
    }

    @Bean
    public Exchange routingDirectExchange() {
        return ExchangeBuilder.directExchange(ROUTING_LOGS_EXCHANGE).build();
    }

    @Bean
    public Binding routingErrorBindingToFile() {
        return BindingBuilder.bind(routingFileQueue()).to(routingDirectExchange()).with(ROUTING_LOGS_ERROR_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding routingWarnBindingToConsole() {
        return BindingBuilder.bind(routingConsoleQueue()).to(routingDirectExchange()).with(ROUTING_LOGS_WARN_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding routingErrorBindingToConsole() {
        return BindingBuilder.bind(routingConsoleQueue()).to(routingDirectExchange()).with(ROUTING_LOGS_ERROR_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding routingInfoBindingToConsole() {
        return BindingBuilder.bind(routingConsoleQueue()).to(routingDirectExchange()).with(ROUTING_LOGS_INFO_ROUTING_KEY).noargs();
    }

}
