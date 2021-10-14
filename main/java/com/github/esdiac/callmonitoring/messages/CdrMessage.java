package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrMessage implements CallMessage, Comparable {
    public String Event = "Cdr";
    public String callId;
    public String callerId;
    public String BillableSeconds;
    public String Disposition;

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

    public String getBillableSeconds() {
        return BillableSeconds;
    }

    public void setBillableSeconds(String billableSeconds) {
        BillableSeconds = billableSeconds;
    }

    public String getDisposition() {
        return Disposition;
    }

    public void setDisposition(String disposition) {
        Disposition = disposition;
    }


    @Override
    public boolean equals(Object o){
        return compareTo(o)==1;
    }
}
