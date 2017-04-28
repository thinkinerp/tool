package com.intfocus.hdk.vo;

import java.util.Date;

public class JsapiToken {
    private Integer id;

    private String jsapiTokenContent;

    private Date getTime;

    private Integer expiresIn;

    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsapiTokenContent() {
        return jsapiTokenContent;
    }

    public void setJsapiTokenContent(String jsapiTokenContent) {
        this.jsapiTokenContent = jsapiTokenContent == null ? null : jsapiTokenContent.trim();
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}