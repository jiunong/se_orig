package designPattern;

/**
 * 单例模式
 *
 * @TODO Ensure a class has only one instance, and provide a global point of access to it.（确保某一个类
 * 只有一个实例，而且自行实例化并向整个系统提供这个实例。）
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    /*私有构造方法 保证无法被创建对象*/
    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    private static Singleton INSTANCE2 = null;

    public static Singleton getInstance2() {
        if (INSTANCE2 == null) {
            INSTANCE2 = new Singleton(); //
        }
        return INSTANCE2;
    }

    private static Singleton INSTANCE3 = null;

    public static Singleton getInstance3() {
        synchronized (Singleton.class) {
            if (INSTANCE2 == null) {
                INSTANCE2 = new Singleton(); //
            }
            return INSTANCE2;
        }

    }

}
