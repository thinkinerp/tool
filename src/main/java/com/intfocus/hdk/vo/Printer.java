package com.intfocus.hdk.vo;

import java.util.Date;

public class Printer {
    private Integer id;

    private String printerId;

    private String printerBrand;

    private String printerModel;

    private String printerPort;

    private String surId;

    private String installId;

    private String createdAt;

    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId == null ? null : printerId.trim();
    }

    public String getPrinterBrand() {
        return printerBrand;
    }

    public void setPrinterBrand(String printerBrand) {
        this.printerBrand = printerBrand == null ? null : printerBrand.trim();
    }

    public String getPrinterModel() {
        return printerModel;
    }

    public void setPrinterModel(String printerModel) {
        this.printerModel = printerModel == null ? null : printerModel.trim();
    }

    public String getPrinterPort() {
        return printerPort;
    }

    public void setPrinterPort(String printerPort) {
        this.printerPort = printerPort == null ? null : printerPort.trim();
    }

    public String getSurId() {
        return surId;
    }

    public void setSurId(String surId) {
        this.surId = surId == null ? null : surId.trim();
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId == null ? null : installId.trim();
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}