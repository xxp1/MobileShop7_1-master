package com.edu.huatec.mobileshop.http.presenter;

import com.edu.huatec.mobileshop.http.HttpMethods;
import com.edu.huatec.mobileshop.http.entity.MemberEntity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xia on 2018/10/17.
 */

public class MemberPresenter extends HttpMethods{

    //用户注册
    public static void register(Subscriber<MemberEntity> subscriber, String username, String emial, String password) {
        Observable observable = memberService.register(username, password, emial)
                .map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable, subscriber);
    }

    //用户登录
    public static void login2(Subscriber<MemberEntity> subscriber, String username, String password) {
        Observable observable = memberService.login2(username, password)
                .map(new HttpResultFunc<MemberEntity>());
        toSubscribe(observable, subscriber);
    }

    //修改密码
    public static void changePassword(Subscriber subscriber, String memberId, String old_pwd, String
            new_pwd) {
        Observable observable = memberService.changePassword(memberId, old_pwd, new_pwd);
        toSubscribe(observable, subscriber);
    }

}
