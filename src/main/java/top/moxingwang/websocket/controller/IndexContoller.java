package top.moxingwang.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: MoXingwang 2019-04-22 19:45
 **/
@RestController
@RequestMapping (value = "index")
public class IndexContoller {
    Logger logger = LoggerFactory.getLogger(IndexContoller.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping(value = "send/{sessionId}/{message}")
    public void sendMessage(@PathVariable(value = "sessionId") String sessionId, @PathVariable(value = "message") String message) {
        logger.info("发送消息{}，{}", sessionId, message);
        simpMessagingTemplate.convertAndSendToUser(sessionId, "/broker", message, createHeaders(sessionId));
    }

    /**
     * 设置消息头
     *
     * @param sessionId
     * @return
     */
    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
