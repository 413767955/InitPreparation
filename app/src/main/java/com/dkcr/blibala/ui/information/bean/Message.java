package com.dkcr.blibala.ui.information.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public  class Message implements MultiItemEntity {

     private String uuid;
     private String msgId;
     private MsgType msgType;
     private MsgBody body;
     private MsgSendStatus sentStatus;
     private String senderId;
     private String targetId;
     private long sentTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public MsgBody getBody() {
        return body;
    }

    public void setBody(MsgBody body) {
        this.body = body;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public MsgSendStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(MsgSendStatus sentStatus) {
        this.sentStatus = sentStatus;
    }



    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public int getItemType() {
        switch (msgType){
            case TEXT://发送的文本信息
                return 1;
            case RECEIVE_TEXT://接收的文本信息
                return 2;
            case IMAGE://发送的图片
                return 3;
            case RECEIVE_IMAGE://接收的图片
                return 4;
            case VIDEO://发送的视频
                return 5;
            case RECEIVE_VIDEO://接收视频
                return 6;
            case FILE://发送的文件
                return 7;
            case RECEIVE_FILE://接收的文件
                return 8;
            case AUDIO://发送的文本信息
                return 9;
            case RECEIVE_AUDIO://接收的文本信息
                return 10;
        }
        return 0;
    }
}
