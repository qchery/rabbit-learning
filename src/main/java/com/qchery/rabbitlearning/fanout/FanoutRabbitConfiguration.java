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
    public Queue fanoutConsoleQueue(){
        return QueueBuilder.durable(FANOUT_LOGS_CONSOLE_QUEUE).build();
    }

    /**
     * 声明输出到文件的队列
     */
    @Bean
    public Queue fanoutFileQueue() {
        return QueueBuilder.durable(FANOUT_LOGS_FILE_QUEUE).build();
    }

    /**
     * 使用FanoutExchange实现发布/订阅场景，一个生产者多个消费者
     * 当发布一条新消息时，会广播到所有绑定到该Exchange的队列中去，不使用RoutingKey
     */
    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange(FANOUT_LOGS_EXCHANGE).build();
    }

    @Bean
    public Binding fanoutConsoleBinding() {
        return BindingBuilder.bind(fanoutConsoleQueue()).to(fanoutExchange()).with("").noargs();
    }

    @Bean
    public Binding fanoutFileBinding(){
        return BindingBuilder.bind(fanoutFileQueue()).to(fanoutExchange()).with("").noargs();
    }

}
