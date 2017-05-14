package com.intfocus.hdk.vo;

import java.util.Date;

public class Survey {
    private Integer id;

    private String proId;
    private String surId;
    private String order;

    private String shopId;
    private String shopMerStation;

    private String surNetwork;

    private String surPower;

    private String surVip;

    private String surVipData;

    private Date createdAt;

    private Date updatedAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSurRemarks() {
        return surRemarks;
    }

    public void setSurRemarks(String surRemarks) {
        this.surRemarks = surRemarks == null ? null : surRemarks.trim();
    }

	public String getSurId() {
		return surId;
	}

	public void setSurId(String surId) {
		this.surId = surId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getShopMerStation() {
		return shopMerStation;
	}

	public void setShopMerStation(String shopMerStation) {
		this.shopMerStation = shopMerStation;
	}
}