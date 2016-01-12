package ch.ebu.rabbitmq.simple;

import ch.ebu.common.Connect;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by metairie on 11-Jan-16.
 */
public class Receive {
    private static Connection c = null;
    private static Channel ch = null;

    public static void main(String[] argv) throws java.io.IOException {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);

        // receive
        if (ch != null) {
            // declare queue if already exist do nothing
            ch.queueDeclare(Queue.QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            Consumer consumer = new DefaultConsumer(ch) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            ch.basicConsume(Queue.QUEUE_NAME, true, consumer);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Connect.closeAll(c, ch);
    }
}
