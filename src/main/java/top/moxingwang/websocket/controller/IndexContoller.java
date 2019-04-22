package top.moxingwang.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: MoXingwang 2019-04-22 19:45
 **/
@RestController
public class IndexContoller {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

}
