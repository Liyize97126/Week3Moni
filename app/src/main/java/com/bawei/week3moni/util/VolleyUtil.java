package com.bawei.week3moni.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.week3moni.contact.IContact;

import java.io.UnsupportedEncodingException;

/**
 * 网络工具类
 */
public class VolleyUtil {
    //定义
    private RequestQueue queue = Volley.newRequestQueue(MyApplication.getContext());
    //单例模式
    private static final VolleyUtil VOLLEY_UTIL = new VolleyUtil();
    private VolleyUtil() {
    }
    public static VolleyUtil getVolleyUtil() {
        return VOLLEY_UTIL;
    }
    //网络判断
    public boolean hasNet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
            return true;
        }
        return false;
    }
    //get请求
    public void get(String url, final IContact.IModel iModel){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Tag", "请求成功！\n" + response);
                        iModel.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Tag", "请求失败！\n" + error.getMessage());
                        iModel.fail(error.getMessage());
                    }
                }) {
            //处理乱码
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //添加请求队列
        queue.add(stringRequest);
    }
}
