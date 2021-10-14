package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DialEndMessage implements CallMessage,Comparable{
    public String Event = "DialEnd";
    public String callId;
    public String callerId;
    public String DialStatus;
    public String startTime = ""+(new Date().getTime());

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime() {

    }
    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getDialStatus() {
        return DialStatus;
    }

    public void setDialStatus(String dialStatus) {
        DialStatus = dialStatus;
    }

    @Override
    public boolean equals(Object o){
        return compareTo(o)==1;
    }
}
