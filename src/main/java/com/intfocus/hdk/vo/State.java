package com.intfocus.hdk.vo;

public class State {
    private Integer id;

    private String staId;

    private String staName;

    private String ownerTable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId == null ? null : staId.trim();
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName == null ? null : staName.trim();
    }

    public String getOwnerTable() {
        return ownerTable;
    }

    public void setOwnerTable(String ownerTable) {
        this.ownerTable = ownerTable == null ? null : ownerTable.trim();
    }
}