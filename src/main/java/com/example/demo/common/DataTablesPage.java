package com.example.demo.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class DataTablesPage<T> {

    private Integer draw;

    private Long recordsTotal;

    private Long recordsFiltered;

    private List<T> data;

    private String error;

    public DataTablesPage(Integer draw, PageInfo pageInfo) {
        this.draw = draw;
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = pageInfo.getTotal();
        this.data = pageInfo.getList();
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
