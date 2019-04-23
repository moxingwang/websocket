package top.moxingwang.websocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * websocket连接监听
 */
public class WebsocketConnectListener implements ApplicationListener<SessionConnectEvent> {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketConnectListener.class);

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        //获取Session连接信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //用户ID
        String param1 = sha.getFirstNativeHeader("param1");
        String param2 = sha.getFirstNativeHeader("param2");
        //获取SessionId
        String sessionId = sha.getSessionId();

        logger.info("websocket建立连接 {}，{}，{}", sessionId, param1, param2);

    }
}
