package com.intfocus.hdk.vo;

public class Survey {
    private Integer id;

    private String proId;

    private String shopId;

    private String surNetwork;

    private String surPower;

    private String surVip;

    private String surVipData;

    private String surRemarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSurNetwork() {
        return surNetwork;
    }

    public void setSurNetwork(String surNetwork) {
        this.surNetwork = surNetwork == null ? null : surNetwork.trim();
    }

    public String getSurPower() {
        return surPower;
    }

    public void setSurPower(String surPower) {
        this.surPower = surPower == null ? null : surPower.trim();
    }

    public String getSurVip() {
        return surVip;
    }

    public void setSurVip(String surVip) {
        this.surVip = surVip == null ? null : surVip.trim();
    }

    public String getSurVipData() {
        return surVipData;
    }

    public void setSurVipData(String surVipData) {
        this.surVipData = surVipData == null ? null : surVipData.trim();
    }

    public String getSurRemarks() {
        return surRemarks;
    }

    public void setSurRemarks(String surRemarks) {
        this.surRemarks = surRemarks == null ? null : surRemarks.trim();
    }
}