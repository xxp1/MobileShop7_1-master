package com.edu.huatec.mobileshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;



public class MyWebView extends WebView {
    private IWebViewScroll mIWebViewScroll;

    public MyWebView(Context context) {
        super(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mIWebViewScroll != null && t == 0) {
            mIWebViewScroll.onTop();
        } else if (mIWebViewScroll != null && t != 0) {
            mIWebViewScroll.notOnTop();
        }
    }
    /**
     * 设置滑动监听
     *
     * @param listener
     */
    public void setOnCustomScrollChanged(IWebViewScroll listener) {
        this.mIWebViewScroll = listener;
    }

    //自定义的接口
    public interface IWebViewScroll {
        void onTop();//处于顶部回调

        void notOnTop();//
    }
}
