package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.esdiac.callmonitoring.video.VideoMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ICEMessage implements VideoMessage {
    public String event = "ice";
    public String destID;
    public String candidate;
    public String sdpMLineIndex;
    public String sdpMid;
    public String roomID;
    public String getDestID() {
        return destID;
    }

    public void setDestID(String destID) {
        this.destID = destID;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(String sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }
}
