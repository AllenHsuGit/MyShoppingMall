package com.porfirio.myshoppingmall.home.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.porfirio.myshoppingmall.R;
import com.porfirio.myshoppingmall.home.bean.ResultBeanData;
import com.porfirio.myshoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;
    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;
    /**
     * 活动类型
     */
    public static final int ACT = 2;
    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;
    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖类型
     */
    public static final int HOT = 5;
    /**
     * used to initialize layout.
     */
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ResultBeanData.ResultBean resultBean;
    /**
     * current type of the item.
     */
    private int currentType = 0;

    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * equal to getView of the BaseAdapter.
     * used to create ViewHolder.
     *
     * @param parent   父类
     * @param viewType current type of the view returned.
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }
        if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        }
        if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        }
        if (viewType == SECKILL) {
            return new SecKillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }
        return null;
    }

    /**
     * equal to module which is used to bind Data in getView.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }
        if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }
        if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }
        if (getItemViewType(position) == SECKILL) {
            SecKillViewHolder secKillViewHolder = (SecKillViewHolder) holder;
            secKillViewHolder.setData(resultBean.getSeckill_info());
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            // set Data of the banner.
            // get set of the image url.
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            // 设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    // connect internet to request image.use Glide.
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + url).into(view);
                    //view = ImageView.inflate()
                }
            });
            // 设置item点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;

        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
            // 设置item的点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            // 得到数据
            // 设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channel_info);
            gv_channel.setAdapter(adapter);
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            act_viewpager.setPageMargin(50);
            act_viewpager.setOffscreenPageLimit(3);
            // 决定动画效果
            act_viewpager.setPageTransformer(true,new ScaleInTransformer());
            // 得到数据
            // 设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object 由instantiateItem方法返回的值
                 * @return 比较是否是同一个页面
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container Viewpager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ResultBeanData.ResultBean.ActInfoBean actInfoBean = act_info.get(position);
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE + actInfoBean.getIcon_url()).into(imageView);
                    // 添加到容器中
                    container.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SecKillRecyclerViewAdapter adapter;
        public SecKillViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {
            // 得到数据
            // 设置数据：文本和recyclerView的数据
            adapter = new SecKillRecyclerViewAdapter(mContext,seckill_info.getList());
            rv_seckill.setAdapter(adapter);
            // 设置布局管理器
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            // 设置item的点击事件
            adapter.setOnSecKillRecyclerView(new SecKillRecyclerViewAdapter.OnSecKillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "秒杀==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * get type of the item.
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * count of the item.
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //        return 0;
        return 4;
    }
}
