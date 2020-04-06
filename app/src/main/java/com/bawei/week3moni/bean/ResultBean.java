package com.bawei.week3moni.bean;

import java.io.Serializable;

public class ResultBean implements Serializable {
    private GoodsListBean mlss;
    private GoodsListBean pzsh;
    private GoodsListBean rxxp;
    public GoodsListBean getMlss() {
        return mlss;
    }
    public void setMlss(GoodsListBean mlss) {
        this.mlss = mlss;
    }
    public GoodsListBean getPzsh() {
        return pzsh;
    }
    public void setPzsh(GoodsListBean pzsh) {
        this.pzsh = pzsh;
    }
    public GoodsListBean getRxxp() {
        return rxxp;
    }
    public void setRxxp(GoodsListBean rxxp) {
        this.rxxp = rxxp;
    }
}
