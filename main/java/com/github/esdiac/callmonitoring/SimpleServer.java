package com.github.esdiac.callmonitoring;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.glassfish.grizzly.http.server.Session;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.ws.rs.core.Cookie;

import static com.github.esdiac.callmonitoring.StreamReader.*;

public class SimpleServer extends WebSocketServer {

    public SimpleServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        try {
            addUser(conn);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
//        try {
//            removeUser(conn);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (message.equals("thisuserhasloggedin")) {
            System.out.println("thisuserhasloggedin");
            try {
                sendEvents(conn);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            String[] messageSplit = message.split(",:,");

            Requester requester = new Requester();
            if (messageSplit.length == 2) {
                try {
                    String attempt = requester.sendRequest(messageSplit[0], messageSplit[1]);
                    if (attempt != null) {
                        Session userSession = new Session();
                        userSession.setSessionTimeout(100000);
                        userSession.setAttribute("username", messageSplit[0]);
                        userSession.setAttribute("password", messageSplit[1]);
                        updateUser(conn, userSession);
                        userSession.access();
                        conn.send("{\"isValid\":\"true\"}");
                    }
                    else{
                        System.out.println("Incorrect username or password");
                        conn.send("{\"isValid\":\"false\"}");
                    }

                } catch (Exception e) {
                    System.out.println("Incorrect username or password");
                    conn.send("{\"isValid\":\"false\"}");
                }
            } else {
                System.out.println("Please fill in username and password");
                conn.send("{\"isValid\":\"false\"}");
            }
        }
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }
}
