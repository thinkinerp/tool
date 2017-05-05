package com.intfocus.hdk.vo;

import java.util.Date;

public class Install {
    private Integer id;

    private String installId;

    private String installStation;

    private String proId;

    private String shopId;

    private String cashId;

    private String printerId;

    private String eqId;

    private String installData;

    private Date installTime;

    private String installUser;

    private String installNetwork;

    private String installRemote;

    private Date installEndtime;

    private String installRemarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId == null ? null : installId.trim();
    }

    public String getInstallStation() {
        return installStation;
    }

    public void setInstallStation(String installStation) {
        this.installStation = installStation == null ? null : installStation.trim();
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

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId == null ? null : printerId.trim();
    }

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId == null ? null : eqId.trim();
    }

    public String getInstallData() {
        return installData;
    }

    public void setInstallData(String installData) {
        this.installData = installData == null ? null : installData.trim();
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public String getInstallUser() {
        return installUser;
    }

    public void setInstallUser(String installUser) {
        this.installUser = installUser == null ? null : installUser.trim();
    }

    public String getInstallNetwork() {
        return installNetwork;
    }

    public void setInstallNetwork(String installNetwork) {
        this.installNetwork = installNetwork == null ? null : installNetwork.trim();
    }

    public String getInstallRemote() {
        return installRemote;
    }

    public void setInstallRemote(String installRemote) {
        this.installRemote = installRemote == null ? null : installRemote.trim();
    }

    public Date getInstallEndtime() {
        return installEndtime;
    }

    public void setInstallEndtime(Date installEndtime) {
        this.installEndtime = installEndtime;
    }

    public String getInstallRemarks() {
        return installRemarks;
    }

    public void setInstallRemarks(String installRemarks) {
        this.installRemarks = installRemarks == null ? null : installRemarks.trim();
    }
}