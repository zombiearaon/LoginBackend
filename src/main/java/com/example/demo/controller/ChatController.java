package com.example.demo.controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat/{userName}")
public class ChatController {
    private String nickName;

    /**
     * socket会话
     */
    private Session session;

    /**
     * 存放websocket对象
     */
    private static CopyOnWriteArraySet<ChatController> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     * 用来记录用户名和该session进行绑定
     */
    private static Map<String, Session> map = new ConcurrentHashMap<>();

    /**
     * 记录用户 session_id:nickName
     */
    private static Map<String,String> peoples = new ConcurrentHashMap<>();

    /**
     * 建立会话
     *
     * @param session  会话
     * @param nickName 昵称
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickName") String nickName) {
//        var msg = new JSONObject();
        this.session = session;
        this.nickName = nickName;
        peoples.put(session.getId(), nickName);
        map.put(session.getId(), session);
        webSocketSet.add(this);
//        log.info("{}加入连接，当前在线有{}人", nickName, webSocketSet.size());
//        //消息类型，0-连接成功，1-用户消息
//        msg.put("type", 0);
//        //在线人数
//        msg.put("people_num", webSocketSet.size());
//        msg.put("people", peoples);
//        //昵称
//        msg.put("name", nickName);
//        //频道号
//        msg.put("aisle", session.getId());
//        broadcast(msg.toJSONString());
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() throws IOException {
        peoples.remove(this.session.getId());
//        var msg = new JSONObject();
//        msg.put("type", 2);
//        //在线人数
//        msg.put("people_num", webSocketSet.size()-1);
//        msg.put("people", peoples);
//        //昵称
//        msg.put("name", nickName);
//        broadcast(msg.toJSONString());
        //从set中删除
        webSocketSet.remove(this);
//        log.info("{}退出聊天室，剩余{}人", nickName, webSocketSet.size());
    }

    @OnMessage
    public void OnMessage(Session session, @PathParam("nickName") String nickName, String msg) {
//        Message message;
//        message = JSON.parseObject(msg, Message.class);
//        var sendMsg = new HashMap<String, Object>(16);
//        if (message.getType() == 1) {
//            // 私聊
//            var fromUser = map.get(session.getId());
//            var toUser = map.get(message.getToUser());
//            var user = Optional.ofNullable(toUser);
//            user.ifPresentOrElse(u -> {
//                sendMsg.put("type", 1);
//                sendMsg.put("name", nickName);
//                sendMsg.put("msg", message.getMsg());
//                var m = JSON.toJSONString(sendMsg);
//                try {
//                    fromUser.getAsyncRemote().sendText(m);
//                    u.getBasicRemote().sendText(m);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                log.info(JSON.toJSONString(msg));
//            }, () -> {
//                sendMsg.put("type", 1);
//                sendMsg.put("name", nickName);
//                sendMsg.put("msg", "-*** 系统消息：对方未在线! ***-");
//                fromUser.getAsyncRemote().sendText(JSON.toJSONString(sendMsg));
//            });
//        } else {
//            var bm = new JSONObject();
//            bm.put("type", 1);
//            bm.put("name", nickName);
//            bm.put("msg", message.getMsg());
//            //群发消息
//            broadcast(bm.toJSONString());
//        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     */
    public void broadcast(String message) {
        webSocketSet.forEach(item -> {
            try {
                if (item.session.isOpen()){
                    item.session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
