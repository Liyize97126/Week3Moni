package com.bawei.week3moni.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week3moni.R;
import com.bawei.week3moni.bean.GoodsBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public abstract class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHouler> {
    //定义
    private List<GoodsBean> list = new ArrayList<>();
    //封装
    public List<GoodsBean> getList() {
        return list;
    }
    //方法实现
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        return new MyViewHouler(inflate);
    }
    //方法封装
    protected abstract int getItemLayout();
    @Override
    public void onBindViewHolder(@NonNull MyViewHouler holder, int position) {
        GoodsBean goodsBean = list.get(position);
        //设置图片
        RequestOptions requestOptions = new RequestOptions()
                .fallback(R.mipmap.zhanpict)
                .placeholder(R.mipmap.zhanpict)
                .error(R.mipmap.errorpict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)));
        Glide.with(holder.masterPic.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(goodsBean.getMasterPic())
                .into(holder.masterPic);
        //设置文本
        holder.commodityName.setText(goodsBean.getCommodityName());
        holder.price.setText("￥" + goodsBean.getPrice());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHouler extends RecyclerView.ViewHolder {
        //定义
        protected ImageView masterPic;
        protected TextView commodityName;
        protected TextView price;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            masterPic = itemView.findViewById(R.id.masterPic);
            commodityName = itemView.findViewById(R.id.commodityName);
            price = itemView.findViewById(R.id.price);
        }
    }
}
