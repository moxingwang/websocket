package top.moxingwang.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.sockjs.transport.handler.WebSocketTransportHandler;

import java.util.Map;

/**
 * websocket配置类
 * https://stackoverflow.com/questions/43637864/spring-websocket-configuration-using-websocketmessagebrokerconfigurationsupport/43640903
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private String domain = "*";


    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String endPoint = "/ws/test1";

        //WebSocketClient建立连接
        RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
        registry.addEndpoint(endPoint)
                .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
                .setAllowedOrigins(domain);

        //websocket js建立连接
        registry
                .addEndpoint(endPoint)
                .setAllowedOrigins(domain)
                .addInterceptors(new HandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                        String origin = request.getHeaders().getOrigin();
                        return origin != null;
                    }

                    @Override
                    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                               WebSocketHandler wsHandler, Exception exception) {
                    }
                })
                .withSockJS()
                .setTransportHandlers(new WebSocketTransportHandler(handshakeHandler()))//设置支持的协议，如果不设置默认全部支持
                .setHeartbeatTime(25000);
    }

    @Bean
    public HandshakeHandler handshakeHandler() {
        return new DefaultHandshakeHandler();
    }

    /**
     * 配置信息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称
        registry.enableSimpleBroker("/broker");
        registry.setUserDestinationPrefix("/trade/topic");
    }

    /**
     * WebSocket 连接
     *
     * @return
     */
    @Bean
    public WebsocketConnectListener websocketConnectListener() {
        return new WebsocketConnectListener();
    }

    /**
     * WebSocket 断开连接
     *
     * @return
     */
    @Bean
    public WebSocketDisconnectListener webSocketDisconnectListener() {
        return new WebSocketDisconnectListener();
    }

    /**
     * Websocket Error处理
     *
     * @return
     */
    @Bean
    public StompSubProtocolErrorHandler webSocketHandler() {
        return new WebSocketErrorHandler();
    }


    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();

        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}

