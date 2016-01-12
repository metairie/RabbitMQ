package ch.ebu.rabbitmq.rpc;

import java.io.IOException;

/**
 * Created by metairie on 12-Jan-16.
 */
public class Send {
    public static void main(String[] argv) throws Exception {
        RpcClient fibonacciRpc = new RpcClient();
        System.out.println(" [x] Requesting fib(30)");
        String response = fibonacciRpc.call("30");
        System.out.println(" [.] Got '" + response + "'");
        fibonacciRpc.close();
    }
}
