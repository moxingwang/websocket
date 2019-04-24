package top.moxingwang.websocket.jmeter;


import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CountDownLatch;


public class WebsocketTest extends AbstractJavaSamplerClient {
    public static void mainTest() {

        {
            new Thread(() -> {
                WebSocketClient transport = new StandardWebSocketClient();
                WebSocketStompClient stompClient = new WebSocketStompClient(transport);
                stompClient.setMessageConverter(new StringMessageConverter());
                String url = "wss://ordersocket.mklmall.com/p-trade-oc-bweb/websocket/trade/order/paid";
                ListenableFuture<StompSession> future = stompClient.connect(url, new MyStompSessionHandler());
            }).start();

        }


    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        SampleResult result = new SampleResult();
        result.sampleStart();
        try {

            WebsocketTest.mainTest();


        } finally {
            result.sampleEnd();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
