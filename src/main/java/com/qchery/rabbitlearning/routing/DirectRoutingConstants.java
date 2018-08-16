package com.qchery.rabbitlearning.routing;

/**
 * @author Chery
 * @date 2018/8/16 21:43
 */
public class DirectRoutingConstants {
    static final String ROUTING_LOGS_CONSOLE_QUEUE = "routing.logs.console.queue";
    static final String ROUTING_LOGS_FILE_QUEUE = "routing.logs.file.queue";

    static final String ROUTING_LOGS_EXCHANGE = "routing.logs.exchange";

    static final String ROUTING_LOGS_ERROR_ROUTING_KEY = "routing.logs.error.routingKey";
    static final String ROUTING_LOGS_WARN_ROUTING_KEY = "routing.logs.warn.routingKey";
    static final String ROUTING_LOGS_INFO_ROUTING_KEY = "routing.logs.info.routingKey";
    public static final String TEST_DIRECT_EXCHANGE = "test.direct.exchange";
}
