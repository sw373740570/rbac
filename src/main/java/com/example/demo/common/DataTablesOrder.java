package com.example.demo.common;

public class DataTablesOrder {
    private Integer column;//需要排序的列
    private String dir;//desc 降序 asc升序

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
