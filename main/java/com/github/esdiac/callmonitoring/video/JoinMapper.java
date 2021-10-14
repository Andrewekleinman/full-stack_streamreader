package com.github.esdiac.callmonitoring.video;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.esdiac.callmonitoring.JsonSerializable;
import com.github.esdiac.callmonitoring.messages.*;
import org.glassfish.jersey.internal.Errors;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "msgType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DataDecipher.class, name = "2"),


})
public interface JoinMapper extends JsonSerializable {
    public String callType = "Video Call";

}
