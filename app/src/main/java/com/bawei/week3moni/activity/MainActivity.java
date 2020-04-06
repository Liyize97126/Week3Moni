package com.bawei.week3moni.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week3moni.R;
import com.bawei.week3moni.adapter.GoodsListAdapter;
import com.bawei.week3moni.base.BaseActivity;
import com.bawei.week3moni.base.BasePresenter;
import com.bawei.week3moni.bean.DataBean;
import com.bawei.week3moni.bean.ResultBean;
import com.bawei.week3moni.contact.IContact;
import com.bawei.week3moni.presenter.GoodsPresenter;
import com.bawei.week3moni.util.MyApplication;
import com.bawei.week3moni.util.VolleyUtil;

public class MainActivity extends BaseActivity {
    //定义
    private TextView rxxptitle,mlsstitle,pzshtitle;
    private RecyclerView rxxp,mlss,pzsh;
    private ImageButton searchgo;
    private GoodsListAdapter rxxpadapter,mlssadapter,pzshadapter;
    //方法实现
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected BasePresenter initPresenter() {
        return new GoodsPresenter(new IContact.IView() {
            @Override
            public void success(String json) {
                //缓存数据
                MyApplication.getSharedPreferences().edit().putString("goods",json).commit();
                //调用解析方法
                gsonAndAdds(json);
            }
            @Override
            public void fail(String error) {
                Toast.makeText(MainActivity.this,"参数错误！",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void initView() {
        //获取ID
        searchgo = findViewById(R.id.searchgo);
        rxxptitle = findViewById(R.id.rxxptitle);
        mlsstitle = findViewById(R.id.mlsstitle);
        pzshtitle = findViewById(R.id.pzshtitle);
        rxxp = findViewById(R.id.rxxp);
        mlss = findViewById(R.id.mlss);
        pzsh = findViewById(R.id.pzsh);
        //点击事件
        searchgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        //设置适配器
        //第一个适配器
        rxxpadapter = new GoodsListAdapter() {
            @Override
            protected int getItemLayout() {
                return R.layout.rxxplistcontent;
            }
        };
        rxxp.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rxxp.setAdapter(rxxpadapter);
        //第二个适配器
        mlssadapter = new GoodsListAdapter() {
            @Override
            protected int getItemLayout() {
                return R.layout.mlsslistcontent;
            }
        };
        mlss.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mlss.setAdapter(mlssadapter);
        //第三个适配器
        pzshadapter = new GoodsListAdapter() {
            @Override
            protected int getItemLayout() {
                return R.layout.pzshlistcontent;
            }
        };
        pzsh.setLayoutManager(new GridLayoutManager(this,2));
        pzsh.setAdapter(pzshadapter);
        //获取缓存数据
        String goodsSharedPreferences = MyApplication.getSharedPreferences().getString("goods", null);
        //判断是否有缓存
        if(goodsSharedPreferences != null){
            //调用方法
            gsonAndAdds(goodsSharedPreferences);
            Toast.makeText(MainActivity.this,"已加载缓存数据！",Toast.LENGTH_LONG).show();
        } else {
            //判断是否有网络
            if(VolleyUtil.getVolleyUtil().hasNet()){
                //发起请求
                initPresenter().request();
            } else {
                //提示没网
                Toast.makeText(MainActivity.this,"没有网络，请检查！",Toast.LENGTH_LONG).show();
                mlsstitle.setVisibility(View.GONE);
                pzshtitle.setVisibility(View.GONE);
                rxxptitle.setVisibility(View.GONE);
            }
        }
    }
    //封装一个解析数据并添加数据的方法
    private void gsonAndAdds(String json){
        DataBean dataBean = getGson().fromJson(json, DataBean.class);
        if(dataBean.getStatus().equals("0000")){
            ResultBean result = dataBean.getResult();
            //向集合中添加数据
            mlsstitle.setText(result.getMlss().getName());
            mlssadapter.getList().addAll(result.getMlss().getCommodityList());
            pzshtitle.setText(result.getPzsh().getName());
            pzshadapter.getList().addAll(result.getPzsh().getCommodityList());
            rxxptitle.setText(result.getRxxp().getName());
            rxxpadapter.getList().addAll(result.getRxxp().getCommodityList());
            //刷新适配器
            mlssadapter.notifyDataSetChanged();
            pzshadapter.notifyDataSetChanged();
            rxxpadapter.notifyDataSetChanged();
        } else {
            Toast.makeText(MainActivity.this,"未获取到数据，请重试！",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected String getAppTitle() {
        return "百姓生活商城";
    }
}
