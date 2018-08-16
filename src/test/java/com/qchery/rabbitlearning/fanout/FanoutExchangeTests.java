package com.qchery.rabbitlearning.fanout;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.qchery.rabbitlearning.fanout.FanoutConstants.FANOUT_LOGS_EXCHANGE;

/**
 * @author Chery
 * @date 2018/8/15 22:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutExchangeTests {

    private static final String EVERY_THING_NOT_EQUALS_BLANK_STRING = "every thing not equals blank string";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testFanout() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            amqpTemplate.convertAndSend(FANOUT_LOGS_EXCHANGE, EVERY_THING_NOT_EQUALS_BLANK_STRING, RandomStringUtils.randomAlphanumeric(5));
        }
        // 睡眠等待消息被消费
        Thread.sleep(3000);
    }

}
