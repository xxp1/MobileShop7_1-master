package com.edu.huatec.mobileshop.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.edu.huatec.mobileshop.R;
import com.edu.huatec.mobileshop.common.BaseActivity;




public class SplashActivity extends BaseActivity {

    ImageView splash_loading_item;
    @Override
    public int getContentViewId() {
       return R.layout.activity_splash;
    }
    @Override
    protected void initView() {
        super.initView();

        splash_loading_item= (ImageView) findViewById(R.id.splash_loading_item);
        //导入动画
        // 这里动画可以采用两种方式创建
        // 一种是使用XML文件的形式创建，使用AnimationUtils.loadAnimation导入动画
        Animation translate = AnimationUtils.loadAnimation(this,R.anim.splash_loading);

        //另一种方式直接创建动画对象
        //TranslateAnimation是平移动画的类，负责View的位移
        /*Animation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,//X起点，
                Animation.RELATIVE_TO_PARENT,0.6f,//X终点
                Animation.RELATIVE_TO_SELF,0f,//Y起点
                Animation.RELATIVE_TO_SELF,0f//Y终点
        );
        //停留在最后一帧
        translate.setFillAfter(true);
        //时长
        translate.setDuration(1000L);*/

        //给动画添加监听器，监听动画结束，动画结束之后打开AdActivity
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {

                //这里添加了一个Activity转场动画，就是Activity切换的动画
                startActivity(new Intent(SplashActivity.this, AdActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                finish();
            }
        });
        //启动动画
//        splash_loading_item.setAnimation(translate);
        splash_loading_item.startAnimation(translate);
    }
}
