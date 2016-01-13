package ch.ebu.rabbitmq.rpc;

import ch.ebu.common.Connect;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by metairie on 11-Jan-16.
 */
public class Receive {
    private static Connection c = null;
    private static Channel ch = null;

    public static void main(String[] argv) throws InterruptedException, IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);

        // send
        if (ch != null) {
            ch.queueDeclare(Queue.QUEUE_NAME, Queue.durable, Queue.exclusive, Queue.autodelete, null);
            ch.basicQos(1);
            QueueingConsumer consumer = new QueueingConsumer(ch);
            ch.basicConsume(Queue.QUEUE_NAME, false, consumer);
            System.out.println(" [x] Awaiting RPC requests");
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                AMQP.BasicProperties props = delivery.getProperties();
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(props.getCorrelationId())
                        .build();
                String message = new String(delivery.getBody());
                int n = Integer.parseInt(message);
                System.out.println(" [.] fib(" + message + ")");
                String response = "" + fib(n);
                ch.basicPublish("", props.getReplyTo(), replyProps, response.getBytes());
                ch.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }

        Connect.closeAll(c, ch);
    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
