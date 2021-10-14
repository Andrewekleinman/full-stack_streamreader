package com.github.esdiac.callmonitoring.video;

import com.github.esdiac.callmonitoring.video.VideoMessage;
import com.github.esdiac.callmonitoring.video.user;

import java.util.List;

public class CallStateMessage implements VideoMessage {
    public String event = "call_state";
    public String userID;
    public String roomID;
    public String destination;
    public String ipAddress;
    public String lang;
    public String callID;
    public String destID;
    public String err;
    public String state;
    public String playAnnouncement;
    public String userAgent;
}
