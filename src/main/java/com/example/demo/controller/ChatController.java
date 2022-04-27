package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chat/{userName}")
public class ChatController {
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    /**
     * 建立会话
     *
     * @param session  会话
     * @param userName 昵称
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        sessionMap.put(userName,session);
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        for(Object key : sessionMap.keySet()){
            JSONObject object = new JSONObject();
            object.put("username",key);
            array.add(object);
        }
        result.put("users",array);
        broadCast(JSON.toJSONString(result));
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(@PathParam("userName")String userName) {
        sessionMap.remove(userName);
    }

    @OnMessage
    public void OnMessage(Session session, @PathParam("userName") String userName, String message) {
        JSONObject msg = JSON.parseObject(message);
//        String ToUserName = msg.getString("to");

        Session toSession = sessionMap.get("ToUserName");
        if(toSession != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("from",userName);
            jsonObject.put("text",msg.getString("text"));
            this.sendMessage(JSON.toJSONString(jsonObject),session);
        }else{
            System.out.println("发送失败，未找到该用户");
        }
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
//    public void broadcast(String message) {
//        webSocketSet.forEach(item -> {
//            try {
//                if (item.session.isOpen()){
//                    item.session.getBasicRemote().sendText(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private void sendMessage(String msg,Session session){
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadCast(String msg){
        for(Session session:sessionMap.values()){
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
