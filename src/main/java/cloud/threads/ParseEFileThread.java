package cloud.threads;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/7 11:33
 */
public class ParseEFileThread implements Runnable{


    private String modelName;


    public ParseEFileThread() {
    }

    public ParseEFileThread(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void run() {
        // 打印正在执行的缓存线程信息
        System.out.println(Thread.currentThread().getName() + "正在被执行");
        try {
            System.out.println("处理"+this.modelName);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
