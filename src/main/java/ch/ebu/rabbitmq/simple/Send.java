package ch.ebu.rabbitmq.simple;

import ch.ebu.common.Connect;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
            String message = (argv.length<=0?"HelloWorld":argv[0]);
            // declare queue if already exist do nothing
            ch.queueDeclare(Queue.QUEUE_NAME, false, false, false, null);
            ch.basicPublish("", Queue.QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        Connect.closeAll(c, ch);
    }
}
