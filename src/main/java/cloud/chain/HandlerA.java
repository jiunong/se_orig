package cloud.chain;

import cn.hutool.core.date.DateUtil;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2022/5/7 10:36
 */
public class HandlerA extends AbstractHandler {
    public HandlerA(AbstractHandler next) {
        super(next);
    }

    public HandlerA() {
    }

    @Override
    public void handleRequest(String request) {
        System.out.println(DateUtil.now()+"HandlerA handleRequest: " + request);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
