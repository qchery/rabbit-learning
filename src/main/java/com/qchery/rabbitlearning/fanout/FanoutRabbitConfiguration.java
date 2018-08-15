package com.qchery.rabbitlearning.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_CONSOLE_QUEUE;
import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_EXCHANGE;
import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_FILE_QUEUE;

/**
 * @author Chery
 * @date 2018/8/15 21:58
 */
@Configuration
public class FanoutRabbitConfiguration {

    /**
     * 声明输出到控制台的队列
     */
    @Bean
    public Queue declareConsoleQueue(){
        return QueueBuilder.nonDurable(FANOUT_LOGS_CONSOLE_QUEUE).autoDelete().build();
    }

    /**
     * 声明输出到文件的队列
     */
    @Bean
    public Queue declareFileQueue() {
        return QueueBuilder.nonDurable(FANOUT_LOGS_FILE_QUEUE).autoDelete().build();
    }

    /**
     * 使用FanoutExchange实现发布/订阅场景，一个生产者多个消费者
     * 当发布一条新消息时，会广播到所有绑定到该Exchange的队列中去，不使用RoutingKey
     */
    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.fanoutExchange(FANOUT_LOGS_EXCHANGE).durable(false).build();
    }

    @Bean
    public Binding declareConsoleBinding() {
        return BindingBuilder.bind(declareConsoleQueue()).to(declareExchange()).with("").noargs();
    }

    @Bean
    public Binding declareFileBinding(){
        return BindingBuilder.bind(declareFileQueue()).to(declareExchange()).with("").noargs();
    }

}
