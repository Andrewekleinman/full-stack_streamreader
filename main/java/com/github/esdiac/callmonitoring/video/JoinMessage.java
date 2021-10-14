package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinMessage implements VideoMessage {
    public String event = "join";
    public String userID;
    public String displayName;
    public String shitBrowser;
    public String roomID;
    public List<user> iceServers;
    public String date = new Date().toString();
    public String callType = "Video Call";
    public String startTime = ""+(new Date().getTime());

    public List<user> getIceServers() {
        return iceServers;
    }

    public void setIceServers(List<user> iceServers) {
        this.iceServers = iceServers;
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

    public String getShitBrowser() {
        return shitBrowser;
    }

    public void setShitBrowser(String shitBrowser) {
        this.shitBrowser = shitBrowser;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = new Date(date).toString();
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime() {

    }
}
