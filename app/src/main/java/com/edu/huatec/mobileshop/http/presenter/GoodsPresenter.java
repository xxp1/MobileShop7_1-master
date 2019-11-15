package com.edu.huatec.mobileshop.http.presenter;


import com.edu.huatec.mobileshop.http.HttpMethods;
import com.edu.huatec.mobileshop.http.entity.GoodsDetailEntity;
import com.edu.huatec.mobileshop.http.entity.GoodsEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xia on 2018/10/17.
 */

public class GoodsPresenter extends HttpMethods {

    /**
     * 获取商品详情
     *
     * @param subscriber
     * @param goodsId
     */
    public static void goodsDetail(Subscriber<GoodsDetailEntity> subscriber, int goodsId) {
        Observable<GoodsDetailEntity> observable = goodsService.goodsDetail(goodsId)
                .map(new HttpResultFunc<GoodsDetailEntity>());
        toSubscribe(observable, subscriber);
    }

    public static void listByKeywords(Subscriber<List<GoodsEntity>> subscriber, String keywords) {
        Observable<List<GoodsEntity>> observable = goodsService.listByKeywords(keywords)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 根据二级分类id获取商品列表
     *
     * @param subscriber
     * @param catId
     */
    public static void list(Subscriber<List<GoodsEntity>> subscriber, int catId) {
        Observable<List<GoodsEntity>> observable = goodsService.list(catId)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable, subscriber);
    }
}
