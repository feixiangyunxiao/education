package com.bootdo.common.vo;

public class IdModel {
    private String openid;
    private String session_key;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_ke) {
        this.session_key = session_ke;
    }

    @Override
    public String toString() {
        return "IdModel{" +
                "openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                '}';
    }
}
