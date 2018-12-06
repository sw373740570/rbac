package com.example.demo.common;

import java.util.List;

public class DataTablesParam {

    private Integer draw;//绘制计数器
    private Integer start;//第一条数据的起始位置，比如0代表第一条数据
    private Integer length;//每页显示的条数
    private Search search;
    private List<Order> order;
    private List<Column> columns;

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

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public int getPageNum() {
        return start / length + 1;
    }

    class Search {
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

    class Order {
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

    class Column {
        private String data;//绑定的数据源
        private String name;//columns 的名字
        private Boolean searchable;
        private Boolean orderable;
        private Search search;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getSearchable() {
            return searchable;
        }

        public void setSearchable(Boolean searchable) {
            this.searchable = searchable;
        }

        public Boolean getOrderable() {
            return orderable;
        }

        public void setOrderable(Boolean orderable) {
            this.orderable = orderable;
        }

        public Search getSearch() {
            return search;
        }

        public void setSearch(Search search) {
            this.search = search;
        }
    }
}
