package com.inerdstack.lockscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangjie on 2016/11/7.
 */
public class MyListAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public MyListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(android.R.id.text1);
            tvContent.setText("哈哈哈哈");
        }
    }
}
