package com.qchery.rabbitlearning.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发送给 Topic 的 RoutingKey 有着特定的格式要求，必须是以.分隔的多个单词
 * 单词可以包含两种通配符，描述如下：
 * 1、* : 可以替代一个单词
 * 2、# : 可以替代0个或多个单词
 * 如果RoutingKey中不包含通配符，则效果与Direct一致
 *
 * 事例：
 * RoutingKey 格式为 <speed>.<colour>.<species>
 * 1、quick.orange.rabbit：分发到两个队列
 * 2、lazy.orange.elephant：分发到两个队列
 * 3、quick.orange.fox：分发到第一个队列
 * 4、lazy.brown.fox：分发到第二个队列
 * 5、lazy.pink.rabbit：分发到第二个队列，虽然匹配两个RoutingKey，但同一个队列只分发一次
 * 6、quick.brown.fox：没有匹配的RoutingKey，被直接丢弃
 * 7、quick.orange.male.rabbit：无匹配的RoutingKey，直接丢弃
 * 8、lazy.orange.male.rabbit：颁发到第二个队列，匹配lazy.#
 *
 * @author Chery
 * @date 2018/9/2 09:56
 */
@Configuration
public class TopicRabbitConfiguration {

    public static final String ORANGE_ROUTING_KEY = "*.orange.*";
    public static final String RABBIT_END_ROUTING_KEY = "*.*.rabbit";
    public static final String LAZY_START_ROUTING_KEY = "lazy.#";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String TOPIC_ONE_QUEUE = "topic.one.queue";
    public static final String TOPIC_TWO_QUEUE = "topic.two.queue";

    /**
     * 接收一种RoutingKey
     * 1、*.orange.*
     */
    @Bean
    public Queue topicQueueOne() {
        return QueueBuilder.durable(TOPIC_ONE_QUEUE).build();
    }

    /**
     * 接收两种RoutingKey
     * 1、 *.*.rabbit　表示所有以rabbit结尾，开头为任意两个单词
     * 2、 lazy.#　表示以lazy开头，结尾为0个或多个任意单词
     */
    @Bean
    public Queue topicQueueTwo() {
        return QueueBuilder.durable(TOPIC_TWO_QUEUE).build();
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).build();
    }

    @Bean
    public Binding topicOrangeBindingToQueueOne() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(ORANGE_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding topicRabbitEndBindingToQueueTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(RABBIT_END_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding topicLazyStartBindingToQueueTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(LAZY_START_ROUTING_KEY).noargs();
    }

}
