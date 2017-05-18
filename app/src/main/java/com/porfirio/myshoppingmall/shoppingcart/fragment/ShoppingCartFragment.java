package com.porfirio.myshoppingmall.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.porfirio.myshoppingmall.base.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/3/11.
 */
//作用：Fragment of the ShoppingCartFragment.
public class ShoppingCartFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        Log.e(TAG,"UI of the ShoppingCartFragment was initialized!");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"Data of the ShoppingCartFragment was initialized!");
        textView.setText("ShoppingCart content!");
    }
}
