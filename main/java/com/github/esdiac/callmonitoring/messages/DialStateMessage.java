package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DialStateMessage implements CallMessage,Comparable{
    public String Event = "DialState";
    public String callId;
    public String callerId;
    public String DialStatus;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
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
