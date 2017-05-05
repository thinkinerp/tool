package com.intfocus.hdk.vo;

import java.util.Date;

public class Problem {
    private Integer id;

    private String problemId;

    private String state;

    private String proId;

    private String shopId;

    private String eqId;

    private String mesId;

    private String problemType;

    private String problemObject;

    private String problemDepartment;

    private String problemUser;

    private Date problemHappen;

    private Date problemPut;

    private Date problemEstimate;

    private Date problemResloveTime;

    private String problemResolveUser;

    private String problemDetails;

    private String problemEnclosure;

    private String problemTable;

    private String problemPlan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId == null ? null : eqId.trim();
    }

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId == null ? null : mesId.trim();
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType == null ? null : problemType.trim();
    }

    public String getProblemObject() {
        return problemObject;
    }

    public void setProblemObject(String problemObject) {
        this.problemObject = problemObject == null ? null : problemObject.trim();
    }

    public String getProblemDepartment() {
        return problemDepartment;
    }

    public void setProblemDepartment(String problemDepartment) {
        this.problemDepartment = problemDepartment == null ? null : problemDepartment.trim();
    }

    public String getProblemUser() {
        return problemUser;
    }

    public void setProblemUser(String problemUser) {
        this.problemUser = problemUser == null ? null : problemUser.trim();
    }

    public Date getProblemHappen() {
        return problemHappen;
    }

    public void setProblemHappen(Date problemHappen) {
        this.problemHappen = problemHappen;
    }

    public Date getProblemPut() {
        return problemPut;
    }

    public void setProblemPut(Date problemPut) {
        this.problemPut = problemPut;
    }

    public Date getProblemEstimate() {
        return problemEstimate;
    }

    public void setProblemEstimate(Date problemEstimate) {
        this.problemEstimate = problemEstimate;
    }

    public Date getProblemResloveTime() {
        return problemResloveTime;
    }

    public void setProblemResloveTime(Date problemResloveTime) {
        this.problemResloveTime = problemResloveTime;
    }

    public String getProblemResolveUser() {
        return problemResolveUser;
    }

    public void setProblemResolveUser(String problemResolveUser) {
        this.problemResolveUser = problemResolveUser == null ? null : problemResolveUser.trim();
    }

    public String getProblemDetails() {
        return problemDetails;
    }

    public void setProblemDetails(String problemDetails) {
        this.problemDetails = problemDetails == null ? null : problemDetails.trim();
    }

    public String getProblemEnclosure() {
        return problemEnclosure;
    }

    public void setProblemEnclosure(String problemEnclosure) {
        this.problemEnclosure = problemEnclosure == null ? null : problemEnclosure.trim();
    }

    public String getProblemTable() {
        return problemTable;
    }

    public void setProblemTable(String problemTable) {
        this.problemTable = problemTable == null ? null : problemTable.trim();
    }

    public String getProblemPlan() {
        return problemPlan;
    }

    public void setProblemPlan(String problemPlan) {
        this.problemPlan = problemPlan == null ? null : problemPlan.trim();
    }
}