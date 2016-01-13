package ch.ebu.rabbitmq.routing;

import ch.ebu.common.Connect;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by metairie on 11-Jan-16.
 */
public class Receive {
    private static Connection c = null;
    private static Channel ch = null;

    public static void main(String[] argv) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);

        // receive
        if (ch != null) {
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            ch.exchangeDeclare(Queue.EXCHANGE_NAME, Queue.EXCHANGE_TYPE);
            String queueName = ch.queueDeclare().getQueue();
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(2);
            ch.queueBind(queueName, Queue.EXCHANGE_NAME, "" + randomInt);
            System.out.println(" I'm consuming only " + randomInt);
            Consumer consumer = new DefaultConsumer(ch) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            ch.basicConsume(queueName, true, consumer);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Connect.closeAll(c, ch);
    }
}
