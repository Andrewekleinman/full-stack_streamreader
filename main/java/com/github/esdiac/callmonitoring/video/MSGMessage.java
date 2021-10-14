package com.github.esdiac.callmonitoring.video;

public class MSGMessage implements VideoMessage {
    public String event = "msg";
    public String msg;
    public String userID;
    public String roomID;
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
