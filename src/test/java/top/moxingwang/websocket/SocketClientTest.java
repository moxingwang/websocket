package top.moxingwang.websocket;

import top.moxingwang.websocket.jmeter.WebsocketTest;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: MoXingwang 2019-04-23 19:16
 **/
public class SocketClientTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        WebsocketTest.mainTest();

        countDownLatch.await();

    }
}
