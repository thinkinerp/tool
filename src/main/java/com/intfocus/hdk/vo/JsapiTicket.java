package com.intfocus.hdk.vo;

import java.util.Date;

public class JsapiTicket {
    private Integer id;

    private String jsapiTicketContent;

    private Date getTime;

    private Integer expiresIn;

    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsapiTicketContent() {
        return jsapiTicketContent;
    }

    public void setJsapiTicketContent(String jsapiTicketContent) {
        this.jsapiTicketContent = jsapiTicketContent == null ? null : jsapiTicketContent.trim();
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