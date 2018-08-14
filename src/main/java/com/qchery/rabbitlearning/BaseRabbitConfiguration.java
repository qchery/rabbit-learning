package com.qchery.rabbitlearning;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chery
 * @date 2018/8/14 23:54
 */
@Configuration
public class BaseRabbitConfiguration {

    @Bean
    public DirectExchange declareDirectExchange() {
        return new DirectExchange(RabbitConstants.TEST_DIRECT_EXCHANGE, true, false);
    }

}
