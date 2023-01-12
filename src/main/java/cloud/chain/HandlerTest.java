package cloud.chain;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2022/5/7 10:38
 */
public class HandlerTest {

    public static void main(String[] args) {
       AbstractHandler handler = new HandlerA(new HandlerB());
       handler.handleRequest("hello");
    }

}
