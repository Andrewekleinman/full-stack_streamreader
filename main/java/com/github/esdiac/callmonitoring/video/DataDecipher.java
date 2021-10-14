package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

public class DataDecipher implements JoinMapper{
    public String msgType;
    public String msgSubType;
    public String roomID;
    public String userID;
    public byte[] data;
    static final ObjectMapper mapper = new ObjectMapper();
    static final ObjectReader videoObjectReader = mapper.readerFor(VideoMessage.class);


    public VideoMessage read() throws IOException {
//        JoinMessage message = new JoinMessage();
//        message.userID = this.userID;
//        message.roomID = this.roomID;
//
//        return message;
        VideoMessage message = videoObjectReader.readValue(data);
        if(message instanceof ErrorMessage)
            ((ErrorMessage) message).roomID = this.roomID;
        else if(message instanceof ICEMessage)
            ((ICEMessage) message).roomID = this.roomID;
        else if(message instanceof IdentityMessage)
            ((IdentityMessage) message).roomID = this.roomID;
        else if(message instanceof JoinMessage)
            ((JoinMessage) message).roomID = this.roomID;
        else if(message instanceof ListMessage)
            ((ListMessage) message).roomID = this.roomID;
        else if(message instanceof MSGMessage)
            ((MSGMessage) message).roomID = this.roomID;
        else if(message instanceof MuteMessage)
            ((MuteMessage) message).roomID = this.roomID;
        else if(message instanceof QuitMessage)
            ((QuitMessage) message).roomID = this.roomID;
        else if(message instanceof SDPMessage)
            ((SDPMessage) message).roomID = this.roomID;
        return message;
    }
}
