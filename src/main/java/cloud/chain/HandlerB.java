package cloud.chain;

import cn.hutool.core.date.DateUtil;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2022/5/7 10:37
 */
public class HandlerB extends AbstractHandler {
    public HandlerB(AbstractHandler next) {
        super(next);
    }

    public HandlerB() {
    }

    @Override
    public void handleRequest(String request) {
        System.out.println(DateUtil.now()+"HandlerB handleRequest: " + request);
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
