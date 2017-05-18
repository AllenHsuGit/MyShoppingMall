package com.porfirio.myshoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/11.
 */
// 作用：基类Fragment
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    // this method will be called when this Class(BaseFragment) is created by system.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * abstract Class,will be implemented by subClass. showing different effect.
     * @return
     */
    public abstract View initView();

    /**
     * this method will be called when the Activity is created.
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * this method will be overridden when the subClass needs connect internet to request the Data.
     * requesting the Data in this method.
     */
    public void initData(){

    }
}
