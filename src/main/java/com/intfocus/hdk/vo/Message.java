package com.intfocus.hdk.vo;

public class Message {
    private Integer id;

    private String mesId;

    private String mesUser;

    private String problemId;

    private String mesContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId == null ? null : mesId.trim();
    }

    public String getMesUser() {
        return mesUser;
    }

    public void setMesUser(String mesUser) {
        this.mesUser = mesUser == null ? null : mesUser.trim();
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent == null ? null : mesContent.trim();
    }
}