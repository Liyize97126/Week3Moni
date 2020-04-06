package com.bawei.week3moni.base;

import com.bawei.week3moni.contact.IContact;
import com.bawei.week3moni.util.VolleyUtil;

public abstract class BasePresenter {
    //定义
    private IContact.IView iView;
    //构造
    public BasePresenter(IContact.IView iView) {
        this.iView = iView;
    }
    //请求方法
    public void request(){
        VolleyUtil.getVolleyUtil().get(getUrl(), new IContact.IModel() {
            @Override
            public void success(String json) {
                //反馈
                iView.success(json);
            }
            @Override
            public void fail(String error) {
                //反馈
                iView.fail(error);
            }
        });
    }
    //方法封装
    protected abstract String getUrl();
    //释放资源
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
}
