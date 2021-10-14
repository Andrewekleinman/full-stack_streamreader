package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuitMessage implements VideoMessage {
    public String event = "quit";
    public String userID;
    public String roomID;
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
