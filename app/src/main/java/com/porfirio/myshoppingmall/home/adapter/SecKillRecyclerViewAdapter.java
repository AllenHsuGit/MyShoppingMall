package com.porfirio.myshoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.porfirio.myshoppingmall.R;
import com.porfirio.myshoppingmall.home.bean.ResultBeanData;
import com.porfirio.myshoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
// 秒杀的适配器
public class SecKillRecyclerViewAdapter extends RecyclerView.Adapter<SecKillRecyclerViewAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;

    public SecKillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
        // 绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "秒杀==" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(onSecKillRecyclerView != null) {
                        onSecKillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 秒杀列表条目点击事件监听器
     */
    public interface OnSecKillRecyclerView{
        /**
         * 当某条被点击的时候回调
         * @param position
         */
        public void onItemClick(int position);
    }
    private OnSecKillRecyclerView onSecKillRecyclerView;

    /**
     * 外部设置item监听
     * @param onSecKillRecyclerView
     */
    public void setOnSecKillRecyclerView(OnSecKillRecyclerView onSecKillRecyclerView) {
        this.onSecKillRecyclerView = onSecKillRecyclerView;
    }
}
