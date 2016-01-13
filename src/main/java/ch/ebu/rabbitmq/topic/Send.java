package ch.ebu.rabbitmq.topic;

import ch.ebu.common.Connect;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by metairie on 11-Jan-16.
 */
public class Send {
    private static Connection c = null;
    private static Channel ch = null;

    public static void main(String[] argv) throws InterruptedException, IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);

        // send
        if (ch != null) {
            ch.exchangeDeclare(Queue.EXCHANGE_NAME, Queue.EXCHANGE_TYPE);
            String message = argv[0];
            String routingKey = argv[1];
            ch.basicPublish(Queue.EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' with routing key : " + routingKey );
        }
        Connect.closeAll(c, ch);
    }
}
