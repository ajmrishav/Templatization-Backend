package com.example.TemplateFieldMapping.model;

public class InputFieldsTO {

    private String orgName;
    public String fields[];

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
