package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class DataTablesParam {

    private Integer draw;//绘制计数器
    private Integer start;//第一条数据的起始位置，比如0代表第一条数据
    private Integer length;//每页显示的条数
    private DataTablesSearch search;
    private List<DataTablesOrder> order;
    private List<DataTablesColumns> columns;
    private String pageParam;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public DataTablesSearch getSearch() {
        return search;
    }

    public void setSearch(DataTablesSearch search) {
        this.search = search;
    }

    public List<DataTablesOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataTablesOrder> order) {
        this.order = order;
    }

    public List<DataTablesColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTablesColumns> columns) {
        this.columns = columns;
    }

    public int getPageNum() {
        return start / length + 1;
    }

    public String getPageParam() {
        return pageParam;
    }

    public void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }
}
