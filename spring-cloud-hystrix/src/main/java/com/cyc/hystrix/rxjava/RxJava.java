package com.cyc.hystrix.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * hystrix 核心模式， 命令默认， 基于Rxjava
 * Created by cyc_e on 2017/8/19.
 */
public class RxJava {

    public static void main(String[] args) {
        RxJava rxJava = new RxJava();
        rxJava.testRxJava();
    }

    public void testRxJava() {
        // 创建事件源 Observable
        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
            // Observable对象每发出一次事件，就会调用观察者onNext方法
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("111111111111");
                subscriber.onNext("222222222222");
                subscriber.onCompleted();
            }
        });

        // 创建阅订者
        Observer<String> receiver = new Observer<String>() {
            @Override
            public void onCompleted() {
                //数据接收完成时调用
            }

            @Override
            public void onError(Throwable e) {
                //发生错误调用
            }

            @Override
            public void onNext(String s) {
                //正常接收数据调用
                System.out.println("subscriber ： " + s);
            }
        };

        // 阅订
        sender.subscribe(receiver);
    }
}
