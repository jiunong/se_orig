package cloud.pipeline;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/9/16 8:56
 */
public interface Valve {

    void invoke(Context context);
    void invokeNext(Context context);
    String getValveName();

}
