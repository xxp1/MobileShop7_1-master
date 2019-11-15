package com.edu.huatec.mobileshop.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.huatec.mobileshop.R;
import com.edu.huatec.mobileshop.common.BaseActivity;
import com.edu.huatec.mobileshop.fragment.NavigationFragment;

public class MainActivity extends BaseActivity {

    @Override
    public @LayoutRes
    int getContentViewId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        super.initView();
        //将Fragment添加到Activity中
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //这里是将fragment添加到指定的id为fl_main的地方去，这个fl_main在MainActivity的layout文件中
        transaction.replace(R.id.fl_main,new NavigationFragment());
        transaction.commit();
/*
        FragmentManager manager = getSupportFragmentManager();
        NavigationFragment navigationFragment = (NavigationFragment) manager.findFragmentById(R.id.fragment_main_navigation);
*/

    }
}
