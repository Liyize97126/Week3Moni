package com.bawei.week3moni.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.week3moni.R;

/**
 * 流式布局视图
 */
public class SearchHistoryView extends FrameLayout {
    //声明回调接口
    private EditSearchTextListener editSearchTextListener;
    public void setEditSearchTextListener(EditSearchTextListener editSearchTextListener) {
        this.editSearchTextListener = editSearchTextListener;
    }
    //方法实现
    public SearchHistoryView(@NonNull Context context) {
        super(context);
    }
    public SearchHistoryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public SearchHistoryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //重新布局
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //定义基础变量
        int sumWidth = 0;
        int width = getWidth();
        int lines = 0;
        //for循环
        for (int i = 0; i < getChildCount(); i++) {
            //取出控件
            View view = getChildAt(i);
            if(sumWidth + view.getWidth() > width){
                lines++;
                sumWidth = 0;
            }
            //设置布局
            view.layout(sumWidth,lines*view.getHeight(),sumWidth + view.getWidth(),lines*view.getHeight() + view.getHeight());
            sumWidth += view.getWidth();
        }
    }
    //添加历史
    public void addHistory(String text){
        //获得控件
        TextView textView = (TextView) View.inflate(getContext(), R.layout.history_item, null);
        textView.setText(text);
        textView.setTag(text);
        //自定义点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editSearchTextListener != null){
                    editSearchTextListener.editSearchText((String) v.getTag());
                }
            }
        });
        //获取方向
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //添加视图
        addView(textView,layoutParams);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    //定义回调接口
    public interface EditSearchTextListener {
        void editSearchText(String text);
    }
}
