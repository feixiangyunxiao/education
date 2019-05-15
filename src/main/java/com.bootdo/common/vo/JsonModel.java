package com.bootdo.common.vo;

import java.util.List;

public class JsonModel {

    private int code;
    private String message;
    private List data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public static JsonModel returnOk() {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCode(200);
        jsonModel.setMessage("成功了");
        return jsonModel;
    }

    public static JsonModel returnErr() {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setCode(201);
        jsonModel.setMessage("失败了");
        return jsonModel;
    }

    @Override
    public String toString() {
        return "JsonModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
