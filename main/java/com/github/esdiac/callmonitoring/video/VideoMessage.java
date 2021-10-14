package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.esdiac.callmonitoring.JsonSerializable;
import com.github.esdiac.callmonitoring.messages.*;
import org.glassfish.jersey.internal.Errors;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "event")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SDPMessage.class, name = "sdp"),
        @JsonSubTypes.Type(value = ICEMessage.class, name = "ice"),
        @JsonSubTypes.Type(value = MSGMessage.class, name = "msg"),
        @JsonSubTypes.Type(value = ErrorMessage.class, name = "error"),
        @JsonSubTypes.Type(value = IdentityMessage.class, name = "identity"),
        @JsonSubTypes.Type(value = JoinMessage.class, name = "join"),
        @JsonSubTypes.Type(value = QuitMessage.class, name = "quit"),
        @JsonSubTypes.Type(value = ListMessage.class, name = "list"),
        @JsonSubTypes.Type(value = MuteMessage.class, name = "mute"),
        @JsonSubTypes.Type(value = ClosedMessage.class, name = "room_closed"),
        @JsonSubTypes.Type(value = CallStateMessage.class, name = "call_state"),
})
public interface VideoMessage extends JsonSerializable {
    public String callType = "Video Call";
}
