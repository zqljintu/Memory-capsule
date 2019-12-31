package com.zql.base.helper;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by Totoro
 * 2019-11-08 17:44
 **/
public class RxHelper {

    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件主线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件io线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelperIO() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }
    /**
     * 创建flowable
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createFlowable(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 通过RxJava切换到UI线程 适合不取消的任务
     *
     * @param runnable 要在UI线程中运行的任务
     */
    public static void runOnUi(final Runnable runnable) {
        Observable.just("").observeOn(AndroidSchedulers.mainThread()).subscribe(s -> runnable.run());
    }


    public static void runOnUiDelay(long delayTime, final Runnable runnable) {
        Observable.just("").delay(delayTime, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> runnable.run());
    }

    /* *//**
     * 运行在RxJava IO线程池中 适合不取消的后台任务
     *
     * @param runnable 要在子线程中运行的任务
     * @param compositeSubscription 用于释放资源
     */
    public static void runOnPool(final Runnable runnable, @NonNull CompositeDisposable compositeSubscription) {
        compositeSubscription.add(Observable.just("").observeOn(Schedulers.io()).subscribe(s -> runnable.run(), throwable -> {}));
    }

    /**
     * 通过RxJava切换到UI线程 适合不取消的任务
     *
     * @param runnable 要在UI线程中运行的任务
     */
    public static void runOnUi(final Runnable runnable, @NonNull CompositeDisposable compositeSubscription) {
        compositeSubscription.add(Observable.just("").observeOn(AndroidSchedulers.mainThread()).subscribe(s -> runnable.run()));
    }
}
