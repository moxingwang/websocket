/*
package top.moxingwang.websocket.jmeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

*/
/**
 * @description:
 * @author: MoXingwang 2019-04-23 18:56
 **//*

public class WebsocketTest extends AbstractJavaSamplerClient {
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
            stompClient.setMessageConverter(new StringMessageConverter());

            String url = "ws://localhost:8080/ws/test1";
            StompSessionHandler sessionHandler = new MyStompSessionHandler();
            stompClient.connect(url, sessionHandler);


        } finally {
            result.sampleEnd();
        }
        return result;
    }
}
*/
