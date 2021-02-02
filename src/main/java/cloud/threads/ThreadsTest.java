package cloud.threads;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author xuhong.ding
 * @since 2020/9/7 8:36
 */
public class ThreadsTest {


    /**
     * The sict thread factory
     */
    static class SictThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private String threadName;

        SictThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        SictThreadFactory(String threadName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
            this.threadName = threadName ;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + this.threadName,
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
    
    public static void main(String[] args) throws Exception {
            cacheThreadPool();
           // fixedThreadPool();
    }


    /**
    * 带有缓存的线程池
    * @author xuhong.ding
    * @since 2020/9/7 11:09
    */
    static void cacheThreadPool() throws Exception{
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new SictThreadFactory("sbustation"));
        cachedThreadPool.execute(new ParseEFileThread("sbustation1"));
        cachedThreadPool.execute(new ParseEFileThread("sbustation2"));
        cachedThreadPool.execute(new ParseEFileThread("sbustation3"));
        cachedThreadPool.shutdown();
    }


    /**
     * @author xuhong.ding
     * @since 2020/9/7 11:09
     */
    static void fixedThreadPool() throws Exception{
        System.out.println(LocalDateTime.now());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        System.out.println(LocalDateTime.now());
    }

}
