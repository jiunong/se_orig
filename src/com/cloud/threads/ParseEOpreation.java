package com.cloud.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/10/9 14:38
 */
public class ParseEOpreation {


    static SictThreadFactory getSictThreadFactory(String threadPoolName){
        return  new SictThreadFactory(threadPoolName);
    }

    /**
     * TODO threadFactory to creat thread
     * @author xuhong.ding
     * @since 2020/9/7 14:21
     */
    static class SictThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        SictThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        SictThreadFactory(String threadPoolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + threadPoolName + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    /**
     * TODO 开始执行e文件解析 多个threads同时进行
     * @return void
     * @author xuhong.ding
     * @since 2020/9/7 14:51
     */
    public static void begin(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(getSictThreadFactory("parseE"));
        cachedThreadPool.execute(new ParseEFileThread("1"));
        cachedThreadPool.execute(new ParseEFileThread("2"));
        cachedThreadPool.execute(new ParseEFileThread("3"));
        cachedThreadPool.execute(new ParseEFileThread("4"));
        cachedThreadPool.shutdown();
    }
}
