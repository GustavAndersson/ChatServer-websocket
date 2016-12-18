/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author guan97005
 */
@ServerEndpoint("/chatserver")
public class ChatserverEndPoint {

    static Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void open(Session session) throws IOException {
        sessions.add(session);
    }

    @OnClose
    public void close(Session session) throws IOException {
        sessions.remove(session);
        Iterator<Session> users = sessions.iterator();
        while (users.hasNext()) {
            Session user = users.next();
            user.getBasicRemote().sendText(buildJsonUsers());
            user.getBasicRemote().sendText(buildJsonData("System", (String) session.getUserProperties().get("username") + " have left the chatroom."));

        }
    }

    @OnMessage
    public void onMessage(String message, Session userSession) throws IOException {
        String username = (String) userSession.getUserProperties().get("username");

        if (username == null) { //användarnamn saknas
            //använd första meddelandet som användarnamn
            userSession.getUserProperties().put("username", message);
            String returnMessage = buildJsonData("System", " You are now connected as: " + message);
            userSession.getBasicRemote().sendText(returnMessage);
            Iterator<Session> users = sessions.iterator();
            while (users.hasNext()) {
                users.next().getBasicRemote().sendText(buildJsonUsers());
            }
        } else {
            Iterator<Session> iterator = sessions.iterator();
            while (iterator.hasNext()) {
                iterator.next().getBasicRemote().sendText(buildJsonData(username, message));
            }
        }
    }

    private String buildJsonData(String username, String message) {
                //skapar json {"username":username, "message":message} //fast som text
        JsonObject object;
        if (message.contains("#")) {
             object = Calculator.calc(username, message);
             System.out.println("Du har kommit till #");
        }else{
            object = Json.createObjectBuilder().add("username", username).add("message", message).build();
            System.out.println("Du är på else");
        }
        
        
        return object.toString();
    }

    private String buildJsonUsers() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        Iterator<Session> users = sessions.iterator();
        while (users.hasNext()) {
            try {
                jsonArrayBuilder.add(Json.createObjectBuilder().add("username", (String) users.next().getUserProperties().get("username")).build());
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
        return jsonArrayBuilder.build().toString();
    }

}
