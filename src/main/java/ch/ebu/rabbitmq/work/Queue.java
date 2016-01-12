package ch.ebu.rabbitmq.work;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by metairie on 12-Jan-16.
 */
public class Queue {
    final static String QUEUE_NAME = "work";
    final static boolean durable = true;
    final static boolean exclusive = false;
    final static boolean autodelete = false;
    final static AMQP.BasicProperties persist = MessageProperties.PERSISTENT_TEXT_PLAIN;
}
