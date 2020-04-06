package com.bawei.week3moni.presenter;

import com.bawei.week3moni.base.BasePresenter;
import com.bawei.week3moni.contact.IContact;

public class GoodsPresenter extends BasePresenter {
    //方法实现
    public GoodsPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected String getUrl() {
        return "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    }
}
