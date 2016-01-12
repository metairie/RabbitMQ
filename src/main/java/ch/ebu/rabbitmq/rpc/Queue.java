package ch.ebu.rabbitmq.rpc;

import com.rabbitmq.client.AMQP;

/**
 * Created by metairie on 12-Jan-16.
 */
public class Queue {
    final static String QUEUE_NAME = "rpc";
    final static boolean durable = false;
    final static boolean exclusive = false;
    final static boolean autodelete = false;
    final static AMQP.BasicProperties persist = null;
}
