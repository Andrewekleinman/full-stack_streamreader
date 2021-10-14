package com.github.esdiac.callmonitoring.video;

import com.github.esdiac.callmonitoring.video.VideoMessage;
import com.github.esdiac.callmonitoring.video.user;

import java.util.List;

public class ClosedMessage implements VideoMessage {
    public String event = "identity";
    public String userID;
    public String reason;
    public String roomID;
}
