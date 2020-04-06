package com.bawei.week3moni.bean;

import java.io.Serializable;

public class DataBean implements Serializable {
    private ResultBean result;
    private String message;
    private String status;
    public ResultBean getResult() {
        return result;
    }
    public void setResult(ResultBean result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
