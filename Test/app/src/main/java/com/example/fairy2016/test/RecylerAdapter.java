package com.example.fairy2016.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fairy2016 on 2016/12/22.
 */

public class RecylerAdapter extends RecyclerView.Adapter {
    private List<String> data;
    public RecylerAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //oneCreateViewHolder加载布局文件，并根据布局文件构造ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item,
                parent, false);
        return new ViewHolder(v);
    }

    //onBindViewHolder 绑定数据至控件
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.position = position;
        viewHolder.textView.setText(data.get(position));
    }

    //同样需要定义ViewHolder类，不过继承于RecyclerView.ViewHolder和实现点击接口
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private int position;
        //构造函数初始化控件
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.testView);
            textView.setOnClickListener(this);
        }

        //重写OnClick函数
        @Override
        public void onClick(View view) {
            if(mListener != null) {
                mListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
