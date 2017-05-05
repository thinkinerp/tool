package com.intfocus.hdk.vo;

public class Cash {
    private Integer id;

    private String cashId;

    private String cashBrand;

    private String cashRegister;

    private String cashSystem;

    private String cashPort;

    private String printerDriver;

    private String surId;

    private String installId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    public String getCashBrand() {
        return cashBrand;
    }

    public void setCashBrand(String cashBrand) {
        this.cashBrand = cashBrand == null ? null : cashBrand.trim();
    }

    public String getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(String cashRegister) {
        this.cashRegister = cashRegister == null ? null : cashRegister.trim();
    }

    public String getCashSystem() {
        return cashSystem;
    }

    public void setCashSystem(String cashSystem) {
        this.cashSystem = cashSystem == null ? null : cashSystem.trim();
    }

    public String getCashPort() {
        return cashPort;
    }

    public void setCashPort(String cashPort) {
        this.cashPort = cashPort == null ? null : cashPort.trim();
    }

    public String getPrinterDriver() {
        return printerDriver;
    }

    public void setPrinterDriver(String printerDriver) {
        this.printerDriver = printerDriver == null ? null : printerDriver.trim();
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
}