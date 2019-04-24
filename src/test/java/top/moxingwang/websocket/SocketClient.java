package top.moxingwang.websocket;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import top.moxingwang.websocket.jmeter.MyStompSessionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: MoXingwang 2019-04-23 19:16
 **/
public class SocketClient {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

   /*     {
            List<Transport> transports = new ArrayList<>(1);
            transports.add(new WebSocketTransport(new StandardWebSocketClient()));
            WebSocketClient webSocketClient = new SockJsClient(transports);


            WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
            stompClient.setMessageConverter(new StringMessageConverter());

            String url = "http://localhost:8080/ws/test1";
            StompSessionHandler sessionHandler = new MyStompSessionHandler();
            stompClient.connect(url, sessionHandler);
        }*/

        {
            new Thread(()->{
                WebSocketClient transport = new StandardWebSocketClient();
                WebSocketStompClient stompClient = new WebSocketStompClient(transport);
                stompClient.setMessageConverter(new StringMessageConverter());
                String url = "ws://127.0.0.1:8080/ws/test1";
                ListenableFuture<StompSession> future = stompClient.connect(url,new MyStompSessionHandler());
            }).start();

        }

        countDownLatch.await();

    }
}
