package com.cyc.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * Created by cyc_e on 2017/8/19.
 */
@Service
public class HystrixService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * HystrixCommand ： 断路时访问的方法， 此为同步返回，是为调用HystrixCommand.execute()
     * fallbackMethod ： 异常访问方法， 返回类型要给hystrixService返回类型一样
     * <p>
     * commandKey : 命令名字
     * groupKey ： Hystrix线程池分配依靠命令组来划分
     * hreadPoolKey ： 在命令组基础上，更进一步细化分
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorBack", commandKey = "hystrixService", groupKey = "hystrixServiceGroup", threadPoolKey = "hystrixServiceThread")
    public User hystrixService() {
        return restTemplate.getForObject("http://SPRING-CLOUD-PROVIDER/provideUser", User.class);
    }

    /**
     * HystrixCommand ： 断路时访问的方法， 此为异步返回，是为调用HystrixCommand.queue()
     * fallbackMethod ： 异常访问方法
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorBack")
    public Future<User> hystrixServiceAsync() {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://SPRING-CLOUD-PROVIDER/provideUser", User.class);
            }
        };
    }

    /**
     * 使用HystrixObservableCommand
     * ObservableExecutionMode.EAGER ： 表示使用observe()执行方式
     * ObservableExecutionMode.LAZY : 表示使用toObservable()执行方式
     *
     * @return
     */
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "errorBack")
    public Observable<User> observableUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://SPRING-CLOUD-PROVIDER/provideUser", User.class);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

    /**
     * 1. 只有抛出HystrixBadRequestException异常，才会异常处理，否则都是降级服务处理[即fallbackMethod]
     * 2. ignoreExceptions : 将包括的异常包装为HystrixBadRequestException抛出，这样就不会出发后续的fallback逻辑
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "errorBack", ignoreExceptions = {RuntimeException.class})
    public User hystrixServiceException() {
        int i = 10 / 0;
        return restTemplate.getForObject("http://SPRING-CLOUD-PROVIDER/provideUser", User.class);
    }

    /**
     * 降级服务方法
     * 1. 当操作方法返回类型为void或是为空的 Observable时候，则实现降级服务意义不大，当失败时，只需要通知调用者即可
     * 2. 方法参数加入Throwable， 就可以捕获异常了
     *
     * @return
     */
    public User errorBack(Throwable e) {
        User user = new User();
        if (e != null) {
            user.setName(e.getMessage());
        } else {
            user.setName("error");
        }
        return user;
    }

}
