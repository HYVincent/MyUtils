package com.vincent.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincent.library.R;
import com.vincent.library.listener.CommonItemOnClickListener;

import java.util.List;

/**
 * @name MyUtils
 * @class name：com.vincent.library.adapter
 * @class describe
 * @anthor Vincent QQ:1032006226
 * @time 2017/9/2 13:09
 * @change
 * @chang time
 * @class describe
 */

public class CommonStrItemAdapter extends RecyclerView.Adapter<CommonStrItemAdapter.CommonStrItemViewHolder>{

    private List<String> data;
    private Context mContext;
    private CommonItemOnClickListener onItemOnClickListener;

    public CommonStrItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemOnClickListener(CommonItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    @Override
    public CommonStrItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null)mContext = parent.getContext();
        return new CommonStrItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_str,parent,false));
    }

    @Override
    public void onBindViewHolder(CommonStrItemViewHolder holder, int position) {
        holder.tvStr.setText(data.get(position));
        if(position == data.size()-1){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }

    class CommonStrItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvStr;
        private View line;

        public CommonStrItemViewHolder(View itemView) {
            super(itemView);
            tvStr = itemView.findViewById(R.id.item_tv_str);
            line = itemView.findViewById(R.id.item_tv_line);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemOnClickListener.onItemClick(view,getLayoutPosition());
                }
            });
        }
    }
}
