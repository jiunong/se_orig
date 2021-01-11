package com.cloud.JDK11;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/11 13:39
 */
public class FlowTest {

    public static class MySubscriber<T> implements Flow.Subscriber<T> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(T item) {
            System.out.println("GOT" + item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("Done");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        MySubscriber<Integer> mySubscriber = new MySubscriber<>();
        publisher.subscribe(mySubscriber);
        for (int i = 0; i < 10; i++) {
            publisher.submit(i);
            TimeUnit.SECONDS.sleep(1);
        }
        publisher.close();
        TimeUnit.SECONDS.sleep(100);
    }


}

