package com.github.esdiac.callmonitoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.esdiac.callmonitoring.messages.*;
import com.github.esdiac.callmonitoring.video.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import org.glassfish.grizzly.http.server.Session;

public class StreamReader {
    static HashMap<String,CallMessage> createMap = new HashMap();
    static HashMap<String,CallMessage> updateMap = new HashMap();
    static HashMap<String,VideoMessage> videoCallMap = new HashMap();
    static HashMap<String,VideoMessage> videoUserMap = new HashMap();
    static HashMap<WebSocket,Session> users = new HashMap();
    static final ObjectMapper mapper = new ObjectMapper();
    static final ObjectReader objectReader = mapper.readerFor(CallMessage.class);
    static final ObjectReader videoObjectReader = mapper.readerFor(VideoMessage.class);
    static final ObjectReader joinObjectReader = mapper.readerFor(JoinMapper.class);
    static String host = "0.0.0.0";
    static int port = 8882;
    static WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
    public static void main(String[] args) throws Exception {
        String topicName = "call";
        String videoTopicName = "signaler";
        Properties props = new Properties();
        props.put("bootstrap.servers", "voip.ml:9092");
        props.put("group.id", "" + new Random().nextInt());
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "100000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer
                <String, String>(props);

        consumer.subscribe(Arrays.asList(videoTopicName,topicName));
        System.out.println("Subscribed to topic " + topicName +" and "+videoTopicName);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                server.run();
            }
        });
        t.start();

        Date expiry = new Date();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                if (record.topic().equals("call")) {
                    CallMessage message = objectReader.readValue(record.value());
                    if (message instanceof CreateEndpointMessage) {
                        String today = expiry.toString();
                        System.out.println(expiry.toString());
                        //if (((CreateEndpointMessage) message).date != null && ((CreateEndpointMessage) message).Callee != null && ((CreateEndpointMessage) message).date.equals(today)) {
                        createMap.put(((CreateEndpointMessage) message).getCallId(), message);
                        System.out.println("create");
                        Thread delayDelete = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    TimeUnit.MINUTES.sleep(1);
                                } catch (InterruptedException e) {
                                }
                                if (!updateMap.containsKey(((CreateEndpointMessage) message).getCallId())) {
                                    createMap.remove(((CreateEndpointMessage) message).getCallId());
                                    DeleteEndpointMessage del = new DeleteEndpointMessage();
                                    del.callId = ((CreateEndpointMessage) message).getCallId();
                                    try {
                                        server.broadcast(mapper.writeValueAsString(del));
                                    } catch (JsonProcessingException e) {
                                    }
                                }
                            }
                        });
                        delayDelete.start();

                    } else if (message instanceof DeleteEndpointMessage) {
                        createMap.put(((DeleteEndpointMessage) message).callId, message);
                        updateMap.put(((DeleteEndpointMessage) message).callId, message);
                        createMap.remove(((DeleteEndpointMessage) message).callId, message);
                        updateMap.remove(((DeleteEndpointMessage) message).callId, message);
                        System.out.println("delete");
                    } else if (message instanceof DialBeginMessage) {
                        updateMap.put(((DialBeginMessage) message).callId, message);
                        System.out.println("dialbegin");
                    } else if (message instanceof DialEndMessage) {
                        updateMap.put(((DialEndMessage) message).callId, message);
                        System.out.println("dialend");
                    } else if (message instanceof DialStateMessage) {
                        updateMap.put(((DialStateMessage) message).callId, message);
                        System.out.println("dialstate");
                    } else if (message instanceof HangupMessage) {
                        updateMap.put(((HangupMessage) message).callId, message);
                        updateMap.remove(((HangupMessage) message).callId, message);
                        System.out.println("hangup");
                    } else if (message instanceof CdrMessage) {
                        updateMap.put(((CdrMessage) message).callId, message);
                        System.out.println("cdr");
                    }
                    System.out.println(updateMap.values().toString());
                    server.broadcast(mapper.writeValueAsString(message));
                }
                if(record.topic().equals("signaler")){
                    System.out.println(record.value());
                    VideoMessage message;
                    try {
                        message = videoObjectReader.readValue(record.value());
                    }catch(Exception e) {
                        JoinMapper joinMap = joinObjectReader.readValue(record.value());
                        message = ((DataDecipher)joinMap).read();
                    }

                    //System.out.println(mapper.writeValueAsString(message));
                    videoHandle(message);
                }
            }
        }
    }

    public static void addUser(WebSocket client) throws JsonProcessingException {
        Session emptySession = new Session();
        users.put(client,emptySession);
        for(CallMessage message: createMap.values()){
            client.send(mapper.writeValueAsString(message));

            server.broadcast(mapper.writeValueAsString(message));
        }
        for(CallMessage message: updateMap.values()){
            server.broadcast(mapper.writeValueAsString(message));
        }
//        for(VideoMessage message: videoCallMap.values()){
//            server.broadcast(mapper.writeValueAsString(message));
//        }
//        for(VideoMessage message: videoUserMap.values()){
//            server.broadcast(mapper.writeValueAsString(message));
//        }
    }

    public static void sendEvents(WebSocket client) throws JsonProcessingException {
        System.out.println(createMap.values());
        for(CallMessage message: createMap.values()){
            server.broadcast(mapper.writeValueAsString(message));
        }
        for(CallMessage message: updateMap.values()){
            server.broadcast(mapper.writeValueAsString(message));
        }
        for(VideoMessage message: videoCallMap.values()){
            server.broadcast(mapper.writeValueAsString(message));
        }
        for(VideoMessage message: videoUserMap.values()){
            server.broadcast(mapper.writeValueAsString(message));
        }
    }
    public static void videoHandle(VideoMessage message) throws JsonProcessingException {
        if(message instanceof ErrorMessage){
            System.out.println(((ErrorMessage) message).event);
        }else if(message instanceof ICEMessage){
            System.out.println(((ICEMessage) message).event);
        }else if(message instanceof IdentityMessage){
            System.out.println(((IdentityMessage) message).event);
            videoCallMap.put(((IdentityMessage) message).userID,message);
        }else if(message instanceof JoinMessage){
            System.out.println(((JoinMessage) message).event);
            videoUserMap.put(((JoinMessage) message).userID,message);

        }else if(message instanceof ListMessage){
            System.out.println(((ListMessage) message).event);

        }else if(message instanceof MSGMessage){
            System.out.println(((MSGMessage) message).event);

        }else if(message instanceof MuteMessage){
            System.out.println(((MuteMessage) message).event);

        }else if(message instanceof QuitMessage){
            System.out.println(((QuitMessage) message).event);
            videoUserMap.put(((QuitMessage) message).userID,message);
            videoUserMap.remove(((QuitMessage) message).userID);

        }else if(message instanceof SDPMessage){
            System.out.println(((SDPMessage) message).event);

        }
        else if(message instanceof ClosedMessage){
            System.out.println(((ClosedMessage) message).event);
            videoCallMap.put(((ClosedMessage) message).userID,message);
            videoCallMap.remove(((ClosedMessage) message).userID);
        }
        server.broadcast(mapper.writeValueAsString(message));
    }
    public static void updateUser(WebSocket client, Session session) {
        users.put(client,session);
    }
    public static void removeUser(WebSocket client) throws JsonProcessingException {
        users.remove(client);
    }
}
