package cloud.logs;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;


/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/1/15 14:51
 */
public class LogsHandler {

    private static LogsHandler logsHandler;

    private static final String logPath = System.getProperty("user.dir").concat(FileUtil.isWindows() ? "\\logs\\db.out" : "/logs/db.out");

    private static final File logOutFile;

    static {
        logOutFile = new File(logPath);
        if (!FileUtil.exist(logOutFile)) {
            FileUtil.touch(logOutFile);
        }
    }

    public static LogsHandler instance() {
        if (logsHandler == null) {
            logsHandler = new LogsHandler();
        }
        return logsHandler;
    }


    /**
     * TODO 添加日志
     *
     * @param log 日志
     * @author xuhong.ding
     * @since 2021/1/19 10:04
     */
    public void logs(String log, Object... var2) {
        AtomicReference<String> logNew = new AtomicReference<>(log);

        Arrays.asList(var2).forEach(u -> logNew.set(logNew.get().replaceFirst("\\{}", String.valueOf(u))));
        FileUtil.appendUtf8Lines(ListUtil.toList(DateTime.now().toString()
                .concat(":")
                .concat(logNew.get())), logOutFile);
    }


}
