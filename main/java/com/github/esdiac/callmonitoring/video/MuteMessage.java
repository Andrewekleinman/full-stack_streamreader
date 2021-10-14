package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MuteMessage implements VideoMessage {
    public String event = "mute";
    public String type;
    public String state;
    public String userID;
    public String roomID;
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




}
