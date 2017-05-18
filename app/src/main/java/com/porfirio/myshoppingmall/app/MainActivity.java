package com.porfirio.myshoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.porfirio.myshoppingmall.R;
import com.porfirio.myshoppingmall.base.BaseFragment;
import com.porfirio.myshoppingmall.community.fragment.CommunityFragment;
import com.porfirio.myshoppingmall.home.fragment.HomeFragment;
import com.porfirio.myshoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.porfirio.myshoppingmall.type.fragment.TypeFragment;
import com.porfirio.myshoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    public int position = 0;
    /**
     * cached Fragment or last Fragment.
     */
    public BaseFragment tempFragment;

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    /**
     * a set which is used to add multi instances of the Fragment.
     */
    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ButterKnife bound current Activity.
        ButterKnife.bind(this);
        /**
         * initialize each Fragment.
         */
        initFragment();
        /**
         * setting the listener of the RadioGroup.
         */
        initListener();
        rgMain.check(R.id.rb_home);
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                // get difficult Fragment by position.
                BaseFragment baseFragment = getFragment(position);
                // the first param:last Fragment
                // the second param:current Frament which will be shown.
                switchFragment(tempFragment, baseFragment);
            }
        });
    }


    /**
     * adding the Object in accordance with the order.
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
                // decide whether nextFragment is added.
                if (!nextFragment.isAdded()) {
                    // hide tempFragment
                    if (fromFragment != null) {
                        transation.hide(fromFragment);
                    }
                    transation.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    if (fromFragment != null) {
                        transation.hide(fromFragment);
                    }
                    transation.show(nextFragment).commit();
                }
            }
        }
    }
}
