package ch.ebu.rabbitmq.rpc;

import ch.ebu.common.Connect;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by metairie on 11-Jan-16.
 */
public class RpcClient {
    private static Connection c = null;
    private static Channel ch = null;
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RpcClient() throws Exception {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);
        replyQueueName = ch.queueDeclare().getQueue();
        consumer = new QueueingConsumer(ch);
        ch.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) throws Exception {
        String response = null;
        String corrId = java.util.UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties()
                .builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        ch.basicPublish("", Queue.QUEUE_NAME, props, message.getBytes());

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody());
                break;
            }
        }

        return response;
    }

    public void close() throws Exception {
        Connect.closeAll(c, ch);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Connect.closeAll(c, ch);
    }
}
