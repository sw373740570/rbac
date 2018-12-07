package com.example.demo.common;

public class DataTablesSearch {
    private String value;//全局的搜索条件
    private Boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(Boolean regex) {
        this.regex = regex;
    }
}
