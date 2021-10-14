package com.github.esdiac.callmonitoring.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.esdiac.callmonitoring.JsonSerializable;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "Event")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateEndpointMessage.class, name = "CreateEndpoint"),
        @JsonSubTypes.Type(value = DeleteEndpointMessage.class, name = "DeleteEndpoint"),
        @JsonSubTypes.Type(value = DialBeginMessage.class, name = "DialBegin"),
        @JsonSubTypes.Type(value = DialEndMessage.class, name = "DialEnd"),
        @JsonSubTypes.Type(value = DialStateMessage.class, name = "DialState"),
        @JsonSubTypes.Type(value = HangupMessage.class, name = "Hangup"),
        @JsonSubTypes.Type(value = CdrMessage.class, name = "Cdr"),
})
public interface CallMessage extends JsonSerializable, Comparable {
    public String callId = null;
    public String callType = "Voice Call";
    @Override
    public default int compareTo(Object o) {
        CallMessage that = (CallMessage) o;
        if(this.callId == that.callId)
            return 1;
        else return 0;
    }
    @Override
    public boolean equals(Object o);

}
