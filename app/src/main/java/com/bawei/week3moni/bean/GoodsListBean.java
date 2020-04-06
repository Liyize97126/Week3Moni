package com.bawei.week3moni.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsListBean implements Serializable {
    private List<GoodsBean> commodityList;
    private long id;
    private String name;
    public List<GoodsBean> getCommodityList() {
        return commodityList;
    }
    public void setCommodityList(List<GoodsBean> commodityList) {
        this.commodityList = commodityList;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
