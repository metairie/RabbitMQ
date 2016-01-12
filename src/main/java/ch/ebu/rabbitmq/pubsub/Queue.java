package ch.ebu.rabbitmq.pubsub;

import com.rabbitmq.client.AMQP;

/**
 * Created by metairie on 12-Jan-16.
 */
public class Queue {
    final static String EXCHANGE_NAME = "pubsub";
    final static String EXCHANGE_TYPE = "fanout";
    final static boolean durable = false;
    final static boolean exclusive = false;
    final static boolean autodelete = false;
    final static AMQP.BasicProperties persist = null;
}
