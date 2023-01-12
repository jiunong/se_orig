package cloud.chain;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2022/5/7 10:35
 */
public abstract class AbstractHandler {


    protected AbstractHandler nextHandler;

    public AbstractHandler() {
    }

    public AbstractHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


    public abstract void handleRequest(String request);


}
