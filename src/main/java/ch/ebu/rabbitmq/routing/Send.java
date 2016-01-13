package ch.ebu.rabbitmq.routing;

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
            String message = getMessage(argv);
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(2);
            ch.basicPublish(Queue.EXCHANGE_NAME, ""+randomInt, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "' with " + randomInt);
        }
        Connect.closeAll(c, ch);
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
