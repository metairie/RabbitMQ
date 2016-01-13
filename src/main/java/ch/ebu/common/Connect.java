package ch.ebu.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * Created by metairie on 12-Jan-16.
 */
public class Connect {
    static ConnectionFactory factory = new ConnectionFactory();

    public static Connection getConnection() {
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Channel getChannel(Connection c) {
        try {
            return c.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // ------------------------------------------------------------ end
    public static void closeAll(Connection c, Channel ch) {
        try {
            ch.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" END messaging. all connections are closed.");
    }

    public static void init() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        String uri = System.getProperty("uri");
        if (uri == null || uri.equalsIgnoreCase("")) {
            Properties properties = new Properties();
            InputStream s = Connect.class.getClass().getResourceAsStream("/rabbitmq.properties");
            properties.load(s);
            s.close();
            uri = properties.getProperty("uri");
            System.setProperty("uri", uri);
        }
        factory.setUri(uri);
        factory.setVirtualHost("neos");
        System.out.println(" START messaging");
    }
}
