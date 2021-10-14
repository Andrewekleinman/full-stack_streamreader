package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteEndpointMessage implements CallMessage,Comparable{
    public String Event = "DeleteEndpoint";
    public String callId;
    public String callerId;

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


    @Override
    public boolean equals(Object o){
        return compareTo(o)==1;
    }
}
