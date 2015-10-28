package com.vgaw.netty;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/13.
 */
public class SubscribeResp implements Serializable {
    /**
     * Ä¬ÈÏÐòÁÐID
     */
    public static final long serialVersionUID = 1L;

    private int subReqID;
    private int respCode;
    private String desc;

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqID=" + subReqID +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
