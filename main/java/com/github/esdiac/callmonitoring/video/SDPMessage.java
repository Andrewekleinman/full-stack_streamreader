package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SDPMessage implements VideoMessage {
    public String event = "sdp";
    public String destID;
    public String sdp;
    public String type;
    public String roomID;
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDestID() {
        return destID;
    }

    public void setDestID(String destID) {
        this.destID = destID;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
