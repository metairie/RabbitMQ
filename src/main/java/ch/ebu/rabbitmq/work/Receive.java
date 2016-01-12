package ch.ebu.rabbitmq.work;

import ch.ebu.common.Connect;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by metairie on 11-Jan-16.
 */
public class Receive {
    private static Connection c = null;
    private static Channel ch = null;

    public static void main(String[] argv) throws IOException {
        Connect.init();
        c = Connect.getConnection();
        ch = Connect.getChannel(c);

        // receive
        if (ch != null) {
            ch.basicQos(1);
            // declare queue if already exist do nothing
            ch.queueDeclare(Queue.QUEUE_NAME, Queue.durable, Queue.exclusive, Queue.autodelete, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            Consumer consumer = new DefaultConsumer(ch) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    boolean bug = false;
                    System.out.println(" [x] Received '" + message + "'");
                    try {
                        doWork(message);
                    } catch (InterruptedException e) {
                        ch.basicNack(envelope.getDeliveryTag(), false, true);
                        bug = true;
                        e.printStackTrace();
                    } finally {
                        System.out.println(envelope.getDeliveryTag());
                        System.out.println(" [x] Done");
                        if (!bug) ch.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            ch.basicConsume(Queue.QUEUE_NAME, false, consumer);
        }
    }

    private static void doWork(String task) throws InterruptedException {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(50);
        if (randomInt > 10) {
            throw new InterruptedException();
        }
        System.out.println(" proceed ...");
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                System.out.print(".");
                Thread.sleep(1000);
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Connect.closeAll(c, ch);
    }
}
