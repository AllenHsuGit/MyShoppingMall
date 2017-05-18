package com.porfirio.myshoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.porfirio.myshoppingmall.R;
import com.porfirio.myshoppingmall.base.BaseFragment;
import com.porfirio.myshoppingmall.home.adapter.HomeFragmentAdapter;
import com.porfirio.myshoppingmall.home.bean.ResultBeanData;
import com.porfirio.myshoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/3/11.
 */
//作用：Fragment of the HomeFragmet.
public class HomeFragment extends BaseFragment {
    private RecyclerView rv_home;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomeFragmentAdapter adapter;
    /**
     * return Data.
     */
    private ResultBeanData.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG, "UI of the HomeFragment was initialized!");
        View view = View.inflate(mContext,R.layout.fragment_home,null);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        // setting listener
        initListener();
        return view;
    }

    private void initListener() {
        // listener which is used to back to the top.
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to the top.
                rv_home.scrollToPosition(0);
            }
        });
        // listener which is used to search.
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"search",Toast.LENGTH_SHORT).show();
            }
        });
        // listener which is used to get message.
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "message center", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "Data of the HomeFragment was initialized!");
        //请求网络数据
        getDataFromNet();
        //请求手机本地数据
        //getDataFromLocal();

    }

    private void getDataFromLocal() {
        String url = Constants.LOCAL_HOME_URL;
        File file = new File(url);
        String jsonLocalResponse = new String();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            char[] c = new char[100];
            int len;
            while ((len = isr.read(c)) != -1){
                jsonLocalResponse = new String(c,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        processData(jsonLocalResponse);
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * this method is called when connecting internet to request Data was unsuccess
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"that the homeFragment request the Data is unsuccess! ==" + e.getMessage());
                    }

                    /**
                     * this method is called when connecting internet to request Data was success.
                     * @param response the Data which is requested successfully.
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"that the homeFragment request the Data is success! ==" + response);
                        // parse Data
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if(resultBean != null){
            // there is Data.
            // set adapter of the recyclerView.
            adapter = new HomeFragmentAdapter(mContext,resultBean);
            rv_home.setAdapter(adapter);
            // 设置布局管理者
            rv_home.setLayoutManager(new GridLayoutManager(mContext,1));
        } else {
            // there is not Data.
        }
        Log.e(TAG,"parse successfully == " + resultBean.getHot_info().get(0).getName());
    }
}
