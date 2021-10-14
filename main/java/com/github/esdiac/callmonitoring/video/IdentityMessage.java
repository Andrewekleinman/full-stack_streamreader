package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityMessage implements VideoMessage {
    public String event = "identity";
    public String userID;
    public String displayName;
    public List<user> iceServers;
    public String date;
    public String callType = "Video Call";
    public String roomID;
    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<user> getIceServers() {
        return iceServers;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        long epoch = Long.parseLong( date );
        Date expiry = new Date( epoch * 1000 );
        this.date = expiry.toString();
    }

    public void setIceServers(List<user> iceServers) {
        this.iceServers = iceServers;
    }
}
