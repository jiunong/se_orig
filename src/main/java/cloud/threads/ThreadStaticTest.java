package cloud.threads;

public class ThreadStaticTest {


    public static void main(String[] args) {

        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();
        new Thread(()->new StaticModel().add()).run();


    }
}
