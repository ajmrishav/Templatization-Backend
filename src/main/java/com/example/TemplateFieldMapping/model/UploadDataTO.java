package com.example.TemplateFieldMapping.model;

public class UploadDataTO {

    private String orgName;
    private String[][] data;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
