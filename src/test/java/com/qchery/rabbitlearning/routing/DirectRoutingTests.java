package com.qchery.rabbitlearning.routing;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.qchery.rabbitlearning.routing.DirectRoutingConstants.*;

/**
 * @author Chery
 * @date 2018/8/16 22:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectRoutingTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private String[] routingKeys = new String[]{
            ROUTING_LOGS_ERROR_ROUTING_KEY,
            ROUTING_LOGS_WARN_ROUTING_KEY,
            ROUTING_LOGS_INFO_ROUTING_KEY
    };

    /**
     * 随机生成10条消息，随机使用3种不同的RoutingKey，消息会根据RoutingKey路由到不同的队列
     */
    @Test
    public void testRouting() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int index = RandomUtils.nextInt(0, 3);
            String routingKey = routingKeys[index];
            String message = routingKey + "-" + RandomStringUtils.randomNumeric(5);
            amqpTemplate.convertAndSend(ROUTING_LOGS_EXCHANGE, routingKey, message);
        }
        // 等待所有消息被消费掉
        Thread.sleep(3000);
    }

}
