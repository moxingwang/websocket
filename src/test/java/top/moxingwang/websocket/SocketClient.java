package top.moxingwang.websocket;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import top.moxingwang.websocket.jmeter.MyStompSessionHandler;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: MoXingwang 2019-04-23 19:16
 **/
public class SocketClient {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        {
            new Thread(() -> {
                WebSocketClient transport = new StandardWebSocketClient();
                WebSocketStompClient stompClient = new WebSocketStompClient(transport);
                stompClient.setMessageConverter(new StringMessageConverter());
                String url = "ws://localhost:53105/p-trade-oc-bweb/websocket/trade/order/paid";
                ListenableFuture<StompSession> future = stompClient.connect(url, new MyStompSessionHandler());
            }).start();

        }

        countDownLatch.await();

    }
}
