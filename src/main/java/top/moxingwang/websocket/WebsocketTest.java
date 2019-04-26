package top.moxingwang.websocket;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class WebsocketTest {
    public static void mainTest() {

        {
            new Thread(() -> {
                WebSocketClient transport = new StandardWebSocketClient();
                WebSocketStompClient stompClient = new WebSocketStompClient(transport);
                stompClient.setMessageConverter(new StringMessageConverter());
                String url = "wss://localhost:8080/p-trade-oc-bweb/websocket/trade/order/paid";
                ListenableFuture<StompSession> future = stompClient.connect(url, new MyStompSessionHandler());
            }).start();

        }

    }

}
