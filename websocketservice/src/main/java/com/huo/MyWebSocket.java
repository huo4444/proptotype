package com.huo;

import com.gemstone.gemfire.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 霍闯【2597】
 *         company qianmi.com
 *         Date 2017-06-06
 */
@ServerEndpoint("/websocket")
@Component
public class MyWebSocket {

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    @Autowired
    Region customer;


    @OnOpen
    public void onOpen (Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());

         Region r= SpringUtils.getApplicationContext().getBean(Region.class);
        r.put("hello","value");



    }

    @OnClose
    public void onClose (){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        // 群发消息
        for ( MyWebSocket item : webSocketSet ){
            item.sendMessage(message);
        }
    }

    public void sendMessage (String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized  int getOnlineCount (){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        MyWebSocket.onlineCount--;
    }

}
