package com.bawei.week3moni.contact;

/**
 * 契约类
 */
public interface IContact {
    //界面反馈
    interface IView{
        void success(String json);
        void fail(String error);
    }
    //接口反馈
    interface IModel{
        void success(String json);
        void fail(String error);
    }
}
